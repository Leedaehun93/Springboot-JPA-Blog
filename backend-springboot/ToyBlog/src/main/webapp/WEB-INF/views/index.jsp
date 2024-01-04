<%--
  Created by IntelliJ IDEA.
  User: dhlee
  Date: 2024-01-04
  Time: 오후 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Ivan 블로그</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%-- Navbar --%>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="/blog">Ivan</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/user/login">로그인</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user/join">회원가입</a>
            </li>

        </ul>
    </div>
</nav>
<br>

<%-- Card 1 --%>
<div class="container">
    <div class="card m-2">
        <div class="card-body">
            <h4 class="card-title">제목 적는 부분</h4>
            <p class="card-text">내용 적는 부분</p>
            <a href="#" class="btn btn-primary">상세보기</a>
        </div>
    </div>
</div>

<%-- Card 2 --%>
<div class="container">
    <div class="card m-2">
        <div class="card-body">
            <h4 class="card-title">제목 적는 부분</h4>
            <p class="card-text">내용 적는 부분</p>
            <a href="#" class="btn btn-primary">상세보기</a>
        </div>
    </div>
</div>

<%-- Card 3 --%>
<div class="container">
    <div class="card m-2">
        <div class="card-body">
            <h4 class="card-title">제목 적는 부분</h4>
            <p class="card-text">내용 적는 부분</p>
            <a href="#" class="btn btn-primary">상세보기</a>
        </div>
    </div>
</div>

<%-- Footer --%>
<div class="jumbotron text-center" style="margin-bottom:0">
    <p>Created by Ivan</p>
    <p>📞010-1234-5678</p>
    <p>🏳️부산 해운대구 xx동</p>
</div>
</body>
</html>


