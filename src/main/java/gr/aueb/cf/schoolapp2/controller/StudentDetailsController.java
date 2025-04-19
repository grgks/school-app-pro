package gr.aueb.cf.schoolapp2.controller;


import gr.aueb.cf.schoolapp2.dao.*;
import gr.aueb.cf.schoolapp2.dto.StudentReadOnlyDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp2.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp2.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolapp2.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp2.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp2.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/school-app/student-details")
public class StudentDetailsController extends HttpServlet {

    IStudentDAO studentDAO = new StudentDAOImpl();
    IStudentService studentService = new StudentServiceImpl(studentDAO);

    ICityDAO cityDAO = new CityDAOImpl(); ;
    ICityService cityService = new CityServiceImpl(cityDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String studentId = request.getParameter("id");

        if (studentId == null || studentId.isEmpty()) {
            request.setAttribute("message", "Student ID is missing");
            request.getRequestDispatcher("/WEB-INF/jsp/student-view.jsp").forward(request, response);
            return;
        }

        try {
            StudentReadOnlyDTO student = studentService.getStudentById(Integer.parseInt(studentId));

            if (student == null) {
                request.setAttribute("message", "Student not found");
                request.getRequestDispatcher("/WEB-INF/jsp/student-view.jsp").forward(request, response);
                return;
            }

            //  Εύρεση ονόματος πόλης

            String cityName = cityService.getAllCities().stream()
                    .filter(city -> city.getId().equals(student.getCityId()))
                    .findFirst()
                    .map(city -> city.getName())
                    .orElse(" - ");

            //  Set attributes
            request.setAttribute("student", student);
            request.setAttribute("cityName", cityName);

            request.getRequestDispatcher("/WEB-INF/jsp/student-view.jsp").forward(request, response);

        } catch (NumberFormatException | SQLException e) {
            request.setAttribute("message", "Invalid student ID");
            request.getRequestDispatcher("/WEB-INF/jsp/student-view.jsp").forward(request, response);
        } catch (StudentDAOException | StudentNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/student-view.jsp").forward(request, response);
        }
    }
}


