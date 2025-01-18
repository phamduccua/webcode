<%@ page import="static org.hibernate.type.SqlTypes.JSON" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 26/11/2024
  Time: 11:54 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="home.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đề bài</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .main {
            position: relative !important;
            width: 950px !important;
            margin: 30.39px auto 0 !important;
            padding: 25.6px 39.19px !important;
            border-radius: 4px !important;
            background-color: #FEF9F9 !important;
            text-align: left !important;
        }

        .detail {
            position: relative !important;
            width: 870px !important;
            margin: 30.39px auto 0 !important;
            padding: 25.6px 39.19px !important;
            border-radius: 4px !important;
            background-color: #FFFF !important;
            text-align: left !important;
        }

        .inputFile_value {
            border: 1px solid black !important;
            background-color: #FFFF !important;
            width: 350px !important;
            height: 33px !important;
            border-radius: 4px !important;
            padding: 4px !important;
        }

        .inputFile_browser {
            float: right !important;
            width: 50px !important;
            height: 31px !important;
            border: 1px solid black !important;
            background-color: #fdedf6 !important;
            text-align: center !important;
            border-radius: 4px !important;
        }

        .form-container {
            display: inline !important;
            align-items: center !important;
            gap: 10px !important;
            display: flex !important;
            flex-wrap: nowrap !important;
        }

        select {
            width: 90px !important;
            height: 35px !important;
            padding: 5px !important;
            border-radius: 4px !important;
            margin-right: 240px !important;
            text-align: center !important;
        }

        button {
            float: right !important;
            background-color: #bb2019 !important;
            color: #FFFF !important;
            border-radius: 4px !important;
            width: 154.32px !important;
            height: 37.35px !important;
        }

        h2 {
            position: relative !important;
            text-align: center !important;
        }

        table.table {
            width: 100% !important;
            border-collapse: collapse !important;
            text-align: center !important;
        }

        table.table th {
            height: 40px !important;
            text-align: center !important;
            background-color: #FAEDED !important;
            top: 10px !important;
        }

        table.table td {
            text-align: center !important;
            background-color: #F1ECEC !important;
            padding: 0 !important;
        }
        .dropdown {
            position: relative !important;
            display: inline-block !important;
            overflow: visible !important;
        }

        .dropdown-btn {
            cursor: pointer !important;
            color: white !important;
            font-weight: bold !important;
        }

        .dropdown-icon {
            cursor: pointer !important;
        }

        .dropdown-menu-wrapper {
            display: none !important;
            position: absolute !important;
            top: 100% !important;
            right: 0 !important;
            background-color: white !important;
            border: 1px solid #ccc !important;
            border-radius: 4px !important;
            z-index: 1000 !important;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2) !important;
            overflow-y: auto !important;
            width: max-content !important;
        }
        a {
            text-decoration: none !important;
            pointer-events: auto !important;
        }
        .example {
            width: 100% !important;
            border-collapse: collapse !important;
            margin-bottom: 10px;
        }

        table.example th {
            height: 30px !important;
            text-align: left !important;
            border: 1px solid black !important;
        }

        table.example td {
            text-align: left !important;
            border: 1px solid black !important;
        }

        #fileInput {
            display: none !important;
        }

        p {
            margin-bottom: 5px !important;
            margin-top: 0px !important;
            margin-left: 0px !important;
            margin-right: 0px !important;
        }
        img{
            width: 300px;
            height: 200px;
        }
    </style>
</head>
<body>
<div class="main">
    <label style=" font-size: 17px; color: #bb2019;"><strong>${detail.title}</strong></label>
    <label style="float: right; font-size: 17px; color: #bb2019;"><strong>Bài làm tốt nhất</strong></label>
        <div class="detail">
            <div id="description">

            </div>
            <div id="input">
            </div>
            <c:if test="${not empty detail.constraints}" >
                <div>
                    <label><strong>Giới hạn</strong></label>
                    <p>${detail.constraints}</p>
                </div>
            </c:if>
            <div id="output">
            </div>
            <c:if test="${not empty listTest and listTest.size() > 0}">
    <div>
        <p><strong>Ví dụ</strong></p>
        <div id="example">
        <c:forEach var="item" items="${listTest}">
            <c:if test="${item.type == 'std'}">
                <table class="example">
                    <thead>
                        <tr>
                            <th>Input</th>
                            <th>Output</th>
                        </tr>
                    </thead>
                    <tbody id="test_case">
                        <tr>
                            <td data-content="${item.inputs.get(0).contentFile}"></td>
                            <td data-content="${item.output.contentFile}"></td>
                        </tr>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${item.type == 'file'}">
                <table class="example">
                    <tbody>
                        <c:forEach var="it" items="${item.inputs}" varStatus="status">
                            <c:if test="${status.count == 1}">
                                <tr>
                                    <td><strong>${it.fileName}</strong></td>
                                    <c:if test="${item.output.fileName == 'std'}">
                                        <td><strong>Output</strong></td>
                                    </c:if>
                                    <c:if test="${item.output.fileName != 'std'}">
                                        <td><strong>${item.output.fileName}</strong></td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td data-content="${it.contentFile}"></td>
                                    <td data-content="${item.output.contentFile}" rowspan="${item.inputs.size()*2 - 1}"></td>
                                </tr>
                            </c:if>
                            <c:if test="${status.count > 1}">
                                <tr>
                                    <td><strong>${it.fileName}</strong></td>
                                </tr>
                                <tr>
                                    <td data-content="${it.contentFile}"></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:forEach>


                </div>

            </div>
        </c:if>
</div><br>
        <p>Giới hạn bài tập: ${detail.time_limit}s</p>
        <p>Giới hạn bộ nhớ: ${detail.memory_limit}Kb</p> <br>
    <div>
        <form id="uploadForm" enctype="multipart/form-data">
            <div class="form-container">
                <p>Trình biên dịch</p>
                <select id="selectPogram" name="programingLanguage">
                    <c:forEach var="item" items="${listLanguage}" >
                        <option value="${item}" >${item}</option>
                    </c:forEach>
                </select>

                <p class="inputFile_value" onclick="upload()">Chọn tệp</p>
                <p class="inputFile_browser" onclick="upload()">Duyệt</p>
                <input type="file" name="file" id="fileInput" accept=".c,.cpp,.java,.py"/>
            </div>
            <button type="submit" style="margin-top:18px;">Nộp bài</button> <br> <br>
        </form>
        <div>
            <h2>Lịch sử</h2>
            <div>
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
            <tbody class="table-body" id="listSubmission">
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
                    <c:if test="${item.status == null}">
                        <td class="spinner">
                            <div class="spinner-border text-primary small-spinner" role="status">
                                <span class="visually-hidden">Loading...</span>
                            </div>
                        </td>
                        <td></td>
                        <td></td>
                    </c:if>
                    <c:if test="${item.status != null}">
                        <c:if test="${item.status == true}">
                            <td style="color: #19BE6B;">AC</td>
                            <td>${item.time}s</td>
                            <td>${item.memoryUsed}Kb</td>
                        </c:if>
                        <c:if test="${item.status == false}">
                            <c:if test="${item.code == 'CE'}">
                                <td style="color: rgb(0, 0, 0)">CE</td>
                                <td></td>
                                <td></td>
                            </c:if>
                            <c:if test="${item.code != 'CE'}">
                                <td style="color: #FF0000;">${item.code}</td>
                                <td>${item.time}s</td>
                                <td>${item.memoryUsed}Kb</td>
                            </c:if>
                        </c:if>
                    </c:if>
                    <td>${item.language}</td>
                </tr>
            </c:forEach>


            </tbody>
        </table>
            </div>
        </div>
    </div>
    <input type="hidden" id="id" value="${detail.id}" />
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    function updateStatus() {
        var count = 0;
        $("tbody tr").each(function () {
            var status = $(this).find("td:nth-child(4)").text().trim();
            if (status !== ""  && status !== "AC" && status !== "WA" && status !== "RTE" && status !== "TLE" && status !== "CE"　&& status !== "MLE") {
                count++;
            }
        });
        if (count > 0) {
            location.reload();
        }
    }
    setTimeout(updateStatus, 3000);

</script>
<script>
    function upload(){
        $('#fileInput').click();
    }
    $('#fileInput').change(function() {
        var fileName = $(this).val().split('\\').pop();
        $('.inputFile_value').text(fileName ? 'C:\\fakepath\\' +  fileName : 'Chọn tệp');
    });
</script>
<script>
    $(document).ready(function() {
        $('#uploadForm').on('submit', function(e) {
            e.preventDefault();
            var formData = new FormData();
            var fileInput = $('#fileInput')[0].files[0];
            var language = $('#selectPogram').val();
            var id = $('#id').val();
            var fileName = $('#fileInput').val().split('\\').pop();
            if(fileName.split('.').pop() !== 'c' && fileName.split('.').pop() !== 'cpp' && fileName.split('.').pop() !== 'java' && fileName.split('.').pop() !== 'py'){
                Swal.fire({
                    title: 'Lỗi!',
                    html: "File không hợp lệ !!! <br> Vui lòng kiểm tra lại !!!",
                    icon: 'error',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.isConfirmed) {
                        location.reload();
                    }
                });

            }
            else{
                formData.append('file', fileInput);
                formData.append('language',language);
                formData.append('id', id);
                $.ajax({
                    url: '/uploads/file',
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function(response) {
                        console.log("Thành công");
                        window.location.href = '/admin/history';
                    },
                    error: function(xhr, status, error) {
                        Swal.fire({
                            title: 'Lỗi!',
                            text: 'Đã xảy ra lỗi !!!',
                            icon: 'error',
                            confirmButtonText: 'OK'
                        });
                    }
                });
            }
        });
    });

</script>
<script>
    var description = document.getElementById('description');
    var line_description = `${detail.description}`.split("\n");
    description.innerHTML = "";
    let rows = '';
    line_description.forEach(item => {
        if(item.includes("![image]")){
            rows += ('<img ' + 'src="http://localhost:8080/uploads/' + item.substring(9,item.length-1) + '"' + '>');
        }else{
            rows += ('<p>' + item + '</p>');
        }
        description.innerHTML = rows;
    });
</script>
<script>
    var input = document.getElementById('input');
    var line_input = `${detail.inputFormat}`.split("\n");
    input.innerHTML = "<label><strong>Input</strong></label>";
    line_input.forEach(item => {
        const p = document.createElement("p");
        p.textContent = item;
        input.appendChild(p);
    });
</script>
<script>
    var output = document.getElementById('output');
    var line_output = `${detail.outputFormat}`.split("\n");
    output.innerHTML = "<label><strong>Output</strong></label>";
    line_output.forEach(item => {
        const p = document.createElement("p");
        p.textContent = item;
        output.appendChild(p);
    });
</script>
<script>
    function cut(value){
        var tmp = value.split('\n');
        var rows = '';
        for(let i of tmp){
            rows += '<p>' + i + '</p>';
        }
        return rows;
    }
    document.addEventListener("DOMContentLoaded", () => {
        document.querySelectorAll('td[data-content]').forEach(td => {
            const content = td.getAttribute('data-content');
            td.innerHTML = cut(content);
        });
    });

</script>
</body>
</html>
