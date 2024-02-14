package com.example.Core.scan;

import com.example.Core.AutoAppConfig;
import com.example.Core.member.MemberService;
import com.example.Core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {
    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
