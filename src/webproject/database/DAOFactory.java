package webproject.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import webproject.commun.Constants;

public class DAOFactory {

	BoneCP connectionPool = null;

	DAOFactory(BoneCP connectionPool) {
		this.connectionPool = connectionPool;
	}

	/**
	 * Méthode chargée de récupérer les informations de connexion à la base de
	 * données, charger le driver JDBC et retourner une instance de la Factory
	 */
	public static DAOFactory getInstance(ServletContext context) throws DAOConfigurationException {
		Properties properties = new Properties();
		String url;
		String driver;
		String nomUtilisateur;
		String motDePasse;
		BoneCP connectionPool = null;

		InputStream fichierProperties = context.getResourceAsStream(Constants.PROPERTIES_FILE);

		if ( fichierProperties == null ) {
			throw new DAOConfigurationException( "Le fichier properties " + Constants.PROPERTIES_FILE + " est introuvable." );
		}

		try {
			properties.load( fichierProperties );
			url = properties.getProperty(Constants.PROPERTY_URL);
			driver = properties.getProperty(Constants.PROPERTY_DRIVER);
			nomUtilisateur = properties.getProperty(Constants.PROPERTY_USERNAME);
			motDePasse = properties.getProperty(Constants.PROPERTY_PASSWORD);
		} catch ( IOException e ) {
			throw new DAOConfigurationException( "Impossible de charger le fichier properties " + Constants.PROPERTIES_FILE, e );
		}

		try {
			Class.forName(driver);
		} catch ( ClassNotFoundException e ) {
			throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
		}

		try {
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(url);
			config.setUsername(nomUtilisateur);
			config.setPassword(motDePasse);
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(2);
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOConfigurationException( "Erreur de configuration du pool de connexions.", e );
		}

		DAOFactory instance = new DAOFactory(connectionPool);
		return instance;
	}

	/**
	 * Méthode chargée de fournir une connexion à la base de données 
	 */
	Connection getConnection() throws SQLException {
		return connectionPool.getConnection();
	}

	/**
	 * Méthodes de récupération de l'implémentation des différents DAO (un seul
	 * pour le moment)
	 */
	public UserDao getUserDao() {
		return new UserDaoImpl( this );
	}
}
