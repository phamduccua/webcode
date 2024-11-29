<%@ taglib prefix="f" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="home.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/11/2024
  Time: 11:44 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài tập</title>
</head>
<body>
<div class="main">

    <form>
        <label><strong>Lịch sử nộp bài</strong></label>
        <table id="tableList" class="table table-fixed">
            <thead class="table-head">
            <tr>
                <th class="text-middle">ID</th>
                <th>Thời gian</th>
                <th>Bài tập</th>
                <th>Kết quả</th>
                <th>Thời gian</th>
                <th class="mid">Bộ nhớ</th>
                <th class="mid">Trình biên dịch</th>
            </tr>
            </thead>
            <tbody class="table-body">
                <td>10766773</td>
                <td>
                  <p>2024-11-27</p>
                  <p>22:29:58</p>
                </td>
                <td>Hello World</td>
                <td>AC</td>
                <td>0.00s</td>
                <td>1528Kb</td>
                <td>C/C++</td>
            </tbody>
        </table>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function listGroup(){
        document.getElementById("listForm").submit();
    }
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
            url: "/admin/problem/delete-item/" + itemId,
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







<style>
    .head {
        width: 100%;
        height: 60px;
        box-sizing: border-box;
        background-color: #8B1A1A;
    }
    .button {
        color: white;
        position: absolute;
        top:18px;
        border: none;
        padding: 10px 20px;
        text-align: center;
    }
    .button-exam {
        left: 120px;
    }
    .button-status {
        left: 200px;
    }
    .button-history {
        left: 300px;
    }
    .button-rank {
        left: 380px;
    }
    .button-configuration{
        left: 510px;
    }
    .button-gui {
        left: 620px;
    }
    .avatar {
        background-color: white;
        width: 40px;
        height: 40px;
        border-radius: 50%;
        position: absolute;
        top: 15px;
        right: 40px;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .img {
        width: 70%;
        height: 70%;
    }

    .logo {
        /*background-image: url("logo.webp");*/
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
        text-decoration: none;
        pointer-events: auto !important;
    }
    .main {
        width: 1177.6px;
        margin: 30.39px auto 0;
        padding: 25.6px 39.19px;
        border-radius: 4px;
        border: solid 1.6px #e1e4e8;
    }
    #search {
        float: right;
        border-radius: 4px;
        border: solid 1.6px #e1e4e8;
        background-color: #FAEDED;
        width: 273.6px;
        height: 28px;
        padding-left: 8.8px;
    }
    #group {
        float: right;
        border-radius: 4px;
        border: solid 1.6px #e1e4e8;
        background-color: white;
        width: 373.6px;
        height: 28px;
        padding-left: 8.8px;
    }
    .table {
        border-spacing: 0;
        border-radius: 4px;
        /*overflow: hidden;*/
        width: 100%;
        border: 1px solid #ccc;
        margin-top: 30px;
    }
    .dropdown-header {
        display: flex; /* Sử dụng Flexbox */
        justify-content: center; /* Căn giữa theo chiều ngang */
        align-items: center; /* Căn giữa theo chiều dọc */
        gap: 5px; /* Khoảng cách giữa chữ và biểu tượng */
        height: 100%; /* Chiều cao bằng với thẻ <th> */
    }

    .table-head {
        background-color: #8A1111;
        font-size: 16px;
        font-weight: bold;
        color: #FFFFFF;
    }

    .table-head th {
        text-align: center; /* Căn chữ vào giữa theo chiều ngang */
        vertical-align: middle; /* Căn chữ vào giữa theo chiều dọc */
        height: 50px; /* Tăng chiều cao của thẻ <th> */
        padding: 10px; /* Khoảng cách bên trong */
        font-size: 16px; /* Kích thước chữ */
        font-weight: bold; /* Đậm chữ */
        color: #FFFFFF; /* Màu chữ */
        background-color: #8A1111; /* Màu nền */
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
    /* Dropdown container */
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

    /* Căn chỉnh nút Lọc xuống dưới cùng */
    .filter-button-container {
        text-align: center; /* Căn nút Lọc vào giữa */
        margin-top: 10px; /* Khoảng cách giữa danh sách và nút */
    }

    .filter-button {
        padding: 5px 15px; /* Kích thước nút */
        background-color: #f5f5f5; /* Màu nền */
        border: 1px solid #ccc; /* Đường viền */
        cursor: pointer; /* Con trỏ chuột */
        border-radius: 5px; /* Bo góc */
    }
    .filter-button:hover {
        background-color: #e0e0e0; /* Hiệu ứng khi hover */
    }


</style>
</body>
</html>
