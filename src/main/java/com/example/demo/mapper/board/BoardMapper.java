package com.example.demo.mapper.board;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.vo.BoardVO;

@Mapper
public interface BoardMapper {
    public Map selectById();
}
