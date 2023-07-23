package org.gimeast.springex.service;

import lombok.extern.log4j.Log4j2;
import org.gimeast.springex.dto.PageRequestDTO;
import org.gimeast.springex.dto.PageResponseDTO;
import org.gimeast.springex.dto.TodoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Test
    void testRegister() throws Exception {
        //given
        TodoDTO todoDTO = TodoDTO.builder()
                .title("Test......")
                .dueDate(LocalDate.now())
                .writer("user1")
                .build();

        //when
        todoService.register(todoDTO);

        //then
    }
    
    @Test
    void testGetList() throws Exception {
        //given
        List<TodoDTO> todoDTOList = todoService.getAll();

        //when
        
        //then
        todoDTOList.forEach(todoDTO -> log.info("todoDTO: {}", todoDTO));
    }

    @Test
    void testPaging() throws Exception {
        //given
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .build();

        //when
        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);

        //then
        log.info("responseDTO: {}", responseDTO);
        responseDTO.getDtoList().forEach(dto -> log.info("dto: {}", dto));
    }

    @Test
    void testGetOne() throws Exception {
        //given
        TodoDTO todoDTO = todoService.getOne(3L);

        //when

        //then
        log.info("todoDTO: {}", todoDTO);
    }

}