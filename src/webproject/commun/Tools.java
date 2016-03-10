package webproject.commun;

import javax.servlet.http.HttpServletRequest;

/**
 * Divers outils qui pourront être utilisés dans les autres classes
 * Parce que la répétition du code, c'est mal.
 * @author kilian
 *
 */

public class Tools {

	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	/**
	 * Converti byte[] en String hexadécimal incluant uniquement les caractères
	 * 0123456789ABCDEF
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
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
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
