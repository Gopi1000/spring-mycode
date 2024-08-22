package com.scube.techboot.utils;

import java.io.FileNotFoundException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class TechbootResourceBundle {

	private static final String BUNDLE_NAME = "sucessMessages";
	private static final String BUNDLE_NAME_SEO = "seo";
	private static final String BUNDLE_NAME_TITLE = "seo-title";
	private static final String BUNDLE_NAME_META = "seo-meta";
	
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);
	private static final   ResourceBundle RESOURCE_BUNDLE_SEO=ResourceBundle.getBundle(BUNDLE_NAME_SEO);
	private static final ResourceBundle RESOURCE_BUNDLE_META = ResourceBundle.getBundle(BUNDLE_NAME_META);
	private static final ResourceBundle RESOURCE_BUNDLE_TITLE = ResourceBundle.getBundle(BUNDLE_NAME_TITLE);
	
	public static String getValue(String key) throws FileNotFoundException {
		try {
			String value=RESOURCE_BUNDLE.getString(key);
			System.out.println(value);
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException mex) {
			mex.printStackTrace();
			return "";
		} catch (Exception ex) {
			return "NOT FOUND";
		}
	}
	
private static final String DROPDOWN_NAME = "dropdown";
	
	private static final ResourceBundle RESOURCE_BUNDLE1 = ResourceBundle
			.getBundle(DROPDOWN_NAME);
	
	
	public static String getDropdown(String key) throws FileNotFoundException {
		try {
			String value=RESOURCE_BUNDLE1.getString(key);
			System.out.println(value);
			return RESOURCE_BUNDLE1.getString(key);
		} catch (MissingResourceException mex) {
			mex.printStackTrace();
			return "";
		} catch (Exception ex) {
			return "NOT FOUND";
		}
	}
	
	public static String getSEOValue(String key) throws FileNotFoundException{
		try{
		     if( RESOURCE_BUNDLE_SEO.containsKey(key)){
			 return RESOURCE_BUNDLE_SEO.getString(key);
			 }
		     return null;
	         }catch (MissingResourceException mex) {
			 mex.printStackTrace();
			 return "";
		     } catch (Exception ex) {
			 return "NOT FOUND";
	       }
	}


	public static String getTitleValue(String key) throws FileNotFoundException {
		try {
			return RESOURCE_BUNDLE_TITLE.getString(key);

		} catch (MissingResourceException mex) {
			mex.printStackTrace();
			return "";
		} catch (Exception ex) {
			return "NOT FOUND";
		}
	}

	public static String getMetaValue(String key) throws FileNotFoundException {
		try {
			return RESOURCE_BUNDLE_META.getString(key);

		} catch (MissingResourceException mex) {
			mex.printStackTrace();
			return "";
		} catch (Exception ex) {
			return "NOT FOUND";
		}
	}

}
