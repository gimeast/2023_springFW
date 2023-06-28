package com.gimeast.w2.controller;

import com.gimeast.w2.service.TodoService;
import lombok.extern.log4j.Log4j2;
import com.gimeast.w2.dto.TodoDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
@WebServlet(name = "todoModifyController", value = "/todo/modify")
public class TodoModifyController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TodoDto dto = todoService.get(Long.valueOf(req.getParameter("tno")));
            req.setAttribute("dto", dto);
            req.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String finished = req.getParameter("finished");
        TodoDto todoDto = TodoDto.builder()
                .tno(Long.parseLong(req.getParameter("tno")))
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"),DATEFORMATTER))
                .finished(finished != null && finished.equals("false"))
                .build();
        log.info("modify todoDto: {}", todoDto);

        try {
            todoService.modify(todoDto);
            resp.sendRedirect("/todo/read?tno="+req.getParameter("tno"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
