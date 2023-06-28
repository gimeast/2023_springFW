package com.gimeast.w2.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
@Log4j2
public class LogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("logout ...................");
        HttpSession session = req.getSession();

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            boolean exist = cookie.getName().equals("remember-me");
            if(exist) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }

        session.removeAttribute("loginInfo");
        session.invalidate();

        resp.sendRedirect("/");
    }
}
