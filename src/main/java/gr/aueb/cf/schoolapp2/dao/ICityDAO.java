package gr.aueb.cf.schoolapp2.dao;

import gr.aueb.cf.schoolapp2.model.City;

import java.sql.SQLException;
import java.util.List;

import gr.aueb.cf.schoolapp2.model.City;

import java.sql.SQLException;
import java.util.List;

public interface ICityDAO {
    List<City> getAll() throws SQLException;
}