package model;


import java.sql.Date;

public class MedicalRecord {
    private int id;
    private String recordId;
    private Date startDate;
    private Date endDate;
    private String description;
    private int patientId;

    public MedicalRecord() {
    }

    public MedicalRecord(int id, String recordId, Date startDate, Date endDate, String description, int patientId) {
        this.id = id;
        this.recordId = recordId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.patientId = patientId;
    }
    public MedicalRecord(String recordId, Date startDate, Date endDate, String description, int patientId) {
        this.recordId = recordId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.patientId = patientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
}



