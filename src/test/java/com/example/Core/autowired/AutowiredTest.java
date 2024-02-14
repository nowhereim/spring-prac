package com.example.Core.autowired;

import com.example.Core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{

        @Autowired(required = false) /* 이건 호출 자체가 안된다. 의존 관계가 없으면 호출 안됨. */
        public void setNoBean1(Member member1){
            System.out.println("member1 = " + member1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member2){ /*호출은 되고 널로 들어온다.*/
            System.out.println("member1 = " + member2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member3){ /*호출은 되고 Optional.empty로 들어온다.*/
            System.out.println("member2 = " + member3);
        }
    }
}
