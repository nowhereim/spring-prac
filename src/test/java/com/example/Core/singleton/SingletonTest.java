package com.example.Core.singleton;

import com.example.Core.AppConfig;
import com.example.Core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContatiner (){
        AppConfig appConfig = new AppConfig();
        //1.조회: 호출할때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2.조회: 호출할때마다 객체를 생성
        MemberService memberSerivce2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);

        System.out.println("memberSerivce2 = " + memberSerivce2);

        //호출을 할때마다 객체가 생성되는지 확인

        //memberService1 != memberSerivce2
        Assertions.assertThat(memberService1).isNotSameAs(memberSerivce2);
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        Assertions.assertThat(instance1).isSameAs(instance2);
        //same(==)은 참조 비교 , equal은 값 비교 즉 서로 다른 객체여도 값이 같으면 true
    }

    @Test
    @DisplayName("스프링 컨테이너와 섹시한 싱글톤")
    void springContainer(){
//        AppConfig appConfig = new AppConfig();
        //AppConfig.class를 읽어 스프링 컨테이너 등록하는거임.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //1.조회: 스프링 컨테이너에 등록된 빈 가져오기
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);

        //2.조회: 이놈도 같은 빈을 가져오는지 확인
        MemberService memberSerivce2 = ac.getBean("memberService",MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberSerivce2 = " + memberSerivce2);

        //호출을 할때마다 객체가 생성되는지 확인

        //memberService1 != memberSerivce2
        Assertions.assertThat(memberService1).isSameAs(memberSerivce2);
    };
}
