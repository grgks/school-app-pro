package gr.aueb.cf.schoolapp2.service;

import gr.aueb.cf.schoolapp2.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp2.dto.InsertUserDTO;
import gr.aueb.cf.schoolapp2.model.User;
import gr.aueb.cf.schoolapp2.exceptions.UserNotFoundException;

public interface IUserService {
    User insertUser(InsertUserDTO dto) throws UserDAOException;
    User getUserByUsername(String username) throws UserNotFoundException, UserDAOException;
    boolean isUserValid(String username, String password) throws UserDAOException;
    boolean isEmailExists(String username) throws UserDAOException;
}
