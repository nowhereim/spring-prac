package com.example.Core.order;

import com.example.Core.discount.DiscountPolicy;
import com.example.Core.member.Member;
import com.example.Core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository; //= new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy //= new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy; // = new RateDiscountPolicy();

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
//할인 정책을 이용하는 클라이언트인데 이 클라이언트 코드가 변경되어야하는 상황 발생.