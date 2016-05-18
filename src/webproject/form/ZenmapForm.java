package webproject.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import webproject.commun.Command;
import webproject.commun.Constants;
import webproject.commun.ToolName;
import webproject.commun.Tools;

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

	public Command validateZenmap(HttpServletRequest request){
		String hostname = Tools.getFieldValue(request, Constants.FIELD_HOSTNAME);
		String scanType = Tools.getFieldValue(request, Constants.FIELD_SCAN_TYPE);
		String scanValue = null;

		System.out.println(scanType);
		
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
		command.setArguments(Constants.FIELD_SCAN_TYPE, scanValue);
		
		String commandString = "nmap "+ hostname+" "+ scanValue;
		command.setStringCommand(commandString);
		
		if (errors.isEmpty()) {
			// pas d'erreur, on envois la requete
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

}
