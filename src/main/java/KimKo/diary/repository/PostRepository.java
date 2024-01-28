package KimKo.diary.repository;

import KimKo.diary.domain.Member;
import KimKo.diary.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post savePost(Post post);
    List<Post> findPostByUserID(String userID);
    List<Post> findPostByPostID(Long postID);
    public void deletePost(Long postID);
    Post editPost(Post post);
}
