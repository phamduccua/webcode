<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 06/01/2025
  Time: 5:18 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
    <meta charset="UTF-8"/>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .main {
            background-color: #fff;
            border: 1px solid black;
            width: 500px;
            height: 300px;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .formInput {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .label {
            text-align: center;
            font-size: 20px;
            margin-bottom: 20px;
        }
        .item-name {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 80%;
        }
        .item-name-label {
            font-size: 16px;
            width: 100px;
        }
        .item-name-input {
            flex: 1;
            padding: 5px;
        }
        .button {
            color: #FFF;
            background-color: #bb2019;
            height: 35px;
            width: 100px;
            margin-top: 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #a31815;
        }
    </style>
</head>
<body>
<div class="main">
    <!-- Thêm id="loginForm" để gắn sự kiện submit -->
    <form class="formInput" id="loginForm">
        <label class="label">
            <p>Đăng nhập</p>
            <p><strong>Luyện Code Online</strong></p>
        </label>
        <div class="item-name">
            <label class="item-name-label">Username:</label>
            <input type="text" class="item-name-input" name="username" required/>
        </div>
        <div class="item-name">
            <label class="item-name-label">Password:</label>
            <input type="password" class="item-name-input" name="password" required/>
        </div>
        <button type="submit" class="button">Đăng nhập</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script>
    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(event.target);
        const data = Object.fromEntries(formData.entries());

        $.ajax({
            type: "POST",
            url: "/login",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (response) {
                document.cookie = `token=` + response.token + `; path=/; max-age=604800;`;
                Swal.fire({
                    title: 'Thành công!',
                    text: 'Đăng nhập thành công!',
                    icon: 'success',
                    confirmButtonText: 'OK'
                })
                if(response.role === 'ADMIN'){
                    window.location.href = "/admin/list";
                }
                else if(response.role === 'USER'){
                    window.location.href = "/api/question";
                }
            },
            error: function (e) {
                Swal.fire({
                    title: 'Lỗi!',
                    text: e.responseText || 'Đăng nhập thất bại!',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        });
    });
</script>
</body>
</html>
