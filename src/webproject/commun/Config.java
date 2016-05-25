package webproject.commun;

import java.io.InputStream;
import java.util.Properties;

import webproject.main.Index;

/**
 * Class to read values from config.properties
 * @author kilian
 *
 */

public class Config{
	
	/**
	 * The method getProperties return the value of the given parameter
	 * (Exemple: in config.properties we can read 'user=tot',
	 * name is 'user', it will return 'toto')
	 * The config.properties file must  be in /webproject/shell/
	 * @param name
	 * @return
	 */
	
	public static String getProperties(String name){
		String value = null;
		Properties properties = new Properties();
		String path = Index.path+"config/config.properties";
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream("/webproject/shell/config.properties");

        if (fichierProperties == null) {
            System.err.println( "Le fichier properties config.properties est introuvable." );
        }
		
        try{
        	properties.load(fichierProperties);
            value = properties.getProperty(name);
        }catch(Exception e){
        	System.err.println("ERROR: Failed to read "+name+" in config.properties at "+ path);
        	e.printStackTrace();
        }
		
		return value;
	}
	
}