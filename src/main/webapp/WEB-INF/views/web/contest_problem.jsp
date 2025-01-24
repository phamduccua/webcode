<%@ taglib prefix="f" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="menu_contest.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
        <label style="margin-left: 100px;"><strong>Bài tập</strong></label>
        <table id="tableList" class="table table-fixed">
            <thead class="table-head">
            <tr>
                <th class="text-middle">STT</th>
                <th>Tiêu đề</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${problemList}" varStatus="status">
                <c:if test="${item.status != -1}">
                    <c:if test="${item.status == 1}">
                        <tr class="table-body col-24" style="background-color: #86C681;">
                    <td class="col-0.5">
                        ${status.count}
                    </td>
                    <td>
                        <a class="open" href="/api/contest/${id}/solution/${item.code}">
                            ${item.title}
                        </a>
                    </td>
                </tr>
                    </c:if>
                    <c:if test="${item.status == 0}">
                        <tr class="table-body col-24" style="background-color: #FFA29E;">
                    <td class="col-0.5">
                        ${status.count}
                    </td>
                    <td>
                        <a class="open" href="/api/contest/${id}/solution/${item.code}">
                            ${item.title}
                        </a>
                    </td>
                </tr>
                    </c:if>
                </c:if>
                <c:if test="${item.status == -1}">
                    <tr class="table-body col-24">
                    <td class="col-0.5">
                        ${status.count}
                    </td>
                    <td>
                        <a class="open" href="/api/contest/${id}/solution/${item.code}">
                            ${item.title}
                        </a>
                    </td>
                </tr>
                </c:if>
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
    if(totalPage > 1){
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
    }

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
        margin-left: 100px;
        margin-top: 30px;
        border-spacing: 0;
        border-radius: 4px;
        width: 800px;
        border: 1px solid #ccc;
    }

    .table-head {
        background-color: #8A1111;
        font-size: 16px;
        font-weight: bold;
        color: #FFFFFF;
    }

    .table-head th {
        text-align: left;
        vertical-align: middle;
        height: 50px;
        padding: 10px;
        font-size: 16px;
        font-weight: bold;
        color: #FFFFFF;
        background-color: #8A1111;
    }
    .table-head .mid{
        text-align: center;
    }
    .table-body td {
        align-content: center;
        text-align: left;
        padding: 10px;
        border-bottom: 1px solid black;
    }
    .table-body .mid{
        text-align: center;
    }
    .text-middle {
        width: 40px;
        min-width: 40px;
        padding: 0;
        text-align: center;
    }
    .dropdown {
        position: relative;
        display: inline-block;
        overflow: visible;
    }
    .dropdown-btn {
        cursor: pointer;
        color: white;
        font-weight: bold;
    }
    .dropdown-icon {
        cursor: pointer;
    }
    .dropdown-menu-wrapper {
        display: none;
        position: absolute;
        top: 100%;
        right: 0;
        background-color: white;
        border: 1px solid #ccc;
        border-radius: 4px;
        z-index: 1000;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        overflow-y: auto;
        width: max-content;
    }
    .dropdown:hover .dropdown-menu-wrapper {
        display: block;
    }
    .filter-button-container {
        text-align: center;
        margin-top: 10px;
    }
    .filter-button {
        border : 1px solid black;
        background-color : forestgreen;
        padding: 5px 15px;
        cursor: pointer;
        border-radius: 5px;
    }
    .filter-button:hover {
        background-color: #e0e0e0;
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
