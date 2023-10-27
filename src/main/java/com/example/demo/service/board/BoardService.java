package com.example.demo.service.board;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.board.BoardMapper;
import com.example.demo.vo.BoardVO;

@Service
public class BoardService {
    
    @Autowired
    private BoardMapper boardMapper;

    public Map selectById(){
        return boardMapper.selectById();
    }
}
