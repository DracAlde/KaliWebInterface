package webproject.commun;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Modèle de l'objet utilisateur
 * @author kilian
 *
 */

public class User {

	private long id;
	private String username;
	private String password;
	private String email;

	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(long id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * Définit le mot de passe de l'utilisateur.
	 * Si hash est vrai, le mot de passe sera hashé
	 * sinon, le mot de passe sera défini tel quel
	 * @param password
	 * @param hash
	 */
	
	public void setPassword(String password, Boolean hash) {
		if(hash){
			this.password = hashPassword(password);
		} else {
			this.password = password;
		}
	}

	/**
	 * Return true if the password is correct
	 * @param password
	 * @return
	 */
	public Boolean testPassword(String password){
		password = hashPassword(password);
		if(password.equals(this.password)){
			return true;
		}
		return false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Hash le mot de passe en entrée avec SHA-256 et le retourne en hexadecimal
	 * @param password
	 * @return
	 */
	private String hashPassword(String password){
		password += getEmail();
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			password = Tools.bytesToHex(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}
}
