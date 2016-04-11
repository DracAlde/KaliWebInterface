package webproject.commun;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import webproject.commun.Constants;

public class Language {

	public String getLanguageValue(String valueAttribute)
	{
		System.out.println("Entering in Language");
		Constants constantLang = new Constants();
		String langPath = Constants.LANG_PATH_FR;
		String result = null;


		if ("EN".equals(constantLang.getLang()))
		{
			langPath = Constants.LANG_PATH_EN;
		}
		else
		{
			langPath = Constants.LANG_PATH_FR;
		}

		try {
			//InputStream inputFile = getClass().getResourceAsStream(langPath);
			String res = getClass().getClassLoader().getResource(".").toString();
			System.out.println(res);
			String basePath = new File(res).getAbsolutePath();
			System.out.println(basePath);
			File inputFile = new File(langPath);
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