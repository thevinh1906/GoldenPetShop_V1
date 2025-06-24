package com.utc2.petShop.model.repository.Select;

import com.utc2.petShop.model.entities.vaccine.Vaccine;
import com.utc2.petShop.utils.DBConnection;
import javafx.beans.property.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectVaccine {
    public static List<Vaccine> getAllVaccines() {
        List<Vaccine> vaccines = new ArrayList<>();
        String sql = "SELECT * FROM VACCINE WHERE isDeleted = 0";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vaccine vaccine = new Vaccine();
                vaccine.setVaccineId(rs.getInt("vaccineId"));
                vaccine.setVaccineName(rs.getString("vaccineName"));
                vaccine.setDescription(rs.getString("description"));
                vaccine.setApplicableSpecies(rs.getString("applicableSpecies"));
                vaccine.setDoseCount(rs.getInt("doseCount"));
                vaccine.setIntervalDays(rs.getInt("intervalDays"));
                vaccine.setValidityMonths(rs.getInt("validityMonths"));
                vaccine.setIsMandatory(rs.getBoolean("isMandatory"));

                vaccines.add(vaccine);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vaccines;
    }
}



