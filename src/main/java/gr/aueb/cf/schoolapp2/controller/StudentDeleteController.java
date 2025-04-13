package gr.aueb.cf.schoolapp2.controller;

import gr.aueb.cf.schoolapp2.dao.IStudentDAO;
import gr.aueb.cf.schoolapp2.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp2.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapp2.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp2.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp2.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolapp2.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp2.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp2.service.IStudentService;
import gr.aueb.cf.schoolapp2.service.ITeacherService;
import gr.aueb.cf.schoolapp2.service.StudentServiceImpl;
import gr.aueb.cf.schoolapp2.service.TeacherServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/school-app/students/delete")
public class StudentDeleteController extends HttpServlet {

    IStudentDAO studentDAO = new StudentDAOImpl();
    IStudentService studentService = new StudentServiceImpl(studentDAO);
    String message = "";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try {
            studentService.deleteStudent(id);
            req.setAttribute("id", id);
            req.getRequestDispatcher("/WEB-INF/jsp/student-deleted.jsp").forward(req, resp);
        } catch (StudentDAOException | StudentNotFoundException e) {
            message = e.getMessage();
            req.setAttribute("message", message);
            req.getRequestDispatcher("/WEB-INF/jsp/students.jsp").forward(req, resp);
        }
    }


}
