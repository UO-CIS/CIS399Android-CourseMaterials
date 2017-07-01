package edu.uoregon.bbird.lifecycledemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private int count = 0;
    private TextView countText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Log.d("MainActivity", "In onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
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
        count++;
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
