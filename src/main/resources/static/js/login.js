$(document).ready(function () {
    $("#loginBtn").click(function () {
        //사용자 계정정보 암호화전 평문
        let uid = $("#id").val();
        let pwd = $("#password").val();

        //RSA 암호화 생성
        const rsa = new RSAKey();
        rsa.setPublic($("#RSAModulus").val(), $("#RSAExponent").val());

        //사용자 계정정보를 암호화 처리
        uid = rsa.encrypt(uid);
        pwd = rsa.encrypt(pwd);

        $.ajax({
            type: "POST",
            url: "/HappyHouse/user/login",
            data: {id: uid, password: pwd},  //사용자 암호화된 계정정보를 서버로 전송
            dataType: "json",
            success: function (login) {
                if (!login) {
                    alert("아이디와 비밀번호가 일치하지 않습니다.");
                    window.location.assign("/HappyHouse/user/login");
                } else {
                    window.location.assign("/HappyHouse/");
                }
            }
        });
    });
})
;



