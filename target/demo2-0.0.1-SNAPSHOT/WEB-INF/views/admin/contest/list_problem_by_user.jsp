<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 22/01/2025
  Time: 3:24 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/admin/problem/home.jsp" %>
<%@ include file="contest_menu.jsp" %>
<html>
<head>
  <meta charset="UTF-8" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .main{
      width: 1177.6px;
      margin: 30.39px auto 0;
      padding: 25.6px 39.19px;
      border-radius: 4px;
      border: solid 1.6px #e1e4e8;
    }
    .title{
      display: flex;
      justify-content: center;
    }
    #listProblem{
      width: 100%;
      display: flex;
      justify-content: center;
    }
    #listProblem table{
      min-width: 600px;
      border: 1px solid black;
      border-collapse:collapse;
    }
    #listProblem table thead th{
      color: #fff;
      background-color: #8A1111;
      height: 40px;
      border: 1px solid black;
      text-align: center;
      align-content: center;
    }
    #listProblem table tbody tr td{
      height: 28px;
      text-align: center;
      align-content: center;
      border: 1px solid black;
    }
    .search{
      margin-top: 20px;
      display: flex;
      justify-content: center;
      width: 100%;
      margin-bottom: 30px;
    }
    .search input{
      width: 240px;
      height: 35px;
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
</head>
<body>
<div class="main">
  <div class="title">
    <h2>Danh sách bài tập bạn đã tạo</h2>
  </div>
  <form:form  id="listForm" modelAttribute="modelSearch" method="GET">
    <div class="search">
      <form:input class="search" type="text" placeholder="Nhập tên bài tập" path="name" />
    </div>
    <form:input type="hidden" id="contestId" path="contestId" />
    <div id="listProblem">
      <table>
        <thead>
          <th>Trạng thái</th>
          <th>Tên bài tập</th>
        </thead>
        <tbody>
        <c:forEach var="item" items="${listProblem}">
          <tr>
            <td><input type="checkbox" ${item.status == '1' ? 'checked' : null} onchange="editproblem(${item.problemId},${id},this.checked)" /></td>
            <td>${item.name}</td>
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
<script>
  var item = document.getElementById('page')
  item.innerHTML = "";
  var totalPage = ${modelSearch.totalPage};
  var page = ${modelSearch.page};
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
    document.getElementById("listForm").submit();
  }
  function doipage(i){
    const valuePage = document.getElementById("inPage");
    valuePage.value = i;
    listRole();
  }
</script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
  function editproblem(problemId,conId,check){
    let data = {};
    data['contestId'] = conId;
    data['problemId'] = problemId;
    data['checked'] = check;
    $.ajax({
      type: "POST",
      url: "/demo2-0.0.1-SNAPSHOT/admin/edit-problem-by-user",
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
</html>
