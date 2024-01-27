package KimKo.diary.repository;

import KimKo.diary.domain.Member;
import KimKo.diary.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.security.Timestamp;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class JdbcPostRepository implements PostRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcPostRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Post savePost(Post post) {
        String sql = "INSERT INTO post (userID, title, content, date) VALUES (?, ?, ?, ?)";
        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis()); // 현재 날짜를 가져와서 java.sql.Date로 변환

        jdbcTemplate.update(sql, post.getUserID(), post.getTitle(), post.getContent(), currentDate);
        return post;
    }

    public Post editPost(Post post) {
        String sql = "UPDATE post SET title = ?, content = ? WHERE postID = ?";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), post.getPostID());

        return post;
    }

    @Override
    public Optional<Post> findPost(String userID) {
        List<Post> result = jdbcTemplate.query("SELECT * FROM post WHERE userID = ?", postRowMapper(), userID);
        return result.stream().findAny();
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
