package webproject.form;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import webproject.commun.AsyncItem;
import webproject.commun.Command;
import webproject.commun.Constants;
import webproject.commun.ToolName;
import webproject.commun.Tools;
import webproject.main.AsyncRequest;
import webproject.shell.Shell;

/**
 * This class is used to validate the POST request form The Harvester.
 * It will return a shell command and a Map with form errors
 * @author arn0f
 *
 */
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
	
	/**
	 * Test every parameters of the request and build a command
	 * The errors are saved in the error Map
	 * @param request
	 * @return
	 */
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
			sourceToUse = getSourceToUse(informationSource, command);
		}
		if (chkReverseDNS)
		{
			reverseDNS = "-n";
			command.setArguments(Constants.CHK_REVERSE_DNS, "on");
		}
		if (chkVerifyHostname)
		{
			verifyHostname = "-v";
			command.setArguments(Constants.CHK_VERIFY_HOST, "on");
		}
		if (chkTLDDiscovery)
		{
			TLDDiscovery = "-t";
			command.setArguments(Constants.CHK_TLD_DISCOVERY, "on");
		}
		if (chkShodan)
		{
			shodan = "-h";
			command.setArguments(Constants.CHK_SHODAN, "on");
		}
		if (chkStartResult)
		{
			startResult = "-s " + Tools.getFieldValue(request, Constants.FIELD_START_NUMBER);
			command.setArguments(Constants.CHK_START_NUMBER, "on");
			command.setArguments(Constants.FIELD_START_NUMBER, Tools.getFieldValue(request, Constants.FIELD_START_NUMBER));
		}
		if (chkDNSToUse)
		{
			DNSToUse = "-e " + Tools.getFieldValue(request, Constants.FIELD_DNS);
			command.setArguments(Constants.CHK_USE_DNS, "on");
			command.setArguments(Constants.FIELD_DNS, Tools.getFieldValue(request, Constants.FIELD_DNS));
		}
		if (chkPrintLimit)
		{
			printLimit = "-l " + Tools.getFieldValue(request, Constants.FIELD_MAX_NUMBER);
			command.setArguments(Constants.CHK_FIX_LIMIT_NUMBER, "on");
			command.setArguments(Constants.FIELD_MAX_NUMBER, Tools.getFieldValue(request, Constants.FIELD_MAX_NUMBER));
		}
			
		//add this command to history
		command.setArguments(Constants.FIELD_DOMAIN, domain);
		
		String commandString = "theharvester " + domain + " " + sourceToUse + " " + reverseDNS +
				" " + verifyHostname + " " + TLDDiscovery + " " + shodan + " " + startResult +
				" " + DNSToUse + " " + printLimit;
		
		command.setStringCommand(commandString);
		
		if (errors.isEmpty()) {
			// pas d'erreur, on envois la requetesartResult			
			command.setSuccess(true);
			result = "Succès de la commande";
			
			AsyncItem item = new AsyncItem(request.getSession().getId(), Constants.TOOL_THE_HARVESTER, "");
			AsyncRequest.addAsyncItem(item);
			
			@SuppressWarnings("unused")
			Shell shell = new Shell(request.getSession().getId(), Constants.TOOL_THE_HARVESTER, commandString);
		} else {
			command.setSuccess(false);
			result = "Echec de la commande";
		}

		return command;
	}

	/**
	 * Add an error message associated with a field in the error map
	 */
	private void setError(String field, String message) {
		errors.put(field, message);
	}
	
	/**
	 * Special method to get the select values
	 * @param selectList
	 * @param command
	 * @return LinkedList of the selected values
	 */
	private String getSourceToUse(LinkedList<String> sourceList, Command command)
	{
		String bufferList = "-b";
		
		if (sourceList.contains("multiselect-all"))
		{
			sourceList.remove("multiselect-all");
			command.setArguments(Constants.FIELD_SOURCE_INFO, "multiselect-all");
		}
		
		for (int i = 0; i < sourceList.size(); i++) 
		{
			bufferList += " " + sourceList.get(i);
			command.setArguments(Constants.FIELD_SOURCE_INFO, sourceList.get(i));
		}
		
		return bufferList;
	}
	
	/**
	 * Get the value of the checkbox named in parameters
	 * @param request
	 * @param field
	 * @return true or false
	 */
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
