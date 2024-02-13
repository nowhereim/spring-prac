package com.example.Core.beanfind;

import com.example.Core.discount.DiscountPolicy;
import com.example.Core.discount.FixDiscountPolicy;
import com.example.Core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate(){
        //이건 그냥 내가 타입 전체 조회 해본거.
        //Map<String,DiscountPolicy> test = ac.getBeansOfType(DiscountPolicy.class);
        //System.out.println("test = " + test);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByParentTypeDuplicateBeanName(){
       DiscountPolicy rateDiscountPolicy =  ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
       assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입을 조회하기")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    };

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2); //조회한게 2개랑 같으면 통과

        for(String key : beansOfType.keySet()){ //keySet()은 Map에 있는 key들을 Set으로 반환한다.
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    };

    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}

//실제 테스트 할때는 출력물을 넣지 않아야함. 시스템상에서 판단하게 해야함.