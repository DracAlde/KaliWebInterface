package webproject.commun;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import webproject.commun.Constants;

/**
 * That class is defined to permits loading of language terms from XML files
 * @author arn0f
 *
 */
public class Language {

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
	public String getLanguageValue(String valueAttribute)
	{
		Constants constantLang = new Constants();
		String langPath = Constants.LANG_PATH_FR;
		String longPath = Constants.MY_PROJECT_PATH; /* That line must be replaced in the future
		* by an automatic means to get the full project path
		*/
		String result = "Constants -> MY_PROJETC_PATH";


		if ("EN".equals(constantLang.getLang()))
		{
			langPath = Constants.LANG_PATH_EN;
		}
		else
		{
			langPath = Constants.LANG_PATH_FR;
		}

		try {
			
			longPath += langPath;
						
			File inputFile = new File(longPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();

			Document document = dBuilder.parse(inputFile);
			document.getDocumentElement().normalize();

			XPath xPath =  XPathFactory.newInstance().newXPath();

			NodeList nodeList = (NodeList) xPath.compile(valueAttribute).evaluate(document, XPathConstants.NODESET);
			result = nodeList.item(0).getTextContent();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return result;
	}
}