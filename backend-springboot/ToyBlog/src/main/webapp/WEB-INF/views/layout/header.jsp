<%--
  Created by IntelliJ IDEA.
  User: dhlee
  Date: 2024-01-04
  Time: 오후 9:48
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
</head>

<body>
<%-- TODO: Navbar Start --%>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="/blog">Ivan</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link" href="/blog/user/loginForm">로그인</a></li>
            <li class="nav-item"><a class="nav-link" href="/blog/user/joinForm">회원가입</a></li>
        </ul>
    </div>
</nav>
<%-- TODO: Navbar End --%>
<br/>