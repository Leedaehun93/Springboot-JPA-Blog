<!-- FileName : index.jsp -->
<!-- Note : 33강(블로그 프로젝트) - 메인화면 만들기 -->
<!-- Note : 34강(블로그 프로젝트) - 로그인, 회원가입 화면 만들기 -->
<!-- Note : 50강(블로그 프로젝트) - 비밀번호 해시 후 회원가입하기 -->
<!-- Note : 53강(블로그 프로젝트) - 글쓰기 완료 -->
<!-- Note : 55강(블로그 프로젝트) - 글목록 페이징하기 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- header include --%>
<%@ include file="layout/header.jsp" %>

<%-- Card Start --%>
<div class="container">
    <%-- JSTL forEach 태그를 사용하여 서버로부터 받은 boards 컬렉션의 content를 순회하며, 각 게시글 정보를 카드 형식으로 표시 --%>
    <c:forEach var="board" items="${boards.content}">
        <%-- 각 게시글에 대한 카드 스타일 컨테이너. 카드 형식으로 게시글 제목과 상세보기 버튼 제공된다. --%>
        <div class="card m-2">
                <%-- 카드 본문 부분으로, 게시글 제목과 상세보기 링크가 포함된다. --%>
            <div class="card-body">
                    <%-- EL(Expression Language)을 사용해 board 객체에서 제목을 가져와 표시한다. --%>
                <h4 class="card-title">${board.title}</h4> <!-- 여기에서 board.gettitle 을 호출하여 실제 게시글 제목을 표시한다.  -->
                    <%-- 각 게시글의 ID를 URL에 포함하여 상세 페이지로 이동할 수 있는 링크를 생성한다. 각 게시글의 ID를 URL 매개변수로 사용 --%>
                <a href="/board/${board.id}" class="btn btn-primary">상세보기</a> <!-- 상세보기 링크 -->
            </div>
        </div>
    </c:forEach>

    <%-- Bootstrap 4 Pagination Start --%>
    <%-- Bootstrap 4를 이용한 페이지네이션 구현. 현재 페이지가 첫 페이지일 경우 이전 버튼 비활성화, 마지막 페이지일 경우 다음 버튼 비활성화 --%>
    <ul class="pagination justify-content-center">
        <%-- 이전 페이지 링크. 첫 페이지인 경우 비활성화 --%>
        <c:choose>
            <c:when test="${boards.first}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:otherwise>
        </c:choose>

        <%-- 다음 페이지 링크. 마지막 페이지인 경우 비활성화 --%>
        <c:choose>
            <c:when test="${boards.last}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
    <%-- Bootstrap 4 Pagination End --%>
</div>
<%-- Card End --%>

<%-- footer include --%>
<%@ include file="layout/footer.jsp" %>
