<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 09/01/2025
  Time: 1:26 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/admin/problem/home.jsp" %>
<html>
<head>
    <meta charset="UTF-8" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="main">
    <div class="title">
        <h1>Quản lí tài khoản</h1>
    </div>
    <form:form id="listAccount" modelAttribute="userSearchRequest" method="GET">
    <div class="item">
        <div>
            <form:input class="search" type="text" path="name" placeholder="Tìm theo mã, họ tên"/>
            <form:select class="role" path="role" onchange="listRole()">
                <form:option value="">---Chọn vai trò---</form:option>
                <form:options items="${roles}"/>
            </form:select>
        </div>
        <div class="add" onclick="btnadd()">Thêm tài khoản</div>
        <div class="delete" onclick="restoreUsers()">Khôi phục</div>
    </div>

        <div class="table">
            <table>
                <thead>
                <tr>
                    <th>
                        STT
                    </th>
                    <th>Tên đăng nhập</th>
                    <th>Họ Tên</th>
                    <th>Số điện thoại</th>
                    <th>Vai trò</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${list}" varStatus="status">
                    <tr class="col-12">
                        <td class="col-1">
                            <input type="checkbox" value="${item.id}" >
                            ${status.count}
                        </td>
                        <td class="col-2">${item.username}</td>
                        <td>${item.fullname}</td>
                        <td class="col-2">${item.phone_number}</td>
                        <td class="col-1">${item.role}</td>
                        <td class="midd col-1">
                            <div class="box" onclick="configdisplay(this)">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="dropdown-icon bi bi-three-dots-vertical" viewBox="0 0 16 16">
                                    <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0"/>
                                </svg>
                                <div class="config" >
                                    <ul>
                                        <li onclick="doi_trang(${item.id})">Chỉnh sửa</li>
                                        <li onclick="restoreUser(${item.id})">Khôi phục</li>
                                    </ul>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <form:input type="hidden" id="inPage" path="page" />
    </form:form>
    <div class="page" id="page">
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    var item = document.getElementById('page')
    item.innerHTML = "";
    var totalPage = ${userSearchRequest.totalPages};
    var page = ${userSearchRequest.page};
    console.log(totalPage);
    console.log(page);
    var tmp = '<ul>';
    if(totalPage > 1){
        if(totalPage <= 3){
            for(var i = 1;i <= totalPage;i++){
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
            for(var i = begin; i <= end ;i++){
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

</script>
<script>
    function btnadd(){
        window.location.href = "edit-account";
    }
    function listRole(){
        document.getElementById("listAccount").submit();
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
            event.stopPropagation(); // Ngăn lan sự kiện ra ngoài
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
    function doi_trang(id){
        window.location.href = "edit-account-" + id;
    }
    function doipage(i){
        const valuePage = document.getElementById("inPage");
        valuePage.value = i;
        listRole();
    }
</script>
<script>
    function restoreUser(id){
        let userId = [id];
        Restore(userId);
    }
    function restoreUsers(){
        let userIds =$('#listAccount').find('tbody input[type=checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        Restore(userIds);
    }
    function Restore(data){
        $.ajax({
            type: "PUT",
            url:  "/web/admin/restore/" + data,
            data: JSON.stringify(data),
            success: function(response) {
                Swal.fire({
                    text: 'Đã xóa tài khoản thành công',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => {
                    location.reload();
                });
            },
            error: function(error) {
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
    .main{
        border: 1px solid black;
        width: 1177.6px;
        margin: 30.39px auto 0;
    }
    .title{
        margin-top: 30px;
         display: flex;
         justify-content: center;
        margin-bottom:30px;
     }
    .item{
        display: flex;
        align-content: center;
        width: 100%;
    }
    .search{
        margin-left: 50px;
        width: 300px;
        height: 30px;
        left: 50px;
        background-color: #FAEDED;
        border-radius: 5px;
        margin-right: 50px
    }
    .role{
        top: 230px;
        border-radius: 5px;
        left: 50px;
    }
    .add{
        background-color: #8A1111;
        color: #fff;
        width: 160px;
        height: 30px;
        border-radius: 5px;
        text-align: center;
        align-content: center;
        margin-left: auto;
    }
    .delete{
        background-color: #8A1111;
        color: #fff;
        width: 50px;
        height: 30px;
        border-radius: 5px;
        align-content: center;
        text-align: center;
        margin-left: 30px;
        margin-right: 100px;
    }
    .table{
        left: 50px;
        top: 290px;
        width: 100%;
    }
    .table table{
        margin-top: 50px;
        margin-left: 50px;
        margin-bottom: 50px;
        width: 90%;
        border: 1px solid black;
        border-collapse: collapse;
    }
    .table table tr th{
        border: 1px solid black;
        background-color: #8A1111;
        height: 50px;
        color: #fff;
        text-align: center;
    }
    .table table tr td{
        text-align: center;
        height:30px;
        border: 1px solid black;
    }
    .role option{
        text-align: center;
    }
    .box{
        position: relative;
        left : 43%;
        width: 20px;
    }
    .config{
        position: absolute;
        display: none;
        z-index: 1000;
    }
    .config ul{
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
</html>
