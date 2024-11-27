<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 24/11/2024
  Time: 10:45 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="menu-edit.jsp" %>
<html>
<head>
    <title>Test Case</title>
    <meta charset="UTF-8">
    <style>
        #home {
            background-color: #8A1111;
        }

        .main {
            width: 1178px; /* Làm tròn giá trị */
            margin: 30px auto 0;
            padding: 26px 39px;
            border-radius: 4px;
            border: 1.6px solid #e1e4e8;
            text-align: left;
        }

        .labelTitle {
            color: white;
            font-size: 30px;
            margin-bottom: 20px;
            display: block;
        }

        .menu {
            background-color: white;
            width: 800px;
            border-radius: 4px;
            border: 1.6px solid #e1e4e8;
            text-align: left;
        }

        .chose {
            background-color: aquamarine;
            color: black;
            border: 1px solid #ccc;
            text-decoration: none;
            margin: 10px;
            display: inline-block;
            border-radius: 4px;
            padding: 2px;
        }

        .form-group > div {
            margin-bottom: 20px;
            display: flex;
        }

        .label {
            font-size: 16px;
            width: 150px;
            text-align: left;
            margin-right: 30px;
        }
        .text {
            width: 400px;
            height:30px;
            padding: 8px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .textarea{
            width: 700px;
            height: 300px;
            padding: 8px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .button {
            background-color:rgb(0, 255, 238); /* Màu nền */
            color: white; /* Màu chữ */
            font-size: 16px; /* Kích thước chữ */
            font-weight: bold; /* In đậm chữ */
            padding: 10px 20px; /* Khoảng cách bên trong nút */
            border: none; /* Loại bỏ viền mặc định */
            border-radius: 8px; /* Bo tròn góc nút */
            cursor: pointer; /* Hiển thị biểu tượng tay khi hover */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
            transition: all 0.3s ease; /* Hiệu ứng mượt khi thay đổi */
            margin: 20px;
        }

        /* Hiệu ứng khi di chuột qua nút */
        .button:hover {
            background-color: limegreen; /* Đổi màu nền khi hover */
            box-shadow: 0 6px 8px rgba(0, 0, 0, 0.2); /* Tăng đổ bóng */
            transform: translateY(-2px); /* Dịch lên nhẹ */
        }

        /* Hiệu ứng khi nhấn nút */
        .button:active {
            background-color: forestgreen; /* Màu tối hơn khi nhấn */
            box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.2); /* Hiệu ứng lõm */
            transform: translateY(1px); /* Dịch xuống nhẹ */
        }
        body {
            font-family: "Times New Roman", Times, serif !important;
        }
    </style>
</head>
<body>
<div class="main">
    <form:form class="form-group" id="listForm" modelAttribute="problemEdit" method="GET">
        <div>
            <label for="code" class="label">Mã bài tập</label>
            <form:input class="text" type="text" id="code" placeholder="Nhập mã bài tập" path="code"/>
        </div>
        <div>
            <label for="title" class="label">Tên bài tập</label>
            <form:input class="text" type="text" id="title" placeholder="Nhập tên bài tập" path="title"/>
        </div>
        <div>
            <label for="problem_statement" class="label">Đề bài</label>
            <form:textarea class="textarea" id="problem_statement" placeholder="Nhập đề bài" path="description"></form:textarea>
        </div>
        <div>
            <labe class="label">Độ khó</labe>
            <form:select path="difficulty">
                <form:options items="${listDifficulty}"/>
            </form:select>
        </div>
        <div>
            <label for="input" class="label">Đầu vào</label>
            <form:textarea class="textarea" id="input" placeholder="Nhập đầu vào" path="inputFormat"></form:textarea>
        </div>
        <div>
            <label for="constraints" class="label">Giới hạn bài toán</label>
            <form:textarea class="textarea" id="constraints" placeholder="Nhập giới hạn đề bài" path="constraints"></form:textarea>
        </div>
        <div>
            <label for="ouput" class="label">Đầu ra</label>
            <form:textarea class="textarea" id="ouput" placeholder="Nhập đầu ra" path="outputFormat"></form:textarea>
        </div>
        <div>
            <label for="type" class="label">Nhóm</label>
            <form:input class="text" type="text" id="type" placeholder="Nhập nhóm bài tập" path="type"/>
        </div>
        <div>
            <label for="topic" class="label">Chủ đề</label>
            <form:input class="text" type="text" id="topic" placeholder="Nhập nhóm bài tập" path="topic"/>
        </div>
        <div>
            <label class="label">Lớp</label>
            <form:select path="group">
                <form:options items="${listGroup}" />
            </form:select>
        </div>
        <form:hidden path="id" id="problemId"/>
        <button class="button" id="updateProblem">Cập nhật</button>
    </form:form>
</div>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $('#updateProblem').click(function(e){
        e.preventDefault();
        var data = {};
        var formData = $('#listForm').serializeArray();

        $.each(formData, function(i, v){
            data[v.name] = v.value;
        });

        $.ajax({
            type:"POST",
            url: "/admin/problem/",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function () {
                Swal.fire({
                    title: 'Thành công!',
                    text: 'Yêu cầu của bạn đã được xử lý.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => {
                    location.reload();
                });
            },
            error: function (e) {
                Swal.fire({
                    title: 'Lỗi!',
                    text: e,
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        });
    });
</script>
</body>
</html>
