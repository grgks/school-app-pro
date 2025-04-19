package gr.aueb.cf.schoolapp2.controller;

import gr.aueb.cf.schoolapp2.dao.*;
import gr.aueb.cf.schoolapp2.dto.StudentReadOnlyDTO;
import gr.aueb.cf.schoolapp2.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp2.exceptions.*;
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

@WebServlet("/school-app/students/update")
public class StudentUpdateController extends HttpServlet {

    IStudentDAO studentDAO = new StudentDAOImpl();
    IStudentService studentService = new StudentServiceImpl(studentDAO);
    ICityDAO cityDAO = new CityDAOImpl();
    ICityService cityService = new CityServiceImpl(cityDAO);
    StudentUpdateDTO updateDTO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<City> cities = null;
        Integer id = Integer.parseInt(req.getParameter("id").trim());
        try {
            cities = cityService.getAllCities();  // Λαμβάνουμε τις πόλεις
            StudentReadOnlyDTO studentReadOnlyDTO = studentService.getStudentById(id);  // Λαμβάνουμε τα στοιχεία του μαθητή
            req.setAttribute("cities", cities);  // Περάστε τις πόλεις στη JSP
            req.setAttribute("updateDTO", studentReadOnlyDTO);  // Περάστε τα στοιχεία του μαθητή στη JSP
            req.getRequestDispatcher("/WEB-INF/jsp/student-update.jsp").forward(req, resp);
        } catch (SQLException | StudentDAOException | StudentNotFoundException e) {
            String errorMessage = e.getMessage();
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/WEB-INF/jsp/student-update.jsp")
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

        updateDTO = new StudentUpdateDTO(id, firstname, lastname, vat, fathername, phoneNum, email, street, streetNum, zipcode, cityId);

        try {
            // Validate dto
            errors = StudentValidator.validate(updateDTO);

            if (!errors.isEmpty()) {
                firstnameMessage = errors.getOrDefault("firstname", "");
                lastnameMessage = errors.getOrDefault("lastname", "");
                vatMessage = errors.getOrDefault("vat", "");
                fathernameMessage = errors.getOrDefault("fathername", "");
                phoneNumMessage = errors.getOrDefault("phoneNum", "");
                emailMessage = errors.getOrDefault("email", "");
                streetMessage = errors.getOrDefault("street", "");
                streetNumMessage = errors.getOrDefault("streetNum", "");
                zipcodeMessage = errors.getOrDefault("zipcode", "");
                cityIdMessage = errors.getOrDefault("cityId", "");

                req.getSession().setAttribute("firstnameMessage", firstnameMessage);
                req.getSession().setAttribute("lastnameMessage", lastnameMessage);
                req.getSession().setAttribute("vatMessage", vatMessage);
                req.getSession().setAttribute("fathernameMessage", fathernameMessage);
                req.getSession().setAttribute("phoneNumMessage", phoneNumMessage);
                req.getSession().setAttribute("emailMessage", emailMessage);
                req.getSession().setAttribute("streetMessage", streetMessage);
                req.getSession().setAttribute("streetNumMessage", streetNumMessage);
                req.getSession().setAttribute("zipcodeMessage", zipcodeMessage);
                req.getSession().setAttribute("cityIdMessage", cityIdMessage);
                req.getSession().setAttribute("updateDTO", updateDTO);

                resp.sendRedirect(req.getContextPath() + "/school-app/students/update?id=" + id);
                return;
            }

            // Call the service to update the student
            StudentReadOnlyDTO readOnlyDTO = studentService.updateStudent(id, updateDTO);

            HttpSession session = req.getSession(false);
            session.setAttribute("studentInfo", readOnlyDTO);

            // PRG Pattern: Post/Redirect/Get
            resp.sendRedirect(req.getContextPath() + "/school-app/student-updated");

        } catch (StudentDAOException | StudentAlreadyExistsException | StudentNotFoundException e) {
            errorMessage = e.getMessage();
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/WEB-INF/jsp/student-update.jsp")
                    .forward(req, resp);
        }
    }
}