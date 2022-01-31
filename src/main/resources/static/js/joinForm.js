let idCheckForRegister = false;
let nickCheckForRegister = false;

$('.idCheckBtn').on('click' , function (event) {

    event.preventDefault();
    $('#registerForm').unbind();
    $.ajax({
        url : "/idCheck" ,
        method : "post" ,
        data : {
            "userAccountId" : $('.inputId').val()
        },
        success : function (result) {
            console.log("idCheck success!");
            console.log("result.idCheck : " + result.idCheck);
            if(result.idCheck == true){
                alert("사용가능한 아이디입니다.");
                idCheckForRegister = true;
            }else{
                alert("이미 사용중인 아이디입니다.");
                idCheckForRegister = false;
            }
        },
        error : function (error) {
            console.log(error);
        }
    });
});

$('.nickCheckBtn').on('click' , function (event) {

    event.preventDefault();
    $('#registerForm').unbind();
    $.ajax({
        url : "/nickCheck" ,
        method: "post" ,
        data : {
            "userNickname" : $('.inputNick').val()
        },
        success : function (result) {
            console.log("nickCheck success!");
            if(result.nickCheck == true){
                alert("사용가능한 닉네임입니다.");
                nickCheckForRegister = true;
            }else{
                alert("이미 사용중인 닉네임입니다.");
                nickCheckForRegister = false;
            }
        },
        error : function (error) {
            console.log(error);
        }
    })
});

$('.registerBtn').on('click' ,function (event) {

    event.preventDefault();

    if(idCheckForRegister == true && nickCheckForRegister == true && $('.inputPw').val() != ""){
        $('#registerForm').unbind();
        $('#registerForm').submit();
    }else{
        if(idCheckForRegister == false){
            alert("아이디 중복을 확인해주세요");
        }
        if(nickCheckForRegister == false){
            alert("닉네임 중복을 확인해주세요");
        }
        if($('.inputPw').val() == ""){
            alert("비밀번호를 입력해주세요");
        }
    }

});

$('.inputId').change(function (){
   idCheckForRegister = false;
});

$('.inputNick').change(function () {
   nickCheckForRegister = false;
});

$('.goLoginFormBtn').on('click' , function () {
    location.href="/loginForm";
});