package com.example.Core.order;

import com.example.Core.AppConfig;
import com.example.Core.member.Grade;
import com.example.Core.member.Member;
import com.example.Core.member.MemberService;
import com.example.Core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();
//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        // given
        Long memberId = 1L;
        // when
        // then
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order orderA = orderService.createOrder(memberId,"itemA",10000);
        Assertions.assertThat(orderA.getDiscountPrice()).isEqualTo(1000);
    }
}
