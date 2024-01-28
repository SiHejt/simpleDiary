package KimKo.diary.service;

import KimKo.diary.domain.Post;
import KimKo.diary.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void savePost(Post post, String userID) {
        post.setUserID(userID);
        postRepository.savePost(post);
    }

    public Post findPostByPostID(Long postID) {
        return postRepository.findPostByPostID(postID).get(0);
    }

    public void deletePost(Long postID) {
        postRepository.deletePost(postID);
    }

    public void editPost(Post post) {
        postRepository.editPost(post);
    }

    public List<Post> viewTitles(String userID) {
        return postRepository.findPostByUserID(userID);
    }

}
