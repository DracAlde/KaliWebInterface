package webproject.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import webproject.commun.User;

public class UserDaoImpl implements UserDao {

	private DAOFactory daoFactory;

	private static final String SQL_SELECT_BY_EMAIL = "SELECT id, email, username, password FROM USERS WHERE email = ?";
	private static final String SQL_INSERT = "INSERT INTO USERS (email, password, username) VALUES (?, ?, ?)";
	private static final String SQL_SELECT_BY_USERNAME = "SELECT id, email, username, password FROM USERS WHERE username = ?";

	UserDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/**
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des utilisateurs (un
	 * ResultSet) et un bean Utilisateur.
	 */
	private static User map( ResultSet resultSet ) throws SQLException {
		User utilisateur = new User();
		utilisateur.setId(resultSet.getLong( "id" ));
		utilisateur.setEmail(resultSet.getString("email"));
		utilisateur.setUsername(resultSet.getString("username"));
		utilisateur.setPassword(resultSet.getString("password"), false);
		return utilisateur;
	}

	/**
	 * Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao 
	 */
	@Override
	public User find(String email) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = DAOTools.getPreparedStatment(connexion, SQL_SELECT_BY_EMAIL, false, email);
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()) {
				user = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOTools.close(resultSet, preparedStatement, connexion);
		}
		return user;
	}

	/* Implémentation de la méthode creer() définie dans l'interface UtilisateurDao */
	@Override
	public void create(User user) throws IllegalArgumentException, DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = DAOTools.getPreparedStatment(connexion, SQL_INSERT, true, user.getEmail(), user.getPassword(), user.getUsername() );

			int statut = preparedStatement.executeUpdate();

			if ( statut == 0 ) {
				throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
			}

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if ( valeursAutoGenerees.next() ) {
				user.setId( valeursAutoGenerees.getLong( 1 ) );
			} else {
				throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
			}

		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOTools.close( valeursAutoGenerees, preparedStatement, connexion );
		}
	}

	/**
	 * Return true if the username or the email already exist in base
	 */
	public Boolean emailExist(String email) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = DAOTools.getPreparedStatment(connexion, SQL_SELECT_BY_EMAIL, false, email);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOTools.close( resultSet, preparedStatement, connexion );
		}
		return false;
	}

	@Override
	public Boolean usernameExist(String username) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = DAOTools.getPreparedStatment(connexion, SQL_SELECT_BY_USERNAME, false, username);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOTools.close( resultSet, preparedStatement, connexion );
		}
		return false;
	}
}
