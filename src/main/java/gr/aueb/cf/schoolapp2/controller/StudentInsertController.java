package gr.aueb.cf.schoolapp2.controller;

import gr.aueb.cf.schoolapp2.dao.*;
import gr.aueb.cf.schoolapp2.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapp2.dto.StudentReadOnlyDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp2.exceptions.StudentAlreadyExistsException;
import gr.aueb.cf.schoolapp2.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp2.exceptions.TeacherAlreadyExistsException;
import gr.aueb.cf.schoolapp2.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp2.model.City;
import gr.aueb.cf.schoolapp2.model.Teacher;
import gr.aueb.cf.schoolapp2.service.*;
import gr.aueb.cf.schoolapp2.validator.StudentValidator;
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

@WebServlet("/school-app/students/insert")
public class StudentInsertController extends HttpServlet {

    IStudentDAO studentDAO = new StudentDAOImpl();
    IStudentService studentService = new StudentServiceImpl(studentDAO);
    ICityDAO cityDAO = new CityDAOImpl();
    ICityService cityService = new CityServiceImpl(cityDAO);

    StudentInsertDTO studentInsertDTO = new StudentInsertDTO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<City> cities = null;
        try {
            cities = cityService.getAllCities();
        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            req.getSession().setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/WEB-INF/jsp/student-insert.jsp")
                    .forward(req, resp);
        }
        req.setAttribute("cities", cities);

        if (req.getSession().getAttribute("insertDTO") != null) {
            // Move from session to request scope for JSP
            req.setAttribute("insertDTO", req.getSession().getAttribute("insertDTO"));


            // Clear session data (so it doesn't persist after refresh)
            req.getSession().removeAttribute("insertDTO");

        }
        req.getRequestDispatcher("/WEB-INF/jsp/student-insert.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentInsertDTO insertDTO;
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
        insertDTO = new StudentInsertDTO(firstname, lastname, vat, fathername, phoneNum,
                email, street, streetNum, zipcode, cityId);


        try {
            // Validate dto
            errors = StudentValidator.validate(insertDTO);

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
                req.getSession().setAttribute("insertDTO", insertDTO);

                resp.sendRedirect(req.getContextPath() + "/school-app/students/insert");
                return;
            }

            // Call the service

            StudentReadOnlyDTO readOnlyDTO = studentService.insertStudent(insertDTO);
            HttpSession session = req.getSession(false);
            session.setAttribute("studentInfo", readOnlyDTO);
            // PRG Pattern
            resp.sendRedirect(req.getContextPath() + "/school-app/student-inserted");

        } catch (StudentDAOException | StudentAlreadyExistsException e) {
            errorMessage = e.getMessage();
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/WEB-INF/jsp/student-insert.jsp")
                    .forward(req, resp);
        }
}
}
