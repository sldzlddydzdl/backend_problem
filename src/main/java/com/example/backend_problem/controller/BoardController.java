package com.example.backend_problem.controller;

import com.example.backend_problem.domain.Board;
import com.example.backend_problem.domain.Like;
import com.example.backend_problem.domain.User;
import com.example.backend_problem.dto.PageRequestDTO;
import com.example.backend_problem.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    private final ReplyService replyService;

    private final UserService userService;

    private final LikeService likeService;

    private final BoardHistoryService boardHistoryService;

    private final HttpSession httpSession;

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO , Model model){

        model.addAttribute("result" , boardService.getList(pageRequestDTO));

        User user = (User)httpSession.getAttribute("user");
        model.addAttribute("user" , user);
        model.addAttribute("accountType", user.getAccountType());

        List<Like> likeList = likeService.findByUserId(user.getId());
        model.addAttribute("likeList" , likeList);

        return "board";

    }

    @GetMapping("/write")
    public String write(Model model){

        User user = (User)httpSession.getAttribute("user");

        model.addAttribute("user" , user);

        return "write";

    }

    @PostMapping("/register")
    public String register(Board board){

        User user = (User)httpSession.getAttribute("user");
        boardService.save(board , user);

        return "redirect:/board/list";

    }

    @GetMapping("/read")
    public String read(
            @RequestParam Long id ,
            Model model ,
            PageRequestDTO pageRequestDTO
            ){

        User user = (User)httpSession.getAttribute("user");

        Board board =  boardService.findById(id);

        Long replyCount  = replyService.getReplyCount(id);

        model.addAttribute("replyCount" , replyCount);
        model.addAttribute("board" , board);
        model.addAttribute("user" , user);
        model.addAttribute("result" , replyService.getList(pageRequestDTO, id));

        return "read";
    }

    @GetMapping("/read/likePeople/{boardId}")
    public String showLikePeoples(
            @PathVariable Long boardId,
            Model model,
            PageRequestDTO pageRequestDTO
    ){

        Board board = boardService.findById(boardId);

        model.addAttribute("result" , likeService.getList(pageRequestDTO, boardId));
        model.addAttribute("board" , board);

        return "likePeople";
    }

    @PostMapping("/updateBoard")
    public @ResponseBody String update(
            @RequestParam Long boardId,
            @RequestParam String title,
            @RequestParam String content
    ){
        boardService.update(boardId, title, content);

        return "updateBoard";
    }

    @PostMapping("/deleteBoard")
    public @ResponseBody String delete(
            @RequestParam Long boardId,
            @RequestParam Long userId
    ){

        boardService.deleteBoard(boardId);

        return "deleteBoard";

    }

    @PostMapping("/leaveMember")
    public @ResponseBody String leaveMember(@RequestParam Long userId){

        userService.leaveMember(userId);
        return "leaveMemberSuccess";

    }

    @PostMapping("/heart")
    public @ResponseBody Map<String , Boolean> heartCheck(
            @RequestParam Long boardId
    ){
        User user = (User)httpSession.getAttribute("user");
        Boolean heartCheck = likeService.likeCheck(user , boardId);

        Map<String , Boolean> send = new HashMap<>();
        send.put("heartfullCheck" , heartCheck);

        return send;

    }

}
