package service;

import dto.MedicalRecordDto;
import model.MedicalRecord;
import repository.IMedicalRecordRepository;
import repository.MedicalRecordRepository;

import java.util.List;

public class MedicalRecordService implements IMedicalRecordService {
    @Override
    public List<MedicalRecordDto> findAllDto() {
        return productRepository.findAllDto();
    }

    private IMedicalRecordRepository productRepository = new MedicalRecordRepository();
//    @Override
//    public List<MedicalRecord> findAll() {
//        return productRepository.findAll();
//    }

    @Override
    public List<MedicalRecordDto> search(String name, String manufactor) {
        return productRepository.search(name, manufactor);
    }

    @Override
    public MedicalRecord findById(int id) {
        return productRepository.findById(id);
    }



    @Override
    public boolean add(MedicalRecord newMedicalRecord) {
        return productRepository.add(newMedicalRecord);
    }

    @Override
    public boolean deleteById(int id) {
        return productRepository.deleteById(id);
    }

    @Override
    public boolean update(MedicalRecord medicalReCord) {
        return productRepository.update(medicalReCord);
    }
}
