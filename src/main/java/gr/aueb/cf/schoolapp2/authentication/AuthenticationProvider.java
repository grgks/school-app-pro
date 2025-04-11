package gr.aueb.cf.schoolapp2.authentication;

import gr.aueb.cf.schoolapp2.dao.IUserDAO;
import gr.aueb.cf.schoolapp2.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp2.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp2.dto.UserLoginDTO;
import gr.aueb.cf.schoolapp2.service.IUserService;
import gr.aueb.cf.schoolapp2.service.UserServiceImpl;

public class AuthenticationProvider {
    private final static IUserDAO userDAO = new UserDAOImpl();
    private final static IUserService userService = new UserServiceImpl(userDAO);

    private AuthenticationProvider() {}

    public static boolean authenticate(UserLoginDTO userLoginDTO) throws UserDAOException {
        return userService.isUserValid(userLoginDTO.getUsername(), userLoginDTO.getPassword());
    }
}