package KimKo.diary.repository;

import KimKo.diary.domain.Member;
import KimKo.diary.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post savePost(Post post);
    Optional<Post> findPost(String userID);
    Post editPost(Post post);
}
