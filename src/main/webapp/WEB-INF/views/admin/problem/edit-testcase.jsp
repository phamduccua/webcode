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
    body {
      font-family: "Times New Roman", Times, serif !important;
    }

  </style>
</head>
<body>

<div class="main">
  <button data-bs-toggle="modal" data-bs-target="#exampleModal" class="button">Thêm Test Case mới</button>
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
          <input type="checkbox" value="${item.id}"/>
          ${status.count}
        </td>
        <td>input${status.count}</td>
        <td>output${status.count}</td>
        <td>
            <input class="exam-checkbox" type="checkbox" value="${item.example}"
                   ${item.example != "" ? 'checked' : ''}
                   onchange="edit(${item.id},this.checked)" />
        </td>

        <td>
          <div class="dropdown">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="dropdown-icon bi bi-three-dots-vertical" viewBox="0 0 16 16">
              <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0"/>
            </svg>
            <div class="dropdown-menu-wrapper">
              <table class="table-configuration">
                <tbody>
                <tr>
                  <td>
                    <div
                      data-bs-toggle="modal"
                      data-bs-target="#exampleModal"
                      data-id="${item.id}"
                      data-input="${item.input}"
                      data-output="${item.expected_output}"
                      data-checkbox="${item.example != '' ? 'check' : ''}">
                      Chỉnh sửa
                    </div>
                  </td>
                </tr>
                <tr><td onclick="confirmDelete(${item.id})" >Xóa</td></tr>
                </tbody>
              </table>
            </div>
          </div>
        </td>
      </tr>
      <input id="testInput-${item.id}" type="hidden" value="${item.input}" />
      <input id="testOuput-${item.id}" type="hidden" value="${item.expected_output}" />
      </c:forEach>

      </tbody>
    </table>
</div>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Test Case</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form id="testCase">
            <div class="mb-3">
              <label for="inputContent" class="form-label">Input</label>
              <textarea name="input" class="form-control" id="inputContent" rows="6" placeholder="Enter Input"></textarea>
            </div>
            <div class="mb-3">
              <label for="outputContent" class="form-label">Output</label>
              <textarea name="expected_output" class="form-control" id="outputContent" rows="6" placeholder="Enter Output"></textarea>
            </div>
            <input name="id" type="hidden" id="itemId" value=""/>
            <input name="problemId" type="hidden" id="problemId" value="${id}" />
            <input name="example" type="hidden" id="checkboxValue" value=""/>
            <div id="actionButton">
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</form:form>
<script>
  const modal = new bootstrap.Modal(document.getElementById('exampleModal'));
  function api(data) {
      $.ajax({
        type: "POST",
        url: "/admin/testcase/",
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
        error: function () {
          Swal.fire({
            title: 'Lỗi!',
            text: 'Đã xảy ra lỗi khi xử lý yêu cầu, vui lòng thử lại.',
            icon: 'error',
            confirmButtonText: 'OK'
          });
        }
      });
    }
  $(document).on('click', '#edit', function (e) {
    e.preventDefault();
    var data = {};
    data.id = $('#itemId').val();
    data.input = $('#inputContent').val();
    data.expected_output = $('#outputContent').val();
    data.problemId = $('#problemId').val();
    var tmp = $('#checkboxValue').val();
    data.example = (tmp != null ? tmp : "");
    api(data);
  });
  function edit(id,example){
    var data = {};
    data.id = id;
    data.input = $('#testInput-' + id).val();
    data.expected_output = $('#testOuput-' + id).val();
    data.problemId = ${id};
    data.example = (example === true ? "check" : '');
    api(data);
  }
  $(document).on('click', '#add', function (e) {
    e.preventDefault();
    var data = {};
    data.id = $('#itemId').val();
    data.input = $('#inputContent').val();
    data.expected_output = $('#outputContent').val();
    data.problemId = $('#problemId').val();
    var tmp = $('#checkboxValue').val();
    data.example = (tmp != null ? tmp : "");
    api(data);
  });

  $('#exampleModal').on('show.bs.modal', function (event) {
    var div = $(event.relatedTarget);
    var itemId = div.data('id');
    var input = div.data('input');
    var output = div.data('output');
    var checkboxValue = div.data('checkbox');
    $('#itemId').val(itemId);
    $('#inputContent').val(input);
    $('#outputContent').val(output);
    $('#checkboxValue').val(checkboxValue);
    var actionButtonDiv = document.getElementById('actionButton');
    if (itemId === undefined) {
      actionButtonDiv.innerHTML = `
                  <button id="add" type="button" class="btn btn-primary" data-bs-dismiss="modal">Thêm</button>
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Trở lại</button>
                `;
    } else {
      actionButtonDiv.innerHTML = `
                    <button id="edit" type="button" class="btn btn-primary" data-bs-dismiss="modal">Sửa</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Trở lại</button>
                  `;
    }
  });

</script>

<script>
  function confirmDelete(itemId) {
    Swal.fire({
      title: 'Bạn có chắc chắn?',
      text: 'Nếu không muốn xóa hãy nhấn hủy!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Xóa',
      cancelButtonText: 'Hủy',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        deleteItem(itemId);
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire({
          title: 'Đã hủy!',
          text: 'Không có thay đổi nào được thực hiện.',
          icon: 'info',
          confirmButtonText: 'OK'
        });
      }
    });
  }

  function deleteItem(itemId) {
    $.ajax({
      type: "DELETE",
      url: "/admin/testcase/delete-item/" + itemId,
      success: function () {
        Swal.fire({
          title: 'Đã xóa!',
          text: 'Mục đã được xóa thành công.',
          icon: 'success',
          confirmButtonText: 'OK'
        }).then(() => {
          location.reload();
        });
      },
      error: function () {
        Swal.fire({
          title: 'Lỗi!',
          text: 'Không thể xóa mục này. Vui lòng thử lại.',
          icon: 'error',
          confirmButtonText: 'OK'
        });
      }
    });
  }
</script>
</body>
</html>
