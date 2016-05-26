package webproject.form;

import java.util.HashMap;
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
 * This class is used to validate the POST request form Zenmap.
 * It will return a shell command and a Map with form errors
 * @author arn0f
 *
 */
public class ZenmapForm {
	
	private String 				result = "";
	private Map<String, String> errors = new HashMap<String, String>();

	public ZenmapForm(){}

	public String getResult() {
		return result;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	/**
	 * Test every parameters of the request and build a command
	 * The errors are saved in the error Map
	 * @param request
	 * @return
	 */
	public Command validateZenmap(HttpServletRequest request){
		String hostname = Tools.getFieldValue(request, Constants.FIELD_HOSTNAME);
		String scanType = Tools.getFieldValue(request, Constants.FIELD_SCAN_TYPE);
		String scanValue = null;

		//System.out.println(scanType);
		
		try{
			Tools.hostnameValidation(hostname);
		} catch (Exception e) {
			setError(Constants.FIELD_HOSTNAME, e.getMessage());
		}

		try{
			scanValue = Tools.scanValidation(scanType);
		} catch (Exception e) {
			setError(Constants.FIELD_SCAN_TYPE, "Error: " + e);
		}

		
		//add this command to history
		Command command = new Command(ToolName.Zenmap);
		command.setArguments(Constants.FIELD_HOSTNAME, hostname);
		command.setArguments(Constants.FIELD_SCAN_TYPE, scanType);
		
		String commandString = "nmap "+ hostname+" "+ scanValue;
		command.setStringCommand(commandString);
		
		if (errors.isEmpty()) {
			// pas d'erreur, on envois la requete
			command.setSuccess(true);
			result = "Succès de la commande";
			
			AsyncItem item = new AsyncItem(request.getSession().getId(), Constants.TOOL_ZENMAP, "");
			AsyncRequest.addAsyncItem(item);
			
			@SuppressWarnings("unused")
			Shell shell = new Shell(request.getSession().getId(), Constants.TOOL_ZENMAP, commandString);
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

}
