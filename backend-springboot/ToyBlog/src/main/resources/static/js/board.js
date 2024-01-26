/**
 * ======================================
 * FileName : board.js
 * Note :
 * 53강(블로그 프로젝트) - 글쓰기 완료
 * ======================================
 */

/**
 * 웹 페이지의 동작을 관리하는 board 객체를 정의
 * init 함수와 save 함수를 포함
 */
let index = {
    /**
     * init 함수 : 버튼에 클릭 이벤트 리스너를 연결한다.
     * 화살표 함수를 사용하여 이벤트 핸들러 내부에서 this가 index 객체를 참조하도록 한다.
     */
    init: function () {
        $("#btn-save").on("click", () => { // 버튼이 클릭되면 this.save 즉, index.save 함수가 호출 된다.
            this.save();
        });
    },
    /**
     * save 함수 : 사용자가 입력한 데이터를 가져와서 JSON 객체로 변환한다. $.ajax를 사용하여 서버에 POST 요청을 보내고 JSON 형식으로 전송된다.
     * 1) .done 성공 처리 로직 : 요청이 성공하면 사용자를 다른 페이지(/)로 리디렉션하고
     * 2) .fail 오류 처리 로직 : 실패할 경우, 오류 메시지를 표시한다.
     */
    save: function () {
        /**
         * 사용자 입력 데이터를 JSON 객체로 만들어 서버에 전송한다.
         */
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        }
        /**
         * Ajax를 통해 서버에 글쓰기 데이터를 비동기적으로 전송한다.
         */
        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            alert("글쓰기가 완료되었습니다."); // 화면 테스트 완료
            location.href = "/";
        }).fail(function (error) {
            // 요청이 실패하였을 때 실행된다.
            alert(JSON.stringify(error));
        });
    },
} // end of index
/**
 * init 함수는 페이지가 로드되었을 때 호출된다.
 * 스크립트 실행 시 init 함수를 호출하여 이벤트 리스너를 설정한다.
 */
index.init();