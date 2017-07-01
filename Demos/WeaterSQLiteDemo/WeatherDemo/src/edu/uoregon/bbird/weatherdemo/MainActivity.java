package edu.uoregon.bbird.weatherdemo;
// references: 
// www.drdobbs.com/database/using-sqlite-on-android/232900584?pgno=1
// http://www.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
// http://www.vogella.com/tutorials/AndroidSQLite/article.html
// http://stackoverflow.com/questions/5457699/cursor-adapter-and-sqlite-example
// http://developer.android.com/reference/android/widget/SimpleCursorAdapter.html

import java.util.ArrayList;
import java.util.HashMap;

import edu.uoregon.bbird.weatherdemo.R;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity 
				implements OnItemClickListener {

	private FileIO io;
	private WeatherItems weatherItems;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Create a Cursor from the database
        
        WeatherSQLiteHelper helper = new WeatherSQLiteHelper(this);

        SQLiteDatabase weatherDb = helper.getReadableDatabase();

        String query = "SELECT * FROM clients WHERE Zip = ? ORDER BY Date ASC";

        // rawQuery must not include a trailing ';'
        String[] variables = new String[]{"97405"};
        Cursor cursor = weatherDb.rawQuery(query, variables); 

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this, 
                R.layout.listview_items, 
                cursor, 
                new String[]{"Date", "Description"}, 
                new int[]{R.id.iconImageView,
          			  R.id.dateTextView, 
          			  }, 
          		0 );	// no flags
        
       /* 
        // Get the data from the XML file
        io = new FileIO(getApplicationContext());
        weatherItems = io.readFile();
           
        
        // Set up data and an adapter for use by the ListView
        ArrayList<HashMap<String, String>> data = new
        		ArrayList<HashMap<String, String>>();
        
        for (WeatherItem item : weatherItems)
        {
        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put("date", item.getForecastDateFormatted());
        	// map.put("lowTemp", getString(R.string.low) + item.getLowTemp());
        	// map.put("highTemp", getString(R.string.high) + item.getHighTemp());
        	map.put("imageName", 
        			Integer.toString(getResources().getIdentifier(
        					item.getDescription().toLowerCase().replaceAll("\\s+",""), "drawable", getPackageName() 
        					)));
        	data.add(map);
        }
        
        SimpleAdapter adapter = new SimpleAdapter(this,
        	data,
        	R.layout.listview_items,
        	new String[]{"date", "imageName"},
        	new int[]{R.id.iconImageView,
        			  R.id.dateTextView, 
        			  }
        );
*/        
        ListView itemsListView = (ListView)findViewById(R.id.listView1);
        itemsListView.setAdapter(adapter);
        itemsListView.setOnItemClickListener(this);
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		WeatherItem item = weatherItems.get(position);
		Toast.makeText(this, 
				"Low: " + item.getLowTemp() + "\r\n" +
				"High:" + item.getHighTemp(),
				Toast.LENGTH_LONG).show();
	}    
}
