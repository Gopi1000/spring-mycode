/**
 * 
 */
package com.scube.mycognitiv.utils;

import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author Administrator
 *
 */
public class CommenFuncation {
	private static final Logger LOGGER = Logger
			.getLogger(CommenFuncation.class);
	
	public String convertGMTtodbDate(Date gmtDate)
	{
	    try
	    {
        	    String yyyymmdd = null;
        	    if(gmtDate.equals(null))
        	    {
        		return null;
        	    }
        	    String gmtStringDate = String.valueOf(gmtDate);
        	    String splitArray[] = gmtStringDate.split(" ");
        	    //Wed Feb 01 00:00:00 IST 2012
        	    String dd = splitArray[2];
        	    String mm = getMonthNumberFromMonthString(splitArray[1]);
        	    String yyyy = splitArray[5];
        	    yyyymmdd = yyyy+"-"+mm+"-"+dd;
        	    
        	    return yyyymmdd;
	    }catch (Exception e) {
	    	if(LOGGER.isDebugEnabled()){
				LOGGER.debug(e.getMessage()+e);
			}
	//	e.printStackTrace();
	    }
	    return null;
	}
	public String getMonthNumberFromMonthString(String monthString)
	{
	    String monthNumber = null;
	    if(monthString.equals("Jan"))
	    {
		monthNumber = "01";
	    }else if(monthString.equals("Feb"))
	    {
		monthNumber = "02";
	    }else if(monthString.equals("Mar"))
	    {
		monthNumber = "03";
	    }else if(monthString.equals("Apr"))
	    {
		monthNumber = "04";
	    }else if(monthString.equals("May"))
	    {
		monthNumber = "05";
	    }else if(monthString.equals("Jun"))
	    {
		monthNumber = "06";
	    }else if(monthString.equals("Jul"))
	    {
		monthNumber = "07";
	    }else if(monthString.equals("Aug"))
	    {
		monthNumber = "08";
	    }else if(monthString.equals("Sep"))
	    {
		monthNumber = "09";
	    }else if(monthString.equals("Oct"))
	    {
		monthNumber = "10";
	    }else if(monthString.equals("Nov"))
	    {
		monthNumber = "11";
	    }else if(monthString.equals("Dec"))
	    {
		monthNumber = "12";
	    }
	    return monthNumber;
	}
}
