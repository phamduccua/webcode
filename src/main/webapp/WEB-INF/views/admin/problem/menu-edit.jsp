<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 24/11/2024
  Time: 1:02 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <style>
    #home {
      background-color: #8A1111;
      color: #fff;
    }

    .main {
      width: 1178px;
      margin: 30px auto 0;
      padding: 26px 39px;
      border-radius: 4px;
      border: 1.6px solid #e1e4e8;
      text-align: left;
    }

    .labelTitle {
      color: white;
      font-size: 30px;
      margin-bottom: 20px;
      display: block;
    }

    .menu {
      background-color: white;
      width: 800px;
      border-radius: 4px;
      border: 1.6px solid #e1e4e8;
      text-align: left;
    }

    .chose {
      background-color: aquamarine;
      color: black;
      border: 1px solid #ccc;
      text-decoration: none;
      margin: 10px;
      display: inline-block;
      border-radius: 4px;
      padding: 2px;
    }
  </style>
</head>
<body>
<div id="home" class="main">
  <label class="labelTitle">${nameProblem}</label>
  <div class="menu">
    <a class="chose detail" href="/admin/detail-${code}">Chi tiết</a>
    <a class="chose testcase" href="/admin/list-testcase-${code}">Test Case</a>
    <a class="chose install" href="/admin/list">Quay về trang chủ</a>
  </div>
</div>
</body>
</html>
