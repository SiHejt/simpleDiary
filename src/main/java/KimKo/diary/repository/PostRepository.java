package KimKo.diary.repository;

import KimKo.diary.domain.Member;
import KimKo.diary.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post savePost(Post post);
<<<<<<< HEAD
    List<Post> findPostByUserID(String userID);
    List<Post> findPostByPostID(Long postID);
    public void deletePost(Long postID);
    Post editPost(Post post);
=======

    void deletePost(Long postID);

    Post editPost(Post post);
    List<Post> findPostByUserID(String userID);

    List<Post> findPostByPostID(Long postID);
>>>>>>> c16d0f8e16e156c30e1b2846a4cf5882ce6424c7
}
