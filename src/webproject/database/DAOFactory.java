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
		String username;
		String password;
		BoneCP connectionPool = null;

		InputStream propertiesFile = context.getResourceAsStream(Constants.PROPERTIES_FILE);

		if ( propertiesFile == null ) {
			throw new DAOConfigurationException( "Properties file " + Constants.PROPERTIES_FILE + " unfound." );
		}

		try {
			properties.load( propertiesFile );
			url = properties.getProperty(Constants.PROPERTY_URL);
			driver = properties.getProperty(Constants.PROPERTY_DRIVER);
			username = properties.getProperty(Constants.PROPERTY_USERNAME);
			password = properties.getProperty(Constants.PROPERTY_PASSWORD);
		} catch ( IOException e ) {
			throw new DAOConfigurationException( "Failed to load properties file " + Constants.PROPERTIES_FILE, e );
		}

		try {
			Class.forName(driver);
		} catch ( ClassNotFoundException e ) {
			throw new DAOConfigurationException( "Driver unfound in classpath.", e );
		}

		try {
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(url);
			config.setUsername(username);
			config.setPassword(password);
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(2);
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOConfigurationException( "Failed to configure connection pool.", e );
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
