$("input[name='likeName']").each(function (i) {
   let boardNum = $("input[name='likeName']").eq(i).attr("value");
   $("#heartIcon"+boardNum).attr("src" , "/img/heartFull.png");
});

if($('#userAccountType').val() != 'ROLE_ADMIN'){
   $('.goHistory').css('display', 'none');
}

$('.goHistory').on('click' , function () {
   location.href="/boardHistory";
});

$('.logoutBtn').on('click', function () {
   location.href="/logout";
});

$('.myPageBtn').on('click' , function () {
   location.href="/myPage?userId="+$('#userId').val();
});

$('.writeBtn').on('click' , function () {
   if($('#accountType').val() == "ROLE_USER"){
      alert("일반유저는 글을 작성할수 없습니다.");
   }
   else{
      location.href="/board/write";
   }
});

$('.leaveMemberBtn').on('click' , function () {

   let leave = confirm("정말 회원을 탈퇴하시겠습니까?");

   if(leave == true){
      $.ajax({
         url : "/board/leaveMember",
         method : "post" ,
         data : {
            "userId" : $('#userId').val()
         },
         success : function (result) {
            console.log("leaveMemberSuccess");
            location.href="/logout";
         },
         error : function (error) {
            console.log(error)
         }
      });
   }
});

$('.heartIcon').on('click' , function () {
   var heartId = $(this).attr("id")
   var boardIdForHeart = heartId.substring(9)
   $.ajax({
      type : "Post",
      url : "/board/heart",
      async : false,
      data : {
         "boardId" : boardIdForHeart
      },
      success: function (data) {
         if( data.heartfullCheck == true) {
            $("#"+heartId).attr("src", "/img/heartFull.png")
            $('#heartCount'+boardIdForHeart).text(parseInt($('#heartCount'+boardIdForHeart).text())+1);
            $.ajax({
               url : "/insertHistory",
               method: "post",
               data: {
                  "userId" : $('#userId').val(),
                  "boardId" : boardIdForHeart,
                  "historyKinds" : "heartClickOn"
               },
               success : function (result) {
                  console.log("insertHistory success!");
               },
               error : function (error) {
                  console.log(error);
               }
            });
         } else {
            //이미지가 빈 하트로
            $("#"+heartId).attr("src", "/img/heartEmpty.png")
            $('#heartCount'+boardIdForHeart).text(parseInt($('#heartCount'+boardIdForHeart).text())-1);
            $.ajax({
               url : "/insertHistory",
               method: "post",
               data: {
                  "userId" : $('#userId').val(),
                  "boardId" : boardIdForHeart,
                  "historyKinds" : "heartClickOff"
               },
               success : function (result) {
                  console.log("insertHistory success!");
               },
               error : function (error) {
                  console.log(error);
               }
            });
         }
      },
      error : function () {
         console.log("실패");
      }
   });

});


