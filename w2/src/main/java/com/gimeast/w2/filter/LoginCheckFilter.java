package com.gimeast.w2.filter;

import com.gimeast.w2.dto.MemberDto;
import com.gimeast.w2.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter(urlPatterns = "/todo/*")
@Log4j2
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Login Check Filter............");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        //로그인정보가 세션에 있으면 필터통과
        if (session.getAttribute("loginInfo") != null) {
            chain.doFilter(request, response);
            return;
        }

        //이름이 remember-me인 쿠키를 찾아서 존재하면 가져오고 없다면 null을 반환한다.
        Cookie cookie = findCookie(req.getCookies(), "remember-me");
        //쿠키가 없다면 로그인 controller로 redirect 한다.
        if (cookie == null) {
            res.sendRedirect("/login");
            return;
        }

        log.info("cookie는 존재하는 상황");
        //쿠키가 존재한다면 쿠키의 값을 가져온다.
        String uuid = cookie.getValue();

        try {
            //쿠키의값으로 사용자 정보를 가져온다.
            MemberDto memberDto = MemberService.INSTANCE.getByUuid(uuid);
            log.info("쿠키의 값으로 조회한 사용자 정보: {}", memberDto);

            //사용자정보가 null이면 쿠키값이 잘못되었다는 exception을 날린다.
            if (memberDto == null) {
                throw new Exception("Cookie value is not valid");
            }

            //쿠키값으로 찾은 사용자정보가 DB에 있다면 세션에 저장한다.
            session.setAttribute("loginInfo", memberDto);
            //필터를 통과시킨다.
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("/login");//DB에서 조회한 사용자정보가 null로 로그인창으로 redirect 한다.
        }

    }

    private Cookie findCookie(Cookie[] cookies, String name) {

        //쿠키가 없다면 null 반환
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        //쿠키가 존재한다면 name과 동일한 이름을 가진 쿠키를 찾는다
        Optional<Cookie> result = Arrays.stream(cookies).filter(ck -> ck.getName().equals(name)).findFirst();
        //동일한 쿠키가 존재한다면 쿠키를 반환하고 동일한 쿠키가 없다면 null을 반환한다.
        return result.isPresent() ? result.get() : null;
    }


}
