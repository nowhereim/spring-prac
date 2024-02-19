package com.example.Core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

    @Test
    @DisplayName("프로토타입 예제")
    void prototypeFind(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);

        /*이건 뭐 당연히 각자 따로노는 인스턴스다. 스프링 컨테이너가 관리하지 않는다.*/



    };

    @Test
    @DisplayName("싱글톤 빈과 프로토타입 빈을 함께 사용시 문제점")
    void singletonClientUsePrototype(){
        ApplicationContext cl = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        ClientBean clBean = cl.getBean(ClientBean.class);
        int logic1 = clBean.logic();
        Assertions.assertThat(logic1).isEqualTo(1);

        ClientBean clBean2 = cl.getBean(ClientBean.class);
        int logic2 = clBean2.logic();
        Assertions.assertThat(logic2).isEqualTo(2);
    };

    @Scope("singleton")
    static class ClientBean{
        /*이런식으로도 쓸 수 있음. 얘는 메서드 호출 시점에 컨테이너에서 찾아줌.*/
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        /*FIXME 이런식으로 프로토타입 쓰면 될거임.*/
//        @Autowired
//        ApplicationContext applicationContext;
        private final PrototypeBean prototypeBean;

        @Autowired //안하고 걍 리콰이어아규먼트해도되는데 지금은 눈에 익혀두기.
        public ClientBean(PrototypeBean prototypeBean){
            this.prototypeBean = prototypeBean;
        }
        /*FIXME 이런식으로 싱글톤 컨테이너 내부에 프로토타입빈을 주입받아 쓰면 객체로 등록되어서 계속 유지된다. 대신 이놈 자체는 컨테이너에서 관리하는게 아님.*/
        /*이렇게 쓸꺼면 프로토타입 왜씀? 싱클톤 쓰지 -> 프로토타입은 보통 이렇게 쓰고싶진않을꺼임.*/
        public int logic(){
            /*이런식으로 호출할때 찾아서 넣어줌.*/
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            /*FIXME 이런식으로 하면 로직 호출 시점에서만 프로토타입을 새로 받아 사용가능하게할수있음.*/
//            PrototypeBean prototypeBeanTest = applicationContext.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

        public int getCount(){
            return prototypeBean.getCount();
        }
    }


    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        };

        public int getCount(){
            return count;
        };

        @PostConstruct // 이거는 프로토타입 빈이 생성되고 의존성 주입이 끝난 뒤에 실행되는 콜백이다.
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        };

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        };
    }



}
