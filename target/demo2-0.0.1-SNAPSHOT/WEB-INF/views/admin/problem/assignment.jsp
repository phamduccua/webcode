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
    <style>
        .main {
            position: relative;
            width: 800px;
            margin: 30.39px auto 0;
            padding: 25.6px 39.19px;
            border-radius: 4px;
            background-color: #FEF9F9 !important;
            text-align: left;
        }
        .detail {
            position: relative;
            width: 700px;
            margin: 30.39px auto 0;
            padding: 25.6px 39.19px;
            border-radius: 4px;
            background-color: #FFFF;
            text-align: left;
        }
        .inputFile_value{
            border: 1px solid black;
            background-color: #FFFF;
            width:300px;
            height:20px;
            border-radius: 4px;
            padding:4px;
        }
        .inputFile_browser{
            float: right;
            width:50px;
            height:20px;
            border: 1px solid black;
            background-color: #fdedf6;
            text-align: center;
            border-radius: 4px;
        }
        .form-container {
            display:inline;
            align-items: center; /* Căn giữa theo chiều dọc */
            gap: 10px; /* Khoảng cách giữa các phần tử */
            display: flex;             /* Đặt các phần tử ngang hàng */
            flex-wrap: nowrap;
        }
        select {
            width:80px;
            height:30px;
            padding: 5px;
            border-radius: 4px;
            margin-right:230px;
        }
        button{
            float: right;
            background-color: #bb2019;
            color:#FFFF;
            border-radius: 4px;
            width:154.32px;
            height:37.35px;
        }
        h2{
            position:relative;
            text-align: center;
        }
        table.table {
            width: 100%;
            border-collapse: collapse;
        }
        table.table th{
            height:80px;
            text-align: center;
            background-color:#FAEDED;
        }
        table.table td{
            text-align: center;
            background-color: #F1ECEC;
        }
        .head {
            width: 100%;
            height: 60px;
            box-sizing: border-box;
            background-color: #8B1A1A;
        }
        .button {
            color: white;
            position: absolute;
            top:18px;
            border: none;
            padding: 10px 20px;
            text-align: center;
        }
        .button-exam {
            left: 120px;
        }
        .button-status {
            left: 200px;
        }
        .button-history {
            left: 300px;
        }
        .button-rank {
            left: 380px;
        }
        .button-configuration{
            left: 510px;
        }
        .button-gui {
            left: 620px;
        }
        .avatar {
            background-color: white;
            width: 40px;
            height: 40px;
            border-radius: 50%;
            position: absolute;
            top: 15px;
            right: 40px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .img {
            width: 70%;
            height: 70%;
        }
        .dropdown {
            position: relative; /* Định vị tương đối */
            display: inline-block;
            overflow: visible;
        }

        /* Nút "Cấu hình" */
        .dropdown-btn {
            cursor: pointer; /* Biểu tượng trỏ chuột khi hover */
            color: white; /* Màu chữ */
            font-weight: bold;
        }
        .dropdown-icon {
            cursor: pointer; /* Trỏ chuột khi hover vào icon */
        }

        /* Ẩn menu mặc định */
        /* Định vị lại menu để không bị che khuất */
        .dropdown-menu-wrapper {
            display: none; /* Ẩn menu mặc định */
            position: absolute; /* Định vị menu thả xuống */
            top: 100%; /* Hiển thị ngay bên dưới icon */
            right: 0; /* Căn lề phải để tránh tràn */
            background-color: white; /* Nền trắng */
            border: 1px solid #ccc; /* Viền bảng */
            border-radius: 4px; /* Bo góc */
            z-index: 1000; /* Đảm bảo menu nằm trên cùng */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Đổ bóng */
            overflow-y: auto;
            width: max-content; /* Kích thước vừa với nội dung */
        }

        .dropdown:hover .dropdown-menu-wrapper {
            display: block;
        }


        /* Table configuration */
        .table-configuration {
            border-collapse: collapse;
            width: 100%;
        }

        .table-configuration td {
            padding: 10px;
            text-align: left; /* Căn chữ sang trái */
            cursor: pointer;
            color: black;
        }

        /* Hiệu ứng hover trên từng dòng */
        .table-configuration tr:hover td {
            background-color: #f0f0f0; /* Highlight row */
        }
        /* Thêm thanh cuộn cho checkbox */
        .scrollable-checkboxes {
            max-height: 100px; /* Chiều cao tối đa của danh sách checkbox */
            overflow-y: auto; /* Thêm thanh cuộn dọc */
            border: 1px solid #ccc; /* Đường viền để phân biệt danh sách */
            padding: 10px; /* Khoảng cách bên trong */
        }

        /* Căn chỉnh nút Lọc xuống dưới cùng */
        .filter-button-container {
            text-align: center; /* Căn nút Lọc vào giữa */
            margin-top: 10px; /* Khoảng cách giữa danh sách và nút */
        }

        .filter-button {
            padding: 5px 15px; /* Kích thước nút */
            background-color: #f5f5f5; /* Màu nền */
            border: 1px solid #ccc; /* Đường viền */
            cursor: pointer; /* Con trỏ chuột */
            border-radius: 5px; /* Bo góc */
        }
        .filter-button:hover {
            background-color: #e0e0e0; /* Hiệu ứng khi hover */
        }
        a {
            text-decoration: none;
            pointer-events: auto !important;
        }
        .detail p {
            white-space: pre-wrap;
        }
        .example{
            width: 100%;
            border-collapse: collapse;
        }
        table.example th{
            height:30px;
            text-align: left;
            border: 1px solid black;
        }
        table.example td{
            text-align: left;
            border: 1px solid black;
        }
        #fileInput{
            display:none;
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
        <p>Giới hạn bộ nhớ: ${detail.memory_limit}Kb</p>
    <div>
        <form action="/uploads/file" method="post" enctype="multipart/form-data">
            <div class="form-container">
                <p>Trình biên dịch</p>
                <select>
                    <option>C/C++</option>
                </select>
                <p class="inputFile_value" onclick="upload()">Chọn tệp</p>
                <p class="inputFile_browser" onclick="upload()">Duyệt</p>
                <input type="file" name="code_file" id="fileInput" accept=".c,.cpp,.java,.py"/>
            </div>
            <button type="submit">Nộp bài</button> <br> <br>
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
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function upload(){
        $('#fileInput').click();
    }
</script>
</body>
</html>
