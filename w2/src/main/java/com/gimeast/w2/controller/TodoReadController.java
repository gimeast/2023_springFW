package com.gimeast.w2.controller;


import lombok.extern.log4j.Log4j2;
import com.gimeast.w2.dto.TodoDto;
import com.gimeast.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
            Long tno = Long.valueOf(req.getParameter("tno"));
            TodoDto todoDto = todoService.get(tno);
            log.info("todoDto: {}",todoDto);
            req.setAttribute("dto", todoDto);

            //쿠키
            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");//쿠키 존재 여부를 확인하고 기존에 있다면 가져오고 없다면 쿠키를 생성해온다.
            String todoListStr = viewTodoCookie.getValue();//쿠키 name이 viewTodos인 쿠키의 value를 가져온다.

            boolean exist = false;//todoListStr이 쿠키의 value에 포함되어있는지

            if (todoListStr != null && todoListStr.indexOf(tno + "-") >= 0) {//todoListStr이 null이고 존재한다면(없으면 -1)
                exist = true;
            }

            log.info("exist: {}", exist);

            if (!exist) {//존재하지않으면 viewTodos 쿠키의 value에 todoListStr 값을 이어 붙이고 유효기간과 경로를 새로 저장한다.(::쿠키를 변경할때는 다시 경로나 유효시간을 셋팅 해줘야한다.)
                todoListStr += tno + "-";
                viewTodoCookie.setValue(todoListStr);
                viewTodoCookie.setPath("/");
                viewTodoCookie.setMaxAge(60*60*24);//24시간
                resp.addCookie(viewTodoCookie);
            }
            //

            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Cookie findCookie(Cookie[] cookies, String cookieName) {
        Cookie targetCookie = null;

        if (cookies != null && cookies.length > 0) {//쿠키 존재여부 확인
            for (Cookie ck : cookies) {
                if (ck.getName().equals(cookieName)) {//찾는 쿠키가 존재한다면 값을 targetCookie로 넣어주고 반환함.
                    targetCookie = ck;
                    break;
                }
            }
        }

        if (targetCookie == null) {//쿠키가 없다면 targetCookie 생성
            targetCookie = new Cookie(cookieName, "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*24);//24시간
        }

        return targetCookie;
    }
}
