package KimKo.diary.repository;

import KimKo.diary.domain.Member;
import KimKo.diary.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcPostRepository implements PostRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcPostRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public boolean savePost(Post post) {
        return false;
    }

    @Override
    public List<Post> findPost(Long userID) {
        return null;
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> {
            Post post = new Post();
            post.setUserID(rs.getString("userID"));
            post.setPostID(rs.getLong("postID"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            post.setDate(rs.getString("date"));
            return post;
        };
    }
}
