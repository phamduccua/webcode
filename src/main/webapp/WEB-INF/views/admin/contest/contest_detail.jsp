<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/01/2025
  Time: 11:05 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/admin/problem/home.jsp" %>
<%@ include file="contest_menu.jsp" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Chi tiết cuộc thi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="main_detail">
        <form:form id="formData" modelAttribute="contestDTO" method="GET">
            <div class="detail">
                <div class="detail_item">
                    <label >Tên cuộc thi:</label>
                    <form:input type="text" placeholder="Nhập tên cuộc thi" path="name"/>
                </div>
                <div class="detail_item">
                    <label>Thời gian bắt đầu</label>
                    <form:input type="datetime-local" path="start_time"/>
                </div>
                <div class="detail_item">
                    <label>Thời gian kết thúc</label>
                    <form:input type="datetime-local" path="end_time"/>
                </div>
                <form:input type="hidden" path="id" />
                <div class="detail_item btnupdate" onclick="updateContest()">
                    <div class="btncreaet">Cập nhật</div>
                </div>
            </div>
        </form:form>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    function updateContest(){
        var data = {};
        var formData = $('#formData').serializeArray();
        $.each(formData, function(i,v){
            data[v.name] = v.value;
        });
        const now = new Date();
        const startTime = new Date(data['start_time']);
        const endTime = new Date(data['end_time']);
        if(data['start_time'] === undefined || data['start_time'] === "" || data['end_time'] === undefined || data['end_time'] ===""){
            Swal.fire({
                title: 'Lỗi!',
                text: 'Thời gian bắt đầu và kết thúc cuộc thi không được để trống !!',
                icon: 'error',
                confirmButtonText: 'OK'
            });
        }
        else if(endTime < now){
            Swal.fire({
                title: 'Lỗi!',
                text: 'Thời gian kết thúc phải là thời gian trong tương lai !!',
                icon: 'error',
                confirmButtonText: 'OK'
            });
        }
        else if(startTime > endTime){
            Swal.fire({
                title: 'Lỗi!',
                text: 'Thời gian bắt đầu phải trước thời gian kết thúc !!',
                icon: 'error',
                confirmButtonText: 'OK'
            });
        }
        else{
            $.ajax({
                type: "PUT",
                url: "/demo2-0.0.1-SNAPSHOT/admin/update_contest",
                data: JSON.stringify(data),
                contentType: "application/json",
                success(){
                    Swal.fire({
                        text: 'Đã cập nhật cuộc thi thành công',
                        icon: 'success',
                        confirmButtonText: 'OK'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            location.reload();
                        }
                    });
                },
                error:function(e){
                    Swal.fire({
                        title: 'Lỗi!',
                        text: 'Không thể cập  nhật cuộc thi !!',
                        icon: 'error',
                        confirmButtonText: 'OK'
                    });
                }
            })
        }

    }
</script>
<style>
    .main_detail{
        width: 1177.6px;
        margin: 30.39px auto 0;
        border: 1px solid black;
    }
    .detail{
        margin-top: 30px;
        background-color:#FFFFFF;
        border-radius: 5px;
        padding: 30px;
        margin-left: 150px;
        border: 1px solid black;
        width: 70%;
        margin-bottom: 30px;
    }
    .detail_item{
        margin-bottom: 80px;
    }
    .detail .detail_item:first-of-type {
        margin-top: 30px;
    }
    .detail_item label{
        margin-right: 50px;
        margin-left: 30px;
    }
    .detail_item input{
        height: 30px;
        width: 350px;
        border-radius: 5px;
    }
    .btnupdate{
        margin-left: 30px;
        margin-top: 30px;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: brown;
        color: #fff;
        border-radius: 8px;
        border: 1px solid black;
        width: 180px;
        height: 35px;
        margin-top: 80px;
    }
</style>
</html>
