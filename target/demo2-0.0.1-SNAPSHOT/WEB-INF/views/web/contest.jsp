<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/01/2025
  Time: 5:35 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="home.jsp" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Danh sách cuộc thi</title>
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
        .btn-join{
            border-radius: 8px;
            background-color: brown;
            color: #fff;
            display: none;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script><div class="main">
    <div class="title">
        <h1>Danh sách cuộc thi</h1>
    </div>
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
                        <button class="btn-join">Tham gia</button>
                    </td>
                    <input type="hidden" class="start-time" value="${item.start_time}" />
                    <input type="hidden" class="end-time" value="${item.end_time}" />
                </tr>

            </c:forEach>
            </tbody>


        </table>
    </div>
</div>
</body>
<script>
    function updateContestStatus() {
        const now = new Date();
        const rows = document.querySelectorAll("#contest-list tr");
        rows.forEach(row => {
            const startTimeInput = row.querySelector(".start-time");
            const endTimeInput = row.querySelector(".end-time");
            const statusSpan = row.querySelector(".status");
            const joinButton = row.querySelector("td .btn-join");
            if (startTimeInput && endTimeInput && statusSpan) {
                const startTime = new Date(startTimeInput.value);
                const endTime = new Date(endTimeInput.value);
                if (!isNaN(startTime.getTime()) && !isNaN(endTime.getTime())) {
                    if (startTime > now) {
                        statusSpan.innerText = "Sắp diễn ra";
                        statusSpan.classList.add("upcoming");
                        statusSpan.classList.remove("ongoing", "ended");
                        if (joinButton) joinButton.style.display = "none";
                    } else if (endTime > now) {
                        statusSpan.innerText = "Đang diễn ra";
                        statusSpan.classList.add("ongoing");
                        statusSpan.classList.remove("upcoming", "ended");
                        if (joinButton) joinButton.style.display = "block";
                    } else {
                        statusSpan.innerText = "Kết thúc";
                        statusSpan.classList.add("ended");
                        statusSpan.classList.remove("upcoming", "ongoing");
                        if (joinButton) joinButton.style.display = "none";
                    }
                }
            }
        });
    }
    setInterval(updateContestStatus, 60000);
    document.addEventListener("DOMContentLoaded", updateContestStatus);

</script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</html>
