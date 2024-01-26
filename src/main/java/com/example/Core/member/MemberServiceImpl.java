package com.example.Core.member;

public class MemberServiceImpl implements MemberService{
    // FIXME:추상화에 의존하면서 구현체에도 의존하는 안좋은 코드
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
