<%--
  Created by IntelliJ IDEA.
  User: dhlee
  Date: 2024-01-04
  Time: 오후 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- header include --%>
<%@include file="../layout/header.jsp" %>

<%-- Bootstrap-4 ed Form Start --%>
<div class="container">
    <%-- <form action="#">는 폼이 제출될 때 서버로 데이터를 전송하는 경로를 지정한다. 현재 #으로 설정되어 있어, 실제 서버 경로로의 데이터 전송은 이루어지지 않는다. --%>
    <form action="#">
        <%-- username Start --%>
        <div class="form-group"><label for="username">Username:</label>
            <input type="text" class="form-control" placeholder="Enter username" id="username">
        </div>
        <%-- username End --%>

        <%-- password Start --%>
        <div class="form-group"><label for="password">Password:</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <%-- password End --%>

        <%-- email Start --%>
        <div class="form-group"><label for="email">Email:</label>
            <input type="email" class="form-control" placeholder="Enter email" id="email">
        </div>
        <%-- email End --%>

        <%-- 회원가입완료 button Start --%>
        <button id="btn-save" class="btn btn-primary">회원가입완료</button>
        <%-- 회원가입완료 button End --%>
    </form>

</div>
<%-- Bootstrap-4 ed Form End --%>

<%-- footer include --%>
<%@include file="../layout/footer.jsp" %>