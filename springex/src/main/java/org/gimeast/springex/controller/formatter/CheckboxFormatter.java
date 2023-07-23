package org.gimeast.springex.controller.formatter;

import lombok.extern.log4j.Log4j2;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

@Log4j2
public class CheckboxFormatter implements Formatter<Boolean> {

    @Override
    public Boolean parse(String text, Locale locale) throws ParseException {
       log.info("CheckboxFormatter parse text: {}", text);
        if (text == null) {
            return false;
        }

        return text.equals("on");
    }

    @Override
    public String print(Boolean object, Locale locale) {
        log.info("CheckboxFormatter print object: {}", object.toString());
        return object.toString();
    }
}
