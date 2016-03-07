package webproject.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import webproject.commun.Constants;
import webproject.commun.User;
import webproject.database.DAOException;
import webproject.database.UserDao;

public class InscriptionForm {

	private UserDao userDao;

	public InscriptionForm( UserDao userDao ) {
		this.userDao = userDao;
	}

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public User createUser(HttpServletRequest request) {
		String email = getValeurChamp(request, Constants.EMAIL_FIELD);
		String password = getValeurChamp(request, Constants.PASSWORD_FIELD);
		String confirmation = getValeurChamp(request, Constants.CONFIRMATION_FIELD);
		String username = getValeurChamp(request, Constants.USERNAME_FIELD);

		System.out.println("Email : "+email+"\nPassword : "+password+"\nConfirmation : "+confirmation+"\nUsername : "+username);

		User user = new User();

		try {
			emailValidation( email );
		} catch ( Exception e ) {
			setErreur(Constants.EMAIL_FIELD, e.getMessage());
		}
		user.setEmail(email);

		try {
			usernameValidation(username);
		} catch (Exception e) {
			setErreur(Constants.USERNAME_FIELD, e.getMessage());
		}
		user.setUsername(username);

		try {
			passworValidation(password, confirmation);
		} catch (Exception e) {
			setErreur(Constants.PASSWORD_FIELD, e.getMessage());
			setErreur(Constants.CONFIRMATION_FIELD, null);
		}
		user.setPassword(password, true);

		try{
			if (erreurs.isEmpty()) {
				userDao.create(user);
				resultat = "Succès de l'inscription.";
			} else {
				resultat = "Échec de l'inscription.";
			}
		} catch ( DAOException e ) {
			resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * 
	 * @param email
	 * @throws Exception
	 */
	private void emailValidation(String email) throws Exception {
		if(email != null) {
			if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse mail valide.");
			}
			if(userDao.emailExist(email)){
				throw new Exception("Cette addresse est déjà utilisée");
			}
		} else {
			throw new Exception("Merci de saisir une adresse mail.");
		}
	}

	/**
	 * 
	 * @param password
	 * @param confirmation
	 * @throws Exception
	 */
	private void passworValidation(String password, String confirmation) throws Exception {
		if (password != null && confirmation != null) {
			if (!password.equals(confirmation)) {
				throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
			} else if (password.length() < 3) {
				throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
			}
		} else {
			throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
		}
	}

	/**
	 * Vérifie que le nom d'utilisateur contient au moins trois caractères
	 * @param username
	 * @throws Exception
	 */
	private void usernameValidation(String username) throws Exception {
		if (username != null){
			if(username.length() < 3) {
				throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
			}
			if(userDao.usernameExist(username)){
				throw new Exception("Ce nom d'utilisateur est déjà utilisée");
			}
		} else {
			throw new Exception("Merci de saisir un nom d'utilisateur");
		}
	}

	/**
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String field, String message) {
		erreurs.put(field, message);
	}

	/**
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp(HttpServletRequest request, String fieldName ) {
		String value = request.getParameter(fieldName);
		if (value == null || value.trim().length() == 0) {
			return null;
		} else {
			return value.trim();
		}
	}
}
