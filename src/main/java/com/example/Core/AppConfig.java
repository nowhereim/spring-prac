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

@Configuration
public class AppConfig {




    //TODO:이렇게 생성자 주입을 하면 멤버서비스는 추상화에만 의존할수있다.
    @Bean
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