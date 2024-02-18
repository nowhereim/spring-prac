package com.example.Core.autowired;

import com.example.Core.AutoAppConfig;
import com.example.Core.discount.DiscountPolicy;
import com.example.Core.member.Grade;
import com.example.Core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "user", Grade.VIP);
        int discountPrice = discountService.discount(member,10000, "fixDiscountPolicy");
        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        // TODO 여기서 부터 시작 7분 19초
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String,DiscountPolicy> policyMap, List<DiscountPolicy> policyList){
            this.policyMap = policyMap;
            this.policies = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        public int discount(Member member, int i, String fixDiscountPolicy) {
            return 0;
        }
    }
}
