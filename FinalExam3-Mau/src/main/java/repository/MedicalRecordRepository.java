package repository;

import dto.MedicalRecordDto;
import model.MedicalRecord;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordRepository implements IMedicalRecordRepository {
    private final static String GET_ALL = "SELECT * FROM medical_record";
    private final static String GET_ALL_DTO =
            "select medical_record.id, medical_record.record_id, start_date,end_date,description, patient.name as patient_name\n" +
            "from medical_record \n" +
            "join patient on patient.patient_id = medical_record.patient_id;";
    private final static String DELETE_BY_ID = "DELETE FROM medical_record WHERE id = ?";
    private final static String FIND_BY_ID = "SELECT * FROM medical_record WHERE id = ?";

    private final static String SAVE = "INSERT INTO medical_record(record_id,start_date,end_date,description,patient_id) VALUE (?,?,?,?,?)";
    private final static String UPDATE = "UPDATE medical_record SET record_id=? ,start_date= ?,end_date= ?,description=?, patient_id =? WHERE id =?";
    private final static String SEARCH_DTO =
            "SELECT medical_record.id, medical_record.record_id, start_date, end_date,description, patient.name AS patient_name\n" +
            "FROM medical_record\n" +
            "JOIN patient ON patient.patient_id = medical_record.patient_id\n" +
            "WHERE medical_record.record_id like concat('%',?,'%') and patient.name like concat('%',?,'%');";
//    @Override
//    public List<MedicalRecord> findAll() {
//        List<MedicalRecord> medicalRecordList = new ArrayList<>();
//        Connection conn = null;
//        try {
//            conn = ConnectDB.getConnectDB();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            PreparedStatement preparedStatement =  conn.prepareStatement(GET_ALL);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String recordId = resultSet.getString("record_id");
//                Date startDate = resultSet.getDate("start_date");
//                Date endDate = resultSet.getDate("end_date");
//                String description = resultSet.getString("description");
//                int patientId = resultSet.getInt("patient_id");
//                medicalRecordList.add(new MedicalRecord(id,recordId,startDate,endDate,description,patientId));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return medicalRecordList;
//    }

    @Override
    public List<MedicalRecordDto> findAllDto() {
        List<MedicalRecordDto> medicalRecordDtos = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectDB.getConnectDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            PreparedStatement preparedStatement =  conn.prepareStatement(GET_ALL_DTO);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String recordId = resultSet.getString("record_id");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                String description = resultSet.getString("description");
                String patientName = resultSet.getString("patient_name");
                medicalRecordDtos.add(new MedicalRecordDto(id, recordId,startDate,endDate,description,patientName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medicalRecordDtos;
    }

    @Override
    public MedicalRecord findById(int searchId) {
        MedicalRecord foundMedicalRecord = null;
        Connection conn = null;
        try {
            conn = ConnectDB.getConnectDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement preparedStatement =  conn.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, searchId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String recordId = resultSet.getString("record_id");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                String description = resultSet.getString("description");
                int patientId = resultSet.getInt("patient_id");
                foundMedicalRecord = new MedicalRecord(id,recordId,startDate,endDate,description,patientId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foundMedicalRecord;
    }
//    @Override
//    public MedicalRecord findByName(String name) {
//
//        return null;
//    }

    @Override
    public boolean add(MedicalRecord medicalRecord) {
        Connection conn = null;
        int rowEffected = 0;
        try {
            conn = ConnectDB.getConnectDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement preparedStatement =  conn.prepareStatement(SAVE);
//            preparedStatement.setInt(1, newProduct.getId());
            preparedStatement.setString(1, medicalRecord.getRecordId());
            preparedStatement.setDate(2, medicalRecord.getStartDate());
            preparedStatement.setDate(3, medicalRecord.getEndDate());
            preparedStatement.setString(4, medicalRecord.getDescription());
            preparedStatement.setInt(5, medicalRecord.getPatientId());
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
    @Override
    public boolean deleteById(int id) {
        Connection conn = null;
        int rowEffected = 0;
        try {
            conn = ConnectDB.getConnectDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement preparedStatement =  conn.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            rowEffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return rowEffected>0;
    }

    @Override
    public boolean update(MedicalRecord medicalRecord) {
        int updateId = medicalRecord.getId();
        Connection conn = null;
        int rowEffected = 0;
        try {
            conn = ConnectDB.getConnectDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement preparedStatement =  conn.prepareStatement(UPDATE);
            preparedStatement.setString(1, medicalRecord.getRecordId());
            preparedStatement.setDate(2, medicalRecord.getStartDate());
            preparedStatement.setDate(3, medicalRecord.getEndDate());
            preparedStatement.setString(4, medicalRecord.getDescription());
            preparedStatement.setInt(5, medicalRecord.getPatientId());
            preparedStatement.setInt(6, updateId);
            rowEffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
//            throw new RuntimeException(e);
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return rowEffected>0;

    }

    @Override
    public List<MedicalRecordDto> search(String searchRecordId, String searchPatientName) {
        List<MedicalRecordDto> recordDtos = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectDB.getConnectDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement preparedStatement =  conn.prepareStatement(SEARCH_DTO);
            preparedStatement.setString(1, searchRecordId);
             preparedStatement.setString(2, searchPatientName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int foundId = resultSet.getInt("id");
                String recordId = resultSet.getString("record_id");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                String description = resultSet.getString("description");
                String patientName = resultSet.getString("patient_name");
                recordDtos.add(new MedicalRecordDto(foundId,recordId,startDate,endDate,description,patientName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recordDtos;
    }
}
