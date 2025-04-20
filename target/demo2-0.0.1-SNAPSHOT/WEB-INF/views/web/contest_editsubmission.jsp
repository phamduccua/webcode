<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 19/01/2025
  Time: 11:12 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="menu_contest.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .main {
            border: 1px solid black;
            width: 1177.6px;
            margin: 30.39px auto 0;
            background-color: #FEF9F9;
            padding: 20px;
        }
        .form-data {
            margin-top: 20px;
        }
        .language {
            margin-left: 75px;
        }
        .language select {
            margin-left: 30px;
            text-align: center;
            width: 120px;
            height: 30px;
        }
        .editor-container {
            display: flex;
            margin-top: 20px;
            justify-content: center;
            width: 100%;
            margin-bottom: 30px;
        }
        .name_problem {
            margin-top: 30px;
            margin-left: 75px;
        }
        .line-numbers {
            border: 1px solid black;
            background-color: #f4f4f4;
            overflow: hidden;
            user-select: none;
            height: 300px;
            overflow-y: hidden;
            min-width: 30px;
            padding-right: 15px;
            margin-right: -1px;
        }
        .line-numbers div {
            font-size: 13px;
            height: 20px;
            line-height: 20px;
            padding-left: 10px !important;
        }
        .code-editor {
            outline: none;
            width: 1000px;
            height: 300px;
            font-size: 13px;
            padding: 0;
            border: 1px solid black;
            resize: none;
            font-family: monospace;
            line-height: 20px;
            overflow: auto;
        }
        #btnadd {
            margin-left: 75px;
            width: 140px;
            border-radius: 8px;
            height: 32px;
            background-color: brown;
            color: #fff;
        }
        .error{
            display: flex;
            justify-content: center;
            width: 100%;
            margin-bottom: 30px;
        }
        .error-detail{
            width: 1034px;
            border: 1px solid black;
        }
    </style>
</head>
<body>
<div class="main">
    <div class="name_problem">
        <a class="open" href="/api/contest/${id}/solution/${problemDTO.code}">${problemDTO.code} - ${problemDTO.title}</a>
    </div>
    <form:form class="form-data" modelAttribute="submission" type="GET">
        <div class="language">
            <label>Trình biên dịch:</label>
            <form:select path="language" id="language">
                <form:options items="${listLanguage}" />
            </form:select>
        </div>
        <div class="editor-container">
            <div class="line-numbers" id="lineNumbers"></div>
            <form:textarea spellcheck="false" class="code-editor" id="codeEditor" oninput="updateLineNumbers()" onscroll="syncScroll()" path="submitted"></form:textarea>

        </div>
        <c:if test="${submission.error != null && submission.error !=  ''}">
            <div class="error">
                <div class="error-detail" id="error"></div>
            </div>
        </c:if>

    </form:form>
    <div>
        <button id="btnadd">Nộp lại bài</button>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function updateLineNumbers() {
        const editor = document.getElementById("codeEditor");
        const lineNumbers = document.getElementById("lineNumbers");
        const lineCount = editor.value.split("\n").length;
        let lines = "";
        for (let i = 1; i <= lineCount; i++) {
            lines += `<div>` + i + `</div>`;
        }
        lineNumbers.innerHTML = lines;
        lineNumbers.scrollTop = editor.scrollTop;
    }

    function syncScroll() {
        const editor = document.getElementById("codeEditor");
        const lineNumbers = document.getElementById("lineNumbers");
        lineNumbers.scrollTop = editor.scrollTop;
    }
    document.getElementById("codeEditor").addEventListener("keydown", function (event) {
        const editor = this;
        if (event.key === "Tab") {
            event.preventDefault();
            const start = editor.selectionStart;
            const end = editor.selectionEnd;
            editor.value =
                editor.value.substring(0, start) + "    " + editor.value.substring(end);
            editor.selectionStart = editor.selectionEnd = start + 4;
            updateLineNumbers();
        }
        if (event.key === "Enter") {
            event.preventDefault();
            const start = editor.selectionStart;
            const end = editor.selectionEnd;
            const lines = editor.value.substring(0, start).split("\n");
            const currentLine = lines[lines.length - 1];
            const leadingSpaces = currentLine.match(/^\s*/)[0];
            editor.value =
                editor.value.substring(0, start) +
                "\n" +
                leadingSpaces +
                editor.value.substring(end);
            editor.selectionStart = editor.selectionEnd = start + leadingSpaces.length + 1;
            updateLineNumbers();
        }
        const openCloseMap = {
            '(': ')',
            '{': '}',
            '[': ']',
            '"': '"',
            "'": "'"
        };
        if (openCloseMap[event.key]) {
            event.preventDefault();
            const start = editor.selectionStart;
            const end = editor.selectionEnd;
            const open = event.key;
            const close = openCloseMap[event.key];
            editor.value =
                editor.value.substring(0, start) + open + close + editor.value.substring(end);
            editor.selectionStart = editor.selectionEnd = start + 1;
            updateLineNumbers();
        }
        if (event.key === "Backspace") {
            const start = editor.selectionStart;
            const end = editor.selectionEnd;
            if (start > 0 && editor.value[start - 1] in openCloseMap && editor.value[start] === openCloseMap[editor.value[start - 1]]) {
                event.preventDefault();
                editor.value =
                    editor.value.substring(0, start - 1) + editor.value.substring(start + 1);
                editor.selectionStart = editor.selectionEnd = start - 1;
                updateLineNumbers();
            }
        }
    });
    document.addEventListener("DOMContentLoaded", () => {
        updateLineNumbers();
    });
</script>
<script>
    <c:if test="${submission.error != null && submission.error !=  ''}">
        let error = `${submission.error}`;
        let arr = error.split("\n");
        let item = document.getElementById('error');
        let rows ='';
        for(let i of arr){
            rows += '<p>' + i + '</p>';
        }
        item.innerHTML = rows;
    </c:if>
</script>
<script>
    $('#btnadd').click(function(e){
        e.preventDefault();
        let formData = new FormData();
        let language = $('#language').val();
        let code = $('#codeEditor').val();
        let problemId = ${problemDTO.id};
        formData.append('language',language);
        formData.append('code',code);
        formData.append('problemId',problemId);
        $.ajax({
            type: 'POST',
            url: '/api/uploads/code',
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                console.log("Thành công");
                window.location.href = '/api/contest/${id}/history';
            },error: function(xhr, status, error) {
                Swal.fire({
                    title: 'Lỗi!',
                    text: 'Đã xảy ra lỗi !!!',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        })
    });
</script>
</body>
</html>
