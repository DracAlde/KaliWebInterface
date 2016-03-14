package webproject.database;

import java.util.LinkedList;

import webproject.commun.User;

public interface UserDao {
	
    public void create(User user) throws DAOException;
    
    public User find(String email) throws DAOException;
    
    public Boolean emailExist(String email) throws DAOException;
    
    public Boolean usernameExist(String username) throws DAOException;

	public LinkedList<User> getAllUsers() throws DAOException;

	public void removeUser(String username) throws DAOException;
}
