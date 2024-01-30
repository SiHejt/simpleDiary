package KimKo.diary.service;

import KimKo.diary.controller.LoginForm;
import KimKo.diary.controller.SignUpForm;
import KimKo.diary.domain.Member;
import KimKo.diary.repository.JdbcMemberRepository;
import KimKo.diary.repository.MemberRepository;

import java.util.List;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean login(LoginForm loginForm) {
        return memberRepository.vaildateMember(loginForm.getUserID(), loginForm.getPassword());
    }


    public boolean signUp(SignUpForm signUpForm) {
        List<Member> members = memberRepository.findMember(signUpForm.getUserID());

        if (members.isEmpty()) {
            Member newMember = new Member();
            newMember.setUserID(signUpForm.getUserID());
            newMember.setPassword(signUpForm.getPassword());
            newMember.setUserName(signUpForm.getUserName());
            memberRepository.saveMember(newMember);

            return true;
        }
        return false;
    }


}
