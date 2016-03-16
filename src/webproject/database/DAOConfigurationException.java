package webproject.database;

public class DAOConfigurationException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1682050852930178643L;

	public DAOConfigurationException(String message) {
        super(message);
    }

    public DAOConfigurationException(String message, Throwable e) {
        super(message, e);
    }

    public DAOConfigurationException(Throwable e) {
        super(e);
    }
}
