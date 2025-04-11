package gr.aueb.cf.schoolapp2.controller;

import gr.aueb.cf.schoolapp2.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp2.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp2.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp2.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp2.dto.FiltersDTO;
import gr.aueb.cf.schoolapp2.service.ITeacherService;
import gr.aueb.cf.schoolapp2.service.TeacherServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/school-app/teachers/view")
public class TeachersViewController extends HttpServlet {

    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        List<TeacherReadOnlyDTO> teacherReadOnlyDTOS;
        String filterFirstname = request.getParameter("firstname");
        filterFirstname = filterFirstname == null ? "" : filterFirstname;

        String filterLastname = request.getParameter("lastname");
        filterLastname = filterLastname == null ? "" : filterLastname;

        FiltersDTO filters = new FiltersDTO(filterFirstname, filterLastname);

        String message = "";

        try {
            teacherReadOnlyDTOS = teacherService.getFilteredTeachers(filters);

            if (teacherReadOnlyDTOS.isEmpty()) {
                request.setAttribute("message", "Teachers not found");
                request.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(request, response);
                return;
            }

            request.setAttribute("teachers", teacherReadOnlyDTOS);
            request.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(request, response);
        } catch (TeacherDAOException e) {
            message = e.getMessage();
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(request, response);
        }
    }
}
