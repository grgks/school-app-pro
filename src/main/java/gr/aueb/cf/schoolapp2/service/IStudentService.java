package gr.aueb.cf.schoolapp2.service;

import gr.aueb.cf.schoolapp2.dto.FiltersDTO;
import gr.aueb.cf.schoolapp2.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapp2.dto.StudentReadOnlyDTO;
import gr.aueb.cf.schoolapp2.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapp2.exceptions.StudentAlreadyExistsException;
import gr.aueb.cf.schoolapp2.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp2.exceptions.StudentNotFoundException;

import java.util.List;

public interface IStudentService {
    StudentReadOnlyDTO insertStudent(StudentInsertDTO dto)
            throws StudentDAOException, StudentAlreadyExistsException;

    StudentReadOnlyDTO updateStudent(Integer id, StudentUpdateDTO dto)
            throws StudentDAOException, StudentAlreadyExistsException, StudentNotFoundException;

    void deleteStudent(Integer id) throws StudentDAOException, StudentNotFoundException;

    StudentReadOnlyDTO getStudentById(Integer id) throws StudentDAOException, StudentNotFoundException;

    List<StudentReadOnlyDTO> getAllStudents() throws StudentDAOException;

    List<StudentReadOnlyDTO> getStudentsByLastname(String lastname) throws StudentDAOException;

    List<StudentReadOnlyDTO> getFilteredStudents(FiltersDTO filters) throws StudentDAOException;
}
