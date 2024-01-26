package KimKo.diary;

import KimKo.diary.repository.JdbcMemberRepository;
import KimKo.diary.repository.JdbcPostRepository;
import KimKo.diary.repository.MemberRepository;
import KimKo.diary.repository.PostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService()
    {
        return new MemberService(memberRepository());
    }

    @Bean
    public PostService postService()
    {
        return new PostService(postRepository());
    }

    @Bean
    public MemberRepository memberRepository()
    {
        return new JdbcMemberRepository(dataSource);
    }

    @Bean
    public PostRepository postRepository()
    {
        return new JdbcPostRepository(dataSource);
    }

}
