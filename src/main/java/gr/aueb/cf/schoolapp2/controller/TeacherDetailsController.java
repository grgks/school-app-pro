package gr.aueb.cf.schoolapp2.controller;

import gr.aueb.cf.schoolapp2.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp2.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp2.dto.FiltersDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp2.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp2.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp2.service.ITeacherService;
import gr.aueb.cf.schoolapp2.service.TeacherServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/school-app/teacher-details")
public class TeacherDetailsController extends HttpServlet {

    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Ανάκτηση του ID του δασκάλου από το query string
        String teacherId = request.getParameter("id");

        // Έλεγχος αν το ID είναι έγκυρο
        if (teacherId == null || teacherId.isEmpty()) {
            request.setAttribute("message", "Teacher ID is missing");
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-view.jsp").forward(request, response);
            return;
        }

        try {
            // Ανακτήστε το Teacher DTO με βάση το ID
            TeacherReadOnlyDTO teacher = teacherService.getTeacherById(Integer.parseInt(teacherId));

            if (teacher == null) {
                request.setAttribute("message", "Teacher not found");
                request.getRequestDispatcher("/WEB-INF/jsp/teacher-view.jsp").forward(request, response);
                return;
            }

            // Εδώ μπορείτε να προσθέσετε οποιαδήποτε άλλες πληροφορίες χρειάζεστε για τον δάσκαλο
            request.setAttribute("teacher", teacher);

            // Επιστροφή στην JSP για να εμφανίσετε τα δεδομένα του δασκάλου
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-view.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid teacher ID");
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-view.jsp").forward(request, response);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-view.jsp").forward(request, response);
        }
    }
}