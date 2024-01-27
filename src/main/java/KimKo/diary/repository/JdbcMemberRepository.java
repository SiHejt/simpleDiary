package KimKo.diary.repository;

import KimKo.diary.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member saveMember(Member member) {
        String sql = "INSERT INTO member (userID, password, userName) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, member.getUserID(), member.getPassword(), member.getUserName());
        return member;
    }

    @Override
    public List<Member> findMember(String userID) {
        return jdbcTemplate.query("select * from member where userID = ?", memberRowMapper(), userID);
    }

    @Override
    public Boolean vaildateMember(String userID, String password) {
        List<Member> members = findMember(userID);
        if (!members.isEmpty()) {
            Member member = members.get(0);
            return member.getPassword().equals(password);
        }
        return false;
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setUserID(rs.getString("userID"));
            member.setUserName(rs.getString("userName"));
            member.setPassword(rs.getString("password"));
            return member;
        };
    }
}
