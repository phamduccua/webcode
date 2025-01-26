<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/01/2025
  Time: 5:35 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/admin/problem/home.jsp" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Danh sách cuộc thi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="main">
    <div class="title">
        <h1>Danh sách cuộc thi</h1>
    </div>
    <div class="btnadd" onclick="createContest()">Thêm cuộc thi</div>
    <div class="table">
        <table>
            <thead>
            <th>STT</th>
            <th>Tên</th>
            <th>Bắt đầu</th>
            <th>Kết thúc</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
            </thead>
            <tbody id="contest-list">
            <c:forEach var="item" items="${listContest}" varStatus="status">
                <tr class="col-12">
                    <input type="hidden" value="${item.end_time}" />
                    <td class="col-1">${status.count}</td>
                    <td>${item.name}</td>
                    <td class="col-2">
                        <p>${item.startDate}</p>
                        <p>${item.startTime}</p>
                    </td>
                    <td class="col-2">
                        <p>${item.endDate}</p>
                        <p>${item.endTime}</p>
                    </td>
                    <td class="col-2">
                        <input type="hidden" class="end-time" value="${item.end_time}" />
                        <span class="status">
                            <c:if test="${item.start_time > now}">
                                <span class="status upcoming">
                                    Sắp diễn ra
                                </span>
                            </c:if>
                            <c:if test="${item.start_time <= now && item.end_time >= now}">
                                <span class="status ongoing ">
                                    Đang diễn ra
                                </span>
                            </c:if>
                            <c:if test="${item.end_time < now}">
                                <span class="status ended">
                                    Kết thúc
                                </span>
                            </c:if>
                        </span>
                    </td>
                    <td class="col-1">
                        <div onclick="configdisplay(this)">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="dropdown-icon bi bi-three-dots-vertical" viewBox="0 0 16 16">
                                <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0"/>
                            </svg>
                            <div class="config">
                                <ul>
                                    <li onclick="contestDetail(${item.id})">Chi tiết</li>
                                    <li onclick="deleatContest(${item.id})">Xóa</li>
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
<div class="create_contest" id="create_contest">
    <div class="formacreate">
        <div class="title_creatcreat">
            <h2>Thêm bài tập</h2>
        </div>
        <div id="dataForm">
            <div class="name">
                <label>Tên cuộc thi:</label>
                <input id="title" type="text" placeholder="Nhập tên cuộc thi" name="title"/>
            </div>
            <div class="begin">
                <label>Thời gian bắt đầu</label>
                <input id="startTime" type="datetime-local" name="startTime"/>
            </div>
            <div class="end">
                <label>Thời gian kết thúc</label>
                <input id="endTime" type="datetime-local" name="endTime"/>
            </div>
            <div class="btns" >
                <div class="btncreaet" onclick="createCon()">Thêm</div>
                <div class="btnclose" onclick="create_contest_close()">Đóng</div>
            </div>
        </div>

    </div>
</div>
</body>
<script>
    function createContest(){
        const item = document.getElementById("create_contest");
        item.style.display = "flex";
    }
    function create_contest_close(){
        const item = document.getElementById("create_contest");
        item.style.display = "none";
    }
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


</script>
<script>
    function updateContestStatus() {
        const now = new Date();
        const rows = document.querySelectorAll("#contest-list tr");

        rows.forEach(row => {
            const startTimeInput = row.querySelector(".start-time");
            const endTimeInput = row.querySelector(".end-time");
            const statusSpan = row.querySelector(".status");

            if (startTimeInput && endTimeInput && statusSpan) {
                const startTime = new Date(startTimeInput.value);
                const endTime = new Date(endTimeInput.value);

                if (!isNaN(startTime.getTime()) && !isNaN(endTime.getTime())) {
                    if (startTime > now) {
                        statusSpan.innerText = "Sắp diễn ra";
                        statusSpan.classList.add("upcoming");
                        statusSpan.classList.remove("ongoing", "ended");
                    } else if (endTime > now) {
                        statusSpan.innerText = "Đang diễn ra";
                        statusSpan.classList.add("ongoing");
                        statusSpan.classList.remove("upcoming", "ended");
                    } else {
                        statusSpan.innerText = "Kết thúc";
                        statusSpan.classList.add("ended");
                        statusSpan.classList.remove("upcoming", "ongoing");
                    }
                }
            }
        });
    }
    setInterval(updateContestStatus, 60000);
    document.addEventListener("DOMContentLoaded", updateContestStatus);

</script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    function createCon(){
        var data = {};
        data['name'] = $('#title').val();
        data['startTime'] = $('#startTime').val();
        data['endTime'] = $('#endTime').val();
        const now = new Date();
        const startTime = new Date(data['startTime']);
        const endTime = new Date(data['endTime']);
        if(data['startTime'] === undefined || data['startTime'] === "" || data['endTime'] === undefined || data['endTime'] ===""){
            Swal.fire({
                title: 'Lỗi!',
                text: 'Thời gian bắt đầu và kết thúc cuộc thi không được để trống !!',
                icon: 'error',
                confirmButtonText: 'OK'
            });
        }
        else if(endTime < now){
            Swal.fire({
                title: 'Lỗi!',
                text: 'Thời gian kết thúc phải là thời gian trong tương lai !!',
                icon: 'error',
                confirmButtonText: 'OK'
            });
        }
        else if(startTime > endTime){
            Swal.fire({
                title: 'Lỗi!',
                text: 'Thời gian bắt đầu phải trước thời gian kết thúc !!',
                icon: 'error',
                confirmButtonText: 'OK'
            });
        }
        else{
            $.ajax({
                type: "POST",
                url: "/demo2-0.0.1-SNAPSHOT/admin/create_contest",
                data: JSON.stringify(data),
                contentType: "application/json",
                success(){
                    Swal.fire({
                        text: 'Đã thêm cuộc thi thành công',
                        icon: 'success',
                        confirmButtonText: 'OK'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            location.reload();
                        }
                    });
                },
                error:function(e){
                    Swal.fire({
                        title: 'Lỗi!',
                        text: 'Không thể thêm cuộc thi !!',
                        icon: 'error',
                        confirmButtonText: 'OK'
                    });
                }
            })
        }
    }
</script>
<script>
    function contestDetail(id){
        window.location.href = "/demo2-0.0.1-SNAPSHOT/admin/contest-detail-" + id;
    }
</script>
<script>
    function deleatContest(id){
        $.ajax({
            type: "DELETE",
            url: "/demo2-0.0.1-SNAPSHOT/admin/delete-contest-" + id,
            data: JSON.stringify(id),
            contentType: "application/json",
            success(){
                Swal.fire({
                    text: 'Đã xóa cuộc thi thành công',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.isConfirmed) {
                        location.reload();
                    }
                });
            },
            error:function(e){
                Swal.fire({
                    title: 'Lỗi!',
                    text: 'Không xóa cuộc thi !!',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        })
    }
</script>
<style>
    .main{
        width: 1177.6px;
        margin: 30.39px auto 0;
        border: 1px solid black;
        position: relative;
    }
    .title{
        margin-top: 20px;
        display: flex;
        justify-content: center;
    }
    .btnadd{
        display: flex;
        justify-content: center;
        text-align: center;
        align-items: center;
        width: 150px;
        border: 1px solid black;
        margin-left: 100px;
        height: 30px;
        border-radius: 8px;
        background-color: brown;
        color: #fff;
        margin-bottom: 30px;
    }
    .table table{
        width: 90%;
        margin-left: 50px;
        border: 1px solid black;
        border-collapse: collapse;
        margin-bottom: 50px;
    }
    .table table thead th{
        background-color: rgb(250, 237, 237);
        padding: 10px;
        border: 1px solid black;
        text-align: center;
    }
    .table table tbody tr td{
        text-align: center;
        border: 1px solid black;
    }
    .create_contest{
         display: none;
        /*display: flex;*/
        justify-content: center;
        align-items: center;
        position: absolute;
        top: 0;
        width: 100%;
        height: 100%;
        background-color: #FFFFFF;
        z-index: 1000;
    }
    .formacreate{
        border: 1px solid black;
        width: 1000px;
        min-height: 500px;
        border-radius: 5px;
    }
    .title_creatcreat{
        display:flex;
        justify-content: center;
        margin-top: 20px;
    }
    .name label, .begin label, .end label{
        margin-left: 40px;
    }
    .name input, .begin input, .end input{
        margin: 30px;
        height: 30px;
        width: 350px;
        border: 1px solid #ccc;
        border-radius: 8px;
        transition: border-color 0.3s ease, box-shadow 0.3s ease;
    }
    .btns{
        margin-top: 50px;
        width: 100%;
        display: flex;
    }
    .btncreaet ,.btnclose{
        width: 150px;
        height: 35px;
        display: flex;
        justify-content: center;
        text-align: center;
        align-items: center;
        margin-right: 100px;
        margin-left: 100px;
        border: 1px solid black;
        border-radius: 8px;

    }
    .btncreaet{
        background-color:brown;
        color: #fff;
    }
    .btnclose{
        background-color:blueviolet;
    }
    .upcoming {
        color: blue;
    }

    .ongoing {
        color: green;
    }

    .ended {
        color: red;
    }
    .config{
        position: absolute;
        display: none;
        z-index: 1000;
    }
    .config ul{
        width: 120px;
        margin: 0 !important;
        padding: 0 !important;
        list-style: none;
        background-color: #FFFFFF;
    }
    .config ul li{
        padding: 5px;
        border: 1px solid black;
        margin: -1px;
        height: 40px;
    }
</style>
</html>
