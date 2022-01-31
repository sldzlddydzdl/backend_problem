$('.logoutBtn').on('click' , function () {
   location.href="/logout";
});

$(".boardBtn").on('click' , function () {
   location.href="/board/list";
});

$('#writeForm').submit(function () {
   $.ajax({
      url : "/insertHistory",
      method: "post",
      data: {
         "userId" : $('.userId').val(),
         "title" : $('.inputTitle').val(),
         "historyKinds" : "insertBoard"
      },
      success : function (result) {
         console.log("insertHistory success!");
      },
      error : function (error) {
         console.log(error);
      }
   });
});
