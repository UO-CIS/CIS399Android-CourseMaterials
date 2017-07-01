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
			
			final String TARGET_NAMESPACE = "http://opendap.co-ops.nos.noaa.gov/axis/webservices/highlowtidepred/wsdl";
			final String OPERATION =        "getHLPredAndMetadata";
			final String URL =              "http://opendap.co-ops.nos.noaa.gov/axis/services/highlowtidepred"; //endpoint url
			final String SOAP_ACTION =      URL + "/" + OPERATION;
			
			// Create a SOAP request object and put it in an envelope
			SoapObject request = new SoapObject(TARGET_NAMESPACE, OPERATION);
			request.addProperty("stationId", "9432780");
			request.addProperty("beginDate", "20140715 00:00");
			request.addProperty("endDate", "20140717 23:59");
			request.addProperty("datum", "MLLW");
			request.addProperty("unit", "0");
			request.addProperty("timeZone", "0");
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.implicitTypes = true;
			envelope.dotNet = false;
			envelope.setOutputSoapObject(request);
			
			// Send the request 
			HttpTransportSE ht = new HttpTransportSE(Proxy.NO_PROXY, URL, 60000);
			// ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
			ht.debug = true;
			
			try {
				ht.call(SOAP_ACTION, envelope);  // url is the SOAPAction
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
			String test = ht.requestDump;
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
