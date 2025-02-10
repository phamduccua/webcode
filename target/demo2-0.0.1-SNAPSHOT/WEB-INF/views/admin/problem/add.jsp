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
<%@ include file="/WEB-INF/views/admin/problem/home.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Tạo bài tập</title>
    <style>
        .main {
            backdrop-filter: blur(10px);
            -webkit-backdrop-filter: blur(10px);
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
        .buttonadd {
            background-color:rgb(0, 255, 238);
            color: white;
            font-size: 16px;
            font-weight: bold;
            padding: 10px 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease;
        }
        .buttonadd:hover {
            background-color: limegreen;
            box-shadow: 0 6px 8px rgba(0, 0, 0, 0.2);
            transform: translateY(-2px);
        }
        .buttonadd:active {
            background-color: forestgreen;
            box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.2);
            transform: translateY(1px);
        }
        select{
            height:25px;
        }
        .checkbox-item {
            align-items: center;
            margin-bottom: 10px;
            margin-right: 5px;
            margin-left: 20px;
        }
        #btnupload{
            width: 100px;
            height: 30px;
            margin-left: 20px;
        }
        #menu_upload {
            display: none;
            position: absolute;
            width: 250px;
            height: 300px;
            top: 350px;
            left: 50%;
            transform: translate(-50%, -50%);
            border: 1px solid black;
            background-color: #ffffff;
            z-index: 1000;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
            padding: 20px;
            text-align: center;
            /*align-content: center;*/
        }
        .file_upload{
            display: none;
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
            <button id="btnupload">Chọn tệp</button>
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
            <label class="label">Ngôn ngữ</label>
            <form:checkboxes items="${listLanguages}" path="language" cssClass="checkbox-item"/>
        </div>
        <div>
            <label for="code" class="label">Giới hạn thời gian</label>
            <form:input class="text" type="number" id="code" placeholder="Nhập giới hạn thời gian" path="time_limit"/>
        </div>
        <div>
            <label for="code" class="label">Giới hạn bộ nhớ</label>
            <form:input class="text" type="number" id="code" placeholder="Nhập giới hạn bộ nhớ" path="memory_limit"/>
        </div>
        <button class="buttonadd" id="addProblem">Tạo bài tập</button>
    </form:form>
    <div class="upload" id="menu_upload">
        <div id="file_name" style="border: 1px solid black; margin-bottom: 20px;">Chưa có file nào</div>
        <input type="file" class="file_upload" id="upload">
        <label for="upload" style="border: 1px solid black;">Chọn tệp</label>
        <button id="up">Upload</button>
        <button id="close">Đóng</button>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    var listImages = [];
    $('#btnupload').click(function(e){
        e.preventDefault();
        const upload = document.getElementById('menu_upload');
        upload.style.display = 'block';
    });
    $('#close').click(function(e){
        e.preventDefault();
        const upload = document.getElementById('menu_upload');
        upload.style.display = 'none';
    });
    $('#up').click(function(e){
        e.preventDefault();
        var fileUpload = $('#upload')[0].files[0];
        if (!fileUpload) {
            alert('Vui lòng chọn một tệp');
            return;
        }
        else{
            listImages.push(fileUpload);
            document.getElementById('problem_statement').value = document.getElementById('problem_statement').value + "\n![image](" + fileUpload.name + ")";
            document.getElementById('upload').value = "";
            document.getElementById('file_name').textContent = 'Chưa có file nào';
        }

    });
    const fileInput = document.getElementById('upload');
    const fileNameDiv = document.getElementById('file_name');
    fileInput.addEventListener('change', function () {
        fileNameDiv.textContent = fileInput.files.length > 0 ? fileInput.files[0].name : 'Chưa có file nào';
    });
    async function edit_debai(debai) {
        var arr = debai.split("\n");
        var fileName = [];
        for (let i of arr) {
            if (i.includes("image")) {
                fileName.push(i);
            }
        }
        for (let i of listImages) {
            let t = "![image](" + i.name + ")";
            if (i && i.name && fileName.some(name => t.includes(name))) {
                const name = i.name;
                var formData = new FormData();
                formData.append("file", i);
                await $.ajax({
                    type: "POST",
                    url: "/admin/problem/upload/images",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        debai = debai.replace(`![image](` + name + `)`, `![image](` + response + `)`);
                    },
                    error: function () {
                        alert("Tải lên thất bại");
                    }
                });
            }
        }
        return debai;
    }

    $('#addProblem').click(async function (e) {
        e.preventDefault();
        let tmp = document.getElementById('problem_statement').value;
        tmp = await edit_debai(tmp);
        document.getElementById('problem_statement').value = tmp;
        var data = {};
        var language = [];
        var formData = $('#listForm').serializeArray();

        $.each(formData, function (i, v) {
            if (v.name !== 'language') {
                data[v.name] = v.value;
            } else {
                language.push(v.value);
            }
        });
        data['language'] = language;
        var code = data['code'];
        $.ajax({
            type: "POST",
            url: "/admin/problem",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function () {
                Swal.fire({
                    title: 'Thành công!',
                    text: 'Yêu cầu của bạn đã được xử lý.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => {
                    window.location.href = "/admin/detail-" + code;
                });
            },
            error: function (xhr) {
                let errorMessage = "Mã bài tập đã có trước đó. Vui lòng chọn mã khác !";
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
