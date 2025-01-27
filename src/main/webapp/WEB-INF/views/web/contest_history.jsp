<%@ taglib prefix="f" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="menu_contest.jsp" %>
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
    <title>Lịch sử</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="main">
    <form:form id="listSubmission" modelAttribute="submission" method="GET">
        <label><strong>Lịch sử nộp bài</strong></label>
        <table id="tableList" class="table table-fixed">
            <thead class="table-head">
            <tr>
                <th class="text-middle">ID</th>
                <th>Thời gian</th>
                <th>Bài tập</th>
                <th>Kết quả</th>
                <th>Thời gian</th>
                <th class="mid">Bộ nhớ</th>
                <th class="mid">Trình biên dịch</th>
            </tr>
            </thead>
            <tbody class="table-body" id="listSubmission">
            <c:forEach var="item" items="${listSub}" >
                <tr>
                    <td>${item.id}</td>
                    <td>
                        <p>${item.formattedDate}</p>
                        <p>${item.formattedTime}</p>
                    </td>
                    <td>
                    <a href="/web/api/contest/${id}/solution/${item.problemCode}" style="color: #d30000;">${item.problemName}
                    </td>
                    <c:if test="${item.status == null}">
                        <td class="spinner">
                            <div class="spinner-border text-primary small-spinner" role="status">
                                <span class="visually-hidden">Loading...</span>
                            </div>
                        </td>
                        <td></td>
                        <td></td>
                    </c:if>
                    <c:if test="${item.status != null}">
                        <c:if test="${item.status == true}">
                            <td><a style="color: #19BE6B;" href="/web/api/contest/${id}/submission/${item.id}/edit" />AC</td>
                            <td>${item.time}s</td>
                            <td>${item.memoryUsed}Kb</td>
                        </c:if>
                        <c:if test="${item.status == false}">
                            <c:if test="${item.code == 'CE'}">
                                <td><a style="color: rgb(0, 0, 0)" href="/web/api/contest/${id}/submission/${item.id}/edit"/>CE</td>
                                <td></td>
                                <td></td>
                            </c:if>
                            <c:if test="${item.code != 'CE'}">
                                <td><a style="color: #FF0000;" href="/web/api/contest/${id}/submission/${item.id}/edit" />${item.code}</td>
                                <td>${item.time}s</td>
                                <td>${item.memoryUsed}Kb</td>
                            </c:if>
                        </c:if>
                    </c:if>
                    <td>${item.language}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <form:input type="hidden" id="inPage" path="page" />
    </form:form>
    <div class="page" id="page">
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function updateStatus() {
        var count = 0;
        $("tbody tr").each(function () {
            var status = $(this).find("td:nth-child(4)").text().trim();
            if (status !== ""  && status !== "AC" && status !== "WA" && status !== "RTE" && status !== "TLE" && status !== "CE"　&& status !== "MLE") {
                count++;
            }
        });
        if (count > 0) {
            location.reload();
        }
    }
    setTimeout(updateStatus, 3000);

</script>
<script>
    var item = document.getElementById('page')
    item.innerHTML = "";
    var totalPage = ${submission.totalPages};
    var page = ${submission.page};
    console.log(totalPage);
    console.log(page);
    var tmp = '<ul>';
    if(totalPage > 1){
        if(totalPage <= 3){
            for(let i = 1;i <= totalPage;i++){
                if(page === i){
                    tmp += '<li class="pageChoose">' + i + '</li>' + '\n';
                }
                else tmp += '<li onclick="doipage(' + i + ')">' + i + '</li>' + '\n';
            }
        }
        else{
            let begin = 1;
            let end = 3;
            if(page > 3){
                begin = page - 2;
                end = page;
            }
            if(page > 1) tmp += '<li onclick=doipage(page-1)>«</li>' + '\n';
            for(let i = begin; i <= end ;i++){
                if(page === i){
                    tmp += '<li class="pageChoose">' + i + '</li>' + '\n';
                }
                else tmp += '<li onclick="doipage(' + i + ')">' + i + '</li>' + '\n';
            }
            if(page < totalPage) tmp += '<li onclick=doipage(page+1)>»</li>' + '\n';
        }
        tmp += '</ul>';
        item.innerHTML = tmp;
    }
    function listRole(){
        document.getElementById("listSubmission").submit();
    }
    function doipage(i){
        const valuePage = document.getElementById("inPage");
        valuePage.value = i;
        listRole();
    }
</script>
<style>
    .main {
        width: 1177.6px;
        margin: 30.39px auto 0;
        padding: 25.6px 39.19px;
        border-radius: 4px;
        border: solid 1.6px #e1e4e8;
    }
    .table {
        border-spacing: 0 !important;
        border-radius: 4px !important;
        width: 100% !important;
        border: 1px solid #ccc !important;
        margin-top: 30px !important;
    }
    .dropdown-header {
        display: flex !important;
        justify-content: center !important;
        align-items: center !important;
        gap: 5px !important;
        height: 100% !important;
    }

    .table-head {
        background-color: #8A1111 !important;
        font-size: 16px !important;
        font-weight: bold !important;
        color: #FFFFFF !important;
    }

    .table-head th {
        text-align: center !important;
        vertical-align: middle !important;
        height: 50px !important;
        padding: 10px !important;
        font-size: 16px !important;
        font-weight: bold !important;
        color: #FFFFFF !important;
        background-color: #8A1111 !important;
    }

    .table-head th:nth-child(2),.table-head th:nth-child(3) {
        text-align: left !important;
        vertical-align: middle !important;
        height: 50px !important;
        padding: 10px !important;
        font-size: 16px !important;
        font-weight: bold !important;
        color: #FFFFFF !important;
        background-color: #8A1111 !important;
    }

    .table-body td {
        align-content: center;
        text-align: center;
        padding: 10px !important;
        border-bottom: 1px solid #ccc !important;
    }
    .table-body td:nth-child(2), .table-body td:nth-child(3) {
        align-content: center;
        text-align: left;
        padding: 10px !important;
        border-bottom: 1px solid #ccc !important;
    }
    .text-middle {
        width: 40px !important;
        min-width: 40px !important;
        padding: 0 !important;
        text-align: center !important;
    }
    .dropdown {
        position: relative !important;
        display: inline-block !important;
        overflow: visible !important;
    }

    .dropdown-btn {
        cursor: pointer !important;
        color: white !important;
        font-weight: bold !important;
    }
    .dropdown-icon {
        cursor: pointer !important;
    }
    .dropdown-menu-wrapper {
        display: none !important;
        position: absolute !important;
        top: 100% !important;
        right: 0 !important;
        background-color: white !important;
        border: 1px solid #ccc !important;
        border-radius: 4px !important;
        z-index: 1000 !important;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2) !important;
        overflow-y: auto !important;
        width: max-content !important;
    }

    .dropdown:hover .dropdown-menu-wrapper {
        display: block !important;
    }
    .table-configuration {
        border-collapse: collapse !important;
        width: 100% !important;
    }

    .table-configuration td {
        padding: 10px !important;
        text-align: left !important;
        cursor: pointer !important;
        color: black !important;
    }

    .table-configuration tr:hover td {
        background-color: #f0f0f0 !important;
    }

    .scrollable-checkboxes {
        max-height: 100px !important;
        overflow-y: auto !important;
        border: 1px solid #ccc !important;
        padding: 10px !important;
    }

    .filter-button-container {
        text-align: center !important;
        margin-top: 10px !important;
    }

    .filter-button {
        padding: 5px 15px !important;
        background-color: #f5f5f5 !important;
        border: 1px solid #ccc !important;
        cursor: pointer !important;
        border-radius: 5px !important;
    }
    .filter-button:hover {
        background-color: #e0e0e0 !important;
    }
    p {
        margin: 0px !important;
    }
    .small-spinner {
        width: 20px !important;
        height: 20px !important;
        border-width: 2px !important;
    }
    .page{
        display: flex;
        justify-content: center;
        margin-bottom: 30px;
    }
    .page ul{
        display: flex;
        justify-content: center;
        margin: 0;
        padding: 0;
        border : 1px solid black;
        list-style: none;
    }
    .page ul li{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 25px;
        height: 25px;
        border: 1px solid black;
    }
    #page ul .pageChoose{
        background-color: #bb2019 !important;
        color : #FFFFFF !important;
    }

</style>
</body>
</html>
