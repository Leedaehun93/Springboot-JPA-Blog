<%--
  Created by IntelliJ IDEA.
  User: dhlee
  Date: 2024-01-04
  Time: 오후 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- JSTL Taglibs --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Declaring the Taglibs --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%-- TODO: Note : 49강 - 스프링 시큐리티 기반 로그인 페이지 커스터마이징으로 기존 기본 로그인 방식은 주석 처리하여 참조용으로 보존 --%>
<%-- Spring Security의 태그를 사용하여 사용자 인증(로그인) 상태를 체크한다. 조건에 해당하지 않는 즉, 사용자가 익명이 아닌(로그인이 된 상태) 경우 메시지를 표시해 반환한다. --%>
<%--<sec:authorize access="isAuthenticated()">--%>
<%--    <script>--%>
<%--        alert("로그인이 된 사용자입니다.");--%>
<%--    </script>--%>
<%--</sec:authorize>--%>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal"/>
</sec:authorize>

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
    <a class="navbar-brand" href="/">Ivan</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <c:choose>
            <c:when test="${empty principal}">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li>
                    <li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="/board/form">글쓰기</a></li>
                    <li class="nav-item"><a class="nav-link" href="/user/form">회원정보</a></li>
                    <li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</nav>
<%-- TODO: Navbar End --%>
<br/>