package webproject.commun;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.InetAddressValidator;

import webproject.main.Index;

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
		//System.out.println(value);
		if ( value == null || value.trim().length() == 0 ) {
			return null;
		} else {
			return value;
		}
	}
	
	
	/**
	 * Methods permits to access parameter selected in the "select" form
	 * which are not accessible by default
	 * @param request request to get the list from
	 * @param fieldName "select" field name
	 * @return the list of the parameter selected by the user in the "select" form
	 */
	public static LinkedList<String> getListFieldValue(HttpServletRequest request, String fieldName)
	{
		String[] selectList = request.getParameterValues(fieldName);
		LinkedList<String> multiparameters = new LinkedList<String>();
		
		if (selectList == null || selectList.length == 0)
		{
			return null;
		}
		else 
		{
			for (int i = 0; i < selectList.length; i++) 
			{
				multiparameters.add(selectList[i]);
			}
			
			return multiparameters;
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
		Index.path = request.getSession().getServletContext().getRealPath("");
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
			requestLocale = (Locale) supportedLanguages.get("EN");
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
	
	public static int hostnameValidation(String hostname) throws Exception {
		DomainValidator dv = DomainValidator.getInstance();
		if(dv.isValid(hostname)){
			return 0;
		}

		InetAddressValidator iav = InetAddressValidator.getInstance();
		if(iav.isValid(hostname)){
			return 0;
		}

		throw new Exception("Invalid hostname given");
	}
	
	public static int portValidation(String port) throws Exception
	{
		int newPort;
		
		try{
			newPort = Integer.parseInt(port);
		} catch (Exception e) {
			throw new Exception("Port given is not an integer");
		}
		
		if (newPort < 0 && newPort > 65535)
		{
			throw new Exception("Port given must be between 0 and 65535");
		}
		
		return 0;
	}
	
	public static String scanValidation(String scan) throws Exception
	{
		
		switch (scan) {
		case "intense":
			return "-T4 -A -v";
		case "intense_udp":
			return "-sS -sU T4 -A -v";
		case "intense_tcp":
			return "-p 1-65535 -T4 -A -v";
		case "intense_no_ping":
			return "-T4 -A -v -Pn";
		case "rapide":
			return "-T4 -F";
		case "rapide_plus":
			return "-sV -T4 -O -F --version-light";
		case "ping":
			return "-sn";
		case "traceroute":
			return "-sn --traceroute";
		case "ordinaire":
			return "-v";
		case "complet":
			return "S80,443 -PA3389 -PU4-sS -sU -T4 -A -v -PE -PP -P0125 -PY -g 53 --script \"default or (discovery and safe)\"";
		default:
			throw new Exception("Incorrect scan type given");
		}
	}
}
