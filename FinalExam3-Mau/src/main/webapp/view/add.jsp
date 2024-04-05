<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nghie
  Date: 20/03/2024
  Time: 7:04 CH
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
    <form novalidate action="/medicalRecord?action=add" method="post" class="w-50 ms-5" >
        <h2>Thêm mới</h2>

        <div class="form-floating mb-3">
            <input name="recordId" type="text" id="id1"  class="form-control" placeholder="" pattern="^BA-\d{4}$" required>  <%-- Thay đổi name --%>
            <label for="id1" class="form-label">
                Mã bệnh án
            </label>
        </div>

        <div class="form-floating mb-3">
            <input name="startDate" type="date" id="id2"  class="form-control" placeholder="" required>  <%-- Thay đổi name --%>
            <label for="id2" class="form-label">
                Ngày vào viện                        <%-- Sửa--%>
            </label>
        </div>

        <div class="form-floating mb-3">
            <input name="endDate" type="date" id="id3"  class="form-control" placeholder="" required> <%-- Thay đổi name --%>
            <label for="id3" class="form-label">
                Ngày ra viện                        <%-- Sửa--%>
            </label>
        </div>

        <div class="form-floating mb-3">
            <input name="desc" type="text" id="id4"  class="form-control" placeholder="" required>     <%-- Thay đổi name --%>
            <label for="id4" class="">
                Mô tả
            </label>
        </div>

        <div class="mb-3">
            <label for="id5" class="form-label">
                Chọn bệnh nhân
            </label>
            <select name="patientId" id="id5" class="form-select" >                 <%-- Thay đổi name --%>
                <c:forEach var="patient" items="${patients}">
                    <option value="${patient.getId()}">${patient.getName()}</option>
                </c:forEach>
            </select>
        </div>
        <button class="btn btn-success" type="submit">Thêm mới</button>
    </form>
</div>


<%-- Validate input  --%>
<script>
    const form = document.querySelector("form");
    const idInput = document.querySelector("#id1");

    form.addEventListener("submit", e => {
        if (!form.checkValidity()) {
            e.preventDefault();
        }
        // if (!idInput.value.test(new RegExp("^BA-\d{4}$"))) {
        //     idInput.value.setCustomValidity("format: BA-xxxx")
        //     e.preventDefault();
        // }
        // else idInput.value.setCustomValidity("");
        form.classList.add("was-validated")
    })
</script>


</body>
</html>
