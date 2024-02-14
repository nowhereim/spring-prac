package com.example.Core.discount;

import com.example.Core.annotation.MainDiscountPolicy;
import com.example.Core.member.Grade;
import com.example.Core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary /*이건 우선순위를 정하는거임. 여러개의 빈이 있을때 이걸 붙인 빈을 우선적으로 사용하겠다는 뜻.*/
//@Qualifier("mainDiscountPolicy") 이렇게 하면 문자열 오타나면 런타임에 에러가 잡힌다 ㅠㅠ
/*이건 구분자임. 빈 이름을 변경하는게 아님.*/
//@MainDiscountPolicy //이렇게 하면 컴파일 타임에 에러가 잡힌다. 이게 더 안전하다. 어노테이션 만들기!
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10; // 10% 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        } else{
            return 0;
        }
    }
}
