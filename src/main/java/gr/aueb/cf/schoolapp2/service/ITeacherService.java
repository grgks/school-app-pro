package gr.aueb.cf.schoolapp2.service;

import gr.aueb.cf.schoolapp2.dto.FiltersDTO;
import gr.aueb.cf.schoolapp2.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp2.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp2.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp2.exceptions.TeacherAlreadyExistsException;
import gr.aueb.cf.schoolapp2.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {
    TeacherReadOnlyDTO insertTeacher(TeacherInsertDTO dto)
            throws TeacherDAOException, TeacherAlreadyExistsException;
    TeacherReadOnlyDTO updateTeacher(Integer id, TeacherUpdateDTO dto)
            throws TeacherDAOException, TeacherAlreadyExistsException, TeacherNotFoundException;
    void deleteTeacher(Integer id) throws TeacherDAOException, TeacherNotFoundException;
    TeacherReadOnlyDTO getTeacherById(Integer id) throws TeacherDAOException, TeacherNotFoundException;
    List<TeacherReadOnlyDTO> getAllTeachers() throws TeacherDAOException;
    List<TeacherReadOnlyDTO> getTeachersByLastname(String lastname) throws TeacherDAOException;
    List<TeacherReadOnlyDTO> getFilteredTeachers(FiltersDTO filters) throws TeacherDAOException;
}
