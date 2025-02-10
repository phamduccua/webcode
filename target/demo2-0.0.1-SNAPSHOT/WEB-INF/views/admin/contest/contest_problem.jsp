<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/01/2025
  Time: 10:18 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/admin/problem/home.jsp" %>
<%@ include file="contest_menu.jsp" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Bài tập</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .main_problem{
            width: 1177.6px;
            margin: 30.39px auto 0;
            border: 1px solid black;
        }
        .btnadd{
            display: flex;
            justify-content: center;
            align-content: center;
            margin-left: 150px;
            margin-top: 30px;
            border-radius: 8px;
            background-color: brown;
            color: #fff;
            width: 100px;
            height: 30px;
        }
        .listProblem{
            width:  850px;
            margin: 30.39px auto 30px;
        }
        .listProblem table{
            border: 1px solid black;
            border-collapse: collapse;
            width: 100%;
        }
        .listProblem table thead th{
            border: 1px solid black;
            background-color: rgb(250, 237, 237);
            height: 40px;
            text-align: center;
        }
        .listProblem table tbody tr td{
            text-align: center;
            border: 1px solid black;
            height: 30px;
        }
        .listProblem table tbody tr td a{
            padding-left: 50px;
            padding-right: 20px;
            display: flex;
            text-align: left;
        }
        .config{
            position: relative;
            display: none;
            z-index: 1000;
        }
        .config ul{
            position: absolute;
            right: 85px;
            width: 140px;
            margin: 0 !important;
            padding: 0 !important;
            list-style: none;
            background-color: #FFFFFF;
        }
        .config li{
            padding: 5px;
            border: 1px solid black;
            margin: -1px;
            height: 40px;
        }
        .menu_add{
            position: relative;
            display: none;
        }
        .menu_add ul{
            left: 30px;
            bottom: 20px;
            position: absolute;
            list-style: none;
            width: 180px;
            padding: 0 !important;
            margin: 0 !important;
        }
        .menu_add ul li{
            background-color: #8A1111;
            width: 100%;
            border: 1px solid black;
            color: #fff;
            height: 30px;
        }
    </style>
</head>
<body>
<div class="main_problem">
    <div class="btnadd" onclick="displaymenu()">
        Thêm bài tập
    </div>
    <div class="menu_add" id="menuAdd">
        <ul >
            <li onclick="pageProblemByUser()">Sử dụng bài tập có sẵn</li>
            <li onclick="create_problem()">Tạo bài tập mới</li>
            <li onclick="closeMenu()">Đóng</li>
        </ul>
    </div>
    <div class="listProblem">
        <table>
            <thead>
                <th>STT</th>
                <th>Tên</th>
                <th>Thao tác</th>
            </thead>
            <tbody>
            <c:forEach var="item" items="${listProblem}" varStatus="status">
                <tr class="col-12">
                    <td class="col-1">${status.count}</td>
                    <td class="col-8"><a class="open" href="/admin/assignment-${item.code}" />${item.title}</td>
                    <td class="col-2">
                        <div class="box" onclick="configdisplay(this)">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="dropdown-icon bi bi-three-dots-vertical" viewBox="0 0 16 16">
                                    <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0"/>
                                </svg>
                                <div class="config" >
                                    <ul>
                                        <li onclick="doi_trang('${item.code}')">Chỉnh sửa</li>
                                        <li onclick="submission('${item.code}')">Xem các bài nộp</li>
                                        <li onclick="deleateProblem(${item.id},${contestDTO.id})">Xóa</li>
                                    </ul>
                                </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    function create_problem(){
        window.location.href = "/admin/contest-create_problem-${contestDTO.id}"
    }
    function displaymenu(){
        const item = document.getElementById('menuAdd');
        item.style.display = 'block';
    }
    function closeMenu(){
        const item = document.getElementById('menuAdd');
        item.style.display = 'none';
    }
    function pageProblemByUser(){
        window.location.href = "/admin/list-problem-by-user/contest-${contestDTO.id}"
    }

    function submission(code){
        window.location.href = "/admin/submission/problem/" + String(code);
    }
</script>
<script>
    function configdisplay(element) {
        const allMenus = document.querySelectorAll(".config");
        allMenus.forEach(menu => {
            if (menu !== element.querySelector(".config")) {
                menu.style.display = "none";
            }
        });
        const configMenu = element.querySelector(".config");
        if (configMenu.style.display === "flex") {
            configMenu.style.display = "none";
        } else {
            configMenu.style.display = "flex";
        }
        configMenu.addEventListener("click", function (event) {
            event.stopPropagation();
        });
        document.addEventListener("click", function hideMenus(event) {
            if (!element.contains(event.target)) {
                allMenus.forEach(menu => {
                    menu.style.display = "none";
                });
                document.removeEventListener("click", hideMenus);
            }
        });
    }
    function doi_trang(code){
        window.location.href = "/admin/problem_contest-edit-" + code;
    }
</script>
<script>
    function deleateProblem(id,conId){
        let data = {};
        data['contestId'] = conId;
        data['problemId'] = id;
        $.ajax({
            type: "DELETE",
            url: "/admin/delete-problem",
            data: JSON.stringify(data),
            contentType: "application/json",
            success(){
                Swal.fire({
                    title: 'Thành công!',
                    text: 'Xóa bài tập thành công.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => {
                    location.reload();
                });
            },
            error(e){
                Swal.fire({
                    title: 'Lỗi!',
                    text: e.errorMessage,
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        })
    }
</script>
</body>
</html>
