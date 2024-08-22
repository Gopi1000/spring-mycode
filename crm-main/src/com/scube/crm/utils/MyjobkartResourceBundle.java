package com.scube.crm.utils;

import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MyjobkartResourceBundle {

	private static final String BUNDLE_NAME = "messages";
	private static final String BUNDLE_NAME_TITLE = "seo-title";
	private static final String BUNDLE_NAME_META = "seo-meta";
	private static final String BUNDLE_NAME_SEO = "seo";
	private static final String BUNDLE_NAME_SITEMAP = "sitemap";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);
	private static final ResourceBundle RESOURCE_BUNDLE_META = ResourceBundle
			.getBundle(BUNDLE_NAME_META);
	private static final ResourceBundle RESOURCE_BUNDLE_TITLE = ResourceBundle
			.getBundle(BUNDLE_NAME_TITLE);
	private static final ResourceBundle RESOURCE_BUNDLE_SEO = ResourceBundle
			.getBundle(BUNDLE_NAME_SEO);
	private static final ResourceBundle RESOURCE_BUNDLE_SITEMAP = ResourceBundle
			.getBundle(BUNDLE_NAME_SITEMAP);

	public static String getValue(String key) throws FileNotFoundException {
		try {
			return RESOURCE_BUNDLE.getString(key);

		} catch (MissingResourceException mex) {
			mex.printStackTrace();
			return "";
		} catch (Exception ex) {
			return "NOT FOUND";
		}
	}

	public static String getSEOValue(String key) throws FileNotFoundException {
		try {
			if (RESOURCE_BUNDLE_SEO.containsKey(key)) {
				return RESOURCE_BUNDLE_SEO.getString(key);
			}
			return null;

		} catch (MissingResourceException mex) {
			mex.printStackTrace();
			return "";
		} catch (Exception ex) {
			return "NOT FOUND";
		}
	}
	
	public static String getSiteMap(String key) throws FileNotFoundException {
		try {
			if (RESOURCE_BUNDLE_SITEMAP.containsKey(key)) {
				return RESOURCE_BUNDLE_SITEMAP.getString(key);
			}
			return null;

		} catch (MissingResourceException mex) {
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
