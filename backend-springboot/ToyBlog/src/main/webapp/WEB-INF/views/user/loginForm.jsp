<%--
  Created by IntelliJ IDEA.
  User: dhlee
  Date: 2024-01-04
  Time: 오후 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- header include --%>
<%@include file="../layout/header.jsp" %>

<%-- Bootstrap-4 ed Form Start --%>
<div class="container">
    <%-- <form action="#">는 폼이 제출될 때 서버로 데이터를 전송하는 경로를 지정한다. 현재 #으로 설정되어 있어, 실제 서버 경로로의 데이터 전송은 이루어지지 않는다. --%>
    <%-- method="POST" : GET 방식의 HTTP 요청은 브라우저에 의해 캐시되어(cached) 저장되어 보안상 취약점으로 중요한 데이터는 POST 방식으로 요청한다. --%>
    <%--  POST 방식은 폼 데이터를 별도로 첨부하여 서버로 전달하는 방식으로 HTTP 요청은 브라우저에 의해 캐시되지 않아서, --%>
    <%--  브라우저 히스토리에도 남지않고 데이터의 길이에 대한 제한도 없는 장점이 있다. --%>
    <form action="/auth/loginProc" method="post">
        <%-- username Start --%>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
        </div>
        <%-- username End --%>

        <%-- password Start --%>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <%-- password End --%>

        <%-- 로그인 button Start --%>
        <button id="btn-login" class="btn btn-primary">로그인</button>
        <%-- 로그인 button End --%R>
    </form>

</div>
<%-- Bootstrap-4 ed Form End --%>

<%-- footer include --%>
<%@include file="../layout/footer.jsp" %>