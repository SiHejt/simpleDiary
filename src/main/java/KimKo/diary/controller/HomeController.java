package KimKo.diary.controller;

import KimKo.diary.domain.Post;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String route() {
        return "route";
    }

    //하단바, 홈화면에서 글 목록 불러옴. 제목만 보임
    @GetMapping("/home")
    public String homePage(Model model) {
        List<Post> posts = postService.viewTitles();
        model.addAttribute("post", posts);
        return "home";
    }

    @GetMapping("/more")
    public String more() {
        return "more";
    }

}
