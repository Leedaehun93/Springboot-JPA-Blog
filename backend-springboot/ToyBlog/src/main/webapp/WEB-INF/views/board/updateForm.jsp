<!-- FileName : updateForm.jsp -->
<!-- Note : 58강(블로그 프로젝트) - 글 수정하기 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- header.jsp, footer.jsp include 상위 경로 다름 --%>
<%-- content textarea 태그 사용 --%>

<%-- header include --%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form>
        <!-- input type="hidden" : 숨겨진 필드로, 서버에 폼 제출 시 'board' 객체의 'id' 값을 전송하는 데 사용 -->
        <input type="hidden" id="id" value="${board.id}"/>
        <%-- Title Start --%>
        <div class="form-group">
            <input value="${board.title}" type="text" class="form-control" placeholder="Enter title" id="title">
        </div>
        <%-- Title End --%>

        <%-- Content Start --%>
        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
        </div>
        <%-- Content End --%>
    </form>

    <%-- 글수정완료 button Start --%>
    <button id="btn-update" class="btn btn-primary">글수정완료</button>
    <%-- 글수정완료 button End --%>
</div>

<%-- 스크립트 script Start --%>
<!-- summernote script -->
<script> $('.summernote').summernote({tabsize: 2, height: 300}); </script>
<!-- 사용자 정의 board 스크립트 -->
<script src="/js/board.js"></script>
<%-- 스크립트 script End --%>

<%-- footer include --%>
<%@ include file="../layout/footer.jsp" %>