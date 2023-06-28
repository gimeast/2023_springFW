package com.gimeast.w2.controller;

import lombok.extern.log4j.Log4j2;
import com.gimeast.w2.dto.TodoDto;
import com.gimeast.w2.service.TodoService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "todoListController", value = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("todo list......");
        try {
            List<TodoDto> list = todoService.listAll();
            list.stream().forEach(dto -> System.out.println("================"+dto));
            req.setAttribute("list", list);

            ServletContext servletContext = req.getServletContext();

            log.info("appName: {}", servletContext.getAttribute("appName"));

            req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
