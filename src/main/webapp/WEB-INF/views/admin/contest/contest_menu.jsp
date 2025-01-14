<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/01/2025
  Time: 9:49 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="main">
        <h1>${contestDTO.name}</h1>
        <div class="menu_contest">
            <div class="item" onclick="contest_detail()">Chi tiết</div>
            <div class="item" onclick="contest_problem()">Bài tập</div>
            <div class="item" onclick="contest_leaderboard()">Bảng xếp hạng</div>
            <div class="item" onclick="contest_member()">Quản lí thành viên</div>
            <div class="item" onclick="contest_install()">Cài đặt</div>
        </div>
    </div>
</body>
<script>
    function contest_detail(){
        window.location.href = "/admin/contest-detail-${contestDTO.id}";
    }
    function contest_problem(){
        window.location.href = "/admin/contest-problem-${contestDTO.id}";
    }
    function contest_member(){
        window.location.href = "/admin/list-member-${contestDTO.id}";
    }
    function contest_leaderboard(){
        window.location.href = "/admin/leader_board-${contestDTO.id}";
    }
    function contest_install(){
        window.location.href = "/admin/install-contest-${contestDTO.id}";
    }
</script>
<style>
    .main{
        width: 1177.6px;
        margin: 30.39px auto 0;
        border: 1px solid black;
    }
    h1{
        margin-top: 30px !important;
        margin-left: 150px;
    }
    .menu_contest{
        border-radius: 5px;
        margin-left: 150px;
        width: 800px;
        height: 40px;
        border: 1px solid black;
        display: flex;
        align-items: center;
        margin-bottom: 30px;
        background-color: #bb2019;
    }
    .item{
        font-size: 17px;
        margin-left: 40px;
        margin-right: 20px;
        color: #fff;
    }
</style>
</html>
