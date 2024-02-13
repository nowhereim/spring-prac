package com.example.Core;

import com.example.Core.discount.DiscountPolicy;
import com.example.Core.discount.FixDiscountPolicy;
import com.example.Core.discount.RateDiscountPolicy;
import com.example.Core.member.MemberService;
import com.example.Core.member.MemberServiceImpl;
import com.example.Core.member.MemoryMemberRepository;
import com.example.Core.order.OrderService;
import com.example.Core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //이놈을 해줘야 싱글톤을 보장한다. CGLIB라는 바이트코드 조작 라이브러리를 사용해서 싱글톤을 보장한다.
//만약 이놈을 안해주면 매번 다른 객체를 만들어낸다.
//이 에너테이션을 걸면 예를들어 AppConfig를 상속받아 새로운 클래스를 만들고 내부적으로 빈이 이미 있나 없나 확인하고 없으면 빈을 등록하고 있으면 빈을 가져오는 식으로 동작한다.
public class AppConfig {
    //TODO:이렇게 생성자 주입을 하면 멤버서비스는 추상화에만 의존할수있다.

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()


    @Bean //스프링 컨테이너에 등록
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public  MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
}
//여기서 필요한 것들을 주입해줌으로써 서비스임플은 관심사에만 집중할수있게되었다.