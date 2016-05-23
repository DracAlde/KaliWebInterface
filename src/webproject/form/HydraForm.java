package webproject.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import webproject.commun.Command;
import webproject.commun.Constants;
import webproject.commun.ToolName;
import webproject.commun.Tools;
import webproject.shell.Shell;

public class HydraForm {

	private String 				result = "";
	private Map<String, String> errors = new HashMap<String, String>();

	public HydraForm(){}

	public String getResult() {
		return result;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public Command validateHydra(HttpServletRequest request){
		String target = Tools.getFieldValue(request, Constants.FIELD_TARGET);
		String accountRadio = Tools.getFieldValue(request, Constants.RADIO_ACCOUNT);
		String accountValue = null;
		String passwordRadio = Tools.getFieldValue(request, Constants.RADIO_PASSWORD);
		String passwordValue = null;
		String accountArg = "";
		String passwordArg = "";
		
		System.out.println("Account : " + accountRadio + "; Password : " + passwordRadio);
		
		try{
			Tools.hostnameValidation(target);
		} catch (Exception e) {
			setError(Constants.FIELD_TARGET, e.getMessage());
		}
		
		try{
			radioValidation(accountRadio);
			
			if(accountRadio.equals("known")){
				accountArg = "-l";
				accountValue = Tools.getFieldValue(request, Constants.FIELD_ACCOUNT_NAME);
			}else if(accountRadio.equals("dictionary")){
				accountArg = "-L";
				accountValue = Tools.getFieldValue(request, Constants.SLCT_ACCOUNT_DICO);
			}
			
		}catch(Exception e){
			setError(Constants.RADIO_ACCOUNT, e.getMessage());
		}
		
		try{
			radioValidation(accountRadio);
			
			if(passwordRadio.equals("known")){
				passwordArg = "-p";
				passwordValue = Tools.getFieldValue(request, Constants.FIELD_PASSWORD);
			}else if(passwordRadio.equals("dictionary")){
				passwordArg = "-P";
				passwordValue = Tools.getFieldValue(request, Constants.SLCT_PASSWORD_DICO);
			}
			
		}catch(Exception e){
			setError(Constants.RADIO_PASSWORD, e.getMessage());
		}

		
		String commandString = "hydra " + target + " " + accountArg + " " + accountValue + " " + passwordArg + " " + passwordValue;
		System.out.println(commandString);
		
		Command command = new Command(ToolName.Hydra);
		command.setArguments(Constants.FIELD_TARGET, target);
		command.setArguments(Constants.RADIO_ACCOUNT, accountRadio);
		command.setArguments(Constants.FIELD_ACCOUNT_NAME, accountValue);
		command.setArguments(Constants.RADIO_PASSWORD, passwordRadio);
		command.setArguments(Constants.FIELD_PASSWORD, passwordValue);
		
		command.setStringCommand(commandString);
		
		if (errors.isEmpty()) {
			// pas d'erreur, on envois la requete
			command.setSuccess(true);
			result = "Succès de la commande";			
			@SuppressWarnings("unused")
			Shell shell = new Shell(request.getSession().getId(), Constants.TOOL_HYDRA, commandString);
		} else {
			command.setSuccess(false);
			result = "Echec de la commande";
		}
		
		return command;
	}
	
	private int radioValidation(String accountRadio) throws Exception{
		if(accountRadio.equals("known") || accountRadio.equals("dictionary")){
			return 0;
		}
		throw new Exception("You must choose one of these options");
	}

	/**
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setError(String field, String message) {
		errors.put(field, message);
	}

}
