package webproject.commun;

import javax.servlet.http.HttpServletRequest;

/**
 * Tools witch can be used in other classes
 * Code repetition is the devil
 * @author kilian
 *
 */

public class Tools {

	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
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
}
