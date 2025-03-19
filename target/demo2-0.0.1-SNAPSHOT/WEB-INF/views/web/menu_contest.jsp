<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
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
        .button-history {
            top:7px !important;
            left: 200px !important;
        }
        .button-rank {
            top:7px !important;
            left: 290px !important;
        }
        .button-back {
            top:7px !important;
            left: 420px !important;
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
            border-radius: 50%;
        }

        .logo {
            background-size: cover;
            position: absolute;
            width: 50px;
            height: 50px;
            border-radius: 50%;
            top: 12px !important;
            left: 50px;
        }
        .open{
            color:red;
        }
        a {
            text-decoration: none !important;
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
            color: black;
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
</head>
<body>
<div class="head">
    <div class="logo">
        <img class="img" src="http://luyencode.online/uploads/logo.webp" alt="Ảnh">
    </div>
    <a class="button button-exam" href="/api/contest/${id}/question">Bài tập</a>
    <a class="button button-history" href="/api/contest/${id}/history">Lịch sử</a>
    <a class="button button-rank" href="/api/contest/${id}/ranking">Bảng xếp hạng</a>
    <a class="button button-back" href="/api/question">Trở về trang chủ</a>
</div>
</body>
</html>
