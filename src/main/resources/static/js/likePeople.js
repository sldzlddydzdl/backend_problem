$('.backBtn').on('click' ,function () {
    location.href="/board/read?id="+$('.boardId').val()
        +"&page=1";
});

$('.logoutBtn').on('click', function () {
    location.href='/logout';
});