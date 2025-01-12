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
  <label class="labelTitle">${problemDTO.title}</label>
  <div class="menu_edit">
    <a class="chose detail" href="problem_contest-edit-${problemDTO.id}">Chi tiết</a>
    <a class="chose testcase" href="list-testcase-${problemDTO.id}">Test Case</a>
    <a class="chose install" href="install">Cài đặt</a>
  </div>
</div>
</body>
</html>
