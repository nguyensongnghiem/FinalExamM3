package controller;


import dto.MedicalRecordDto;
import model.MedicalRecord;
import model.Patient;
import service.IMedicalRecordService;
import service.IPatientService;
import service.MedicalRecordService;
import service.PatientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "Servlet", value = "/FinalExam")
public class Servlet extends HttpServlet {
    private IMedicalRecordService medicalRecordService = new MedicalRecordService();
    private IPatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add":
                showAddForm(req, resp);
                break;
            case "delete":
                deleteById(req, resp);
                break;
            case "edit":
                showEditForm(req,resp);
                break;
            case "search":
                search(req,resp);
                break;
            case "list":
                showList(req, resp);
                break;
            default:
                showList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add":
                add(req, resp);
                break;
            case "delete":
                deleteById(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "search":
                search(req,resp);
                break;
            case "list":
                showList(req, resp);
                break;
            default:
                showList(req, resp);
        }
    }

    // SHOW FORM
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int editId = Integer.parseInt(req.getParameter("editId"));
        MedicalRecord medicalRecord = medicalRecordService.findById(editId);
        req.setAttribute("medicalRecord", medicalRecord);
        List<Patient> patients = patientService.getAll();
        req.setAttribute("patients", patients);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/edit.jsp");
        requestDispatcher.forward(req,resp);
    }
    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Patient> patients = patientService.getAll();
        req.setAttribute("patients", patients);
        RequestDispatcher rd = req.getRequestDispatcher("/view/add.jsp");
        rd.forward(req,resp);
    }
    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Truyền list chính lên form
        List<MedicalRecordDto> recordDtos = medicalRecordService.findAllDto();
        req.setAttribute("recordDtos", recordDtos);

        // Truyền list phụ lên form để hiển thị ds khi tìm kiếm
        List<Patient> patients = patientService.getAll();
        req.setAttribute("patients", patients);

        // FW đến trang list.jsp
        RequestDispatcher rd = req.getRequestDispatcher("/view/list.jsp");
        rd.forward(req, resp);
    }

    // DO THINGS
    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String recordId = req.getParameter("recordId");
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date endDate = Date.valueOf(req.getParameter("endDate"));
        String desc = req.getParameter("desc");
        int patientId = Integer.parseInt(req.getParameter("patientId"));
        boolean isOk = medicalRecordService.update(new MedicalRecord(id, recordId, startDate,endDate, desc, patientId));
        String message = isOk?"OK":"NOK";
        resp.sendRedirect("/medicalRecord?action=list&mess="+message);

    }
    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchRecordId = req.getParameter("searchRecordId");
        String searchPatientName = req.getParameter("searchPatientName");

        // Tìm DTOs thỏa mãn Seach
        List<MedicalRecordDto> recordDtos = medicalRecordService.search(searchRecordId,searchPatientName);
        req.setAttribute("recordDtos", recordDtos);

        // Lấy thông tin về ds trong bảng phụ
        List<Patient> patients = patientService.getAll();
        req.setAttribute("patients", patients);

        // FW thông tin đến trang list
        RequestDispatcher rd = req.getRequestDispatcher("/view/list.jsp");
        rd.forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String recordId = req.getParameter("recordId");
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date endDate = Date.valueOf(req.getParameter("endDate"));
        String desc = req.getParameter("desc");
        int patientId = Integer.parseInt(req.getParameter("patientId"));
        boolean isOk = medicalRecordService.add(new MedicalRecord(recordId, startDate, endDate,desc, patientId));
        String message = isOk?"OK":"NOK";
        resp.sendRedirect("/medicalRecord?action=list&mess="+message);
    }
    private boolean deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        System.out.println(req.getParameter("deleteId"));
        int deleteId = Integer.parseInt(req.getParameter("deleteId"));
        resp.sendRedirect("/medicalRecord?action=list");
        return medicalRecordService.deleteById(deleteId);
    }
}
