<%--
  Created by IntelliJ IDEA.
  User: dhlee
  Date: 2024-01-04
  Time: 오후 9:48
  Note : 53강(블로그 프로젝트) - 글쓰기 완료
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 스크립트 로딩 순서 중요 : --%>
<%-- 1. <head> 태그 내부나 <body> 태그의 가장 아래에 jQuery 라이브러리를 포함시키는 것이 일반적인 관행이다. --%>
<%--
     2. $를 사용하는 스크립트(여기서는 user.js)가 jQuery 라이브러리 자체보다 먼저 실행되는 경우,
        jQuery 함수들을 찾을 수 없어 충돌이 발생한다. 이를 해결하기 위해, jQuery 라이브러리를 포함하는
        <script> 태그는 user.js 스크립트보다 먼저 로드되어야 한다.
 --%>
<%-- <head> 태그 내부나 <body> 태그의 가장 아래에 jQuery 라이브러리를 포함시키는 것이 일반적인 관행이다. --%>

<%-- JSTL Taglibs --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Declaring the Taglibs --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--
  Spring Security 태그 라이브러리 사용
  - sec:authorize 태그는 현재 사용자가 인증되었는지 여부를 확인한다.
  - access 속성에 "isAuthenticated()"를 사용하여 현재 사용자가 인증된 경우에만 내부의 코드를 실행한다.
  - sec:authentication 태그는 현재 인증된 사용자의 주요 정보를 가져온다.
  - property="principal"은 현재 인증된 사용자의 주체(Principal) 정보를 가져온다.
  - var="principal"은 가져온 주체 정보를 "principal"이라는 이름의 변수로 JSP 스코프에 저장한다.
--%>
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

    <%-- 스크립트 script Start --%>
    <!-- 전체 jQuery 버전 포함 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Bootstrap 및 기타 필요한 스크립트 포함 -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <%-- Note : 53강(블로그 프로젝트) - 글쓰기 완료 --%>
    <!-- summernote 및 기타 필요한 링크 포함 -->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
    <%-- 스크립트 script End --%>
</head>

<body>
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
                    <li class="nav-item"><a class="nav-link" href="/board/saveForm">글쓰기</a></li>
                    <li class="nav-item"><a class="nav-link" href="/user/updateForm">회원정보</a></li>
                    <li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</nav>
<br/>