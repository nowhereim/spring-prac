package com.example.Core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    // FIXME:추상화에 의존하면서 구현체에도 의존하는 안좋은 코드

    //TODO: 이렇게 하면 추상화에만 의존하게된다.
    private final MemberRepository memberRepository;

    @Autowired // 컴포넌트를 쓰면 이걸 주입 받을 방법이 없어짐. 그래서 오토와이어드 즉 자동 연결 을 사용하면 스프링이 자동으로 연결해준다.
    //ac.getBean(MemberRepository.class) 이런식으로 연결해준다.
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
