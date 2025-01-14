<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 14/01/2025
  Time: 11:21 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ICPC Leaderboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }
        header {
            background-color: #bb2019;
            color: white;
            padding: 1rem;
            text-align: center;
            position: sticky;
            top: 0;
            z-index: 1000;
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
            background-color: #343a40;
            color: white;
            position: sticky;
            top: 0;
            z-index: 999;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #d3d3d3;
        }
        .solved {
            color: #28a745;
            font-weight: bold;
        }
        .unsolved {
            color: #dc3545;
        }
    </style>
</head>
<body>
<header>
    <h1>Bảng xếp hạng cuộc thi</h1>
    <h2>Cuộc thi 1</h2>
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
                <td>${item.fullname}</td>
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
</body>
</html>
