<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/admin/problem/home.jsp" %>
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

    .menu_edit {
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
  <div class="menu_edit">
    <a class="chose detail" href="/demo2-0.0.1-SNAPSHOT/problem_contest-edit-${code}">Chi tiết</a>
    <a class="chose testcase" href="/demo2-0.0.1-SNAPSHOT/list_testcase_contest-problem-${code}">Test Case</a>
  </div>
</div>
</body>
</html>
