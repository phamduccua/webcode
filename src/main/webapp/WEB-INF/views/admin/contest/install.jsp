<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 15/01/2025
  Time: 1:03 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/admin/problem/home.jsp" %>
<%@ include file="contest_menu.jsp" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Cài đặt</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
      .main{
        width: 1177.6px;
        margin: 30.39px auto 0;
        border: 1px solid black;
      }
      .checkbox-item {
        align-items: center;
        margin-bottom: 10px;
        margin-right: 5px;
        margin-left: 20px;
      }
      .buttonadd {
        background-color: brown;
        color: white;
        font-size: 16px;
        font-weight: bold;
        padding: 10px 20px;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        transition: all 0.3s ease;
        margin: 20px;
      }
      .title{
        margin-top: 40px;
        display: flex;
        justify-content: center;
      }
      #listForm{
        margin-top: 50px;
        margin-left: 200px;
      }
    </style>
</head>
<body>
<div class="main">
  <div class="title">
    <h2>Cài đặt</h2>
  </div>
  <form:form id="listForm" modelAttribute="contestDTO" method="GET">
    <div>
      <label>Ngôn ngữ sử dụng: </label>
      <form:checkboxes items="${listLanguages}" path="language" cssClass="checkbox-item"/>
    </div>
    <button class="buttonadd" id="updateProblem">Cập nhật</button>
  </form:form>

</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
  $('#updateProblem').click(function(e){
    e.preventDefault();
    var data = {};
    var language  = [];
    var formData = $('#listForm').serializeArray();
    $.each(formData, function(i, v){
      if(v.name === 'language'){
        language.push(v.value);
      }
    });
    data['languages'] = language;
    data['contestId'] = ${contestDTO.id};
    console.log(data);
    $.ajax({
      type:"PUT",
      url: "/admin/update_language_contest",
      data: JSON.stringify(data),
      contentType: "application/json",
      success: function () {
        Swal.fire({
          title: 'Thành công!',
          text: 'Yêu cầu của bạn đã được xử lý.',
          icon: 'success',
          confirmButtonText: 'OK'
        }).then(() => {
          location.reload();
        });
      },
      error: function (e) {
        Swal.fire({
          title: 'Lỗi!',
          text: e,
          icon: 'error',
          confirmButtonText: 'OK'
        });
      }
    });
  });
</script>
</html>
