package org.gimeast.service;

import lombok.extern.log4j.Log4j2;
import com.gimeast.w2.dto.TodoDto;
import com.gimeast.w2.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@Log4j2
public class TodoServiceTests {

    private TodoService todoService;

    @BeforeEach
    public void ready() {
        todoService = TodoService.INSTANCE;
    }

    @Test
    void testRegister() throws Exception {
        //given
        TodoDto todoDto = TodoDto.builder()
                .title("JDBC Test Title")
                .dueDate(LocalDate.now())
                .build();

        log.info("-------------------------------");
        log.info("todoDto: {}",todoDto);
        //when
        todoService.register(todoDto);

        //then

    }

}
