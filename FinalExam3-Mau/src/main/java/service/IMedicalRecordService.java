package service;

import dto.MedicalRecordDto;
import model.MedicalRecord;

import java.util.List;

public interface IMedicalRecordService {
//    public List<MedicalRecord> findAll();
    public List<MedicalRecordDto> findAllDto();
    public List<MedicalRecordDto> search(String medicalRecord, String patientName);

    public MedicalRecord findById(int id);


    public boolean add(MedicalRecord newMedicalRecord);

    public boolean deleteById(int id);

    public boolean update(MedicalRecord medicalReCord);

}
