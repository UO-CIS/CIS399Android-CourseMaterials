package com.example.feedtestapp;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private ListView itemsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize database
        WeatherSQLiteHelper helper = new WeatherSQLiteHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        
        // Put test data in database- just for testing!
        ContentValues cv = new ContentValues();
        cv.put("Date", "7/12/13");
        cv.put("Zip", 97405);
        cv.put("City", "Eugene");
        cv.put("Description", "Sunny");
        cv.put("LowTemp", 59);
        cv.put("HighTemp", 90);
        db.insert("Forecast", null, cv);
        db.close();
        
        //Get data from SQLite database - just testing!
             db = helper.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM Forecast WHERE Zip = ? ORDER BY Date ASC" ,
        		new String[]{"97405"});
        
        // debugging
        // int count = cursor.getCount();
        
        //create the adapter
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
        		this, 
        		R.layout.listview_items, 
        		cursor, 
        		new String[]{"Date", "LowTemp", "HighTemp"}, 
        		new int[]{R.id.dateView, R.id.highView, R.id.lowView},
        		0);
        
        itemsListView = (ListView) findViewById(R.id.listView1);
        itemsListView.setAdapter(adapter);
    }
}



//read the input file and give our feed object the file data*
/*    io = new FileIO(getApplicationContext());
    feed = io.readFile();
    
//  
    
    ArrayList<HashMap<String, String>> data = 
            new ArrayList<HashMap<String, String>>();
    
    for(RSSItem item: feed.getAllItems()){
    	HashMap<String, String> map = new HashMap<String, String>();
        map.put("date", item.getForecastDate());
        map.put("low", item.getMorningLow());
        map.put("high", item.getDaytimeHigh());
        data.add(map);
    }
    
 // create and set the adapter
    SimpleAdapter adapter = 
        new SimpleAdapter(this, 
        		data, 
        		R.layout.listview_items, 
        		new String[]{"date", "low", "high"}, 
        		new int[]{R.id.dateView, R.id.highView, R.id.lowView});
    */