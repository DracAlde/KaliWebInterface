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

public class NiktoForm {

	private String 				result = "";
	private Map<String, String> errors = new HashMap<String, String>();
	
	public NiktoForm(){}
	
	public String getResult() 
	{
		return result;
	}

	public Map<String, String> getErrors() 
	{
		return errors;
	}
	
	public Command validateNikto(HttpServletRequest request)
	{
		Command command = new Command(ToolName.Nikto);
		String commandString = "nikto";
		String hostname = Tools.getFieldValue(request, Constants.FIELD_HOSTNAME);
		String vHost = Tools.getFieldValue(request, Constants.FIELD_VHOST);
		String port = Tools.getFieldValue(request, Constants.FIELD_PORT);
		String timeout = Tools.getFieldValue(request, Constants.FIELD_TIMEOUT);
		LinkedList<String> evasionType = Tools.getListFieldValue(request, Constants.SLCT_EVASION_TYPE);
		
	
		try{
			Tools.hostnameValidation(hostname);
			command.setArguments(Constants.FIELD_HOSTNAME, hostname);
			commandString += " -host " + hostname;
		} catch (Exception e) {
			setError(Constants.FIELD_HOSTNAME, "ERROR: hostname doesn't exist\n" + e.getMessage());
		}
		
		if (vHost != null)
		{
			try{
					System.out.println(Tools.hostnameValidation(vHost));
					command.setArguments(Constants.FIELD_VHOST, vHost);
					commandString += " -vhost " + vHost;
			} catch (Exception e) {
				setError(Constants.FIELD_VHOST, "ERROR: vHost doesn't exist\n" + e.getMessage());
			}
		}
		
		try{
			Tools.portValidation(port);
			command.setArguments(Constants.FIELD_PORT, port);
			commandString += " -port " + port;
		} catch (Exception e) {
			setError(Constants.FIELD_PORT, "ERROR: port is not correct (must be between 0 and 65535)\n" + e.getMessage());
		}
		
		if (timeout != null)
		{
			try{
				
					Tools.portValidation(timeout);
					command.setArguments(Constants.FIELD_TIMEOUT, timeout);
					commandString += " -Pause " + timeout;
			} catch (Exception e) {
				setError(Constants.FIELD_PORT, "ERROR: timeout is not correct (must be between 0 and 65535)\n" + e.getMessage());
			}
		}
		else 
		{
			command.setArguments(Constants.FIELD_TIMEOUT, "2");
			commandString += " -Pause " + "2";
		}
		
		if (evasionType != null)
		{
			commandString += " -evasion " + getSelectValues(evasionType, command);
		}
		if (getCheckboxInfo(request, Constants.CHK_FIND_ONLY))
		{
			command.setArguments(Constants.CHK_FIND_ONLY, "on");
			commandString += " -findonly";
		}
		if (getCheckboxInfo(request, Constants.CHK_NO_LOOKUP))
		{
			command.setArguments(Constants.CHK_NO_LOOKUP, "on");
			commandString += " -nolookup";
		}
		if (getCheckboxInfo(request, Constants.CHK_FORCE_SSL))
		{
			command.setArguments(Constants.CHK_FORCE_SSL, "on");
			commandString += " -ssl";
		}
		if (getCheckboxInfo(request, Constants.CHK_SINGLE_REQ))
		{
			command.setArguments(Constants.CHK_SINGLE_REQ, "on");
			commandString += " -Single";
		}
		
		
		command.setStringCommand(commandString);
		
		System.out.println(commandString);
		
		if (errors.isEmpty()) {
			// pas d'erreur, on envois la requetesartResult			
			command.setSuccess(true);
			result = "Succès de la commande";
			
			@SuppressWarnings("unused")
			Shell shell = new Shell(request.getSession().getId(), Constants.TOOL_NIKTO, commandString);
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
	
	
	private String getSelectValues(LinkedList<String> selectList, Command command)
	{
		String bufferList = "-evasion";
		
		if (selectList.contains("multiselect-all"))
		{
			selectList.remove("multiselect-all");
			command.setArguments(Constants.SLCT_EVASION_TYPE, "multiselect-all");
		}
		
		for (int i = 0; i < selectList.size(); i++) 
		{
			bufferList += " " + selectList.get(i);
			command.setArguments(Constants.SLCT_EVASION_TYPE, selectList.get(i));
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
