package gr.aueb.cf.schoolapp2.controller;


import gr.aueb.cf.schoolapp2.dao.IStudentDAO;
import gr.aueb.cf.schoolapp2.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapp2.dto.StudentReadOnlyDTO;
import gr.aueb.cf.schoolapp2.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp2.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolapp2.service.IStudentService;
import gr.aueb.cf.schoolapp2.service.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/school-app/student-details")
public class StudentDetailsController extends HttpServlet {

    IStudentDAO studentDAO = new StudentDAOImpl();
    IStudentService studentService = new StudentServiceImpl(studentDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String studentId = request.getParameter("id");

        // Έλεγχος αν το ID είναι έγκυρο
        if (studentId == null || studentId.isEmpty()) {
            request.setAttribute("message", "Student ID is missing");
            request.getRequestDispatcher("/WEB-INF/jsp/student-view.jsp").forward(request, response);
            return;
        }

        try {
            // Ανακτήστε το Student DTO με βάση το ID
            StudentReadOnlyDTO student = studentService.getStudentById(Integer.parseInt(studentId));

            if (student == null) {
                request.setAttribute("message", "Student not found");
                request.getRequestDispatcher("/WEB-INF/jsp/student-view.jsp").forward(request, response);
                return;
            }

            // Εδώ μπορείτε να προσθέσετε οποιαδήποτε άλλες πληροφορίες χρειάζεστε
            request.setAttribute("student", student);

            // Επιστροφή στην JSP για να εμφανίσετε τα δεδομένα
            request.getRequestDispatcher("/WEB-INF/jsp/student-view.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid Student ID");
            request.getRequestDispatcher("/WEB-INF/jsp/student-view.jsp").forward(request, response);
        } catch (StudentDAOException | StudentNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/student-view.jsp").forward(request, response);
        }
    }

}
