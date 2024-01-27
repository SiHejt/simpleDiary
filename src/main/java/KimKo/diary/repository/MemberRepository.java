package KimKo.diary.repository;

import KimKo.diary.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member saveMember(Member member);
    List<Member> findMember(String userID);
    Boolean vaildateMember(String userID, String password);
}
