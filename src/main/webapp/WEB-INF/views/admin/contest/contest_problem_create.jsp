<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/01/2025
  Time: 11:04 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/admin/problem/home.jsp" %>
<%@ include file="contest_menu.jsp" %>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Bài tập</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .main {
      border: 1px solid black;
      padding: 20px;
    }

    .title {
      display: flex;
      justify-content: center;
      margin-bottom: 20px;
    }

    .item_input {
      display: flex;
      align-items: center;
      justify-content: flex-start;
      margin-bottom: 30px;
      gap: 20px;
    }

    .item_input label {
      width: 120px;
      text-align: right;
      margin-right: 100px;
    }

    .item_input textarea {
      width: 600px;
      height: 300px;
      border-radius: 5px;
    }
    .item_input input{
      width: 300px;
      height: 30px;
      border-radius: 5px;
    }
    .nut{
      display: flex;
      justify-content: center;
    }
    .btnadd{
      display: flex;
      justify-content: center;
      align-content: center;
      background-color: brown;
      color: #fff;
      border-radius: 8px;
      width: 200px;
      height: 30px;
      margin-left: 50px;
      margin-top: 50px;
    }
    #btnupload{
      width: 100px;
      height: 30px;
      margin-left: 20px;
    }
    #menu_upload {
      display: none;
      position: absolute;
      width: 250px;
      height: 300px;
      top: 650px;
      left: 50%;
      transform: translate(-50%, -50%);
      border: 1px solid black;
      background-color: #ffffff;
      z-index: 1000;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
      padding: 20px;
      text-align: center;
    }
    .file_upload{
      display: none;
    }
  </style>
</head>
<body>
<div class="main">
  <div>
    <div class="title">
      <h2>Tạo bài tập</h2>
    </div>
    <div>
      <div  class="item_input">
        <label for="name">Tên bài tập:</label>
        <input id="name" type="text" placeholder="Nhập tên bài tập"/>
      </div>
      <div class="item_input">
        <label for="description">Đề bài:</label>
        <textarea id="description"></textarea>
        <button id="btnupload">Chọn tệp</button>
      </div>
      <div class="item_input">
        <label for="input_format">Yêu cầu đầu vào:</label>
        <textarea id="input_format"></textarea>
      </div>
      <div class="item_input">
        <label for="constraints">Giới hạn bài toán:</label>
        <textarea id="constraints"></textarea>
      </div>
      <div class="item_input">
        <label for="output_format">Yêu cầu đầu ra:</label>
        <textarea id="output_format"></textarea>
      </div>
      <div class="item_input">
        <label for="limit_time">Giớ hạn thời gian:</label>
        <input id="limit_time" type="number"/>
      </div>
      <div class="item_input">
        <label for="limit_memory">Giớ hạn bộ nhớ:</label>
        <input id="limit_memory" type="number"/>
      </div>
    </div>
    <div class="nut">
      <div class="btnadd" onclick="createProblem()">Thêm</div>
    </div>
  </div>
  <div class="upload" id="menu_upload">
    <div id="file_name" style="border: 1px solid black; margin-bottom: 20px;">Chưa có file nào</div>
    <input type="file" class="file_upload" id="upload">
    <label for="upload" style="border: 1px solid black;">Chọn tệp</label>
    <button id="up">Upload</button>
    <button id="close">Đóng</button>
  </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
  var listImages = [];
  $('#btnupload').click(function(e){
    e.preventDefault();
    const upload = document.getElementById('menu_upload');
    upload.style.display = 'block';
  });
  $('#close').click(function(e){
    e.preventDefault();
    const upload = document.getElementById('menu_upload');
    upload.style.display = 'none';
  });
  $('#up').click(function(e){
    e.preventDefault();
    var fileUpload = $('#upload')[0].files[0];
    if (!fileUpload) {
      alert('Vui lòng chọn một tệp');
      return;
    }
    else{
      listImages.push(fileUpload);
      document.getElementById('description').value = document.getElementById('description').value + "\n![image](" + fileUpload.name + ")";
      document.getElementById('upload').value = "";
      document.getElementById('file_name').textContent = 'Chưa có file nào';
    }

  });
  const fileInput = document.getElementById('upload');
  const fileNameDiv = document.getElementById('file_name');
  fileInput.addEventListener('change', function () {
    fileNameDiv.textContent = fileInput.files.length > 0 ? fileInput.files[0].name : 'Chưa có file nào';
  });
  async function edit_debai(debai) {
    var arr = debai.split("\n");
    var fileName = [];
    for (let i of arr) {
      if (i.includes("image")) {
        fileName.push(i);
      }
    }
    for (let i of listImages) {
      let t = "![image](" + i.name + ")";
      if (i && i.name && fileName.some(name => t.includes(name))) {
        const name = i.name;
        var formData = new FormData();
        formData.append("file", i);
        await $.ajax({
          type: "POST",
          url: "/web/admin/problem/upload/images",
          data: formData,
          processData: false,
          contentType: false,
          success: function (response) {
            debai = debai.replace(`![image](` + name + `)`, `![image](` + response + `)`);
          },
          error: function () {
            alert("Tải lên thất bại");
          }
        });
      }
    }
    return debai;
  }
  async function createProblem() {
    const name = document.getElementById("name").value;
    let tmp = document.getElementById('description').value;
    tmp = await edit_debai(tmp);
    document.getElementById('description').value = tmp;
    const inputFormat = document.getElementById("input_format").value;
    const constraints = document.getElementById("constraints").value;
    const outputFormat = document.getElementById("output_format").value;
    const time_limit = document.getElementById("limit_time").value;
    const memory_limit = document.getElementById("limit_memory").value;
    const contestId = ${contestDTO.id};
    const formData = {
      title: name,
      description: tmp,
      inputFormat: inputFormat,
      constraints: constraints,
      outputFormat: outputFormat,
      time_limit: time_limit,
      memory_limit: memory_limit,
      id_contest: contestId
    };
    $.ajax({
      type: "POST",
      url: "/web/admin/contest-create_problem",
      data: JSON.stringify(formData),
      contentType: "application/json",
      success() {
        Swal.fire({
          text: 'Đã thêm bài tập thành công',
          icon: 'success',
          confirmButtonText: 'OK'
        })
        //         .then((result) => {
        //   if (result.isConfirmed) {
        //     location.reload();
        //   }
        // });
      },
      error: function (e) {
        Swal.fire({
          title: 'Lỗi!',
          text: 'Không thể thêm bài tập !!',
          icon: 'error',
          confirmButtonText: 'OK'
        });
      }
    })
  }
</script>
</html>
