package webproject.commun;

public class Constants {
	
	/**
	 * Constantes de configuration de la BDD
	 */
	public static final String PROPERTIES_FILE   = "/WEB-INF/config.properties";
	public static final String PROPERTY_URL      = "url";
	public static final String PROPERTY_DRIVER   = "driver";
	public static final String PROPERTY_USERNAME = "username";
	public static final String PROPERTY_PASSWORD = "password";
    public static final String CONF_DAO_FACTORY  = "daofactory";
	
	/**
	 * Constantes de chemins JSP
	 */
	public static final String VIEW_INDEX       = "/WEB-INF/index.jsp";
	public static final String VIEW_INSCRIPTION = "/WEB-INF/inscription.jsp";
	public static final String VIEW_CONNECTION  = "/WEB-INF/connection.jsp";
	public static final String VIEW_HOME        = "/restricted/home.jsp";
	
	/**
	 * Constantes des addresses URL
	 */
	public static final String ADDR_INDEX       = "/";
	public static final String ADDR_INSCRIPTION = "/inscription.html";
	public static final String ADDR_CONNECTION  = "/connection.html";
	public static final String ADDR_HOME        = "/home.html";
	
	/**
	 * Constantes des attributs
	 */
	public static final String ATT_USER         = "user";
	public static final String ATT_FORM         = "form";
	public static final String ATT_CONNECTED    = "connected";
	public static final String ATT_SESSION_USER = "userSession";
	
	/**
	 * Constantes des champs de formulaire
	 */
	public static final String EMAIL_FIELD        = "email";
	public static final String PASSWORD_FIELD     = "password";
	public static final String CONFIRMATION_FIELD = "confirmation";
	public static final String USERNAME_FIELD     = "username";
}
