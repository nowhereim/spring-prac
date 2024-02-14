package com.example.Core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited //이 어노테이션을 사용하면 자식 클래스에도 적용이 된다.
@Documented //이 어노테이션을 사용한 클래스의 문서에 이 어노테이션에 대한 설명이 표시된다.
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}

//이렇게 어노테이션을 만들어 퀄리파이어 이름을 지정해주고 외부에서 이 어노테이션을 사용하면
//컴파일 타임에 에러가 잡힌다. 이게 더 안전하다. 어노테이션 만들기! 오타도 다 잡을 수 있움.