# backend_problem

REALTOR ( 공인중개사 ) , LESSEE ( 임차인 ) , LESSOR ( 임대인 )

|              URI             |     METHOD     |              GRANT            |                                      DESCRIPTION                                 |
|------------------------------|----------------|-------------------------------|----------------------------------------------------------------------------------|
|  /loginForm                  |       GET      |               ALL             |  맨처음 로그인을 하기위한 페이지 요청이다.                                        | 
|  /login                      |      POST      |               ALL             |  로그인을 누르면 아이디와 비밀번호를 확인후 로그인 시켜준다.                       |
|  /joinForm                   |       GET      |               ALL             |  회원가입을 하기위한 페이지 요청이다.                                             |
|  /join                       |      POST      |               ALL             |  회원가입이 완료되면 회원을 저장하고 로그인창으로 이동시켜준다.                    |
|  /idCheck                    |      POST      |               ALL             |  회원가입하기전에 해당 아이디가 중복이되는지 여부를 판단해준다.                    |
|  /nickCheck                  |      POST      |               ALL             |  회원가입하기전에 해당 닉네임이 중복이되는지 여부를 판단해준다.                    |
|  /accessDenied               |       GET      | USER, LESSOR, LESSEE, REALTOR |  권한에 맞지않는 페이지이동시 접근제한 페이지를 요청한다.                          |
|  /board/list                 |       GET      |               ALL             |  게시판에 글에관련된 정보를 보여준다.                                             |  
|  /board/write                |       GET      | LESSOR, LESSEE, REALTOR       |  게시판에 글을쓰기위해 글을쓰는 페이지를 요청한다.                                |
|  /board/register             |      POST      | LESSOR, LESSEE, REALTOR       |  게시판에 글을 등록하면 글에대한 정보를 저장한후 게시판으로 이동.                  |
|  /board/read                 |       GET      |               ALL             |  게시판에 글을 읽는다. 수정, 삭제, 댓글작성, 좋아요누른사람들을 확인할수 있다.     |
|  /board/likepeople/{boardId} |       GET      |               ALL             |  해당글에 대해 어떠한 사람들이 좋아요를 눌렀는지 명단이 나온다.                    |
|  /board/updateBoard          |      POST      | LESSOR, LESSEE, REALTOR       |  자신이 썻던 글에 대에 수정하면 글을 업데이트 해준다. ( 자신이 쓴 글만 해당 )      |
|  /board/deleteBoard          |      POST      | LESSOR, LESSEE, REALTOR       |  자신이 썻던 글을 삭제하면 flag ( 임시 지우기 ) 로 일단 게시판에 글을 안보여준다.  |
|  /board/leaveMember          |      POST      |               ALL             |  회원을 탈퇴한다. 이또한 flag[quit]( 탈퇴 여부 ) 로 완전히 지우지는 않는다.
|  /board/heart                |      POST      |               ALL             |  좋아요를 누르면 해당 글에대한 좋아요 갯수가 올라간다. 만약에 좋아요 눌렀었떤 글이면 좋아요가 취소되고 갯수가 하나 내려간다. |
|  /boardHistory               |       GET      |              ADMIN            |  관리자가 게시글에 어떠한 일이 있었는지 모든 내역을 관찰할수 있게해준다. 누가 어떤 게시물에 좋아요누르기, 좋아요취소하였는지 정보를 저장. 어떤유저가 어떤 제목에대한 글쓰기, 글수정, 글삭제 에대한 정보를 저장한다. 
|  /insertHistory              |      POST      |               ALL             |  좋아요를 누르거나, 좋아요를 취소하는 행위는 모든 유저가 할수 있게 하였지만 글쓰기, 글수정, 글삭제는 LESSOR, LESSEE, REALTOR 만 할수 있게 하였다. 이에 모든 event를 데이터베이스에 저장한다. |
|  /myPage                     |       GET      |               ALL             |  자신이 어떤 게시글에 좋아요했는지에대한 목록이 나오는 페이지를 요청한다. 여기서 해당 게시글에 이동할수 있도록 하였다. |
|  /insertReply                |      POST      |               ALL             |  해당 게시글에 대해 댓글을 작성하면 댓글을 저장하고 그 댓글을 보여준다.            |

위의 URI 들은 52.5.46.29:8080/ 로 해당 프로젝트를 배포하여서 위의 URI 를 직접확인할수 있습니다. ex) 52.5.46.29:8080/loginForm




