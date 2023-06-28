package org.gimeast.jdbcex.controller;


import lombok.extern.log4j.Log4j2;
import org.gimeast.jdbcex.dto.TodoDto;
import org.gimeast.jdbcex.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "todoReadController", urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TodoDto todoDto = todoService.get(Long.valueOf(req.getParameter("tno")));
            log.info("todoDto: {}",todoDto);
            req.setAttribute("dto", todoDto);
            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
