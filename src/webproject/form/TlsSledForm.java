package webproject.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.InetAddressValidator;

import webproject.commun.Constants;
import webproject.commun.Tools;

public class TlsSledForm {

	private String result;
	private Map<String, String> errors = new HashMap<String, String>();

	public TlsSledForm(){}

	public String getResultat() {
		return result;
	}

	public Map<String, String> getErreurs() {
		return errors;
	}

	public void validateTlsSled(HttpServletRequest request){
		String hostname = Tools.getFieldValue(request, Constants.FIELD_HOSTNAME);
		String port = Tools.getFieldValue(request, Constants.FIELD_PORT);

		try{
			hostnameValidation(hostname);
		} catch (Exception e) {
			setError(Constants.FIELD_HOSTNAME, e.getMessage());
		}

		try{
			Integer.parseInt(port);
		} catch (Exception e) {
			setError(Constants.FIELD_PORT, "Le numéro de port n'est pas un nombre entier");
		}

		if (errors.isEmpty()) {
			// pas d'erreur, on envois la requete
			String command = "tlssled "+ hostname+" "+port;
			
			
			
			result="";
		} else {
			result = "Echec de la commande";
		}

	}

	private void hostnameValidation(String hostname) throws Exception {
		DomainValidator dv = DomainValidator.getInstance();
		if(dv.isValid(hostname)){
			return;
		}

		InetAddressValidator iav = InetAddressValidator.getInstance();
		if(iav.isValid(hostname)){
			return;
		}

		throw new Exception("Cet hostname est invalide");
	}

	/**
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setError(String field, String message) {
		errors.put(field, "<br />"+message);
	}

}
