package org.gimeast.springex.sample;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@ToString
@Service
public class SampleService {

    private final SampleDAO sampleDAO;

    @Autowired
    public SampleService(@Qualifier("eventSampleDAOImpl") SampleDAO sampleDAO) {
        this.sampleDAO = sampleDAO;
    }





}
