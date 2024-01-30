package KimKo.diary.controller;

import KimKo.diary.domain.Post;
import KimKo.diary.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 하단바, 등록버튼
    @GetMapping("/home/newPost")
    public String newPostPage(HttpSession session) {
        String userID = (String) session.getAttribute("userID");
        if (userID == null) {
            return "/login";
        }
        return "home/newPost";
    }

    //글 등록
    @PostMapping("/home/newPost")
    public String newPost(HttpSession session, PostForm form) {
        String userID = (String) session.getAttribute("userID");
        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());

        postService.savePost(post, userID);

        return "redirect:/home";
    }

    //게시글 들어갔을 때 조회
    @GetMapping("/home/viewPost")
    public String viewPostPage(HttpSession session, @RequestParam final Long postID, Model model) {
        String userID = (String) session.getAttribute("userID");
        if (userID == null) {
            return "/login";
        }
        Post post = postService.findPostByPostID(postID);
        model.addAttribute("post", post);
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
        String userID = (String) session.getAttribute("userID");
        if (userID == null) {
            return "/login";
        }
        Post post = postService.findPostByPostID(postID);
        model.addAttribute("post", post);
        return "home/editPost";
    }

    @PostMapping("/home/viewPost/editPost")
    public String editPost(@RequestParam final Long postID, PostForm form) {
        Post post = new Post();
        post.setPostID(postID);
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        postService.editPost(post);

        return "redirect:/home";
    }
}
