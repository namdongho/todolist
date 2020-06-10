<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="euc-kr">
    <meta name="viewport" content="width=device-width">
    <link href="<%=request.getContextPath()%>/css/inputStyle.css" rel="stylesheet">
    <title>Input</title>
</head>
<body>
<div id="wrap">

    <h1>할일 등록</h1>

    <form action="/todoproject/TodoAddServlet" method="post">
        <label>Title</label><br>
        <input name="title"  maxlength="24" type="text" placeholder="24자까지"><br>

        <label>Name</label><br>
        <input name="name" width=200px; height=50px; type="text" placeholder="홍길동"><br>

        <label>Priority</label><br>
        <input name="radio" type="radio" value="1" id="r1">
        <label for="r1">1 순위</label>
        <input name="radio" type="radio" value="2" id="r2">
        <label for="r2">2 순위</label>
        <input name="radio" type="radio" value="3" id="r3  ">
        <label for="r3">3 순위</label><br>

        <input type="submit" value="submit">
        <input type="reset" value="Reset">
    </form>
    <button onclick="location='/todoproject/TodoServlet'"> 이전</button>
</div>
</body>
</html>