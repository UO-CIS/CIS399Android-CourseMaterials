package com.example.ksoaptest;

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

import android.os.AsyncTask;
// by Brian Bird, 7/15/14

public class WeatherServiceReader {
	
	SoapSerializationEnvelope envelope;

	// Send a request to the SOAP weather service
	// target is the target namespace of the service
	public void sendSoapRequest(String target) {
        // Create a SOAP request object
        String methodname = "GetCityForecastByZIP";
        SoapObject request = new SoapObject(target, methodname);
        request.addProperty("ZIP", "97405");

        // Create a SOAP envelope
        envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        envelope.dotNet = false;
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false);
        envelope.setOutputSoapObject(request);

        // Set up the HTTP transport object
        HttpTransportSE ht = new HttpTransportSE(Proxy.NO_PROXY, "http://wsf.cdyne.com/WeatherWS/Weather.asmx" , 60000);
        ht.debug = true;
        ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");

        // Send the request
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
		return;
	}
	
	public String getResponse() {
        SoapPrimitive response = null;
        // Get the results
        try {
			response = (SoapPrimitive)envelope.getResponse();
		} catch (SoapFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.toString();
	}


}
