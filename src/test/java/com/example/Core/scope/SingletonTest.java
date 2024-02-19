package com.example.Core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {
    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class); //이렇게 넣으면 자동으로 컴포넌트스캔 클래스가 되어서 스캔떠버림.

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean2 = " + singletonBean2);
        System.out.println("singletonBean1 = " + singletonBean1);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    };

    @Scope("singleton")
    static class SingletonBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        };

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        };
    };
}
