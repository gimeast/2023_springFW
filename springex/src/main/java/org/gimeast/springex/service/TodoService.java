package org.gimeast.springex.service;

import org.gimeast.springex.domain.TodoVO;
import org.gimeast.springex.dto.PageRequestDTO;
import org.gimeast.springex.dto.PageResponseDTO;
import org.gimeast.springex.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    void register(TodoDTO todoDTO);

    List<TodoDTO> getAll();
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    TodoDTO getOne(Long tno);

    void remove(Long tno);

    void modify(TodoDTO todoDTO);
}
