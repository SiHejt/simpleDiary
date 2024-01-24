package KimKo.diary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class MemberController {

    @GetMapping("/login")
    public String loginPage() {


        return "login";
    }

    @GetMapping("/signUp")
    public String signUpPage() {
        return "signUp";
    }

    //아이디등록
    @PostMapping("/signUp")
    public String signUp() {
        return "redirect:/login";
    }
}
