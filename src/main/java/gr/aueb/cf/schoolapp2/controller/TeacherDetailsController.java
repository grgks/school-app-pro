package gr.aueb.cf.schoolapp2.controller;

import gr.aueb.cf.schoolapp2.dao.CityDAOImpl;
import gr.aueb.cf.schoolapp2.dao.ICityDAO;
import gr.aueb.cf.schoolapp2.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp2.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp2.dto.FiltersDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp2.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp2.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp2.model.City;
import gr.aueb.cf.schoolapp2.service.CityServiceImpl;
import gr.aueb.cf.schoolapp2.service.ICityService;
import gr.aueb.cf.schoolapp2.service.ITeacherService;
import gr.aueb.cf.schoolapp2.service.TeacherServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/school-app/teacher-details")
public class TeacherDetailsController extends HttpServlet {

    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

     ICityDAO cityDAO = new CityDAOImpl(); ;
    ICityService cityService = new CityServiceImpl(cityDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String teacherId = request.getParameter("id");

        if (teacherId == null || teacherId.isEmpty()) {
            request.setAttribute("message", "Teacher ID is missing");
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-view.jsp").forward(request, response);
            return;
        }

        try {
            TeacherReadOnlyDTO teacher = teacherService.getTeacherById(Integer.parseInt(teacherId));

            if (teacher == null) {
                request.setAttribute("message", "Teacher not found");
                request.getRequestDispatcher("/WEB-INF/jsp/teacher-view.jsp").forward(request, response);
                return;
            }

            //  Εύρεση ονόματος πόλης

            String cityName = cityService.getAllCities().stream()
                    .filter(city -> city.getId().equals(teacher.getCityId()))
                    .findFirst()
                    .map(city -> city.getName())
                    .orElse("Άγνωστη Πόλη");

            //  Set attributes
            request.setAttribute("teacher", teacher);
            request.setAttribute("cityName", cityName);

            request.getRequestDispatcher("/WEB-INF/jsp/teacher-view.jsp").forward(request, response);

        } catch (NumberFormatException | SQLException e) {
            request.setAttribute("message", "Invalid teacher ID");
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-view.jsp").forward(request, response);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-view.jsp").forward(request, response);
        }
    }
}

