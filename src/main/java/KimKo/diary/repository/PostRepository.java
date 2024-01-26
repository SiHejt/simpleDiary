package KimKo.diary.repository;

import KimKo.diary.domain.Member;
import KimKo.diary.domain.Post;

import java.util.List;

public interface PostRepository {
    boolean savePost(Post post);
    List<Post> findPost(Long userID);
}
