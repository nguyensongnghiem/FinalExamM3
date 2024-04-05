package dto;

import java.util.Date;

public class MedicalRecordDto {

    private int id;
    private String recordId;
    private Date startDate;
    private Date endDate;
    private String description;
    private String patientName;

    public MedicalRecordDto(int id, String recordId, Date startDate, Date endDate, String description, String patientName) {
        this.id = id;
        this.recordId = recordId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.patientName = patientName;
    }

    public MedicalRecordDto() {
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

    public Date getStartDate() {
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}



