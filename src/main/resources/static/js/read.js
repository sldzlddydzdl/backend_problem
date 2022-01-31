const modal = document.querySelector('.modal');
const backImg = document.querySelector('.backImg');

$('.updateBtn').click(function(){
    modal.classList.toggle('show');
    if (modal.classList.contains('show')) {
        $('#main').css('display' , 'none');
    }
});

backImg.addEventListener('click', (event) => {
    if(event.target === backImg){
        modal.classList.toggle('show');
    }
    if(!modal.classList.contains('show')){
        $('#main').css('display' , 'block');
    }
});

if($('#boardAndUserRelation').val() != $('#user').val()){
    $('.updateBtn').css('display' , 'none');
    $('.deleteBtn').css('display' , 'none');
    $('.likePeopleBtn').css('margin-left', '307px')
}

$('#replyInputBtn').on('click' , function () {
    if($('.replyInput').val() == "" || $('.replyInput').val() == null){
        alert("댓글을 입력해주세요!");
    }
    else if($('#userAccountType').val() == "ROLE_USER"){
        alert("기본회원은 댓글을 입력할수 없습니다!");
    }
    else{
        $.ajax({
            url : "/insertReply" ,
            type : "post",
            data : {
                "userId" : $('#user').val(),
                "boardId" : $('#board').val(),
                "replyContent" : $('.replyInput').val()
            },
            success : function (result) {
                console.log("reply insert success!!");
                location.reload();
            },
            error : function (error) {
                console.log(error);
            }
        });
    }
});

$('.logoutBtn').on('click', function () {
    location.href="/logout";
});

$('.boardBtn').on('click' , function () {
    location.href="/board/list";
});

$(".updateBtnModal").on('click' , function () {

    if($('.Modal_inputTitle').val() == "" || $(".Modal_inputTitle").val() == null){
        alert("제목을 입력해주세요!");
    }
    else if($(".Modal_inputContent").val() == "" || $(".Modal_inputContent").val() == null){
        alert("내용을 입력해주세요!")
    }
    else{
        $.ajax({
            url: "/board/updateBoard",
            method : "post",
            data : {
                "boardId" : $('#board').val(),
                "title" : $(".Modal_inputTitle").val(),
                "content" : $(".Modal_inputContent").val()
            },
            success : function (result) {
                console.log("update success");
                modal.classList.toggle('show');
                $('.inputTitle').val($('.Modal_inputTitle').val());
                $('.inputContent').val($('.Modal_inputContent').val());
                $.ajax({
                    url : "/insertHistory",
                    method: "post",
                    data: {
                        "userId" : $('#user').val(),
                        "boardId" : $('#board').val(),
                        "title" : $('.Modal_inputTitle').val(),
                        "historyKinds" : "updateBoard"
                    },
                    success : function (result) {
                        console.log("insertHistory success!");
                    },
                    error : function (error) {
                        console.log(error);
                    }
                });
            },
            error : function (error) {
                console.log(error);
            }
        });
    }
});

$('.deleteBtn').on('click' , function () {

    let del = confirm("정말 삭제하시겠습니까?");
    if(del == true){
        $.ajax({
            url: "/board/deleteBoard",
            method: "post" ,
            data : {
                "boardId" : $("#board").val(),
                "userId" : $("#user").val()
            },
            success : function (result) {
                console.log("delete success!");
                $.ajax({
                    url : "/insertHistory",
                    method: "post",
                    data: {
                        "userId" : $('#user').val(),
                        "boardId" : $('#board').val(),
                        "title" : $('.inputTitle').val(),
                        "historyKinds" : "deleteBoard"
                    },
                    success : function (result) {
                        console.log("insertHistory success!");
                        location.href="/board/list";
                    },
                    error : function (error) {
                        console.log(error);
                    }
                });
            },
            error : function (error) {
                console.log(error);
            }
        });
    }

});

$('.likePeopleBtn').on('click' , function () {
    location.href="/board/read/likePeople/"+$('#board').val();
});