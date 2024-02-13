package com.example.Core.discount;

import com.example.Core.member.Member;

public interface DiscountPolicy {

    /**
     * @return discount price 할인 대상 금액
     */
    int discount(Member member,int price);
}
