package KimKo.diary.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MemberController {

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        Long userID = (Long) session.getAttribute("userID");
        if (userID == null) {
            return "home/login";
        }
        return "redirect:/home";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest httpServletRequest, LoginForm loginForm) {
        boolean result = memberService.login(loginForm);
        if (result == true) {
            httpServletRequest.getSession().invalidate();
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("userID", loginForm.getUserID());
            session.setMaxInactiveInterval(1800);

            return "redirect:/home";
        }
        return "redirect:/home/login";
    }

    @GetMapping("/signUp")
    public String signUpPage() {
        return "home/signUp";
    }

    //아이디등록
    @PostMapping("/signUp")
    public String signUp(SignUpForm signUpForm) {
        memberService.signUp(signUpForm);
        return "redirect:/home/login";
    }
}
