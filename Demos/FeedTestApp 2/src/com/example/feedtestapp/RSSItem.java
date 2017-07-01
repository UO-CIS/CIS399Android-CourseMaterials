package com.example.feedtestapp;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class RSSItem {
    
    private String date = null;
    private String morningLow = null;
    private String daytimeHigh = null;
    
    private SimpleDateFormat dateOutFormat = 
        new SimpleDateFormat("EEEE h:mm a (MMM d)");
    
    private SimpleDateFormat dateInFormat = 
        new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
    
    public void setForecastDate(String date){
        this.date= date;
    }
    
    public void setMorningLow(String low){
    	this.morningLow = low;
    }
    
    public void setDaytimeHigh(String high){
    	this.daytimeHigh = high;
    }
    
    public String getForecastDate() {
        return date;
    }
    
    public String getMorningLow(){
    	return morningLow;
    }
    
    public String getDaytimeHigh(){
    	return daytimeHigh;
    }
}