package com.gimeast.w2.controller;

import com.gimeast.w2.dto.MemberDto;
import com.gimeast.w2.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@Log4j2
@WebServlet("/login")
public class LoginController extends HttpServlet {

    private MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("login get...........");
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");
        String auto = req.getParameter("auto");

        boolean rememberMe = auto != null && auto.equals("on");

        if (rememberMe) {
            String uuid = UUID.randomUUID().toString();
        }

        try {
            MemberDto loginInfo = memberService.login(mid, mpw);
            log.info("loginInfo: {}", loginInfo);

            if (rememberMe) {
                String uuid = UUID.randomUUID().toString();

                memberService.updateUuid(mid, uuid);
                loginInfo.setUuid(uuid);

                Cookie rememberCookie = new Cookie("remember-me", uuid);
                rememberCookie.setPath("/");
                rememberCookie.setMaxAge(60*60*24*7);

                resp.addCookie(rememberCookie);
            }

            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", loginInfo);

            resp.sendRedirect("/todo/list");

        } catch (Exception e) {
            resp.sendRedirect("/login?result=error");
        }

    }
}
