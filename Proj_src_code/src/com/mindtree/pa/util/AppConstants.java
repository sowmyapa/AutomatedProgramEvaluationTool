package com.mindtree.pa.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Resource bundle manager
 * 
 * @author XXX
 * 
 */
public class AppConstants {

	private static final String BUNDLE_NAME = "com.mindtree.pa.util.pa";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	/**
	 * This method returns the value corresponding to the given key
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
