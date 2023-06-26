package org.gimeast.w1.todo;

import org.gimeast.w1.todo.dto.TodoDto;
import org.gimeast.w1.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "todoListController", urlPatterns = "/todo/list")
public class TodoListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/todo/list");

        List<TodoDto> list = TodoService.INSTANCE.getList();

        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req,resp);
    }


}
