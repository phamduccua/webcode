<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 26/11/2024
  Time: 11:54 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="home.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đề bài</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .main {
            position: relative !important;
            width: 950px !important;
            margin: 30.39px auto 0 !important;
            padding: 25.6px 39.19px !important;
            border-radius: 4px !important;
            background-color: #FEF9F9 !important;
            text-align: left !important;
        }

        .detail {
            position: relative !important;
            width: 850px !important;
            margin: 30.39px auto 0 !important;
            padding: 25.6px 39.19px !important;
            border-radius: 4px !important;
            background-color: #FFFF !important;
            text-align: left !important;
        }

        .inputFile_value {
            border: 1px solid black !important;
            background-color: #FFFF !important;
            width: 350px !important;
            height: 33px !important;
            border-radius: 4px !important;
            padding: 4px !important;
        }

        .inputFile_browser {
            float: right !important;
            width: 50px !important;
            height: 31px !important;
            border: 1px solid black !important;
            background-color: #fdedf6 !important;
            text-align: center !important;
            border-radius: 4px !important;
        }

        .form-container {
            display: inline !important;
            align-items: center !important;
            gap: 10px !important;
            display: flex !important;
            flex-wrap: nowrap !important;
        }

        select {
            width: 80px !important;
            height: 35px !important;
            padding: 5px !important;
            border-radius: 4px !important;
            margin-right: 250px !important;
            text-align: center !important;
        }

        button {
            float: right !important;
            background-color: #bb2019 !important;
            color: #FFFF !important;
            border-radius: 4px !important;
            width: 154.32px !important;
            height: 37.35px !important;
        }

        h2 {
            position: relative !important;
            text-align: center !important;
        }

        table.table {
            width: 100% !important;
            border-collapse: collapse !important;
            text-align: center !important;
        }

        table.table th {
            height: 40px !important;
            text-align: center !important;
            background-color: #FAEDED !important;
            top: 10px !important;
        }

        table.table td {
            text-align: center !important;
            background-color: #F1ECEC !important;
        }

        .head {
            justify-content: center !important;
            width: 100% !important;
            height: 60px !important;
            box-sizing: border-box !important;
            background-color: #8B1A1A !important;
        }

        .button {
            color: white !important;
            position: absolute !important;
            top: 18px !important;
            border: none !important;
            padding: 10px 20px !important;
            text-align: center !important;
        }

        .button-exam {
            top: 7px !important;
            left: 120px !important;
        }

        .button-status {
            top: 7px !important;
            left: 200px !important;
        }

        .button-history {
            top: 7px !important;
            left: 300px !important;
        }

        .button-rank {
            top: 7px !important;
            left: 380px !important;
        }

        .button-configuration {
            top: 7px !important;
            left: 510px !important;
        }

        .button-gui {
            top: 7px !important;
            left: 620px !important;
        }

        .avatar {
            background-color: white !important;
            width: 40px !important;
            height: 40px !important;
            border-radius: 50% !important;
            position: absolute !important;
            top: 15px !important;
            right: 40px !important;
            display: flex !important;
            justify-content: center !important;
            align-items: center !important;
        }

        .img {
            width: 70% !important;
            height: 70% !important;
        }

        .dropdown {
            position: relative !important;
            display: inline-block !important;
            overflow: visible !important;
        }

        .dropdown-btn {
            cursor: pointer !important;
            color: white !important;
            font-weight: bold !important;
        }

        .dropdown-icon {
            cursor: pointer !important;
        }

        .dropdown-menu-wrapper {
            display: none !important;
            position: absolute !important;
            top: 100% !important;
            right: 0 !important;
            background-color: white !important;
            border: 1px solid #ccc !important;
            border-radius: 4px !important;
            z-index: 1000 !important;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2) !important;
            overflow-y: auto !important;
            width: max-content !important;
        }

        .dropdown:hover .dropdown-menu-wrapper {
            display: block !important;
        }

        .table-configuration {
            border-collapse: collapse !important;
            width: 100% !important;
        }

        .table-configuration td {
            padding: 10px !important;
            text-align: left !important;
            cursor: pointer !important;
            color: black !important;
        }

        .table-configuration tr:hover td {
            background-color: #f0f0f0 !important;
        }

        .scrollable-checkboxes {
            max-height: 100px !important;
            overflow-y: auto !important;
            border: 1px solid #ccc !important;
            padding: 10px !important;
        }

        .filter-button-container {
            text-align: center !important;
            margin-top: 10px !important;
        }

        .filter-button {
            padding: 5px 15px !important;
            background-color: #f5f5f5 !important;
            border: 1px solid #ccc !important;
            cursor: pointer !important;
            border-radius: 5px !important;
        }

        .filter-button:hover {
            background-color: #e0e0e0 !important;
        }

        a {
            text-decoration: none !important;
            pointer-events: auto !important;
        }

        .detail p {
            white-space: pre-wrap !important;
        }

        .example {
            width: 100% !important;
            border-collapse: collapse !important;
        }

        table.example th {
            height: 30px !important;
            text-align: left !important;
            border: 1px solid black !important;
        }

        table.example td {
            text-align: left !important;
            border: 1px solid black !important;
        }

        #fileInput {
            display: none !important;
        }

        p {
            margin: 0px !important;
        }

    </style>
</head>
<body>
<div class="main">
    <label style=" font-size: 17px; color: #bb2019;"><strong>${detail.title}</strong></label>
    <label style="float: right; font-size: 17px; color: #bb2019;"><strong>Bài làm tốt nhất</strong></label>
        <div class="detail">
            <div>
                <p>${detail.description}</p>
            </div>
            <div>
                <label><strong>Input</strong></label>
                <p>${detail.inputFormat}</p>
            </div>
            <c:if test="${not empty detail.constraints}" >
                <div>
                    <label><strong>Giới hạn</strong></label>
                    <p>${detail.constraints}</p>
                </div>
            </c:if>
            <div>
                <label><strong>Output</strong></label>
                <p>${detail.outputFormat}</p>
            </div>
            <c:if test="${not empty listTest and listTest.size() > 0}">
    <div>
        <p><strong>Ví dụ</strong></p>
        <table class="example">
            <thead>
                <tr>
                    <th>Input</th>
                    <th>Output</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${listTest}">
                    <tr>
                        <td>
                            <p>${item.input}</p>
                        </td>
                        <td>
                            <p>${item.expected_output}</p>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
</div>
        <p>Giới hạn bài tập: ${detail.time_limit}s</p>
        <p>Giới hạn bộ nhớ: ${detail.memory_limit}Kb</p> <br>
    <div>
        <form id="uploadForm" enctype="multipart/form-data">
            <div class="form-container">
                <p>Trình biên dịch</p>
                <select id="selectPogram" name="programingLanguage">
                    <c:forEach var="item" items="${listLanguage}" >
                        <option value="${item}" >${item}</option>
                    </c:forEach>
                </select>

                <p class="inputFile_value" onclick="upload()">Chọn tệp</p>
                <p class="inputFile_browser" onclick="upload()">Duyệt</p>
                <input type="file" name="file" id="fileInput" accept=".c,.cpp,.java,.py"/>
            </div>
            <button type="submit" style="margin-top:18px;">Nộp bài</button> <br> <br>
        </form>
        <div>
            <h2>Lịch sử</h2>
            <div>
                <table class="table">
                    <thead>
                    <th>ID</th>
                    <th>Thời gian</th>
                    <th>Bài tập</th>
                    <th>Kết quả</th>
                    <th>Thời gian</th>
                    <th>Bộ nhớ</th>
                    <th>Trình biên dịch</th>
                    </thead>
                    <tbody>
                    <td>9568444</td>
                    <td>
                        <p>2024-08-12</p>
                        <p>11:58:05</p>
                    </td>
                    <td style="color: #bb2019;">Hello World</td>
                    <td>AC</td>
                    <td>0.01s</td>
                    <td>1528Kb</td>
                    <td>C/C++</td>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <input type="hidden" id="id" value="${detail.id}" />
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    function upload(){
        $('#fileInput').click();
    }
    $('#fileInput').change(function() {
        var fileName = $(this).val().split('\\').pop();
        $('.inputFile_value').text(fileName ? 'C:\\fakepath\\' +  fileName : 'Chọn tệp');
    });
</script>
<script>
    $(document).ready(function() {
        $('#uploadForm').on('submit', function(e) {
            e.preventDefault();

            var formData = new FormData();
            var fileInput = $('#fileInput')[0].files[0];
            var language = $('#selectPogram').val();
            var id = $('#id').val();
            formData.append('file', fileInput);
            formData.append('language',language);
            formData.append('id', id);
            $.ajax({
                url: '/uploads/file',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
                    console.log("Thành công");
                    window.location.href = '/admin/history';
                },
                error: function(xhr, status, error) {
                    Swal.fire({
                        title: 'Lỗi!',
                        text: "File không hợp lệ !!! Vui lòng kiểm tra lại !!!",
                        icon: 'error',
                        confirmButtonText: 'OK'
                    });
                }
            });
        });
    });

</script>
</body>
</html>
