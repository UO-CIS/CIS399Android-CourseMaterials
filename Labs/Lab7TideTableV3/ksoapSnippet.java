
	final String NAMESPACE   = "http://opendap.co-ops.nos.noaa.gov/axis/webservices/highlowtidepred/wsdl";
	//final String METHOD_NAME = "getHLPredAndMetadata";
	final String METHOD_NAME = "getHighLowTidePredictions";
	final String URL         = "http://opendap.co-ops.nos.noaa.gov/axis/services/highlowtidepred";
    // The soap method endpoint - destination for posting a soap request
	final String SOAP_ACTION = URL + "/" + METHOD_NAME;
	

    private class myTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			//SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            //add the properties required to our request
			request.addProperty("stationId", "9432780");
			request.addProperty("beginDate", "20150715 00:00");
			request.addProperty("endDate", "20150717 23:59");
			request.addProperty("datum", "MLLW");
			request.addProperty("unit", "0");
			request.addProperty("timeZone", "0");
			
			//create our envelope to get the data
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.implicitTypes = true;
			envelope.setOutputSoapObject(request);

			
			//setup the http transport object
			HttpTransportSE transport = new HttpTransportSE(Proxy.NO_PROXY, URL, 60000);
			
			try {
				transport.call(SOAP_ACTION, envelope);
			} catch (org.ksoap2.transport.HttpResponseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (XmlPullParserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// Additional code ommitted
			
}