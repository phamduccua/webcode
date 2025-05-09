<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <style>
    .head {
      justify-content: center !important;
      width: 100% !important;
      height: 60px !important;
      box-sizing: border-box !important;
      background-color: #8B1A1A !important;
    }
    .button {
      color: white !important;
      position: absolute !important;
      top: 18px !important;
      border: none !important;
      padding: 10px 20px !important;
      text-align: center !important;
    }
    .button-exam {
      top:7px !important;
      left: 120px !important;
    }
    .button-status {
      top:7px !important;
      left: 200px !important;
    }
    .button-history {
      top:7px !important;
      left: 300px !important;
    }
    .button-rank {
      top:7px !important;
      left: 380px !important;
    }
    .button-configuration {
      top:7px !important;
      left: 510px !important;
    }
    .button-gui {
      top:7px !important;
      left: 620px !important;
    }
    .avatar {
      background-color: white;
      width: 40px;
      height: 40px;
      border-radius: 50%;
      position: absolute;
      top: 10px;
      right: 70px;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .img {
      width: 70%;
      height: 70%;
      border-radius: 50%;
    }

    .logo {
      background-size: cover;
      position: absolute;
      width: 50px;
      height: 50px;
      border-radius: 50%;
      top: 12px !important;
      left: 50px;
    }
    .open{
      color:red;
    }
    a {
      text-decoration: none !important;
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
    .table-configuration {
      border : 1px solid black;
      border-collapse: collapse;
      width: 100%;
    }
    .table-configuration th{
      border : 1px solid black;
      background-color: #FFFFFF;
      color : black;
    }
    .table-configuration td {
      border : 1px solid black;
      padding: 10px;
      text-align: left;
      cursor: pointer;
      color: black;
    }

    .table-configuration tr:hover td {
      background-color: #f0f0f0;
    }  {
      max-height: 100px;
      overflow-y: auto;
      border: 1px solid #ccc;
      padding: 10px;
    }
    .menu{
      position: relative;
      display: none;
      z-index: 2000 !important;
    }
    .menu ul{
      margin: 0 !important;
      padding: 0 !important;
      position: absolute;
      right: 10px;
      width: 200px;
      list-style: none;
      border-collapse: collapse;
      border: 1px solid #FFFFFF;
      border-radius: 8px;
      background-color: rgb(255, 255, 255) !important;
    }
    .menu ul li{
      color: black;
      border-bottom: 1px solid black;
      padding: 7.68px;
    }
    p{
      margin: 5px !important;
    }

    .dropdown:hover .dropdown-menu-wrapper {
      display: block !important;
    }

    .table-configuration {
      border-collapse: collapse !important;
      width: 100% !important;
    }

    .table-configuration td {
      padding: 10px !important;
      text-align: left !important;
      cursor: pointer !important;
      color: black !important;
    }
    .table-configuration tr:hover td {
      background-color: #f0f0f0 !important;
    }
  </style>
</head>
<body>
<div class="head">
  <div class="logo">
    <img class="img" src="http://luyencode.online/uploads/logo.webp" alt="Ảnh">
  </div>
  <a class="button button-exam" href="/admin/list">Bài tập</a>
  <a class="button button-status" href="/admin/status">Trạng thái</a>
  <a class="button button-history" href="/admin/history">Lịch sử</a>
  <a class="button button-rank" href="/admin/ranking">Bảng xếp hạng</a>
  <a class="button button-configuration">
    <div style="display: flex; align-items: center; gap: 8px; position: relative;">
      <div class="dropdown">
        <span class="dropdown-btn">Cấu hình</span>
        <div class="dropdown-menu-wrapper">
          <table class="table-configuration">
            <tbody>
            <tr><td><a href="/admin/add" />Thêm bài tập</td></tr>
            <tr><td><a href="/admin/list_contest" />Danh sách cuộc thi</td></tr>
            <tr><td><a href="/admin/exercises" />Danh sách bài tập của bạn</td></tr>
            </tbody>
          </table>
        </div>
        <svg xmlns="http://www.w3.org/2000/svg" width="10" height="10" fill="currentColor" class="bi bi-caret-down-fill" viewBox="0 0 16 16">
          <path d="M7.247 11.14 2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z"/>
        </svg>
      </div>

    </div>
  </a>
  <div class="avatar" onclick="displayAvatar(event)">
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="dropdown-icon bi bi-person" viewBox="0 0 16 16">
      <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>
    </svg>
    <input type="hidden" id="idUser"/>
    <div class="menu" id="menu">
      <ul>
          <li id="profile">

          </li>
        <security:authorize  access="hasRole('MANAGER')">
          <li onclick="account_management()">Quản lí tài khoản</li>
          <li onclick="list_delete_account()">Danh sách tài khoản bị xóa</li>
        </security:authorize>
        <li onclick="update_password()">Thay đổi mật khẩu</li>
        <li onclick="logout()">Đăng xuất</li>
      </ul>
    </div>
  </div>
</div>
</body>
</html>
<script>
  function displayAvatar(event) {
    const avatar = event.currentTarget;
    const menu = avatar.querySelector(".menu");
    if (menu.style.display === "flex") {
      menu.style.display = "none";
    } else {
      menu.style.display = "flex";
    }
    event.stopPropagation();
  }

  document.addEventListener("click", function () {
    const menus = document.querySelectorAll(".menu");
    menus.forEach(menu => {
      menu.style.display = "none";
    });
  });


</script>
<script>
  function account_management(){
    window.location.href = "/admin/list-account";
  }
  function update_password(){
    let id = document.getElementById('idUser').value;
    window.location.href = "/admin/update_password/" + id;
  }
  function list_delete_account(){
    window.location.href = "/admin/list-delete-account";
  }

  function logout(){
    $.ajax({
      type: "POST",
      url: "/api/logout",
      success(){
        window.location.href = "/login";
      }
    });
  }
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<input type="hidden" id="idUser"/>
<div id="profile"></div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  function loadUser() {
    $.ajax({
      type: "POST",
      url: "/api/home",
      contentType: "application/json",
      dataType: "JSON",
      success: function (id) {
        $.ajax({
          type: "POST",
          url: "/api/loader",
          data: JSON.stringify(id),
          contentType: "application/json",
          dataType: "JSON",
          success: function (response) {
            document.getElementById('idUser').value = response.id;
            var item = document.getElementById('profile');
            if (item) {
              item.innerHTML = "";
              var tmp = "";
              tmp += '<p><strong>' + response.fullname + '</strong></p>' + '\n';
              tmp += '<p>' + response.username + '</p>' + '\n';
              item.innerHTML = tmp;
            } else {
              console.error("Không tìm thấy phần tử #profile");
            }
          },
          error: function (e) {
            console.error("Lỗi khi gọi API loadUser:", e);
          }
        });
      },
      error: function (e) {
        console.error("Lỗi khi gọi API home:", e.responseText);
      }
    });
  }
  $(document).ready(function () {
    loadUser();
  });
</script>
