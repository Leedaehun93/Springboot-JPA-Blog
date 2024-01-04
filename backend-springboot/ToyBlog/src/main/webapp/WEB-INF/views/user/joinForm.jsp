<%--
  Created by IntelliJ IDEA.
  User: dhlee
  Date: 2024-01-04
  Time: 오후 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- TODO: header include --%>
<%@include file="../layout/header.jsp" %>

<%-- TODO: Bootstrap-4 Stacked Form Start --%>
<div class="container">
    <form action="/action_page.php">
        <%-- TODO: username Start --%>
        <div class="form-group"><label for="username">Username:</label> <input type="text" class="form-control" placeholder="Enter username" id="username"></div>
        <%-- TODO: username End --%>

        <%-- TODO: email Start --%>
        <div class="form-group"><label for="email">Email:</label> <input type="email" class="form-control" placeholder="Enter email" id="email"></div>
        <%-- TODO: email End --%>

        <%-- TODO: password Start --%>
        <div class="form-group"><label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password"></div>
        <%-- TODO: password End --%>

        <%-- TODO: 회원가입완료 button Start --%>
        <button type="submit" class="btn btn-primary">회원가입완료</button>
        <%-- TODO: 회원가입완료 button End --%>
    </form>
</div>
<%-- TODO: Bootstrap-4 Stacked Form End --%>

<%-- TODO: footer include --%>
<%@include file="../layout/footer.jsp" %>