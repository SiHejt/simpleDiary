package KimKo.diary.controller;

import KimKo.diary.domain.Post;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class HomeController {
    private final PostService postService;

    @GetMapping("/")
    public String route(HttpSession session) {
        Long userID = (Long) session.getAttribute("userID");
        if (userID == null) {
            return "home/login";
        }
        return "redirect:/home";
    }

    //하단바, 홈화면에서 글 목록 불러옴. 제목만 보임
    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        Long userID = (Long) session.getAttribute("userID");
        if (userID == null) {
            return "home/login";
        }
        List<Post> posts = postService.viewTitles();
        model.addAttribute("post", posts);
        return "home/home";
    }

    @GetMapping("/more")
    public String more() {
        return "more";
    }

}
