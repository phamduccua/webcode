<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 23/11/2024
  Time: 9:19 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tạo bài tập</title>
    <style>
        .main {
            position: relative;
            width: 1177.6px;
            margin: 30.39px auto 0;
            padding: 25.6px 39.19px;
            border-radius: 4px;
            border: solid 1.6px #e1e4e8;
            text-align: center;
        }
        .labelTitle {
            font-size: 30px;
            margin-bottom: 20px;
            display: block;
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
        select{
            height:25px;
        }
    </style>
</head>
<body>
<div class="main">
    <label class="labelTitle">Tạo bài tập</label>
    <form:form class="form-group" id="listForm" modelAttribute="problemAdd" method="GET">
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
        <div>
            <label for="code" class="label">Giới hạn thời gian</label>
            <form:input class="text" type="number" id="code" placeholder="Nhập giới hạn thời gian" path="time_limit"/>
        </div>
        <div>
            <label for="code" class="label">Giới hạn bộ nhớ</label>
            <form:input class="text" type="number" id="code" placeholder="Nhập giới hạn bộ nhớ" path="memory_limit"/>
        </div>
        <button class="button" id="addProblem">Tạo bài tập</button>
    </form:form>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    $('#addProblem').click(function(e){
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
            dataType: "JSON",
            success: function (response) {
                Swal.fire({
                    title: 'Thành công!',
                    text: 'Yêu cầu của bạn đã được xử lý.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => {
                    window.location.href = "/admin/detail-" + response;
                });
            },
            error: function (xhr) {
                let errorMessage = "Đã xảy ra lỗi không xác định.";
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    errorMessage = xhr.responseJSON.message;
                }
                Swal.fire({
                    title: 'Lỗi!',
                    text: errorMessage,
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        });
    });
</script>
</body>
</html>
