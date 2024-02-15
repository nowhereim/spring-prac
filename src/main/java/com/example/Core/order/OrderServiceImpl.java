package com.example.Core.order;

import com.example.Core.discount.DiscountPolicy;
import com.example.Core.member.Member;
import com.example.Core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor //FIXME final이 붙은 필드만 생성자를 만들어준다.
public class OrderServiceImpl implements OrderService{

    private  final MemberRepository  memberRepository; //= new MemoryMemberRepository();
    //FIXME 필드주입은 @Autowired private DiscountPolicy discountPolicy; 이런식인걸 말하는데 이렇게 하면 누군가가 변경할 수 있어서 좋지 않다.
//    private final DiscountPolicy discountPolicy //= new FixDiscountPolicy();
    private  final DiscountPolicy discountPolicy; // = new RateDiscountPolicy();


    //FIXME: 이런식으로 수정자 주입도 가능한데 이러면 누군가가 변경할 수 있어서 좋지 않다.
//  @Autowired
    //FIXME 퀄리파이어 대신에 직접 에너테이션을 만들면 @MainDiscountPolicy 이런식으로 타입 앞에 사용할 수 있다.
//    public void setMemberRepository(MemberRepository memberRepository) {
//      System.out.println("memberRepository1 = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//  @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//      System.out.println("discountPolicy1 = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

//    @Autowired //TODO: 생성자가 1개일때는 @Autowired 생략해도 컴포넌트 스캔할때 자동으로 알아서 주입해준다.
    //생성자에 @Autowired가 있으면 스프링이 연관된 컴포넌트를 찾아와서 주입해준다.
    //즉 MemberRepository 타입에 맞는 애를 찾아와서 주입해준다.
    //DiscountPolicy 타입에 맞는 애를 찾아와서 주입해준다.
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("memberRepository0 = " + memberRepository);
//        System.out.println("discountPolicy0 = " + discountPolicy);
//
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }


    //FIXME: 테스트용
    public MemberRepository getMemberRepository (){
        return memberRepository;
    }


}
//할인 정책을 이용하는 클라이언트인데 이 클라이언트 코드가 변경되어야하는 상황 발생.