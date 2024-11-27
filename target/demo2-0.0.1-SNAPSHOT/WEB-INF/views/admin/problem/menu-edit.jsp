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
</head>
<body>

<div id="home" class="main">
  <label class="labelTitle">${nameProblem}</label>
  <div class="menu">
    <a class="chose detail" href="detail-${id}">Chi tiết</a>
    <a class="chose testcase" href="list-testcase-${id}">Test Case</a>
    <a class="chose languages" href="languages">Ngôn ngữ</a>
    <a class="chose install" href="install">Cài đặt</a>
    <a class="chose install" href="list">Quay về trang chủ</a>
  </div>
</div>

</body>
</html>
