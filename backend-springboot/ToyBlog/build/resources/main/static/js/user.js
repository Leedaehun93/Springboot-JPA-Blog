/**
 * 웹 페이지의 동작을 관리하는 index 객체를 정의
 * init 함수와 save 함수를 포함
 */
let index = {
    /**
     * TODO: init 함수 : 버튼에 클릭 이벤트 리스너를 연결하고 화살표 함수 (() => {})를 사용하여 this를 현재 객체(index)에 바인딩한다.
     */
    init: function () {
        $("#btn-save").on("click", () => { // 버튼이 클릭되면 this.save 즉, index.save 함수가 호출 된다.
            this.save();
        });
        $("#btn-login").on("click", () => { //버튼이 클릭되면 this.save 즉, index.save 함수가 호출 된다.
            this.login();
        });
    },
    /**
     * TODO: save 함수 : 사용자가 입력한 데이터를 가져와서 JSON 객체로 변환한다. $.ajax를 사용하여 서버에 POST 요청을 보내고 JSON 형식으로 전송된다.
     * 1) .done 성공 처리 로직 : 요청이 성공하면 사용자를 다른 페이지(/blog)로 리디렉션하고
     * 2) .fail 오류 처리 로직 : 실패할 경우, 오류 메시지를 표시한다.
     */
    save: function () {
        // 사용자로부터 입력 받은 데이터를 임시 저장하는 용도로 회원가입완료 버튼 클릭시 화면 출력
        alert("save 함수 호출"); // 테스트 완료
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        // console에 데이터가 들어오는지 테스트 완료
        // console.log(data);

        /**
         * ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
         * ajax 호출 시 default가 비동기 호출로 진행된다.
         */
        $.ajax({
            type: "POST",
            url: "/api/user",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME) 알려준다.
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 문자열로 들어오고 json 타입이라면 => javascript 오브젝트로 들어온다. dataType을 생략 했을 경우 jquery가 MIME 타입을 확인하고 자동으로 결정한다.
        }).done(function (resp) {
            alert("회원가입이 완료되었습니다.");
            // alert(resp);
            // console.log(resp);
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    /**
     * TODO: login 함수 : 사용자가 입력한 데이터를 가져와서 JSON 객체로 변환한다. $.ajax를 사용하여 서버에 POST 요청을 보내고 JSON 형식으로 전송된다.
     * 1) .done 성공 처리 로직 : 요청이 성공하면 사용자 계정으로 로그인이 되어 다른 페이지(/blog)로 리디렉션하고
     * 2) .fail 오류 처리 로직 : 실패할 경우, 오류 메시지를 표시한다.
     */
    login: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val()
        }

        /**
         * ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
         * ajax 호출 시 default가 비동기 호출로 진행된다.
         */
        $.ajax({
            type: "POST", // type: GET type는 계정 정보가 남기 때문에 POST 방식을 선택한다.
            url: "/api/user/login",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME) 알려준다.
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 문자열로 들어오고 json 타입이라면 => javascript 오브젝트로 들어온다. dataType을 생략 했을 경우 jquery가 MIME 타입을 확인하고 자동으로 결정한다.
        }).done(function (resp) {
            alert("로그인이 완료되었습니다.");
            // alert(resp);
            console.log(resp);
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}
/**
 * init 함수는 페이지가 로드되었을 때 호출된다.
 */
index.init();