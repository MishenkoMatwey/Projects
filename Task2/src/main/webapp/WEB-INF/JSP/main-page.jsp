<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="static/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="static/js/jquery.cookie.js"></script>
    <script src="static/js/main.js"></script>
    <script src="static/js/operationsFolder.js"></script>
    <link href="static/css/style.css" rel="stylesheet">
    <link href="static/css/styleForTree.css" rel="stylesheet">
</head>

<body>
<div class="header">
    <h1>TreeModel</h1>
    <div class="topPanel">
        <div class="optionButton">
            <label class="l-file" for="DownloadFile">Загрузить из файла</label>
            <input class="file" type="file"  accept=".txt"  id="DownloadFile" />
        </div>

<%--        <div class="optionButton">--%>
<%--            <label class="l-file" for="UploadFile">Сохранить в файл</label>--%>
<%--            <input class="file" do />--%>
<%--        </div>--%>

        <div class="optionButton">
            <button id="DownloadDB"> Загрузить из базы данных</button>
            <jsp:include page="downloadDB.jsp"/>
        </div>

        <div class="optionButton">
            <button id="UploadDB"> Сохранить в базу данных</button>
        </div>

    </div>
</div>

<div class="treeview">
    <ul id="main-ul">
    </ul>
</div>
<div class="podlogka"></div>
</body>
<jsp:include page="operationsDirectory.jsp"/>
<jsp:include page="inputName.jsp"/>
</html>
