package repository;

import model.Patient;

import java.util.List;

public interface IPatientRepository {
    List<Patient> findAll();
    boolean save(Patient patient);

}
