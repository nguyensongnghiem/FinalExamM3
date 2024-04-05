package service;

import model.Patient;
import repository.IPatientRepository;
import repository.PatientRepository;

import java.util.List;

public class PatientService implements IPatientService {
    private IPatientRepository manufactorRepository = new PatientRepository();
    @Override
    public List<Patient> getAll() {
        return manufactorRepository.findAll();
    }

    @Override
    public boolean save(Patient patient) {
        return manufactorRepository.save(patient);
    }


}
