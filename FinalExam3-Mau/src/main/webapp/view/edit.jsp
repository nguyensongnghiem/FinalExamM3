<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nghie
  Date: 18/03/2024
  Time: 8:56 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/bootstrap.bundle.min.js"></script>
</head>
<html>

<body>
<div class="container">
    <form action="/medicalRecord?action=update" method="post" class="w-50 ms-5">
        <h2>SỬA THÔNG TIN</h2>

        <div class="form-floating mb-3">
            <input name="id"  type="text" id="id1" class="form-control" placeholder=""
                   value="${medicalRecord.getId()}" readonly>               <%--    Đổi tên biến --%>
            <label for="id1">
                Id bệnh án
            </label>
        </div>

        <div class="form-floating mb-3">
            <input name="recordId" type="text" id="id2"  class="form-control" placeholder="" <%--    Đổi tên biến --%>
                   value="${medicalRecord.getRecordId()}" required>
            <label for="id2" class="form-label">
                Mã Bệnh án
            </label>
        </div>

        <div class="form-floating mb-3">
            <input name="startDate" type="date" id="id3"  class="form-control" placeholder="" <%--    Đổi tên biến --%>
                   value="${medicalRecord.getStartDate()}" required>
            <label for="id3" class="form-label">
                Ngày vào viện
            </label>
        </div>
        <div class="form-floating mb-3">
            <input name="endDate" type="date" id="id4"  class="form-control" placeholder=""   <%--    Đổi tên biến --%>
                   value="${medicalRecord.getEndDate()}" required>
            <label for="id4" class="form-label">Ngày ra viện</label>
        </div>

        <div class="form-floating mb-3">
            <input name="desc" type="text" id="id5"  class="form-control" placeholder=""  <%--    Đổi tên biến --%>
                   value="${medicalRecord.getDescription()}" required>
            <label for="id5" class="form-label">
                Mô tả
            </label>
        </div>

        <div class="mb-3">
            <label for="id6" class="form-label">Tên bệnh nhân</label>
            <select name="patientId" id="id6" value="">                      <%--    Đổi tên biến --%>
                <c:forEach var="patient" items="${patients}">
                    <option value="${patient.getId()}">${patient.getName()}</option>
                </c:forEach>
            </select>
        </div>

        <button class="btn btn-success" type="submit">Cập nhật</button>
    </form>
</div>
<script>
    const form = document.querySelector("form");
    form.addEventListener("submit", e => {
        if (!form.checkValidity()) {
            e.preventDefault();
        }
        form.classList.add("was-validated")
    })
</script>
</body>
</html>
