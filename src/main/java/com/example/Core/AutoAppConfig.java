package com.example.Core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "com.example.Core", // 이 패키지를 기준으로 ComponentScan을 시작한다.
//        basePackageClasses = AutoAppConfig.class, // 이 클래스 패키지를 기준으로 ComponentScan을 시작한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // Configuration 어노테이션은 제외한다. AppConfig를 제외하기 위한 필터링 예시
        //AppConfig를 제외하는 이유는 AppConfig에 @Configuration이 붙어있기 때문에 AppConfig도 빈으로 등록되어버린다. 그럼 에러 터질거임.
) //@Component가 붙은 클래스를 찾아서 빈으로 등록한다.
//디폴트는 이  설정 정보 클래스의 패키지가 시작 위치가 된다. 이 설정 정보 클래스가 com.example.Core 패키지에 있기 때문에 com.example.Core부터 시작해서 @Component가 붙은 클래스를 찾는다.
//권장 방법은 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다. 최근 스프링 부트도 이 방법을 기본으로 제공한다.
public class AutoAppConfig {

}

//컴포넌트 스캔을 쓰면 @Configuration이 붙은 클래스도 빈으로 자동으로 등록되어버린다.
//Configuration어노테이션 타고 들어가보면 컴포넌트 어노테이션이 붙어있음.




//@Component어노테이션 및 streotype(@Service, @Repository, @Controller.)어노테이션이 부여된 Class들을 자동으로 Scan하여 Bean으로 등록해주는 역할을 하는 어노테이션입니다.