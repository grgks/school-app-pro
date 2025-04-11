package gr.aueb.cf.schoolapp2.service;

import gr.aueb.cf.schoolapp2.dao.IUserDAO;
import gr.aueb.cf.schoolapp2.dto.InsertUserDTO;
import gr.aueb.cf.schoolapp2.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp2.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolapp2.mapper.Mapper;
import gr.aueb.cf.schoolapp2.model.User;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User insertUser(InsertUserDTO dto) throws UserDAOException {
        User user;

        try {
            user = Mapper.mapToUser(dto);
            // log
            return userDAO.insert(user);
        } catch (UserDAOException e) {
            // e.printStackTrace();
            // log
            // rollback
            throw e;
        }
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException, UserDAOException {
        User user;

        try {
            user = userDAO.getByUsername(username);

            if (user == null) {
                throw new UserNotFoundException("User with username: " + username + " not found");
            }
            return user;
        } catch (UserDAOException e) {
            e.printStackTrace();
            // log
            // rollback
            throw e;
        }
    }

    @Override
    public boolean isUserValid(String username, String password) throws UserDAOException {
        try {
            // Logging
            return userDAO.isUserValid(username, password);
        } catch (UserDAOException e) {
            e.printStackTrace();
            // log
            throw e;
        }
    }

    @Override
    public boolean isEmailExists(String username) throws UserDAOException {
        try {
            // Logging
            return userDAO.isEmailExists(username);
        } catch (UserDAOException e) {
            e.printStackTrace();
            // log
            throw e;
        }
    }

//    private User mapToUser(InsertUserDTO dto) {
//        return new User(null, dto.getUsername(), dto.getPassword(), RoleType.valueOf(dto.getRole()));
//    }
}
