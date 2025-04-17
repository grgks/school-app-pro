package gr.aueb.cf.schoolapp2.service;

import gr.aueb.cf.schoolapp2.model.City;

import java.sql.SQLException;
import java.util.List;

public interface ICityService {
    List<City> getAllCities() throws SQLException;
}