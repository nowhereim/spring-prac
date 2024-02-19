package com.example.Core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        client.setUrl("http://hello-spring.dev");
        ac.close();

    }

    @Configuration
    static class LifeCycleConfig{
       /* @Bean(initMethod = "init", destroyMethod = "close" )
        이렇게 하면 스프링컨테이너 생성 -> 빈 생성 -> 의존관계 주입 -> 초기화 콜백(지정한 인잇 메서드) -> 사용 -> 소멸전 콜백(지정한 클로즈 메서드) -> 스프링컨테이너 종료
        이 순서대로 진행된다.*/

        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

    }

}
