<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 14/01/2025
  Time: 12:48 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/admin/problem/home.jsp" %>
<%@ include file="contest_menu.jsp" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Quản lí thành viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .main{
            width: 1177.6px;
            margin: 30.39px auto 0;
            border: 1px solid black;
        }
        .search{
            width: 300px;
            height: 30px;
            margin-left: 237px;
            margin-bottom: 30px;
        }
        .title{
            margin-top: 30px;
            margin-bottom: 50px;
            display: flex;
            justify-content: center;
        }
        .table{
            width: 100%;
            display:flex;
            justify-content: center;
        }
        .table table{
            width: 700px;
            border: 1px solid black;
            border-collapse: collapse;
            margin-bottom: 40px;
        }
        .table table thead th{
            height: 40px;
            background-color:  #8B1A1A;
            color:#fff;
            border: 1px solid black;
            text-align: center;
        }
        .table table tbody tr td{
            height: 30px;
            border: 1px solid black;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="main">
    <div class="title">
        <h2>Quản lí thành viên</h2>
    </div>
    <div>
        <form:form id="listForm" modelAttribute="modelSearch" method="GET">
            <form:input type="text" placeholder="Tìm kiếm theo tên" class="search" path="name" onchange="listForm()"/>
        </form:form>
        <form class="table">
            <table>
                <thead>
                <th>Trạng thái</th>
                <th>Tên</th>
                <th>Mã</th>
                </thead>
                <tbody>
                <c:forEach var="item" items="${list_member}">
                    <tr class="12">
                        <td class="col-2">
                            <input type="checkbox" ${item.checked == 'checked' ? 'checked' : null} onchange="editmem(${item.id},${id},this.checked)"/>
                        </td>
                        <td class="col-6">${item.fullname}</td>
                        <td class="col-4">${item.username}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    function editmem(userId,conId,check){
        var data = {};
        data['contestId'] = conId;
        data['userId'] = userId;
        data['checked'] = check;
        $.ajax({
            type: "PUT",
            url: "/admin/edit-member",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function() {
                Swal.fire({
                    text: 'Đã cập nhật khoản thành công',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => {
                    location.reload();
                });
            },
            error: function(error) {
                Swal.fire({
                    title: 'Lỗi!',
                    text: 'Không thể cập nhật. Vui lòng thử lại.',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        })
    }
</script>
<script>
    function listForm(){
        document.getElementById("listForm").submit();
    }
</script>
</html>
