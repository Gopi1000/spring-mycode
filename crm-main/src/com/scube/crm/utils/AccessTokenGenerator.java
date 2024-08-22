package com.scube.crm.utils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;

public class AccessTokenGenerator {

	private static final Logger LOGGER = Logger
			.getLogger(AccessTokenGenerator.class);

	public static String getAccessToken() throws PayPalRESTException,
			FileNotFoundException {
		String accessToken = null;
		// ###AccessToken
		// Retrieve the access token from
		// OAuthTokenCredential by passing in
		// ClientID and ClientSecret
		Map<String, String> configurationMap = new HashMap<String, String>();
		configurationMap
				.put("oauth.EndPoint", MyjobkartResourceBundle
						.getValue("paypal.service.endPoint.url"));
		configurationMap
				.put("service.EndPoint", MyjobkartResourceBundle
						.getValue("paypal.service.endPoint.url"));
		configurationMap.put("mode",
				MyjobkartResourceBundle.getValue("paypal.service.mode"));
		if (accessToken == null) {

			// ClientID and ClientSecret retrieved from configuration
			String clientID = MyjobkartResourceBundle
					.getValue("paypal.clientID");
			String clientSecret = MyjobkartResourceBundle
					.getValue("paypal.clientSecret");
			OAuthTokenCredential merchantTokenCredential = new OAuthTokenCredential(
					clientID, clientSecret, configurationMap);
			accessToken = merchantTokenCredential.getAccessToken();
		}
		return accessToken;
	}
}
