package webproject.commun;

/**
 * Constants used in other class of the project
 * @author kilian
 *
 */

public class Constants {
	
	/**
	 * JSP path constants
	 */
	public static final String VIEW_HOME         	= "/WEB-INF/home.jsp";
	public static final String VIEW_NIKTO        	= "/WEB-INF/nikto.jsp";
	public static final String VIEW_ZENMAP       	= "/WEB-INF/zenmap.jsp";
	public static final String VIEW_THEHARVESTER 	= "/WEB-INF/the_harvester.jsp";
	public static final String VIEW_TLS_SLED     	= "/WEB-INF/tls_sled.jsp";
	public static final String VIEW_HYDRA        	= "/WEB-INF/hydra.jsp";
	public static final String VIEW_DNS_RECON    	= "/WEB-INF/dns_recon.jsp";
	
	/**
	 * Language constants
	 */
	public String LANGUAGE							= "FR";
	public static final String LANG_PATH_FR 		= "/lang/fr.xml";
	public static final String LANG_PATH_EN			= "/lang/en.xml";
	
	/**
	 * URL address constants
	 */
	public static final String ADDR_HOME         	= "/";
	public static final String ADDR_NIKTO        	= "/nikto.html";
	public static final String ADDR_ZENMAP       	= "/zenmap.html";
	public static final String ADDR_THEHARVESTER 	= "/theharvester.html";
	public static final String ADDR_TLS_SLED     	= "/tlssled.html";
	public static final String ADDR_HYDRA        	= "/hydra.html";
	public static final String ADDR_DNS_RECON    	= "/dnsrecon.html";
	
	/**
	 * Parameters name constants
	 */
	public static final String ATT_FORM            = "form";
	public static final String ATT_SESSION_HISTORY = "history";
	
	/**
	 * Session parameters Constants
	 */
	public static final String SESS_LANG            = "language";
	
	/**
	 * Form field name constants
	 */
	public static final String FIELD_HOSTNAME 		= "hostname";
	public static final String FIELD_PORT 			= "port";
	public static final String FIELD_SCAN_TYPE		= "scan_type";
	
	/**
	 * SSH session constants
	 */
	public static final String SSH_USERNAME 		= "username";
	public static final String SSH_HOST     		= "127.0.0.1";
	public static final String SSH_PASSWORD 		= "password";
	
	/**
	 * Tool name used in Languages file ("*.xml")
	 */
	public static final String TOOL_NIKTO			= "nikto";
	public static final String TOOL_ZENMAP			= "zenmap";
	public static final String TOOL_THE_HARVESTER	= "the-harvester";
	public static final String TOOL_TLS_SLED		= "tls-sled";
	public static final String TOOL_HYDRA			= "hydra";
	public static final String TOOL_DNS_RECON		= "dns-recon";
}
