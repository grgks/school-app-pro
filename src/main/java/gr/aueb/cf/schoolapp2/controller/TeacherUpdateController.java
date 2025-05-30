package gr.aueb.cf.schoolapp2.controller;

import gr.aueb.cf.schoolapp2.dao.CityDAOImpl;
import gr.aueb.cf.schoolapp2.dao.ICityDAO;
import gr.aueb.cf.schoolapp2.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp2.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp2.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp2.exceptions.TeacherAlreadyExistsException;
import gr.aueb.cf.schoolapp2.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp2.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp2.model.City;
import gr.aueb.cf.schoolapp2.model.Teacher;
import gr.aueb.cf.schoolapp2.service.CityServiceImpl;
import gr.aueb.cf.schoolapp2.service.ICityService;
import gr.aueb.cf.schoolapp2.service.ITeacherService;
import gr.aueb.cf.schoolapp2.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp2.validator.TeacherValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/school-app/teachers/update")
public class TeacherUpdateController extends HttpServlet {

    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
    ICityDAO cityDAO = new CityDAOImpl();
    ICityService cityService = new CityServiceImpl(cityDAO);
    TeacherUpdateDTO updateDTO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<City> cities = null;
        Integer id = Integer.parseInt(req.getParameter("id").trim());
        try {
            cities = cityService.getAllCities();
            TeacherReadOnlyDTO teacherReadOnlyDTO = teacherService.getTeacherById(id);
            req.setAttribute("cities", cities);
            if (req.getSession().getAttribute("updateDTO") != null) {
                // Move from session to request scope for JSP
                req.setAttribute("updateDTO", req.getSession().getAttribute("updateDTO"));
                //req.setAttribute("errors", req.getSession().getAttribute("errors"));

                // Clear session data (so it doesn't persist after refresh)
                req.getSession().removeAttribute("updateDTO");
                //req.getSession().removeAttribute("errors");
            }
            else req.setAttribute("updateDTO", teacherReadOnlyDTO);
            req.getRequestDispatcher("/WEB-INF/jsp/teacher-update.jsp").forward(req, resp);
        } catch (SQLException | TeacherDAOException | TeacherNotFoundException e) {
            String errorMessage = e.getMessage();
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/WEB-INF/jsp/teacher-update.jsp")
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> errors;
        String firstnameMessage;
        String lastnameMessage;
        String vatMessage;
        String fathernameMessage;
        String phoneNumMessage;
        String emailMessage;
        String streetMessage;
        String streetNumMessage;
        String zipcodeMessage;
        String cityIdMessage;
        String errorMessage;
        Teacher teacher;

        // Data binding
        String idStr = (req.getParameter("id") != null) ? req.getParameter("id").trim() : "";
        Integer id = Integer.parseInt(idStr);
        String firstname = (req.getParameter("firstname") != null) ? req.getParameter("firstname").trim() : "";
        String lastname = (req.getParameter("lastname") != null) ? req.getParameter("lastname").trim() : "";
        String vat = (req.getParameter("vat") != null) ? req.getParameter("vat").trim() : "";
        String fathername = (req.getParameter("fathername") != null) ? req.getParameter("fathername").trim() : "";
        String phoneNum = (req.getParameter("phoneNum") != null) ? req.getParameter("phoneNum").trim() : "";
        String email = (req.getParameter("email") != null) ? req.getParameter("email").trim() : "";
        String street = (req.getParameter("street") != null) ? req.getParameter("street").trim() : "";
        String streetNum = (req.getParameter("streetNum") != null) ? req.getParameter("streetNum").trim() : "";
        String zipcode = (req.getParameter("zipcode") != null) ? req.getParameter("zipcode").trim() : "";
        Integer cityId = (req.getParameter("cityId") != null) ? Integer.parseInt(req.getParameter("cityId").trim()) : 0;
        updateDTO = new TeacherUpdateDTO(id, firstname, lastname, vat, fathername, phoneNum,
                email, street, streetNum, zipcode, cityId);


        try {
            // Validate dto
            errors = TeacherValidator.validate(updateDTO);

            if (!errors.isEmpty()) {
                firstnameMessage = errors.getOrDefault("firstname", "");
                lastnameMessage = errors.getOrDefault("lastname", "");
                vatMessage = errors.getOrDefault("vat", "");
                fathernameMessage = errors.getOrDefault("fathername", "");
                phoneNumMessage = errors.getOrDefault("phoneNum", "");
                // more ...

                req.getSession().setAttribute("firstnameMessage", firstnameMessage);
                req.getSession().setAttribute("lastnameMessage", lastnameMessage);
                req.getSession().setAttribute("vatMessage", vatMessage);
                req.getSession().setAttribute("fathernameMessage", fathernameMessage);
                req.getSession().setAttribute("phoneNumMessage", phoneNumMessage);
                req.getSession().setAttribute("updateDTO", updateDTO);

                resp.sendRedirect(req.getContextPath() + "/school-app/teachers/update?id=" + id);
                return;
            }

            // Call the service

            TeacherReadOnlyDTO readOnlyDTO = teacherService.updateTeacher(id, updateDTO);
            HttpSession session = req.getSession(false);
            session.setAttribute("teacherInfo", readOnlyDTO);
            // PRG Pattern
            resp.sendRedirect(req.getContextPath() + "/school-app/teacher-updated");

        } catch (TeacherDAOException | TeacherAlreadyExistsException | TeacherNotFoundException e) {
            errorMessage = e.getMessage();
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/WEB-INF/jsp/teacher-insert.jsp")
                    .forward(req, resp);
        }
    }
}
