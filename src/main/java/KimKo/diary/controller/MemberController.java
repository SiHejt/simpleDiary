package KimKo.diary.controller;

import KimKo.diary.service.MemberService;
import KimKo.diary.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        String userID = (String) session.getAttribute("userID");
        if (userID == null) {
            return "/login";
        }
        return "redirect:/home";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest httpServletRequest, LoginForm loginForm, Model model) {
        boolean result = memberService.login(loginForm);
        if (result == true) {
            httpServletRequest.getSession().invalidate();
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("userID", loginForm.getUserID());
            session.setMaxInactiveInterval(1800);

            return "redirect:/home";
        }
        model.addAttribute("loginError", true);
        return "redirect:/login";
    }

    @GetMapping("/signUp")
    public String signUpPage() {
        return "/signUp";
    }

    //아이디등록
    @PostMapping("/signUp")
    public String signUp(SignUpForm signUpForm, Model model) {
        boolean result = memberService.signUp(signUpForm);
        if (result == true)
            return "redirect:/login";
        model.addAttribute("signUpError", true);
        return "redirect:/signUp";
    }
}
