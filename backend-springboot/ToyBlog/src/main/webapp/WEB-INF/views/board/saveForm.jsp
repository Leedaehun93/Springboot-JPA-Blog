<!-- FileName : saveForm.jsp -->
<!-- Note : 53강(블로그 프로젝트) - 글쓰기 완료 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- header.jsp, footer.jsp include 상위 경로 다름 --%>
<%-- content textarea 태그 사용 --%>

<%-- header include --%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form>
        <%-- Title Start --%>
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" placeholder="Enter title" id="title">
        </div>
        <%-- Title End --%>

        <%-- Content Start --%>
        <div class="form-group">
            <label for="content">Content</label>
            <textarea class="form-control summernote" rows="5" id="content"></textarea>
        </div>
        <%-- Content End --%>
    </form>

    <%-- 글쓰기완료 button Start --%>
    <button id="btn-save" class="btn btn-primary">글쓰기완료</button>
    <%-- 글쓰기완료 button End --%>
</div>

<%-- 스크립트 script Start --%>
<!-- summernote script -->
<script> $('.summernote').summernote({tabsize: 2, height: 300}); </script>
<!-- 사용자 정의 board 스크립트 -->
<script src="/js/board.js"></script>
<%-- 스크립트 script End --%>

<%-- footer include --%>
<%@ include file="../layout/footer.jsp" %>