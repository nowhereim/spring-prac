package com.example.Core.member;

public class MemberServiceImpl implements MemberService{
    // FIXME:추상화에 의존하면서 구현체에도 의존하는 안좋은 코드

    //TODO: 이렇게 하면 추상화에만 의존하게된다.
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    //FIXME: 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
