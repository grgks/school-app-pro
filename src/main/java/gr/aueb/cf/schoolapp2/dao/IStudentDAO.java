package gr.aueb.cf.schoolapp2.dao;

import gr.aueb.cf.schoolapp2.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp2.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp2.model.Student;

import java.util.List;

public interface IStudentDAO {
    // Basic services
    Student insert(Student student) throws StudentDAOException;
    Student update(Student teacher) throws StudentDAOException;
    void delete(Integer id) throws StudentDAOException;
    Student getById(Integer id) throws StudentDAOException;
    List<Student> getAll() throws StudentDAOException;

    // Queries
    Student getByUUID(String uuid) throws StudentDAOException;
    List<Student> getByLastname(String lastname) throws StudentDAOException;
    Student getStudentByVat(String vat) throws StudentDAOException;
    List<Student> getFilteredStudents(String firstname, String lastname) throws StudentDAOException;
}
