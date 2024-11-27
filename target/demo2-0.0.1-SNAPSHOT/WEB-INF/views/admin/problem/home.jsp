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
  <a class="button button-exam" href="list">Bài tập</a>
  <a class="button button-status" href="https://www.youtube.com/">Trạng thái</a>
  <a class="button button-history" href="#">Lịch sử</a>
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
  <div class="avatar">
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
      <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>
    </svg>
  </div>
</div>
</body>
</html>
