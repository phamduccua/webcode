<%@ taglib prefix="f" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="home.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function saveSelection() {
        const selectValue = document.getElementById("group").value;
        sessionStorage.setItem("groupSelect", selectValue);
    }
    window.onload = function () {
        const saveValue = sessionStorage.getItem("groupSelect");
        if (saveValue) {
            document.getElementById("group").value = saveValue;
        }
    };

</script>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/11/2024
  Time: 11:44 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài tập</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="main">

    <form:form id="listForm" modelAttribute="modelSearch" method="GET">
        <label><strong>Bài tập</strong></label>
        <form:input id="search" type="search" placeholder="Tìm theo mã, tiêu đề" path="codeOrtitle"/> <br><br>
        <form:select id="group" path="group" onchange="listGroup();saveSelection()">
            <form:options items="${listGroup}" />
        </form:select><br><br>
        <table id="tableList" class="table table-fixed">
            <thead class="table-head">
            <tr>
                <th class="text-middle">STT</th>
                <th>Mã</th>
                <th>Tiêu đề</th>
                <th>Nhóm</th>
                <th>
                    <div class="dropdown">
                        <span class="dropdown-btn">Chủ đề con</span>
                        <div class="dropdown-menu-wrapper">
                            <table class="table-configuration">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>Chủ đề</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <form:checkbox path="topic" value=" "/>
                                        </td>
                                        <td>
                                            Tất cả
                                        </td>
                                    </tr>
                                    <c:forEach var="item" items="${listTopic}">
                                        <tr>
                                            <td>
                                                <c:if test="${item != null}">
                                                    <form:checkbox path="topic" value="${item}" />
                                                </c:if>
                                            </td>
                                            <td>
                                                ${item}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                </table>
                                <div class="filter-button-container">
                                    <button class="filter-button" id="filter">Lọc</button>
                                </div>
                        </div>
                        <svg xmlns="http://www.w3.org/2000/svg" width="10" height="10" fill="currentColor" class="bi bi-caret-down-fill" viewBox="0 0 16 16">
                            <path d="M7.247 11.14 2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z"/>
                        </svg>

                    </div>
                </th>
                <th class="mid">Độ khó</th>
                <th class="mid">Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${problemList}" varStatus="status">
                <tr class="table-body" style="background-color: ${item.color}">
                    <td>
                        <input type="checkbox" value="${item.id}"/>
                        ${status.count}
                    </td>
                    <td>
                        <a class="open" href="assignment-${item.code}">
                            ${item.code}
                        </a>
                    </td>
                    <td>
                        <a class="open" href="assignment-${item.code}">
                            ${item.title}
                        </a>
                    </td>
                    <td>${item.type}</td>
                    <td>${item.topic}</td>
                    <td class="mid">${item.difficul}</td>
                    <td class="mid">
                        <div class="dropdown">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="dropdown-icon bi bi-three-dots-vertical" viewBox="0 0 16 16">
                                <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0"/>
                            </svg>
                            <div class="dropdown-menu-wrapper">
                                <table class="table-configuration">
                                    <tbody>
                                    <tr>
                                        <td>
                                            <a href="detail-${item.id}">Chỉnh sửa</a>
                                        </td>
                                    </tr>
                                    <tr><td onclick="confirmDelete(${item.id})">Xóa</td></tr>
                                </tbody>
                                </table>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <form:input type="hidden" id="inPage" path="page" />
        <div class="listPage" id="page">
        </div>
    </form:form>
</div>
<script>
    var item = document.getElementById('page');
    item.innerHTML = "";
    var totalPage = ${modelSearch.totalPages};
    var page = ${modelSearch.page};
    var tmp = '';
    if(totalPage <= 3){
        for(var i = 1; i <= totalPage; i++){
            if(i === page){
                tmp += '<div class="itemPage pageChoose">' + i +'</div>'+ '\n';
            }
            else{
                tmp += '<div class="itemPage" onclick=choosePage(' + i + ')' + '>' + i + '</div>' + '\n';
            }
        }
    }
    else{
        var begin = 1;
        if(page > 3) begin = page - 2;
        var end = 3;
        if(page > 3) end = page;
        if(page > 1) tmp += '<div class="itemPage" onclick=choosePage(page-1)>«</div>' + '\n';
        for(var i = begin; i <= end; i++){
            if(i === page){
                tmp += '<div class="itemPage pageChoose">' + i +'</div>'+ '\n';
            }
            else{
                tmp += '<div class="itemPage" onclick=choosePage(' + i + ')' + '>' + i + '</div>' + '\n';
            }
        }
        if(page < totalPage) tmp += '<div class="itemPage" onclick=choosePage(page+1)>»</div>' + '\n';
    }

    item.innerHTML = tmp;
</script>
<script>
    function listGroup(){
        document.getElementById("listForm").submit();
    }
    ('#filter').click(function(e){
        e.preventDefault();
        listGroup();
    });
    function choosePage(chPage){
        const valuePage =  document.getElementById("inPage");
        valuePage.value = chPage;
        listGroup();
    }
    function confirmDelete(itemId) {
        Swal.fire({
            title: 'Bạn có chắc chắn?',
            text: 'Nếu không muốn xóa hãy nhấn hủy!',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Xóa',
            cancelButtonText: 'Hủy',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                deleteItem(itemId);
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                Swal.fire({
                    title: 'Đã hủy!',
                    text: 'Không có thay đổi nào được thực hiện.',
                    icon: 'info',
                    confirmButtonText: 'OK'
                });
            }
        });
    }

    function deleteItem(itemId) {
        $.ajax({
            type: "DELETE",
            url: "/admin/problem/delete-item/" + itemId,
            success: function () {
                Swal.fire({
                    title: 'Đã xóa!',
                    text: 'Mục đã được xóa thành công.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => {
                    location.reload();
                });
            },
            error: function () {
                Swal.fire({
                    title: 'Lỗi!',
                    text: 'Không thể xóa mục này. Vui lòng thử lại.',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        });
    }
</script>







<style>
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
        top:7px !important;
        left: 120px !important;
    }
    .button-status {
        top:7px !important;
        left: 200px !important;
    }
    .button-history {
        top:7px !important;
        left: 300px !important;
    }
    .button-rank {
        top:7px !important;
        left: 380px !important;
    }
    .button-configuration {
        top:7px !important;
        left: 510px !important;
    }
    .button-gui {
        top:7px !important;
        left: 620px !important;
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

    .logo {
        /*background-image: url("logo.webp");*/
        background-size: cover;
        position: absolute;
        width: 50px;
        height: 50px;
        border-radius: 50%;
        top: 12px;
        left: 50px;
    }
    .open{
        color:red;
    }
    a {
        text-decoration: none;
        pointer-events: auto !important;
    }
    .main {
        width: 1177.6px;
        margin: 30.39px auto 0;
        padding: 25.6px 39.19px;
        border-radius: 4px;
        border: solid 1.6px #e1e4e8;
    }
    #search {
        float: right;
        border-radius: 4px;
        border: solid 1.6px #e1e4e8;
        background-color: #FAEDED;
        width: 273.6px;
        height: 28px;
        padding-left: 8.8px;
    }
    #group {
        float: right;
        border-radius: 4px;
        border: solid 1.6px #e1e4e8;
        background-color: white;
        width: 373.6px;
        height: 28px;
        padding-left: 8.8px;
    }
    .table {
        border-spacing: 0;
        border-radius: 4px;
        /*overflow: hidden;*/
        width: 100%;
        border: 1px solid #ccc;
    }
    .dropdown-header {
        display: flex; /* Sử dụng Flexbox */
        justify-content: center; /* Căn giữa theo chiều ngang */
        align-items: center; /* Căn giữa theo chiều dọc */
        gap: 5px; /* Khoảng cách giữa chữ và biểu tượng */
        height: 100%; /* Chiều cao bằng với thẻ <th> */
    }

    .table-head {
        background-color: #8A1111;
        font-size: 16px;
        font-weight: bold;
        color: #FFFFFF;
    }

    .table-head th {
        text-align: left; /* Căn chữ vào giữa theo chiều ngang */
        vertical-align: middle; /* Căn chữ vào giữa theo chiều dọc */
        height: 50px; /* Tăng chiều cao của thẻ <th> */
        padding: 10px; /* Khoảng cách bên trong */
        font-size: 16px; /* Kích thước chữ */
        font-weight: bold; /* Đậm chữ */
        color: #FFFFFF; /* Màu chữ */
        background-color: #8A1111; /* Màu nền */
    }
    .table-head .mid{
        text-align: center;
    }
    .table-body td {
        text-align: left;
        padding: 10px;
        border-bottom: 1px solid #ccc;
    }
    .table-body .mid{
        text-align: center;
    }
    /* Giảm kích thước ô STT */
    .text-middle {
        width: 40px; /* Hoặc tùy chỉnh kích thước nhỏ hơn nếu cần */
        min-width: 40px;
        padding: 0;
        text-align: center;
    }
    /* Dropdown container */
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
        border : 1px solid black;
        border-collapse: collapse;
        width: 100%;
    }
    .table-configuration th{
        border : 1px solid black;
        background-color: #FFFFFF;
        color : black;
    }
    .table-configuration td {
        border : 1px solid black;
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
        border : 1px solid black;
        background-color : forestgreen;
        padding: 5px 15px;
        cursor: pointer;
        border-radius: 5px;
    }
    .filter-button:hover {
        background-color: #e0e0e0; /* Hiệu ứng khi hover */
    }
    .listPage{
        color : red;
        display: flex;
        justify-content: center;
    }
    .itemPage {
        display : inline-flex;
        border : 1px solid black;
        padding-left: 8px;
        padding-right : 8px;
    }
    .pageChoose{
        background-color: #bb2019;
        color : #FFFFFF;
    }
</style>
</body>
</html>
