package webproject.form;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import webproject.commun.Command;
import webproject.commun.Constants;
import webproject.commun.ToolName;
import webproject.commun.Tools;
import webproject.shell.Shell;

public class DnsReconForm {

	private String 				result = "";
	private Map<String, String> errors = new HashMap<String, String>();
	
	public DnsReconForm(){}
	
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
	public Command validateDnsRecon(HttpServletRequest request)
	{
		Command command = new Command(ToolName.Nikto);
		String commandString = "dnsrecon";
		String domain = Tools.getFieldValue(request, Constants.FIELD_DOMAIN);
		boolean chkEnum = getCheckboxInfo(request, Constants.CHK_ENUM_SWITCH);
		
	
		try{
			Tools.hostnameValidation(domain);
		} catch (Exception e) {
			setError(Constants.FIELD_DOMAIN, "ERROR: domain doesn't exist\n" + e.getMessage());
		}
		command.setArguments(Constants.FIELD_DOMAIN, domain);
		commandString += " -d " + domain;
		
		if (chkEnum)
		{
			command.setArguments(Constants.CHK_ENUM_SWITCH, "on");
			LinkedList<String> enumType = Tools.getListFieldValue(request, Constants.SLCT_ENUM_TYPE);
			if (!enumType.isEmpty())
			{
				if ("brt".equals(enumType.get(0).toString()) || "snoop".equals(enumType.get(0).toString()))
				{
					commandString += " -D /usr/share/dnsrecon/namelist.txt -t " + enumType.get(0);
				}
				else
				{
					commandString += " -t " + enumType.get(0);
				}
				command.setArguments(Constants.SLCT_ENUM_TYPE, enumType.get(0));
			}
		}
		else
		{
			command.setArguments(Constants.CHK_ENUM_SWITCH, "off");
			commandString += " -a";
		}
		
		
		command.setStringCommand(commandString);
		
		System.out.println(commandString);
		
		if (errors.isEmpty()) {
			// pas d'erreur, on envois la requetesartResult			
			command.setSuccess(true);
			result = "Succès de la commande";
			
			@SuppressWarnings("unused")
			Shell shell = new Shell(request.getSession().getId(), Constants.TOOL_DNS_RECON, commandString);
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
