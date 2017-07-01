package com.example.soapclienttest;

import java.io.IOException;
import java.net.Proxy;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	String weatherXml;
	TextView testTextView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        testTextView = (TextView)findViewById(R.id.testTextView);
        
        new WeatherTask().execute();
    }
    
    private class WeatherTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			
			// Create a SOAP request object and put it in an envelope
			SoapObject request = new SoapObject("http://ws.cdyne.com/WeatherWS/", "GetCityForecastByZIP");
			request.addProperty("ZIP", "97405");
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			
			// Send the request (call the SOAP method)
			HttpTransportSE ht = new HttpTransportSE(Proxy.NO_PROXY,
					"http://wsf.cdyne.com/WeatherWS/Weather.asmx", 60000);
			ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
			ht.debug = true;
			
			try {
				ht.call("http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP", envelope);
			} catch (HttpResponseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Get the response from the SOAP service
			/*
			// This works, but doesn't give us the raw XML
			SoapObject response = null;
			try {
				response = (SoapObject)envelope.getResponse();
			} catch (SoapFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			weatherXml = response.getPropertyAsString("GetCityForecastByZIPResult");
			*/
			
			// This gives us the raw XML
			weatherXml = ht.responseDump;
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			testTextView.setText(weatherXml);
			super.onPostExecute(result);
		}
    	
    }
}
