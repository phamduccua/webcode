<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
</head>
<body>
<div class="head">
  <div class="logo">
    <img souce="logo.webp" alt>
  </div>
  <a class="button button-exam" href="/admin/list">Bài tập</a>
  <a class="button button-status" href="https://www.youtube.com/">Trạng thái</a>
  <a class="button button-history" href="/admin/history">Lịch sử</a>
  <a class="button button-rank" href="#">Bảng xếp hạng</a>
  <a class="button button-configuration">
    <div style="display: flex; align-items: center; gap: 8px; position: relative;">
      <div class="dropdown">
        <span class="dropdown-btn">Cấu hình</span>
        <div class="dropdown-menu-wrapper">
          <table class="table-configuration">
            <tbody>
            <tr><td><a href="add" />Thêm bài tập</td></tr>
            <tr><td>Thêm cuộc thi</td></tr>
            </tbody>
          </table>
        </div>
        <svg xmlns="http://www.w3.org/2000/svg" width="10" height="10" fill="currentColor" class="bi bi-caret-down-fill" viewBox="0 0 16 16">
          <path d="M7.247 11.14 2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z"/>
        </svg>
      </div>

    </div>
  </a>
  <a class="button button-gui" href="#">Hướng dẫn</a>
  <div class="avatar" onmouseenter="display()" onmouseleave="undisplay()">
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
      <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>
    </svg>
    <div class="menu" id="menu">
      <ul>
        <li>
          <p><strong>Nguyễn Văn A</strong></p>
          <p>22/12/1999</p>
        </li>
        <li onclick="account_management()">Quản lí tài khoản</li>
        <li onclick="update_password()">Thay đổi mật khẩu</li>
        <li>Đăng xuất</li>
      </ul>
    </div>
  </div>
</div>
</body>
</html>
<script>
  function display(){
    const menu = document.getElementById("menu");
    menu.style.display = "flex";
  }
  function undisplay(){
    const menu = document.getElementById("menu");
    menu.style.display = "none";
  }
</script>
<script>
  function account_management(){
    window.location.href = "/admin/list-account";
  }
  function update_password(){
    window.location.href = "/api/update_password-12";
  }
</script>
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
  }

  .logo {
    background-size: cover;
    position: absolute;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    top: 12px;
    left: 50px;
  }
  .open{
    color:red;
  }
  a {
    text-decoration: none !important;
    pointer-events: auto !important;
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
    color: #5e6c84 !important;
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
