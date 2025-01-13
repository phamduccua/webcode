<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/01/2025
  Time: 7:17 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <style>
        .main{
            width: 1177.6px;
            margin: 30.39px auto 0;
            border: 1px solid black;
            padding: 20px;
        }
        .select{
            margin-left: 100px;
            margin-top: 20px;
            width: 200px;
            height: 40px;
            border-radius: 5px;
        }
        .input{
            margin-top: 40px;
            margin-left: 100px;
        }
        .output{
            margin-top: 40px;
            margin-left: 100px;
        }
        label, textarea {
            display: block;
            margin-bottom: 10px;
        }
        textarea{
            width: 800px;
            height: 300px;
        }
        .numberfile{
            margin-top: 40px;
            margin-left: 100px;
        }
        .numberfile input{
            height: 30px;
            width: 100px;
            border-radius: 5px;
        }
        .input input{
            width: 200px;
            margin-bottom: 20px;
        }
        .output input{
            width: 200px;
            margin-bottom: 20px;
        }
        p{
            color: red;
        }
        .btntest{
            margin-left: 100px;
            margin-top: 40px;
            width: 200px;
            height: 35px;
            display: flex;
            justify-content: center;
            align-items: center;
            border: 1px solid black;
            border-radius: 8px;
            background-color: brown;
            color: #fff;
        }
        .title{
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
<div class="main">
    <div class="title">
        <h1>Thêm Test Case</h1>
    </div>
    <select onchange="typeInput(this)" class="select" id="type">
        <option value="">Chọn loại đầu vào</option>
        <option value="std">STD</option>
        <option value="file">File</option>
    </select>
    <div id="test">

    </div>
    <div class="btntest" onclick="addTest()">Thêm Test Case</div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function typeInput(element){
        if(element.value === "std"){
            const item = document.getElementById('test');
            item.innerHTML = `<div class="input">
            <label for="input">Input</label>
            <textarea class="content" placeholder="Nhập giá trị đầu vào" id="input"></textarea>
            </div>
            <div class="output">
                <label for="output">Output</label>
                <textarea id="output" class="content" placeholder="Nhập giá trị đầu ra"></textarea>
            </div>
        `
        }
        else if(element.value === "file"){
            const item = document.getElementById('test');
            item.innerHTML = `<div class="numberfile">
            <label>Nhập số file:</label>
            <input type="number" onchange="fileTestcase(this)" min="1" id="numberfile"/>
            <div id="testcase"></div>
        </div>
        `;
        }
    }
    function fileTestcase(element){
        let numberFile = element.value;
        console.log(numberFile);
        const item = document.getElementById('testcase');
        var tmp = '<div>';
        for(let i = 1;i<=numberFile;i++){
            tmp += `<div class="input"><label for="input` + i + `">File ` + i + `</label>
            <input type="text"" id="inputname` + i + `"/>
            <textarea class="content" placeholder="Nhập giá trị đầu vào" id="inputcontent` + i + `"></textarea>
            </div></div>
        ` + '\n';
        }
        tmp += `
        <div class="output">
        <label for="output">Output</label>
                <input type="text" id="outputname"/>
                <p>(Nếu in ra màn hình thì ghi là: std)</p>
                <textarea id="outputcontent" class="content" placeholder="Nhập giá trị đầu ra"></textarea>
            </div>
    `
        tmp += '</div>'
        item.innerHTML = tmp;
    }

    function addTest(){
        var type = document.getElementById('type').value;
        console.log(type);
        var data = {};
        data['problemId'] = ${problemDTO.id};
        data['type'] = type;
        if(type === "std"){
            var inputcontent = document.getElementById('input').value;
            var outputcontent = document.getElementById('output').value;
            data['inputs'] = [{fileName : "std",contentFile: inputcontent}]
            data['output'] =  {fileName : "std", contentFile:outputcontent}
        }
        else{
            let numberFile = document.getElementById('numberfile').value;
            data['numberFile'] = numberFile;
            var inputs = [];
            for(let i = 1; i<= numberFile;i++){
                let idname = "inputname" + i;
                let idcontent = "inputcontent" + i;
                let name = document.getElementById(idname).value;
                let value = document.getElementById(idcontent).value;
                let inputTmp = {
                    fileName : name,
                    contentFile : value
                }
                inputs.push(inputTmp);
            }
            data['inputs'] = inputs;
            var outputTmp = {
                fileName : document.getElementById("outputname").value,
                contentFile: document.getElementById("outputcontent").value
            }
            data['output'] = outputTmp;
        }
        $.ajax({
            type: "POST",
            url: "/admin/testcase",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function () {
                Swal.fire({
                    title: 'Thành công!',
                    text: 'Thêm thành công.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => {
                    location.reload();
                });
            },
            error: function (e) {
                Swal.fire({
                    title: 'Lỗi!',
                    text: "Cập nhật thất bại !!",
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        })
    }
</script>
</html>
