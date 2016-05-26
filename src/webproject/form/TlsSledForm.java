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
 * This class is used to validate the POST request form TLS Sled.
 * It will return a shell command and a Map with form errors
 * @author kilian
 *
 */
public class TlsSledForm {

	private String 				result = "";
	private Map<String, String> errors = new HashMap<String, String>();

	public TlsSledForm(){}

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
	public Command validateTlsSled(HttpServletRequest request){
		String hostname = Tools.getFieldValue(request, Constants.FIELD_HOSTNAME);
		String port = Tools.getFieldValue(request, Constants.FIELD_PORT);

		try{
			Tools.hostnameValidation(hostname);
		} catch (Exception e) {
			setError(Constants.FIELD_HOSTNAME, e.getMessage());
		}

		try{
			Tools.portValidation(port);
		} catch (Exception e) {
			setError(Constants.FIELD_PORT, "Port error: " + e);
		}

		
		//add this command to history
		Command command = new Command(ToolName.TlsSled);
		command.setArguments(Constants.FIELD_HOSTNAME, hostname);
		command.setArguments(Constants.FIELD_PORT, port);

		String commandString = "tlssled " + hostname + " " + port;
		command.setStringCommand(commandString);
		
		if (errors.isEmpty()) {
			// pas d'erreur, on envois la requete
			command.setSuccess(true);
			result = "Succès de la commande";
			
			AsyncItem item = new AsyncItem(request.getSession().getId(), Constants.TOOL_TLS_SLED, "");
			AsyncRequest.addAsyncItem(item);
			
			@SuppressWarnings("unused")
			Shell shell = new Shell(request.getSession().getId(), Constants.TOOL_TLS_SLED, commandString);
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
