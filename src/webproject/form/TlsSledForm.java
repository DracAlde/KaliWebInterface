package webproject.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import webproject.commun.Command;
import webproject.commun.Constants;
import webproject.commun.ToolName;
import webproject.commun.Tools;

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
		} else {
			command.setSuccess(false);
			result = "Echec de la commande";
		}

		return command;
	}

//	private void hostnameValidation(String hostname) throws Exception {
//		DomainValidator dv = DomainValidator.getInstance();
//		if(dv.isValid(hostname)){
//			return;
//		}
//
//		InetAddressValidator iav = InetAddressValidator.getInstance();
//		if(iav.isValid(hostname)){
//			return;
//		}
//
//		throw new Exception("Cet hostname est invalide");
//	}

	/**
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setError(String field, String message) {
		errors.put(field, message);
	}

}
