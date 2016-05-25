package webproject.commun;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import webproject.commun.Constants;
import webproject.main.Index;

/**
 * That class is defined to permits loading of language terms from XML files
 * @author arn0f
 *
 */
public class Language {

	private String currentToolLanguage = null;
	private String language = "";
	
	public Language(HttpServletRequest request){
		language = Tools.detectLocale(request);
	}
	
	
	/**
	 * Permits to replace the "$tool" value in a defined path by the tool name
	 * use in the view to translate
	 * @param pathToolized		node path containing the value "$tool" in
	 * @return					correct path associated to the tool
	 */
	private String getPathAssociatedTool(String pathToolized)
	{
		if (currentToolLanguage == null)
		{
			System.out.println("ERROR : Language: currentToolLanguage == null");
			return "/site/erreur";
		}
		else
		{
			pathToolized = pathToolized.replace("$tool", this.currentToolLanguage);
			return pathToolized;
		}
	}
	
	/**
	 * That method permits to determine the language path. Its means the xml
	 * file containing the language description. For now, there is only two file:
	 * "fr.xml" and "en.xml". In the future, if required, newest language descriptions
	 * could be added
	 * @return		The <b>absolute</b> path associated to the language selected 
	 * by the user
	 */
	private String determineLanguagePath(String folder)
	{
		String langPath = Constants.LANG_PATH_FR;
		String longPath = Index.path;

		longPath += "/lang/" + folder;

		if ("en".equals(language))
		{
			langPath = Constants.LANG_PATH_EN;
		}
		else
		{
			langPath = Constants.LANG_PATH_FR;
		}

		longPath += langPath;
		
		return longPath;
	}
	
	
	/**
	 * Method permitting to get language term from several configs file
	 * The term associated is searched from the appropriate config file
	 * in function of the attribute given to the method.
	 * 
	 * You need to provide the path to the node and the method return the 
	 * value associated to that path: e.g.
	 * <pre>
	 * {@code
	 * <test>
	 * 	<node_to_value> value associated </node_to_value>
	 * </test>
	 * }
	 * </pre>
	 * If you want to access "value associated" you need to provide following 
	 * path : "/test/node_to_value"
	 * @param valueAttribute		the value needed is the path to the node
	 * @return						the value of the node attribute
	 */
	public String getLanguageValue(String folder, String valueAttribute)
	{
		String result = "Constants -> MY_PROJETC_PATH";
		
		if (folder.contains("$tool"))
		{	
			if(currentToolLanguage != null){
				folder = currentToolLanguage;
			}else{
				result = "ERROR: no tool defined";
				System.err.println(result);
				return result;
			}
		}
		
		String longPath = determineLanguagePath(folder);
		
		if(valueAttribute.contains("$tool")){
			valueAttribute = getPathAssociatedTool(valueAttribute);
		}

		try {
						
			File inputFile = new File(longPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();

			Document document = dBuilder.parse(inputFile);
			document.getDocumentElement().normalize();

			XPath xPath =  XPathFactory.newInstance().newXPath();

			NodeList nodeList = (NodeList) xPath.compile(valueAttribute).evaluate(document, XPathConstants.NODESET);
			
			if(nodeList.item(0) == null){
				System.err.println("ERROR: " + valueAttribute + " unfound in " + inputFile.getName());
			}else{
				result = nodeList.item(0).getTextContent();
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}

		return result;
	}
	
	
	public String getCurrentToolLanguage() {
		return currentToolLanguage;
	}


	/**
	 * Set the value of the current tool path
	 * @param currentTool		the current tool name (e.g. "hydra")
	 */
	public void setCurrentTool(String currentTool)
	{
		this.currentToolLanguage = currentTool;
	}


	public void switchLanguage() {
		
		if(language == null) return;
		
		if(language.equals("en")){
			language = "fr";
		}else if(language.equals("fr")){
			language = "en";
		}
		
	}


	public String getLanguage() {
		return language;
	}
}