package com.example.feedtestapp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WeatherSQLiteHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "weather.sqlite";
	private static final int DB_VERSION = 1;
	private Context context;
	
	public WeatherSQLiteHelper(Context c) {
		super(c, DB_NAME, null, DB_VERSION);
		context = c;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		
		database.execSQL("CREATE TABLE Forecast"
				+ "( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "Date TEXT,"
				+ "Zip INTEGER,"
				+ "City TEXT,"
				+ "Description TEXT,"
				+ "LowTemp INTEGER,"
				+ "HighTemp INTEGER"
				+ ")" );
				
		// We aren't going to copy the database file for the lab assignment
		/*try {
			copyDatabase();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
	}
/*	
	private void copyDatabase() throws IOException {
		InputStream inStream = CONTEXT.getAssets().open(DB_NAME);
		String outFileName = "/data/data/com.example.feedtestapp/databases" + DB_NAME;
		OutputStream outStream = new FileOutputStream(outFileName);
		
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inStream.read(buffer)) > 0){
			outStream.write(buffer, 0, length);
		}
		
		outStream.flush();
		outStream.close();
		inStream.close();
	}
*/
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldBase, int newBase) {
			
	}

}
