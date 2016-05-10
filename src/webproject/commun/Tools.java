package webproject.commun;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import webproject.main.Home;

/**
 * Tools witch can be used in other classes
 * Code repetition is the devil
 * @author kilian
 *
 */

public class Tools {

	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	private static final Pattern PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	private static Properties supportedLanguages;
	private static Locale requestLocale;

	/**
	 * Convert byte[] in String hexadecimal including only 0123456789ABCDEF
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for ( int j = 0; j < bytes.length; j++ ) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	/**
	 * Return null if the field is empty or his value
	 */
	public static String getFieldValue(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		if ( value == null || value.trim().length() == 0 ) {
			return null;
		} else {
			return value;
		}
	}

	public static boolean validateIP(String ip) {
		return PATTERN.matcher(ip).matches();
	}

	/**
	 * initiate the path to project
	 * used to find language files
	 * @param request
	 */
	public static void initiatePath(HttpServletRequest request){
		Home.path = request.getSession().getServletContext().getRealPath("");
	}

	/**
	 * Detect request's language
	 * @param request
	 * @return
	 */
	public static String detectLocale(HttpServletRequest request) {
		if(supportedLanguages == null){
			supportedLanguages = new Properties();
			supportedLanguages.put("EN", Locale.US);
			supportedLanguages.put("FR", Locale.FRENCH);
			//Default
			requestLocale = (Locale) supportedLanguages.get("FR");
		}

		Enumeration<Locale> locales = request.getLocales();
		while (locales.hasMoreElements()) {
			Locale locale = (Locale) locales.nextElement();
			if (supportedLanguages.contains(locale)) {
				requestLocale = locale;
				break;
			}
		}

		return requestLocale.getLanguage();
	}
}
