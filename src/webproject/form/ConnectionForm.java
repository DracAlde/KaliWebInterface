package webproject.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import webproject.commun.Constants;
import webproject.commun.Tools;
import webproject.commun.User;
import webproject.database.UserDao;

public class ConnectionForm {

	private String result;
	private Map<String, String> error = new HashMap<String, String>();

	private User user = null;
	private UserDao userDao;

	public ConnectionForm( UserDao userDao ) {
		this.userDao = userDao;
	}
	
	public String getResult() {
		return result;
	}

	public Map<String, String> getErreurs() {
		return error;
	}

	public User connectUser( HttpServletRequest request ) {
		String email = Tools.getFieldValue(request, Constants.EMAIL_FIELD);
		String password = Tools.getFieldValue(request, Constants.PASSWORD_FIELD);

		try {
			validationEmail(email);
		} catch (Exception e) {
			setError(Constants.EMAIL_FIELD, e.getMessage());
		}

		try {
			validationMotDePasse(password);
		} catch (Exception e) {
			setError(Constants.PASSWORD_FIELD, e.getMessage());
		}

		if ( error.isEmpty()) {
			result = "Succès de la connexion.";
		} else {
			result = "Échec de la connexion.";
		}

		return user;
	}

	/**
	 * Valide l'adresse email saisie.
	 */
	private void validationEmail( String email ) throws Exception {
		if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
			throw new Exception( "Merci de saisir une adresse mail valide." );
		}
		user = userDao.find(email);
		if(user == null){
			throw new Exception("That user doesn't exist");
		}
	}

	/**
	 * Valide le mot de passe saisi.
	 */
	private void validationMotDePasse(String password) throws Exception {
		if (password != null) {
			if (password.length() < 3) {
				throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
			}
			if (!user.testPassword(password)){
				throw new Exception("Wrong password");
			}
		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
		}
	}

	/**
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setError(String field, String message) {
		error.put(field, message);
	}
}
