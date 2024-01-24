package KimKo.diary.controller;

import KimKo.diary.domain.Post;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class PostController {

    private final PostService postService;

    // 하단바, 등록버튼
    @GetMapping("/home/newPost")
    public String newPostPage(HttpSession session) {
        Long userID = (Long) session.getAttribute("userID");
        if (userID == null) {
            return "home/login";
        }
        return "home/newPost";
    }

    //글 등록
    @PostMapping("/home/newPost")
    public String newPost(HttpSession session, PostForm form) {
        Long userID = (Long) session.getAttribute("userID");
        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());

        postService.save(post, userID);

        return "redirect:/home";
    }

    //게시글 들어갔을 때 조회
    @GetMapping("/home/viewPost")
    public String viewPostPage(HttpSession session, @RequestParam final Long postID, Model model) {
        Long userID = (Long) session.getAttribute("userID");
        if (userID == null) {
            return "home/login";
        }
        Post posts = postService.findPostByID(postID);
        model.addAttribute("post", posts);
        return "home/viewPost";
        //html에서 id 이동하기
    }

    @PostMapping("/home/viewPost")
    public String deletePost(@RequestParam final Long postID) {
        postService.deletePost(postID);
        return "redirect:/home";
        //삭제 :
    }

    //수정 기능, 하단바
    @GetMapping("/home/viewPost/editPost")
    public String editPostPage(HttpSession session, @RequestParam final Long postID, Model model) {
        Long userID = (Long) session.getAttribute("userID");
        if (userID == null) {
            return "home/login";
        }
        Post post = postService.findPostByID(postID);
        model.addAttribute("post", post);
        return "home/editPost";
    }

    @PostMapping("/home/viewPost/editPost")
    public String editPost(@RequestParam final Long postID) {
        postService.editPost(postID);

        return "redirect:/home";
    }
}
