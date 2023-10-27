package com.example.demo.controller.board;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.board.BoardService;
import com.example.demo.vo.BoardVO;

import org.springframework.ui.Model;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Log4j2
@Controller
@RequestMapping("/views/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    
    @GetMapping("/{boardId}")
    public String view(@PathVariable(name="boardId", required = true) Long boardId, Model model) {
        model.addAttribute("boardId", boardId);
        //ModelAndView mv = new ModelAndView();
        //mv.addObject("boardId", boardId);
        //mv.setViewName("views/board/view");
        return "/views/board/view";
    }

    @PostMapping(value="/sendDataToServer.do")
    @ResponseBody
    public ResponseEntity<BoardVO> sendDataToServer(@RequestBody  List<BoardVO> map) {
        log.info(map.toString());
        BoardVO bvo = new BoardVO();
        return ResponseEntity.ok(bvo);
    }
    
    @GetMapping("/testSearch.do")
    public String testSearch(Model model) 
        model.addAttribute("testList", boardService.selectById());
        return "/views/board/view";
    }
}
