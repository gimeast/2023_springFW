package org.gimeast.dao;

import org.gimeast.jdbcex.dao.TodoDao;
import org.gimeast.jdbcex.domain.TodoVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class TestDaoTests {

    private TodoDao todoDao;

    @BeforeEach
    public void ready() {
        todoDao = new TodoDao();
    }

    @Test
    void testTime() throws Exception {
        System.out.println(todoDao.getTime2());
    }

    @Test
    void testInsert() throws Exception {
        TodoVo todoVo = TodoVo.builder()
                .title("Sample Title...")
                .dueDate(LocalDate.of(2021, 12, 13))
                .build();

        todoDao.insert(todoVo);
    }

    @Test
    void testList() throws Exception {
        List<TodoVo> results = todoDao.selectAll();
        results.forEach(vo -> System.out.println(vo));
    }

    @Test
    void testSelectOne() throws Exception {
        //given
        Long tno = 11L;

        //when
        TodoVo vo = todoDao.selectOne(tno);

        //then
        System.out.println(vo);
    }

    @Test
    void testDeleteOne() throws Exception {
        Long tno = 1L;
        todoDao.deleteOne(tno);
    }

    @Test
    void testUpdateOne() throws Exception {
        TodoVo vo = TodoVo.builder()
                .tno(2L)
                .title("Sample Title.........")
                .dueDate(LocalDate.of(2023, 06, 27))
                .finished(true)
                .build();

        todoDao.updateOne(vo);
    }


}
