package repository;

import dto.MedicalRecordDto;
import model.MedicalRecord;

import java.util.List;

public interface IMedicalRecordRepository {
//    public List<MedicalRecord> findAll();
    public List<MedicalRecordDto> findAllDto();

    public MedicalRecord findById(int id);

//    public MedicalRecord findByName(String name);

    public boolean add(MedicalRecord newMedicalRecord);

    public boolean deleteById(int id);

    public boolean update(MedicalRecord medicalReCord);


    List<MedicalRecordDto> search(String name, String manufactor);
}
