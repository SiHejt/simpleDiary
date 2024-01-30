package KimKo.diary.repository;

import KimKo.diary.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member saveMember(Member member);
    List<Member> findMember(String userID);
    boolean vaildateMember(String userID, String password);
}
