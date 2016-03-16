package webproject.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOTools {
	
	/**
	 * Proper close of the ResultSet
	 * @param resultSet
	 */
	public static void close( ResultSet resultSet ) {
	    if ( resultSet != null ) {
	        try {
	            resultSet.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Failed to close ResultSet : " + e.getMessage() );
	        }
	    }
	}

	/**
	 * Proper close of the Statment
	 * @param statement
	 */
	public static void close( Statement statement ) {
	    if ( statement != null ) {
	        try {
	            statement.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Failed to close Statement : " + e.getMessage() );
	        }
	    }
	}

	/**
	 * Proper close of the connection
	 * @param connexion
	 */
	public static void close( Connection connexion ) {
	    if ( connexion != null ) {
	        try {
	            connexion.close();
	        } catch ( SQLException e ) {
	            System.out.println("Failed to close the cnnection : " + e.getMessage());
	        }
	    }
	}

	/**
	 * Proper close of the statment and the connection
	 * @param statement
	 * @param connection
	 */
	public static void close(Statement statement, Connection connection) {
	    close(statement);
	    close(connection);
	}

	/**
	 * Proper close of the ResultSet, the Statment and the Connection
	 * @param resultSet
	 * @param statement
	 * @param connection
	 */
	public static void close(ResultSet resultSet, Statement statement, Connection connection) {
	    close(resultSet);
	    close(statement);
	    close(connection);
	}
	
	/**
	 * Initialized PreparedStatment
	 * @param connection
	 * @param sql
	 * @param returnGeneratedKeys
	 * @param objects
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement getPreparedStatment( Connection connection, String sql, boolean returnGeneratedKeys, Object... objects ) throws SQLException {
	    PreparedStatement preparedStatement = connection.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
	    for ( int i = 0; i < objects.length; i++ ) {
	        preparedStatement.setObject( i + 1, objects[i] );
	    }
	    return preparedStatement;
	}
}
