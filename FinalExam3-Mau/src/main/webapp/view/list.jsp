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
<head>
    <title>Home</title>
</head>

<body>
<div class="container">

    <h1>QUẢN LÝ BỆNH ÁN</h1>                    <%--   Sửa tên --%>
    <c:if test="${param.mess=='OK'}">
        <h2>Thành công !</h2>
    </c:if>
    <c:if test="${param.mess=='NOK'}">
        <h2>Thất bại !</h2>
    </c:if>


    <a href="/medicalRecord?action=add" class="btn btn-primary mb-3">Thêm mới</a>    <%--   Sửa đường dẫn--%>
    <br>

    <%--    PHẦN TÌM KIẾM    --%>
    <form action="/medicalRecord?action=search" method="post">           <%--   Sửa đường dẫn--%>
        <div class="d-flex flex-wrap">
            <div style="margin-bottom: 5px">
                <button type="submit" class="btn btn-info">
                    Tìm kiếm
                </button>
            </div>

            <%--    TIÊU CHÍ TÌM KIẾM 1--%>
            <div class="input-group" style="margin-bottom: 5px">
                <span class="input-group-text mb-3">
                    Tìm mã bệnh án
                </span>
                <input name="searchRecordId"  type="text" class="form-control mb-3 "    <%--    Đổi name --%>
                       placeholder="nhập Mã Bệnh án">
            </div>

            <%--    TIÊU CHÍ TÌM KIẾM 2--%>
            <div class="mb-3 d-flex flex-nowrap " style="margin-bottom: 5px">
                <label for="id1" class="form-label">Chọn bệnh nhân</label>
                <select name="searchPatientName" class="form-select"  id="id1">    <%--    Đổi name --%>
                    <option value="">- Chọn tất cả -</option>
                    <c:forEach var="patient" items="${patients}">                   <%--    Đổi tên biến --%>
                        <option value="${patient.getName()}">${patient.getName()}</option>      <%--    Đổi tên biến --%>
                    </c:forEach>
                </select>
            </div>
        </div>
    </form>

    <%--    TRANG HIỂN THỊ  DANH SÁCH   --%>
    <table class="table table-striped">
        <tr>
            <th>STT</th>

            <%--    Các thuộc tính của DTOs ( trừ ID)  --%>
            <th>Mã Bệnh án</th>
            <th>Ngày vào viện</th>
            <th>Ngày ra viện</th>
            <th>Mô tả bệnh</th>
            <th>Tên bệnh nhân</th>


            <th>Sửa</th>
            <th>Xóa</th>
        </tr>

        <c:forEach var="medicalRecord" items="${recordDtos}" varStatus="status">
            <tr>
                <td>${status.count}</td>


                <td>${medicalRecord.getRecordId()}</td>
                <td>${medicalRecord.getStartDate()}</td>
                <td>${medicalRecord.getEndDate()}</td>
                <td>${medicalRecord.getDescription()}</td>
                <td>${medicalRecord.getPatientName()}</td>

                    <%--   CHỨC NĂNG SỬA --%>
                <td>
                    <form action="/medicalRecord?action=edit" method="get">                                 <%--   Sửa đường dẫn--%>
                        <button name="action" value="edit" class="btn btn-warning btn-sm">Sửa</button>
                        <input type="hidden" name="editId" value="${medicalRecord.getId()}">
                    </form>
                </td>

                    <%--   CHỨC NĂNG XÓA --%>
                <td>
                    <button onclick="deleteInfo('${medicalRecord.id}','${medicalRecord.recordId}')" type="button"   <%--   Sửa tên param --%>
                            class="btn btn-danger btn-sm"
                            data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                        Xóa
                    </button>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>


    <!-- MODAL TÁC VỤ XÓA-->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/medicalRecord?action=delete" method="post">         <%--   Sửa đường dẫn--%>
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Xác nhận xóa thông tin</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Bạn có muốn xóa thông tin <span id="delete-name"></span> có Id là <span id="delete-id"></span> ?   <%--   Sửa đường dẫn--%>
                </div>
                <input name="deleteId" type="hidden" id="hidden-input-id" >
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-primary">Đồng ý</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    function deleteInfo(id, name) {
        document.getElementById("delete-id").innerHTML = id;
        document.getElementById("hidden-input-id").value = id;
        document.getElementById("delete-name").innerHTML = name;
    }
</script>
</body>
</html>
