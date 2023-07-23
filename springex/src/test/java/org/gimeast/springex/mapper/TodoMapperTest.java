package org.gimeast.springex.mapper;

import lombok.extern.log4j.Log4j2;
import org.gimeast.springex.domain.TodoVO;
import org.gimeast.springex.dto.PageRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
class TodoMapperTest {

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    void testGetTime() throws Exception {
        log.info("todoMapper.getTime(): {}",todoMapper.getTime());
    }

    @Test
    void testInsert() throws Exception {
        //given
        TodoVO todoVO = TodoVO.builder()
                .title("스프링 테스트")
                .dueDate(LocalDate.of(2023, 07, 01))
                .writer("user00")
                .build();

        //when
        todoMapper.insert(todoVO);

        //then
    }

    @Test
    void testSelectAll() throws Exception {
        //given
        List<TodoVO> todoVOList = todoMapper.selectAll();

        //when

        //then
        todoVOList.forEach(todoVO -> log.info("todoVO: {}",todoVO));
    }

    @Test
    void testSelectList() throws Exception {
        //given
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[]{"t", "w"})
                .keyword("한글")
                .finished(true)
                .from(LocalDate.of(2023,07,01))
                .to(LocalDate.of(2023,12,31))
                .build();

        //when
        List<TodoVO> todoVOList = todoMapper.selectList(pageRequestDTO);

        //then
        todoVOList.forEach(todoVO -> log.info("todoVO: {}",todoVO));
    }

    @Test
    void testSelectListSearch() throws Exception {
        //given
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[]{"t", "w"})
                .keyword("스프링")
                .finished(false)
                .from(LocalDate.of(2023,07,01))
                .to(LocalDate.of(2023,12,31))
                .build();

        //when
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        //then
        voList.forEach(vo -> log.info("vo: {}", vo));
        log.info("count: {}", todoMapper.getCount(pageRequestDTO));
    }

    @Test
    void testSelectOne() throws Exception {
        //given
        TodoVO todoVO = todoMapper.selectOne(3L);

        //when

        //then
        log.info("todoVO: {}",todoVO);
    }

}