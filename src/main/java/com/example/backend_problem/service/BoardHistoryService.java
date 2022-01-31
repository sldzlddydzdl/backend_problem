package com.example.backend_problem.service;

import com.example.backend_problem.domain.Board;
import com.example.backend_problem.domain.BoardHistory;
import com.example.backend_problem.domain.User;
import com.example.backend_problem.dto.BoardHistoryDTO;
import com.example.backend_problem.dto.PageRequestDTO;
import com.example.backend_problem.dto.PageResultDTO;
import com.example.backend_problem.repository.BoardRepository;
import com.example.backend_problem.repository.BoardHistoryRepository;
import com.example.backend_problem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BoardHistoryService {

    private final BoardHistoryRepository boardHistoryRepository;

    private final UserRepository userRepository;

    private final BoardRepository boardRepository;

    public BoardHistoryDTO entityToDto(BoardHistory boardHistory){

        String role = boardHistory.getUser().getAccountType();

        if(role.equals("ROLE_REALTOR")){
            role = "(공인 중개사)";
        }else if(role.equals("ROLE_LESSOR")){
            role = "(임대인)";
        }else if(role.equals("ROLE_LESSEE")){
            role = "(임차인)";
        }else if(role.equals("ROLE_USER")){
            role = "";
        }

        User user = userRepository.findById(boardHistory.getUser().getId()).get();

        BoardHistoryDTO boardHistoryDTO = BoardHistoryDTO.builder()
                .id(0L)
                .nickName(user.getNickname()+role)
                .historyContent(boardHistory.getHistoryContent())
                .regDate(boardHistory.getRegDate())
                .build();

        return boardHistoryDTO;
    }

    public PageResultDTO<BoardHistoryDTO, BoardHistory> getList(PageRequestDTO pageRequestDTO){

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("regDate").descending());

        Page<BoardHistory> result =  boardHistoryRepository.findAll(pageable);

        Function<BoardHistory, BoardHistoryDTO> fn = (en -> entityToDto(en));

        return new PageResultDTO<>(result, fn);

    }

    @Transactional
    public void insertHistory(Long userId, Long boardId, String insertTitle ,String historyKinds) {

        User user = userRepository.findById(userId).get();

        String role = user.getAccountType();

        String historyContent = "";

        if(role.equals("ROLE_REALTOR")){
            role = "(공인 중개사)";
        }else if(role.equals("ROLE_LESSOR")){
            role = "(임대인)";
        }else if(role.equals("ROLE_LESSEE")){
            role = "(임차인)";
        }else if(role.equals("ROLE_USER")){
            role = "(일반회원)";
        }

        if(historyKinds.equals("insertBoard")){
            historyContent = user.getNickname()+role+" 유저가 제목이 "+ insertTitle + " 인 게시글을 작성하였습니다.";

            BoardHistory boardHistory = BoardHistory.builder()
                    .id(0L)
                    .nickName(user.getNickname()+role)
                    .historyContent(historyContent)
                    .user(user)
                    .build();

            boardHistoryRepository.save(boardHistory);
        }
        else{
            Board board = boardRepository.findById(boardId).get();
            User boardCreateUser = board.getUser();
            String boardTitle = board.getTitle();

            String boardCreateUserRole = boardCreateUser.getAccountType();

            if(boardCreateUserRole.equals("ROLE_REALTOR")){
                boardCreateUserRole = "(공인 중개사)";
            }else if(boardCreateUserRole.equals("ROLE_LESSOR")){
                boardCreateUserRole = "(임대인)";
            }else if(boardCreateUserRole.equals("ROLE_LESSEE")){
                boardCreateUserRole = "(임차인)";
            }


            if(historyKinds.equals("heartClickOn")){
                historyContent = user.getNickname()+role+" 유저가 작성자가 "+boardCreateUser.getNickname()+boardCreateUserRole+
                        " 이면서 제목이 "+boardTitle + " 인 게시글에 좋아요를 눌렀습니다.";
            }else if(historyKinds.equals("heartClickOff")){
                historyContent = user.getNickname()+role+" 유저가 작성자가 "+boardCreateUser.getNickname()+boardCreateUserRole+
                        " 이면서 제목이 "+boardTitle + " 인 게시글에 좋아요를 취소했습니다.";
            }else if(historyKinds.equals("updateBoard")){
                historyContent = user.getNickname()+role+" 유저가 제목이 "+ insertTitle + " 인 게시글을 수정하였습니다.";
            }else if(historyKinds.equals("deleteBoard")){
                historyContent = user.getNickname()+role+" 유저가 제목이 "+ insertTitle + " 인 게시글을 삭제하였습니다.";
            }

            BoardHistory boardHistory = BoardHistory.builder()
                    .id(0L)
                    .nickName(user.getNickname()+role)
                    .historyContent(historyContent)
                    .user(user)
                    .build();

            boardHistoryRepository.save(boardHistory);
        }

    }
}
