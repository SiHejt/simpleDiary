package KimKo.diary.repository;

import KimKo.diary.domain.Member;

public interface MemberRepository {
    void saveMember(Member member);
    Member findByuserID(Long userID);
    boolean vaildateMember(Long userID, Long password);
}
