package gr.aueb.cf.schoolapp2.service;

import gr.aueb.cf.schoolapp2.dao.IStudentDAO;
import gr.aueb.cf.schoolapp2.dto.FiltersDTO;
import gr.aueb.cf.schoolapp2.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapp2.dto.StudentReadOnlyDTO;
import gr.aueb.cf.schoolapp2.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapp2.exceptions.StudentAlreadyExistsException;
import gr.aueb.cf.schoolapp2.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp2.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolapp2.mapper.Mapper;
import gr.aueb.cf.schoolapp2.model.Student;

import java.util.List;

public class StudentServiceImpl implements IStudentService {

    private final IStudentDAO studentDAO;

    public StudentServiceImpl(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }


    @Override
    public StudentReadOnlyDTO insertStudent(StudentInsertDTO dto) throws StudentDAOException, StudentAlreadyExistsException {

            Student student;
            Student insertedStudent;

            try {
                student = Mapper.mapStudentInsertToModel(dto);
                if (studentDAO.getStudentByVat(dto.getVat()) != null)
                    throw new StudentAlreadyExistsException("Student with vat" + dto.getVat() + " exists");
                insertedStudent = studentDAO.insert(student);
                // logging
                return Mapper.mapStudentToReadOnlyDTO(insertedStudent).orElse(null);
            } catch (StudentDAOException e) {
                //e.printStackTrace();
                // logging
                // rollback
                throw e;
            } catch (StudentAlreadyExistsException e) {
                //e.printStackTrace();
                // logging
                // rollback
                throw e;
            }
    }

    @Override
    public StudentReadOnlyDTO updateStudent(Integer id, StudentUpdateDTO dto) throws StudentDAOException, StudentAlreadyExistsException, StudentNotFoundException {
        return null;
    }

    @Override
    public void deleteStudent(Integer id) throws StudentDAOException, StudentNotFoundException {

    }

    @Override
    public StudentReadOnlyDTO getStudentById(Integer id) throws StudentDAOException, StudentNotFoundException {
        return null;
    }

    @Override
    public List<StudentReadOnlyDTO> getAllStudents() throws StudentDAOException {
        return List.of();
    }

    @Override
    public List<StudentReadOnlyDTO> getStudentsByLastname(String lastname) throws StudentDAOException {
        return List.of();
    }

    @Override
    public List<StudentReadOnlyDTO> getFilteredStudents(FiltersDTO filters) throws StudentDAOException {
        return List.of();
    }
}
