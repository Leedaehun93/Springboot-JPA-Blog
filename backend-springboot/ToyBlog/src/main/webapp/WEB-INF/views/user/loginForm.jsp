<!-- FileName : loginForm.jsp -->
<!-- Note : 34강(블로그 프로젝트) - 로그인, 회원가입 화면 만들기 -->
<!-- Note : 46강(블로그 프로젝트) - 프로젝트 전통적인 방식의 로그인 방법 -->
<!-- Note : 49강(블로그 프로젝트) - 스프링 시큐리티 기반 로그인 페이지 커스터마이징 -->
<!-- Note : 50강(블로그 프로젝트) - 비밀번호 해시 후 회원가입하기 -->
<!-- Note : 52강(블로그 프로젝트) - 스프링 시큐리티 로그인 -->
<!-- Note : 53강(블로그 프로젝트) - 글쓰기 완료 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- header include --%>
<%@include file="../layout/header.jsp" %>

<%-- Bootstrap-4 ed Form Start --%>
<div class="container">
    <%-- <form action="#"> : 폼이 제출될 때 서버로 데이터를 전송하는 경로를 지정한다. 현재 #으로 설정되어 있어, 실제 서버 경로로의 데이터 전송은 이루어지지 않는다. --%>
    <%-- <form action="/auth/loginProc"> : 폼이 제출될 때 서버로 데이터를 전송하는 경로를 지정한다. 현재 /auth/loginProc으로 설정되어 있어, --%>
    <%--  실제 서버 경로로의 데이터 전송이 이루어 진다. --%>
    <%-- method="POST" : GET 방식의 HTTP 요청은 브라우저에 의해 캐시되어(cached) 저장되어 보안상 취약점으로 중요한 데이터는 POST 방식으로 요청한다. --%>
    <%--  POST 방식은 폼 데이터를 별도로 첨부하여 서버로 전달하는 방식으로 HTTP 요청은 브라우저에 의해 캐시되지 않아서, --%>
    <%--  브라우저 히스토리에도 남지않고 데이터의 길이에 대한 제한도 없는 장점이 있다. --%>
    <form action="/auth/loginProc" method="post">
        <%-- Username Start --%>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
        </div>
        <%-- Username End --%>

        <%-- Password Start --%>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <%-- Password End --%>

        <%-- 로그인 button Start --%>
        <button id="btn-login" class="btn btn-primary">로그인</button>
        <%-- 로그인 button End --%>
        <br/>
    </form>
</div>
<%-- Bootstrap-4 ed Form End --%>

<%-- footer include --%>
<%@ include file="../layout/footer.jsp" %>
