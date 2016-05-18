package webproject.form;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.tools.Tool;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import webproject.commun.Command;
import webproject.commun.Constants;
import webproject.commun.ToolName;
import webproject.commun.Tools;

public class TheHarvesterForm {

	private String 				result = "";
	private Map<String, String> errors = new HashMap<String, String>();
	
	public TheHarvesterForm(){}
	
	public String getResult() 
	{
		return result;
	}

	public Map<String, String> getErrors() 
	{
		return errors;
	}
	
	public Command validateTheHarvester(HttpServletRequest request)
	{
		Command command = new Command(ToolName.TheHarvester);
		String reverseDNS = "", verifyHostname = "", TLDDiscovery = "", shodan = "";
		String startResult = "", DNSToUse = "", printLimit = "", sourceToUse = "";
		String domain = Tools.getFieldValue(request, Constants.FIELD_DOMAIN);
		LinkedList<String> informationSource = Tools.getListFieldValue(request, Constants.FIELD_SOURCE_INFO);
		boolean chkReverseDNS = getCheckboxInfo(request, Constants.CHK_REVERSE_DNS);
		boolean chkVerifyHostname = getCheckboxInfo(request, Constants.CHK_VERIFY_HOST);
		boolean chkTLDDiscovery = getCheckboxInfo(request, Constants.CHK_TLD_DISCOVERY);
		boolean chkShodan = getCheckboxInfo(request, Constants.CHK_SHODAN);
		boolean chkStartResult = getCheckboxInfo(request, Constants.CHK_START_NUMBER);
		boolean chkDNSToUse = getCheckboxInfo(request, Constants.CHK_USE_DNS);
		boolean chkPrintLimit = getCheckboxInfo(request, Constants.CHK_FIX_LIMIT_NUMBER);
		
	
		try{
			Tools.hostnameValidation(domain);
		} catch (Exception e) {
			setError(Constants.FIELD_HOSTNAME, e.getMessage());
		}
		
		
		if (informationSource != null)
		{
			sourceToUse = getSourceToUse(informationSource);
		}
		if (chkReverseDNS)
		{
			reverseDNS = "-n";
			command.setArguments(Constants.CHK_REVERSE_DNS, reverseDNS);
		}
		if (chkVerifyHostname)
		{
			verifyHostname = "-v";
			command.setArguments(Constants.CHK_VERIFY_HOST, verifyHostname);
		}
		if (chkTLDDiscovery)
		{
			TLDDiscovery = "-t";
			command.setArguments(Constants.CHK_TLD_DISCOVERY, TLDDiscovery);
		}
		if (chkShodan)
		{
			shodan = "-h";
			command.setArguments(Constants.CHK_SHODAN, shodan);
		}
		if (chkStartResult)
		{
			startResult = "-s " + Tools.getFieldValue(request, Constants.FIELD_START_NUMBER);
			command.setArguments(Constants.CHK_START_NUMBER, "-s");
			command.setArguments(Constants.FIELD_START_NUMBER, Tools.getFieldValue(request, Constants.FIELD_START_NUMBER));
		}
		if (chkDNSToUse)
		{
			DNSToUse = "-e " + Tools.getFieldValue(request, Constants.FIELD_DNS);
			command.setArguments(Constants.CHK_USE_DNS, "-e");
			command.setArguments(Constants.FIELD_DNS, Tools.getFieldValue(request, Constants.FIELD_DNS));
		}
		if (chkPrintLimit)
		{
			printLimit = "-l " + Tools.getFieldValue(request, Constants.FIELD_MAX_NUMBER);
			command.setArguments(Constants.CHK_FIX_LIMIT_NUMBER, "-l");
			command.setArguments(Constants.FIELD_MAX_NUMBER, Tools.getFieldValue(request, Constants.FIELD_MAX_NUMBER));
		}
			
		//add this command to history
		command.setArguments(Constants.FIELD_DOMAIN, domain);
		command.setArguments(Constants.FIELD_SOURCE_INFO, sourceToUse);
		
		String commandString = "theharvester " + domain + " " + sourceToUse + " " + reverseDNS +
				" " + verifyHostname + " " + TLDDiscovery + " " + shodan + " " + startResult +
				" " + DNSToUse + " " + printLimit;
		
		command.setStringCommand(commandString);
		
		if (errors.isEmpty()) {
			// pas d'erreur, on envois la requetesartResult			
			command.setSuccess(true);
			result = "Succès de la commande";
		} else {
			command.setSuccess(false);
			result = "Echec de la commande";
		}

		return command;
	}

	/**
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setError(String field, String message) {
		errors.put(field, message);
	}
	
	
	private String getSourceToUse(LinkedList<String> sourceList)
	{
		String bufferList = "-b";
		
		if (sourceList.contains("multiselect-all"))
		{
			sourceList.remove("multiselect-all");
		}
		
		for (int i = 0; i < sourceList.size(); i++) 
		{
			bufferList += " " + sourceList.get(i);
		}
		
		return bufferList;
	}
	
	private boolean getCheckboxInfo(HttpServletRequest request, String field)
	{
		boolean chkBox = false;
		String[] chkState = null;
		
		chkState = request.getParameterValues(field);
		if (chkState != null)
		{
			chkBox = true;
		}
		
		return chkBox;
	}
}
