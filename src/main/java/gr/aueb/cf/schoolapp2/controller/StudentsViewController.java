package gr.aueb.cf.schoolapp2.controller;

import gr.aueb.cf.schoolapp2.dao.IStudentDAO;
import gr.aueb.cf.schoolapp2.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapp2.dto.FiltersDTO;
import gr.aueb.cf.schoolapp2.dto.StudentReadOnlyDTO;
import gr.aueb.cf.schoolapp2.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp2.service.IStudentService;
import gr.aueb.cf.schoolapp2.service.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/school-app/students/view")
public class StudentsViewController extends HttpServlet {

    IStudentDAO studentDAO = new StudentDAOImpl();
    IStudentService studentService = new StudentServiceImpl(studentDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StudentReadOnlyDTO> studentReadOnlyDTOS;
        String filterFirstname = request.getParameter("firstname");
        filterFirstname = filterFirstname == null ? "" : filterFirstname;

        String filterLastname = request.getParameter("lastname");
        filterLastname = filterLastname == null ? "" : filterLastname;

        FiltersDTO filters = new FiltersDTO(filterFirstname, filterLastname);

        String message = "";

        try {
            studentReadOnlyDTOS = studentService.getFilteredStudents(filters);

            if (studentReadOnlyDTOS.isEmpty()) {
                request.setAttribute("message", "Students not found");
                request.getRequestDispatcher("/WEB-INF/jsp/students.jsp").forward(request, response);
                return;
            }

            request.setAttribute("students", studentReadOnlyDTOS);
            request.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(request, response);
        } catch (StudentDAOException e) {
            message = e.getMessage();
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/jsp/students.jsp").forward(request, response);
        }
    }
    }



