package com.example.ksoaptest;
// by Brian Bird, 7/15/14

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView resultTextView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        
        new WeatherTask().execute("");
    }
    
    /*** AsyncTask for reading the CDYNE weather service ***/
    private class WeatherTask extends AsyncTask<String, Void, String> {
    	WeatherServiceReader soapClient = new WeatherServiceReader();
        final String TARGET_NAMESPACE = "http://ws.cdyne.com/WeatherWS/";
   	
		@Override
		protected String doInBackground(String... params) {
			soapClient.sendSoapRequest(TARGET_NAMESPACE);
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			resultTextView.setText(soapClient.getResponse());
			super.onPostExecute(result);
		}
    	
    }
}
