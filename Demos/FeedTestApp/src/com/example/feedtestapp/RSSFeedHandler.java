package com.example.feedtestapp;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class RSSFeedHandler extends DefaultHandler {
    private RSSFeed feed;
    private RSSItem item;
    
    //booleans to find out desired elements
    private boolean isForecast= false;
    private boolean isTemp = false;
    private boolean isDate = false;
    private boolean isHigh = false;
    private boolean isLow = false;

    
    public RSSFeed getFeed() {
        return feed;
    }
        
    public void startDocument() throws SAXException {
        feed = new RSSFeed();
        item = new RSSItem();
    }
    
    @Override
    public void startElement(String namespaceURI, String localName, 
            String qName, Attributes atts) throws SAXException {
        
        if (qName.equals("Forecast")) {
            item = new RSSItem();
            return;
        }
        else if (qName.equals("Date")){
        	isDate = true;
        }
        else if (qName.equals("Temperatures")){
        	isTemp = true;
        }
        else if (qName.equals("MorningLow")){
        	isLow = true;
        }
        else if (qName.equals("DaytimeHigh")){
        	isHigh = true;
        }
    }
    
    public void endElement(String namespaceURI, String localName, 
            String qName) throws SAXException
    {
        if (qName.equals("Forecast")) {
            feed.addItem(item);
            return;
        }
    }
     
    public void characters(char ch[], int start, int length){
        String s = new String(ch, start, length);
        if (isDate) {
            item.setForecastDate(s);
            isDate = false;
        }
        else if (isTemp){
        	isTemp = false;
        }
        
        else if (isLow){
        	item.setMorningLow(s);
        	isLow = false;
        }
        
        else if (isHigh){
        	item.setDaytimeHigh(s);
        	isHigh = false;
        }
        
    }
}