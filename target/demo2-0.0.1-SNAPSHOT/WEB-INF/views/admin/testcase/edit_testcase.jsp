
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <style>
    .main{
      border: 1px solid black;
      padding: 20px;
    }
    .title{
      display:flex;
      justify-content: center;
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
  </style>
</head>
<body>
<div class="main">
  <div class="title">
    <h1>Sửa Test Case</h1>
  </div>
  <div id="detail_test">
  </div>
  <input type="hidden" id="type" value="${testCaseDTO.type}" />
  <input type="hidden" id="numberfile" value="${testCaseDTO.inputs.size()}" />
  <div class="btntest" onclick="editTest()">Cập nhật Test Case</div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
  function loadData(testCaseDTO){
    var respone = testCaseDTO;
    console.log(respone);
    const item = document.getElementById('detail_test');
    let typeInput = respone.type;
    if(typeInput === "std"){
      let rows = '';
      rows += `<div class="input">
            <label for="input">Input</label>
            <textarea class="content" placeholder="Nhập giá trị đầu vào" id="input">` + respone.inputs[0].contentFile + `</textarea>
            </div>
            <div class="output">
                <label for="output">Output</label>
                <textarea id="output" class="content" placeholder="Nhập giá trị đầu ra">` + respone.output.contentFile + `</textarea>
            </div>`
      item.innerHTML = rows;
    }
    else{
      let rows = '';
      var tmp = '<div>';
      var inputs = respone.inputs;
      for(let i = 1;i<=inputs.length;i++){
        tmp += `<div class="input"><label for="input` + i + `">File ` + i + `</label>
            <input type="text" id="inputname` + i + `" value="` + respone.inputs[i-1].fileName + `"/>
            <textarea class="content" placeholder="Nhập giá trị đầu vào" id="inputcontent` + i + `">` + respone.inputs[i-1].contentFile +`</textarea>
            </div></div>
        ` + '\n';
      }
      tmp += `
        <div class="output">
        <label for="output">Output</label>
                <input type="text" id="outputname" value="` + respone.output.fileName + `">
                <p>(Nếu in ra màn hình thì ghi là: std)</p>
                <textarea id="outputcontent" class="content" placeholder="Nhập giá trị đầu ra">` + respone.output.contentFile + `</textarea>
            </div>
    `
      tmp += '</div>'
      rows += tmp;
      rows += `</div></div>`;
      item.innerHTML = rows;
    }
  }
  function callAPI(){
    $.ajax({
      type: "POST",
      url: '/web/admin/testcase/get/' + ${testCaseDTO.id},
      dataType: "JSON",
      success: function (response) {
        console.log(response);
        loadData(response);
      },
      error: function (error) {
        console.log(response);
      }
    });
  }
</script>
<script>
  window.onload = function () {
    callAPI();
  };
</script>
<script>
  function editTest(){
    var type = document.getElementById('type').value;
    console.log(type);
    var data = {};
    data['id'] = ${testCaseDTO.id};
    data['problemId'] = ${problemId};
    data['type'] = type;
    data['example'] = '${testCaseDTO.example}';
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
        if(name !== 'std' && !name.includes(".")){
          alert("Tên File bị lỗi !!");
          return;
        }
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
      if(outputTmp.fileName !== 'std' && !outputTmp.contentFile.includes(".")){
        alert("Tên File bị lỗi !!");
        return;
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
          text: 'Cập nhật thành công.',
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
