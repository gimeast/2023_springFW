package com.gimeast.w2.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
