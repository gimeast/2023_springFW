package org.gimeast.springex.mapper;

import org.gimeast.springex.domain.TodoVO;
import org.gimeast.springex.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {

    String getTime();

    void insert(TodoVO todoVO);

    List<TodoVO> selectAll();
    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);
    int getCount(PageRequestDTO pageRequestDTO);

    TodoVO selectOne(Long tno);

    void delete(Long tno);

    void update(TodoVO todoVO);
}
