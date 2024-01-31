package KimKo.diary.service;

import KimKo.diary.controller.LoginForm;
import KimKo.diary.controller.SignUpForm;
import KimKo.diary.domain.Member;
<<<<<<< HEAD
=======
import KimKo.diary.repository.JdbcMemberRepository;
>>>>>>> c16d0f8e16e156c30e1b2846a4cf5882ce6424c7
import KimKo.diary.repository.MemberRepository;

import java.util.List;

public class MemberService {
<<<<<<< HEAD
=======

>>>>>>> c16d0f8e16e156c30e1b2846a4cf5882ce6424c7
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

<<<<<<< HEAD
    public boolean login(LoginForm loginForm)
    {
        return memberRepository.vaildateMember(loginForm.getUserID(), loginForm.getPassword());
    }

    public boolean signUp(SignUpForm signUpForm)
    {
        List<Member> members = memberRepository.findMember(signUpForm.getUserID());
=======
    public boolean login(LoginForm loginForm) {
        return memberRepository.vaildateMember(loginForm.getUserID(), loginForm.getPassword());
    }


    public boolean signUp(SignUpForm signUpForm) {
        List<Member> members = memberRepository.findMember(signUpForm.getUserID());

>>>>>>> c16d0f8e16e156c30e1b2846a4cf5882ce6424c7
        if (members.isEmpty()) {
            Member newMember = new Member();
            newMember.setUserID(signUpForm.getUserID());
            newMember.setPassword(signUpForm.getPassword());
            newMember.setUserName(signUpForm.getUserName());
            memberRepository.saveMember(newMember);
<<<<<<< HEAD
=======

>>>>>>> c16d0f8e16e156c30e1b2846a4cf5882ce6424c7
            return true;
        }
        return false;
    }
<<<<<<< HEAD
=======


>>>>>>> c16d0f8e16e156c30e1b2846a4cf5882ce6424c7
}
