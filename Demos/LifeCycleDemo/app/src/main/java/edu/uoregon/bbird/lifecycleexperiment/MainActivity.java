package edu.uoregon.bbird.lifecycleexperiment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private int count = 0;
    private TextView countText;
    private SharedPreferences savedValues;
    private int countIncrement = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //savedValues = getSharedPreferences("clickCounterPrefs", MODE_PRIVATE);
        savedValues = PreferenceManager.getDefaultSharedPreferences(this);
        countText = (TextView)findViewById(R.id.countText);
        Log.d("MainActivity", "In onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "In onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        count = savedValues.getInt("count", 0);
        countText.setText(Integer.toString(count));
        countIncrement = Integer.parseInt(savedValues.getString("pref_increment_amount", "1"));
        //countIncrement = savedValues.getInt("pref_increment_amount", 1);
        Log.d("MainActivity", "In onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putInt("count", count);
        editor.commit();
        Log.d("MainActivity", "In onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "In onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "In onDestroy");
    }

    public void countButtonOnClick(View v) {
        count += countIncrement;
        countText.setText(Integer.toString(count));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
            startActivity( new Intent( getApplicationContext(), SettingsActivity.class));
            return true;
        }
        else if (id== R.id.action_about) {
            Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
