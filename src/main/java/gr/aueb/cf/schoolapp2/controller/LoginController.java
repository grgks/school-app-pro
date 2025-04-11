package gr.aueb.cf.schoolapp2.controller;

import gr.aueb.cf.schoolapp2.authentication.AuthenticationProvider;
import gr.aueb.cf.schoolapp2.dao.IUserDAO;
import gr.aueb.cf.schoolapp2.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp2.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp2.dto.UserLoginDTO;
import gr.aueb.cf.schoolapp2.service.IUserService;
import gr.aueb.cf.schoolapp2.service.UserServiceImpl;
import gr.aueb.cf.schoolapp2.exceptions.UserNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class  LoginController extends HttpServlet {

    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isError = request.getParameter("isError");
        request.setAttribute("isError", isError == null ? "false" : "true");

        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ADMIN_TIMEOUT = 30 * 60; // 30 mins
        // Data binding
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);

        boolean principleIsAuthenticated = false;
        try {
            principleIsAuthenticated = AuthenticationProvider.authenticate(userLoginDTO);

            if (!principleIsAuthenticated) {
                request.setAttribute("error", "Invalid credentials");
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
                return;
            }

            HttpSession oldSession = request.getSession(false);

            if (oldSession != null) {
                oldSession.invalidate();
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("authenticated", true);
            session.setAttribute("username", username);
            session.setAttribute("role", userService.getUserByUsername(username).getRoleType().name());

            if (session.getAttribute("role").equals("ADMIN")) {
                session.setMaxInactiveInterval(ADMIN_TIMEOUT);
            }
             //(PRG)
            response.sendRedirect(request.getContextPath() + "/school-app/dashboard");




        } catch (UserDAOException | UserNotFoundException e) {

            request.setAttribute("error", "Authentication Error");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}
