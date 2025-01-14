<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 14/01/2025
  Time: 11:21 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/admin/problem/home.jsp" %>
<%@ include file="contest_menu.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Bảng xếp hạng</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }
        header {
            /*background-color: rgb(250, 237, 237);*/
            color: black;
            padding: 1rem;
            text-align: center;
            position: sticky;
            top: 0;
        }
        .table-container {
            width: 90%;
            margin: 2rem auto;
            overflow-x: auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: white;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #dee2e6;
            padding: 0.75rem;
            text-align: center;
        }
        th {
            background-color: #8A1111;
            color: white;
            position: sticky;
            top: 0;
            height: 80px;
        }
        .solved {
            background-color: #86C681;
            color: black;
            font-weight: bold;
        }
        .unsolved {
            background-color: #FFA29E;
            color: #fff;
        }
        .main{
            width: 1177.6px;
            margin: 30.39px auto 0;
            border: 1px solid black;
        }
    </style>
</head>
<body>
<div class="main">
    <header>
        <h2>Bảng xếp hạng cuộc thi</h2>
        <h3>Cuộc thi 1</h3>
    </header>
<div class="table-container">
    <table>
        <thead>
        <th>User</th>
        <c:forEach items="${leaderboard.name_problem}" varStatus="status">
            <th>Problem ${status.count}</th>
        </c:forEach>
        </thead>
        <tbody>
        <c:forEach var="item" items="${leaderboard.user}">
            <tr>
                <td class="col-3" style="background-color: grey; color: #fff;">${item.fullname}</td>
                <c:forEach var="it" items="${item.status}">
                    <c:if test="${it == null}">
                        <td></td>
                    </c:if>
                    <c:if test="${it == 'true'}">
                        <td class="solved">SOLVED</td>
                    </c:if>
                    <c:if test="${it == 'false'}">
                        <td class="unsolved">UNSOLVED</td>
                    </c:if>
                </c:forEach>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</div>

</body>
</html>
