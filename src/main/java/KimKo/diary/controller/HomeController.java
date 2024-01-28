package KimKo.diary.controller;

import KimKo.diary.domain.Post;
import KimKo.diary.service.MemberService;
import KimKo.diary.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class HomeController {
    private final PostService postService;

    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String route(HttpSession session) {
        String userID = (String) session.getAttribute("userID");
        if (userID == null) {
            return "/login";
        }
        return "redirect:/home";
    }

    //하단바, 홈화면에서 글 목록 불러옴. 제목만 보임
    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        String userID = (String) session.getAttribute("userID");
        if (userID == null) {
            return "/login";
        }
        List<Post> posts = postService.viewTitles(userID);
        model.addAttribute("post", posts);
        return "/home";
    }

    @GetMapping("/more")
    public String more() {
        return "/more";
    }

}
