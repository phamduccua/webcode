<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 09/01/2025
  Time: 1:35 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Chỉnh sửa tài khoản</title>
</head>
<body>
<div class="main">

  <form:form id="listForm" modelAttribute="accountEdit" method="GET">
    <div class="title">
      <c:if test="${empty accountEdit.id}">
        <h1>Thêm tài khoản</h1>
      </c:if>
      <c:if test="${not empty accountEdit.id}">
        <h1>Sửa tài khoản</h1>
      </c:if>
    </div>
    <div class="name">
      <label for="name">Họ và tên:</label>
      <form:input id="name" type="text" placeholder="Nhập họ và tên" path="fullname"/>
    </div>
    <div class="phone">
      <label for="phone">Số điện thoại:</label>
      <form:input id="phone" type="text" placeholder="Nhập số điện thoại" path="phone_number"/>
    </div>
    <div class="role">
      <label for="role">Vai trò của người dùng:</label>
      <form:select id="role" path="role">
        <form:option value="">---Chọn vai trò---</form:option>
        <form:options items="${roles}" />
      </form:select>
    </div>
    <div class="table" id="classList">
      <br>
      <label>Lớp tham gia:</label>
      <table class="class">
        <thead>
        <th><input type="checkbox" /></th>
        <th>Lớp</th>
        </thead>
        <tbody>
        <c:forEach var="item" items="${listGroup}">
          <tr>
            <td>
              <c:if test="${item != null}">
                <form:checkbox path="class_id" value="${item.key}" />
              </c:if>
            </td>
            <td>
                ${item.value}
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <div class="btn">
      <c:if test="${empty accountEdit.id}">
        <div class="btnadd" onclick="add()">Thêm tài khoản</div>
      </c:if>
      <c:if test="${not empty accountEdit.id}">
        <div class="btnadd" onclick="edit()">Cập nhật tài khoản</div>
        <div class="btnreset" onclick="reset()">Reset mật khẩu</div>
      </c:if>
    </div>
    <form:input type="hidden" path="id" />
  </form:form>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
  function getData(){
    let data = {};
    let class_id = $('#classList').find('tbody input[type=checkbox]:checked').map(function(){
      return $(this).val();
    }).get();
    let formData =$('#listForm').serializeArray();
    $.each(formData, function(i, v){
      data[v.name] = v.value;
    });
    data['class_id'] = class_id;
    return data;
  }
  function add(){
    let data = getData();
    $.ajax({
      type: "POST",
      url: "/admin/register",
      data: JSON.stringify(data),
      contentType: "application/json",
      dataType: "JSON",
      success: function(response) {
        Swal.fire({
          text: 'Đã thêm tài khoản thành công',
          icon: 'success',
          confirmButtonText: 'OK'
        })
      },
      error: function(response) {
        Swal.fire({
          title: 'Lỗi!',
          text: 'Không thêm tài khoản !!',
          icon: 'error',
          confirmButtonText: 'OK'
        });
      }
    });
  }
  function edit(){
    let data = getData();
    $.ajax({
      type: "PUT",
      url: "/admin/update",
      data: JSON.stringify(data),
      contentType: "application/json",
      dataType: "JSON",
      success: function(response) {
        Swal.fire({
          text: 'Đã cập nhật tài khoản thành công',
          icon: 'success',
          confirmButtonText: 'OK'
        })
      },
      error: function(response) {
        Swal.fire({
          text: 'Đã xảy ra lỗi. Cập nhật tài khoản thất bại',
          icon: 'info',
          confirmButtonText: 'OK'
        });
      }
    });
  }
  function reset(){
    let data = getData();
    $.ajax({
      type: "PUT",
      url: "/admin/reset",
      data: JSON.stringify(data),
      contentType: "application/json",
      dataType: "JSON",
      success: function(response) {
        Swal.fire({
          text: 'Đã khôi phục mật khẩu thành công',
          icon: 'success',
          confirmButtonText: 'OK'
        })
      },
      error: function(response) {
        Swal.fire({
          title: 'Lỗi!',
          text: 'Đã xảy ra lỗi. Khôi phục thất bại !!',
          icon: 'error',
          confirmButtonText: 'OK'
        });
      }
    });
  }
</script>
</body>

<style>
  .main{
    border: 1px solid black;
  }
  .title{
    text-align: center;
  }
  .name label, .phone label, .role label, .table label{
    left: 50px;
    font-size: 20px;
    margin-bottom: 20px;
    margin-left: 100px;
    margin-right: 20px;
  }
  .name input,.phone input{
    border-radius: 5px;
    margin-bottom: 20px;
    width: 300px;
    height: 25px;
  }
  select{
    height: 30px;
    width: 200px;
  }
  option{
    text-align: center;
  }
  .table{
    width: 300px;
  }
  .class{
    width: 100%;
    margin-left: 100px;
    margin-top: 20px;
    border: 1px solid black;
    border-collapse: collapse;
    margin-bottom: 30px;
  }
  .class thead th{
    text-align: center;
    border: 1px solid black;
  }
  .class tbody td{
    text-align: center;
    border: 1px solid black;
  }
  .btn{
    display: flex;
  }
  .btnadd ,.btnreset{
    color: #FFF;
    background-color: brown;
    border-radius: 5px;
    margin-left: 100px;
    width: 150px;
    height: 35px;
    margin-bottom: 30px;
    align-content: center;
    text-align: center;
  }
</style>
</html>
