<%--
  Created by IntelliJ IDEA.
  User: dhlee
  Date: 2024-01-04
  Time: 오후 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- TODO: header include --%>
<%@include file="../layout/header.jsp" %>

<%-- TODO: Bootstrap-4 Stacked Form Start --%>
<div class="container">
    <form>
        <%-- TODO: username Start --%>
        <div class="form-group"><label for="username">Username:</label> <input type="text" class="form-control" placeholder="Enter username" id="username"></div>
        <%-- TODO: username End --%>

        <%-- TODO: password Start --%>
        <div class="form-group"><label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password"></div>
        <%-- TODO: password End --%>

        <%-- TODO: Remember me checkbox Start --%>
        <div class="form-group form-check"><label class="form-check-label"> <input class="form-check-input" type="checkbox"> Remember me </label></div>
        <%-- TODO: Remember me checkbox End --%>
    </form>

    <%-- TODO: 로그인 button Start --%>
    <button id="btn-login" class="btn btn-primary">로그인</button>
    <%-- TODO: 로그인 button End --%R>

</div>
<%-- TODO: Bootstrap-4 Stacked Form End --%>

    <%-- TODO: footer include --%>
    <%@include file="../layout/footer.jsp" %>