package webproject.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import webproject.commun.AsyncItem;
import webproject.commun.Command;
import webproject.commun.Config;
import webproject.commun.Constants;
import webproject.commun.ToolName;
import webproject.commun.Tools;
import webproject.main.AsyncRequest;
import webproject.shell.Shell;

/**
 * This class is used to validate the POST request form Hydra.
 * It will return a shell command and a Map with form errors
 * @author kilian
 *
 */
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

	/**
	 * Test every parameters of the request and build a command
	 * The errors are saved in the error Map
	 * @param request
	 * @return
	 */
	public Command validateHydra(HttpServletRequest request){
		String target = Tools.getFieldValue(request, Constants.FIELD_TARGET);
		String protocol = Tools.getFieldValue(request, Constants.SLCT_PROTOCOL);
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
			protocolValidation(protocol);
			protocol += "://";
		} catch (Exception e) {
			setError(Constants.SLCT_PROTOCOL, e.getMessage());
		}
		
		try{
			radioValidation(accountRadio);
			
			if(accountRadio.equals("known")){
				accountValue = Tools.getFieldValue(request, Constants.FIELD_ACCOUNT_NAME);
				accountArg = "-l "+accountValue;
			}else if(accountRadio.equals("dictionary")){
				accountValue = Tools.getFieldValue(request, Constants.SLCT_ACCOUNT_DICO);
				if(accountValue.equals("big-dico")){
					accountArg = "-L "+Config.getProperties("big_dictionary_path");
				}else if(accountValue.equals("small-dico")){
					accountArg = "-L "+Config.getProperties("small_dictionary_path");
				}else{
					accountArg = "-L "+Config.getProperties("medium_dictionary_path");
				}
			}
			
		}catch(Exception e){
			setError(Constants.RADIO_ACCOUNT, e.getMessage());
		}
		
		try{
			radioValidation(accountRadio);
			
			if(passwordRadio.equals("known")){
				passwordValue = Tools.getFieldValue(request, Constants.FIELD_PASSWORD);
				passwordArg = "-p "+passwordValue;
			}else if(passwordRadio.equals("dictionary")){
				passwordValue = Tools.getFieldValue(request, Constants.SLCT_PASSWORD_DICO);
				if(passwordValue.equals("bid-dico")){
					passwordArg = "-P "+Config.getProperties("big_dictionary_path");
				}else if(passwordValue.equals("small-dico")){
					passwordArg = "-P "+Config.getProperties("small_dictionary_path");
				}else{
					passwordArg = "-P "+Config.getProperties("medium_dictionary_path");
				}
			}
			
		}catch(Exception e){
			setError(Constants.RADIO_PASSWORD, e.getMessage());
		}

		
		String commandString = "hydra " + accountArg + " " + passwordArg + " " + protocol + target;
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
			
			AsyncItem item = new AsyncItem(request.getSession().getId(), Constants.TOOL_HYDRA, "");
			AsyncRequest.addAsyncItem(item);
			
			@SuppressWarnings("unused")
			Shell shell = new Shell(request.getSession().getId(), Constants.TOOL_HYDRA, commandString);
		} else {
			command.setSuccess(false);
			result = "Echec de la commande";
		}
		
		return command;
	}
	
	/**
	 * Test if the radio parameters has one of the correct values
	 * @param accountRadio
	 * @return
	 * @throws Exception
	 */
	private int radioValidation(String accountRadio) throws Exception{
		if(accountRadio.equals("known") || accountRadio.equals("dictionary")){
			return 0;
		}
		throw new Exception("You must choose one of these options");
	}
	
	private int protocolValidation(String protocol)  throws Exception{
		if(protocol.equals("ftp")){ return 0; }
		if(protocol.equals("http")){ return 0; }
		if(protocol.equals("ssh")){ return 0; }
		throw new Exception("You must choose one of these options");
	}

	/**
	 * Add an error message associated with a field in the error map
	 */
	private void setError(String field, String message) {
		errors.put(field, message);
	}

}
