package com.example.Core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
//@Scope(value = "request")
/*타겟이 클래스면 CLASS 인터페이스면 INTERFACE 하면 된다.*/
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS) //이렇게하면 프로바이더 안써도 생성당시 에러 안터짐. 왜냐면?
/*이렇게하면 CGLIB 라는 바이트코드조작 라이브러리를 사용해서 MyLogger를 상속받은 가짜 프록시 객체를 생성함.
즉 진짜 대신 가짜를 만들어 프록시 객체를 등록해두었다가 실제 요청이 오면 그때 내부에서 진짜 빈을 요청하는 방식이다.
만약 myLogger.logic()을 호출하면 가짜 프록시 객체는 request 스코프의 진짜 myLogger.logic()를 호출한다.

즉 이놈은 그냥 대리인? 가짜? 라고 생각하면 된다.

프로바이더든 프록시든 핵심적인 존재 이유는 객체를 꼭 필요한 시점까지 지연처리하고 싶을때 사용한다.
이게 바로 다형성과 DI컨테이너가 가진 큰 강점이다.
*/

public class MyLogger {
    private String uuid;
    private String requestURL;
    public void setRequestURL(String requestURL){
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "["+ requestURL +"]" + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    };
}
