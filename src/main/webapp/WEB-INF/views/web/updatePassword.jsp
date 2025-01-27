<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 10/01/2025
  Time: 11:44 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="home.jsp" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Thay đổi mật khẩu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="main">
    <div class="title">
        <h1>Thay đổi mật khẩu</h1>
    </div>
    <form id="listForm">
        <div>
            <label for="password">Mật khẩu hiện tại:</label>
            <input id="password" type="password" placeholder="Nhập mật khẩu hiện tại" name="password"/>
        </div>
        <div>
            <label for="newPassword">Mật khẩu mới:</label>
            <input id="newPassword" type="password" placeholder="Nhập mật khẩu mới" name="newPassword"/>
        </div>
        <div>
            <label for="rentPassword">Xác nhận mật khẩu:</label>
            <input id="rentPassword" type="password" placeholder="Xác nhận mật khẩu" name="rentPassword"/>
        </div>
        <input type="hidden" value="10" name="id"/>
        <div class="btnupdate" onclick="updatePass()">Thay đổi mật khẩu</div>
    </form>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function updatePass(){
        var data = {};
        var formData =$('#listForm').serializeArray();
        $.each(formData, function(i, v){
            data[v.name] = v.value;
        });
        console.log(data);
        if(data.newPassword.length < 8){
            Swal.fire({
                title: 'Lỗi!',
                text:   'Mật khẩu phải dài hơn 8 kí tự',
                icon: 'info',
                confirmButtonText: 'OK'
            });
        }
        else if(data.newPassword !== data.rentPassword){
            Swal.fire({
                title: 'Lỗi!',
                text:  'Mật khẩu xác nhận không chính xác',
                icon: 'info',
                confirmButtonText: 'OK'
            });
        }
        else{
            $.ajax({
                type: "PUT",
                url: "/web/api/update_password",
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "JSON",
                success: function(response) {
                    Swal.fire({
                        text: 'Đã đổi mật khẩu thành công thành công',
                        icon: 'success',
                        confirmButtonText: 'OK'
                    })
                },
                error: function(e) {
                    console.log(e);
                    Swal.fire({
                        title: 'Lỗi!',
                        text:   e.responseText,
                        icon: 'error',
                        confirmButtonText: 'OK'
                    });
                }
            })
        }

    }
</script>
<style>
    .main{
        border: 1px solid black;
        width: 1177.6px;
        margin: 30.39px auto 0;
    }
    .title{
        margin-top: 30px;
        display: flex;
        justify-content: center;
    }
    label{
        margin-left: 100px;
    }
    input{
        margin-right: 90%;
        margin-bottom: 30px;
        margin-left: 100px;
        margin-top: 10px;
        width: 350px;
        height:30px;
        border-radius: 5px;
    }
    .btnupdate{
        display: flex;
        justify-content: center;
        align-items: center;
        margin-left: 100px;
        margin-bottom: 20px;
        background-color: brown;
        color: #FFF;
        border-radius: 8px;
        width: 180px;
        height: 30px;
    }
</style>
</html>
