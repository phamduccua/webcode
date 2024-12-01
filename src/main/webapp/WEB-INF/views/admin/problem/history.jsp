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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
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
            <c:forEach var="item" items="${listSub}" >
                <tr>
                    <td>${item.id}</td>
                    <td>
                        <p>${item.formattedDate}</p>
                        <p>${item.formattedTime}</p>
                    </td>
                    <td>
                    <a href="/admin/assignment-${item.problemCode}" style="color: #d30000;">${item.problemName}
                    </td>
                    <c:if test="${item.status == 0}">
                        <td class="spinner">
                            <div class="spinner-border text-primary small-spinner" role="status">
                                <span class="visually-hidden">Loading...</span>
                            </div>
                        </td>
                        <td></td>
                        <td></td>
                    </c:if>
                    <c:if test="${item.status != 0}">
                        <c:if test="${item.status == 1}">
                            <td style="color: #19BE6B;">AC</td>
                            <td>${item.executionTime}</td>
                            <td>${item.memoryUsed}</td>
                        </c:if>
                        <c:if test="${item.status != 1}">
                            <td style="color: #FF0000;">WA</td>
                            <td>${item.executionTime}</td>
                            <td>${item.memoryUsed}</td>
                        </c:if>
                    </c:if>
                    <td>${item.language}</td>
                </tr>
            </c:forEach>


            </tbody>
        </table>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    var submittedAt = "2024-11-30T14:30:00";
    var dateTime = new Date(submittedAt);

    var date = dateTime.toISOString().split('T')[0];
    var time = dateTime.toTimeString().split(' ')[0];

    document.getElementById("date-time").innerHTML = "<span>" + date + "</span><br><span>" + time + "</span>";
</script>
<script>
    function  updateStatus(){
        var ids = [];
        $("tr").each(function(){
            var status  = $(this).find("td:nth-child(4)").text();
            if(status == "0"){
                var id = $(this).find("td:nth-child(1)").text();
                ids.push(id);
            }
        });
        if(ids.length > 0){
            $ajax({
                type : "POST",
                url : "/admin/update-status",
                contentType : "application/json",
                data : JSON.stringify({ids : ids}),
                success: function(response) {
                    response.forEach(function(item){
                        $("tr").each(function(){
                            var subId = $(this).find("td:nth-child(1)").text();
                            if(subId == item.id){
                                $(this).find("td:nth-child(4)").text(item.status);
                                $(this).find("td:nth-child(5)").text(item.executionTime);
                                $(this).find("td:nth-child(6)").text(item.memoryUsed);
                            }
                        });
                    });
                },
                error: function(error) {
                    console.error('Cập nhật thất bại', error);
                }
            });
        }
    }
    setTimeout(updateStatus, 3000);
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
        background-color: white !important;
        width: 40px !important;
        height: 40px !important;
        border-radius: 50% !important;
        position: absolute !important;
        top: 15px !important;
        right: 40px !important;
        display: flex !important;
        justify-content: center !important;
        align-items: center !important;
    }

    .img {
        width: 70% !important;
        height: 70% !important;
    }

    .logo {
        /*background-image: url("logo.webp");*/
        background-size: cover !important;
        position: absolute !important;
        width: 50px !important;
        height: 50px !important;
        border-radius: 50% !important;
        top: 12px !important;
        left: 50px !important;
    }
    .open {
        color: red !important;
    }
    a {
        text-decoration: none !important;
        pointer-events: auto !important;
    }
    .main {
        width: 1177.6px !important;
        margin: 30.39px auto 0 !important;
        padding: 25.6px 39.19px !important;
        border-radius: 4px !important;
        border: solid 1.6px #e1e4e8 !important;
    }
    #search {
        float: right !important;
        border-radius: 4px !important;
        border: solid 1.6px #e1e4e8 !important;
        background-color: #FAEDED !important;
        width: 273.6px !important;
        height: 28px !important;
        padding-left: 8.8px !important;
    }
    #group {
        float: right !important;
        border-radius: 4px !important;
        border: solid 1.6px #e1e4e8 !important;
        background-color: white !important;
        width: 373.6px !important;
        height: 28px !important;
        padding-left: 8.8px !important;
    }
    .table {
        border-spacing: 0 !important;
        border-radius: 4px !important;
        width: 100% !important;
        border: 1px solid #ccc !important;
        margin-top: 30px !important;
    }
    .dropdown-header {
        display: flex !important; /* Sử dụng Flexbox */
        justify-content: center !important; /* Căn giữa theo chiều ngang */
        align-items: center !important; /* Căn giữa theo chiều dọc */
        gap: 5px !important; /* Khoảng cách giữa chữ và biểu tượng */
        height: 100% !important; /* Chiều cao bằng với thẻ <th> */
    }

    .table-head {
        background-color: #8A1111 !important;
        font-size: 16px !important;
        font-weight: bold !important;
        color: #FFFFFF !important;
    }

    .table-head th {
        text-align: center !important; /* Căn chữ vào giữa theo chiều ngang */
        vertical-align: middle !important; /* Căn chữ vào giữa theo chiều dọc */
        height: 50px !important; /* Tăng chiều cao của thẻ <th> */
        padding: 10px !important; /* Khoảng cách bên trong */
        font-size: 16px !important; /* Kích thước chữ */
        font-weight: bold !important; /* Đậm chữ */
        color: #FFFFFF !important; /* Màu chữ */
        background-color: #8A1111 !important; /* Màu nền */
    }

    .table-body td {
        text-align: center !important;
        padding: 10px !important;
        border-bottom: 1px solid #ccc !important;
    }
    /* Giảm kích thước ô STT */
    .text-middle {
        width: 40px !important; /* Hoặc tùy chỉnh kích thước nhỏ hơn nếu cần */
        min-width: 40px !important;
        padding: 0 !important;
        text-align: center !important;
    }
    /* Dropdown container */
    .dropdown {
        position: relative !important; /* Định vị tương đối */
        display: inline-block !important;
        overflow: visible !important;
    }

    /* Nút "Cấu hình" */
    .dropdown-btn {
        cursor: pointer !important; /* Biểu tượng trỏ chuột khi hover */
        color: white !important; /* Màu chữ */
        font-weight: bold !important;
    }
    .dropdown-icon {
        cursor: pointer !important; /* Trỏ chuột khi hover vào icon */
    }

    /* Ẩn menu mặc định */
    /* Định vị lại menu để không bị che khuất */
    .dropdown-menu-wrapper {
        display: none !important; /* Ẩn menu mặc định */
        position: absolute !important; /* Định vị menu thả xuống */
        top: 100% !important; /* Hiển thị ngay bên dưới icon */
        right: 0 !important; /* Căn lề phải để tránh tràn */
        background-color: white !important; /* Nền trắng */
        border: 1px solid #ccc !important; /* Viền bảng */
        border-radius: 4px !important; /* Bo góc */
        z-index: 1000 !important; /* Đảm bảo menu nằm trên cùng */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2) !important; /* Đổ bóng */
        overflow-y: auto !important;
        width: max-content !important; /* Kích thước vừa với nội dung */
    }

    .dropdown:hover .dropdown-menu-wrapper {
        display: block !important;
    }

    /* Table configuration */
    .table-configuration {
        border-collapse: collapse !important;
        width: 100% !important;
    }

    .table-configuration td {
        padding: 10px !important;
        text-align: left !important; /* Căn chữ sang trái */
        cursor: pointer !important;
        color: black !important;
    }

    /* Hiệu ứng hover trên từng dòng */
    .table-configuration tr:hover td {
        background-color: #f0f0f0 !important; /* Highlight row */
    }

    /* Thêm thanh cuộn cho checkbox */
    .scrollable-checkboxes {
        max-height: 100px !important; /* Chiều cao tối đa của danh sách checkbox */
        overflow-y: auto !important; /* Thêm thanh cuộn dọc */
        border: 1px solid #ccc !important; /* Đường viền để phân biệt danh sách */
        padding: 10px !important; /* Khoảng cách bên trong */
    }

    /* Căn chỉnh nút Lọc xuống dưới cùng */
    .filter-button-container {
        text-align: center !important; /* Căn nút Lọc vào giữa */
        margin-top: 10px !important; /* Khoảng cách giữa danh sách và nút */
    }

    .filter-button {
        padding: 5px 15px !important; /* Kích thước nút */
        background-color: #f5f5f5 !important; /* Màu nền */
        border: 1px solid #ccc !important; /* Đường viền */
        cursor: pointer !important; /* Con trỏ chuột */
        border-radius: 5px !important; /* Bo góc */
    }
    .filter-button:hover {
        background-color: #e0e0e0 !important; /* Hiệu ứng khi hover */
    }
    p {
        margin: 0px !important;
    }
    .small-spinner {
        width: 20px !important;
        height: 20px !important;
        border-width: 2px !important;
    }

</style>

</body>
</html>
