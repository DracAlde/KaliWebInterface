package webproject.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import webproject.commun.Level;
import webproject.commun.User;

public class UserDaoImpl implements UserDao {

	private DAOFactory daoFactory;

	private static final String SQL_SELECT_BY_EMAIL = "SELECT id, email, username, password, creation_date, level FROM USERS WHERE email = ?";
	private static final String SQL_INSERT = "INSERT INTO USERS (email, password, username) VALUES (?, ?, ?)";
	private static final String SQL_SELECT_BY_USERNAME = "SELECT id, email, username, password, creation_date, level FROM USERS WHERE username = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM USERS;";
	private static final String DELETE_USER_BY_USERNAME = "DELETE FROM USERS WHERE username = ?";

	UserDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/**
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des utilisateurs (un
	 * ResultSet) et un bean Utilisateur.
	 */
	private static User map( ResultSet resultSet ) throws SQLException {
		User user = new User();
		user.setId(resultSet.getLong( "id" ));
		user.setEmail(resultSet.getString("email"));
		user.setUsername(resultSet.getString("username"));
		user.setPassword(resultSet.getString("password"), false);
		user.setCreationDate(resultSet.getString("creation_date"));
		user.setLevel(Level.valueOf(resultSet.getString("level")));
		return user;
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
	
	public LinkedList<User> getAllUsers(){
		LinkedList<User> list = new LinkedList<User>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = DAOTools.getPreparedStatment(connexion, SQL_SELECT_ALL, false);
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				list.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOTools.close(resultSet, preparedStatement, connexion);
		}
		
		return list;
	}

	public void removeUser(String username) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = DAOTools.getPreparedStatment(connexion, DELETE_USER_BY_USERNAME, false, username);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOTools.close(preparedStatement, connexion);
		}
	}
}
