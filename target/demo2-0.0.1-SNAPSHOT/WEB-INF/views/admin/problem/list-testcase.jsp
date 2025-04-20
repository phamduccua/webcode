<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 24/11/2024
  Time: 2:06 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="menu-edit.jsp" %>
<html>
<head>
  <meta charset="UTF-8">
  <style>
    #home {
      background-color: #8A1111;
    }

    .main {
      width: 1178px; /* Làm tròn giá trị */
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

    .form-group > div {
      margin-bottom: 20px;
      display: flex;
    }

    .label {
      font-size: 16px;
      width: 150px;
      text-align: left;
      margin-right: 30px;
    }
    .dropdown {
      position: relative; /* Định vị tương đối */
      display: inline-block;
      overflow: visible;
    }

    /* Nút "Cấu hình" */
    .dropdown-btn {
      cursor: pointer; /* Biểu tượng trỏ chuột khi hover */
      color: white; /* Màu chữ */
      font-weight: bold;
    }
    .dropdown-icon {
      cursor: pointer; /* Trỏ chuột khi hover vào icon */
    }

    /* Ẩn menu mặc định */
    /* Định vị lại menu để không bị che khuất */
    .dropdown-menu-wrapper {
      display: none; /* Ẩn menu mặc định */
      position: absolute; /* Định vị menu thả xuống */
      top: 100%; /* Hiển thị ngay bên dưới icon */
      right: 0; /* Căn lề phải để tránh tràn */
      background-color: white; /* Nền trắng */
      border: 1px solid #ccc; /* Viền bảng */
      border-radius: 4px; /* Bo góc */
      z-index: 1000; /* Đảm bảo menu nằm trên cùng */
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Đổ bóng */
      overflow-y: auto;
      width: max-content; /* Kích thước vừa với nội dung */
    }

    .dropdown:hover .dropdown-menu-wrapper {
      display: block;
    }


    /* Table configuration */
    .table-configuration {
      border-collapse: collapse;
      width: 100%;
    }

    .table-configuration td {
      padding: 10px;
      text-align: left; /* Căn chữ sang trái */
      cursor: pointer;
      color: black;
    }

    /* Hiệu ứng hover trên từng dòng */
    .table-configuration tr:hover td {
      background-color: #f0f0f0; /* Highlight row */
    }
    /* Thêm thanh cuộn cho checkbox */
    .scrollable-checkboxes {
      max-height: 100px; /* Chiều cao tối đa của danh sách checkbox */
      overflow-y: auto; /* Thêm thanh cuộn dọc */
      border: 1px solid #ccc; /* Đường viền để phân biệt danh sách */
      padding: 10px; /* Khoảng cách bên trong */
    }
    .table {
      border-spacing: 0;
      border-radius: 4px;
      /*overflow: hidden;*/
      width: 100%;
      border: 1px solid #ccc;
    }
    .table-head {
      font-size: 16px;
      font-weight: bold;
      color: #FFFFFF;
    }

    .table-head th {
      text-align: center; /* Căn chữ vào giữa theo chiều ngang */
      vertical-align: middle; /* Căn chữ vào giữa theo chiều dọc */
      height: 30px; /* Tăng chiều cao của thẻ <th> */
      padding: 10px; /* Khoảng cách bên trong */
      font-size: 16px; /* Kích thước chữ */
      font-weight: bold; /* Đậm chữ */
      color: #FFFFFF !important; /* Màu chữ */
      background-color: #8A1111 !important ; /* Màu nền */
    }

    .table-body td {
      text-align: center;
      padding: 10px;
      border-bottom: 1px solid #ccc;
    }

    /* Giảm kích thước ô STT */
    .text-middle {
      width: 40px; /* Hoặc tùy chỉnh kích thước nhỏ hơn nếu cần */
      min-width: 40px;
      padding: 0;
      text-align: center;
    }
    .button{
      border-radius: 5px ;
      margin-top : 10px;
      margin-bottom: 20px;
      background-color: #8A1111;
      color: #FFFFFF;
    }
    .config{
      position: absolute;
      display: none;
      z-index: 1000;
    }
    .config ul{
      width: 140px;
      margin: 0 !important;
      padding: 0 !important;
      list-style: none;
      background-color: #FFFFFF;
    }
    .config li{
      padding: 5px;
      border: 1px solid black;
      margin: -1px;
      height: 40px;
    }
  </style>
</head>
<body>

<div class="main">
  <button class="button" id="btnadd">Thêm Test Case mới</button>
  <form:form id="listTestCase" modelAttribute="listTest" method="GET">
    <table class="table table-fixed">
      <thead class="table-head">
      <tr>
        <th class="text-middle">STT</th>
        <th>Đầu vào</th>
        <th>Đầu ra</th>
        <th>Ví dụ</th>
        <th>Thao tác</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="item" items="${listTest}" varStatus="status">
      <tr class="table-body">
        <td>
          ${status.count}
        </td>
        <td>input${status.count}</td>
        <td>output${status.count}</td>
        <td>
            <input class="exam-checkbox" type="checkbox" value="${item.example}"
                   ${item.example != null ? 'checked' : null}
                   onchange="example(${item.id},this.checked)" />
        </td>
        <td class="midd">
          <div class="box" onclick="configdisplay(this)">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="dropdown-icon bi bi-three-dots-vertical" viewBox="0 0 16 16">
              <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0"/>
            </svg>
            <div class="config" >
              <ul>
                <li onclick="doi_trang(${item.id})">Chỉnh sửa</li>
                <li onclick="deleteTest(${item.id})">Xóa</li>
              </ul>
            </div>
          </div>
        </td>
      </tr>
      </c:forEach>
      </tbody>
    </table>
</div>
</form:form>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  function configdisplay(element) {
    const allMenus = document.querySelectorAll(".config");
    allMenus.forEach(menu => {
      if (menu !== element.querySelector(".config")) {
        menu.style.display = "none";
      }
    });
    const configMenu = element.querySelector(".config");
    if (configMenu.style.display === "flex") {
      configMenu.style.display = "none";
    } else {
      configMenu.style.display = "flex";
    }
    configMenu.addEventListener("click", function (event) {
      event.stopPropagation();
    });
    document.addEventListener("click", function hideMenus(event) {
      if (!element.contains(event.target)) {
        allMenus.forEach(menu => {
          menu.style.display = "none";
        });
        document.removeEventListener("click", hideMenus);
      }
    });
  }
</script>
<script>
  $('#btnadd').click(function(e){
    e.preventDefault();
    window.location.href = "/admin/add_testcase-problem-${problemCode}";
  });
  function doi_trang(id) {
    window.location.href = "/admin/edit_testcase-" + id;
  }
</script>
<script>
  function example(id,check){
    let data = {};
    data['id'] = id;
    data['example'] = check !== false ? 'check' : null;
    $.ajax({
      type: "PUT",
      url: "/admin/testcase/edit_example",
      data: JSON.stringify(data),
      contentType: "application/json",
      success(){
        Swal.fire({
          text: 'Đã thay đổi thành công',
          icon: 'success',
          confirmButtonText: 'OK'
        }).then(() => {
          location.reload();
        });
      },
      error(e){
        Swal.fire({
          title: 'Lỗi!',
          text: 'Thay đổi không thành công. Vui lòng thử lại.',
          icon: 'error',
          confirmButtonText: 'OK'
        });
      }
    })
  }
  function deleteTest(id){
    $.ajax({
      type: "DELETE",
      url: "/admin/testcase/delete/" + id,
      success(){
        Swal.fire({
          text: 'Đã xóa Test Case thành công',
          icon: 'success',
          confirmButtonText: 'OK'
        }).then(() => {
          location.reload();
        });
      },
      error(e){
        Swal.fire({
          title: 'Lỗi!',
          text: 'Xóa Test Case không thành công. Vui lòng thử lại.',
          icon: 'error',
          confirmButtonText: 'OK'
        });
      }
    })
  }
</script>
</body>
</html>
