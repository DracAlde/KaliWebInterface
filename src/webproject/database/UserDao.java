package webproject.database;

import webproject.commun.User;

public interface UserDao {
	
    void create(User user) throws DAOException;
    
    User find(String email) throws DAOException;
    
    Boolean emailExist(String email) throws DAOException;
    
    Boolean usernameExist(String username) throws DAOException;

}
