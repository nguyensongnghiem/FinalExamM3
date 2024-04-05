package service;

import model.Patient;

import java.util.List;

public interface IPatientService {
    List<Patient> getAll();
    boolean save(Patient patient);
}
