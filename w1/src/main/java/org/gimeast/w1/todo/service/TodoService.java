package org.gimeast.w1.todo.service;

import org.gimeast.w1.todo.dto.TodoDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum TodoService {
    INSTANCE;

    public void register(TodoDto todoDto) {
        System.out.println("DEBUG........."+todoDto);
    }

    public List<TodoDto> getList() {
        List<TodoDto> todoDtos = IntStream.range(0, 10).mapToObj(i -> {
            TodoDto dto = new TodoDto();
            dto.setTno((long) i);
            dto.setTitle("todo.." + i);
            dto.setDueDate(LocalDate.now());

            return dto;
        }).collect(Collectors.toList());

        return todoDtos;
    }

    public TodoDto get(Long tno) {
        TodoDto dto = new TodoDto();
        dto.setTno(tno);
        dto.setTitle("Sample Todo");
        dto.setDueDate(LocalDate.now());
        dto.setFinished(true);

        return dto;
    }
}
