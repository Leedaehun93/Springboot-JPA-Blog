<%--
  Created by IntelliJ IDEA.
  User: dhlee
  Date: 2024-01-04
  Time: 오후 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- TODO: Footer Start --%>
<br/>
<div class="jumbotron text-center" style="margin-bottom:0">
    <p>Created by Ivan</p>
    <p>📞010-1234-5678</p>
    <p>🏳️부산 해운대구 xx동</p>
</div>
<%-- TODO: Footer End --%>

<%-- TODO: script Start --%>
<%-- TODO: 스크립트 로딩 순서 중요 : $를 사용하는 스크립트(여기서는 user.js)가 jQuery 라이브러리 자체보다 먼저 실행되는 경우,
      jQuery 함수들을 찾을 수 없어 충돌이 발생한다.
      이를 해결하기 위해, jQuery 라이브러리를 포함하는 <script> 태그는 user.js 스크립트보다 먼저 로드되어야 한다.
--%>
<%-- TODO: <head> 태그 내부나 <body> 태그의 가장 아래에 jQuery 라이브러리를 포함시키는 것이 일반적인 관행이다. --%>
<!-- 전체 jQuery 버전 포함  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- 사용자 정의 스크립트 -->
<script src="/blog/js/user.js"></script>

<!-- Bootstrap 및 기타 필요한 스크립트 포함 -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%-- TODO: script End --%>
</body>
</html>