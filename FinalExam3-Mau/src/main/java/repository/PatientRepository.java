package repository;

import model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository implements IPatientRepository {
    private final static String GET_ALL = "SELECT * FROM patient";
    private final static String SAVE = "INSERT INTO patient(name) VALUE (?)";
    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectDB.getConnectDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("patient_id");
                String name = resultSet.getString("name");
                patients.add(new Patient(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }

   @Override
    public boolean save(Patient patient) {
        Connection conn = null;
        int rowEffected = 0;
        try {
            conn = ConnectDB.getConnectDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement preparedStatement =  conn.prepareStatement(SAVE);
            preparedStatement.setString(1, patient.getName());
            rowEffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return rowEffected>0;
    }
}

