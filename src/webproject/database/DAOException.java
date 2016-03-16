package webproject.database;

public class DAOException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6804887490227253188L;

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable e) {
        super(message, e);
    }

    public DAOException(Throwable e) {
        super(e);
    }
}
