package gr.aueb.cf.schoolapp2.service;

import gr.aueb.cf.schoolapp2.dao.ICityDAO;
import gr.aueb.cf.schoolapp2.model.City;

import java.sql.SQLException;
import java.util.List;

public class CityServiceImpl implements ICityService {
    private final ICityDAO dao;

    public CityServiceImpl(ICityDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<City> getAllCities() throws SQLException {
        try {
            return dao.getAll();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw e;
        }
    }
}
