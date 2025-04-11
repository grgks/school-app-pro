package gr.aueb.cf.schoolapp2.dao;

import gr.aueb.cf.schoolapp2.model.City;
import gr.aueb.cf.schoolapp2.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements ICityDAO {
    @Override
    public List<City> getAll() throws SQLException {

        String sql = "SELECT * FROM cities order by name asc";
        List<City> cities = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");


                City city = new City(id, name);
                cities.add(city);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null,  "Select error", "Error", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
        return cities;
    }
}
