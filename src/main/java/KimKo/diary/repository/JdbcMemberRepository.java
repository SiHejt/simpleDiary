package KimKo.diary.repository;

import KimKo.diary.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

public class JdbcMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveMember(Member member) {
    }

    @Override
    public Member findByuserID(Long userID) {
        return null;
    }

    @Override
    public boolean vaildateMember(Long userID, Long password) {
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
