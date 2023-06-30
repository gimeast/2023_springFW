package org.gimeast.springex.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
class TimeMapperTest {

    private final TimeMapper timeMapper;
    private final TimeMapper2 timeMapper2;

    @Autowired(required = false)
    TimeMapperTest(TimeMapper timeMapper, TimeMapper2 timeMapper2) {
        this.timeMapper = timeMapper;
        this.timeMapper2 = timeMapper2;
    }

    @Test
    void testGetTime() throws Exception {
        log.info(timeMapper.getTime());
    }

    @Test
    void testGetNow() throws Exception {
        log.info(timeMapper2.getNow());
    }




}