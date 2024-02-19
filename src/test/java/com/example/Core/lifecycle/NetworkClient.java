package com.example.Core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient  {
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);

        /*여기선 다 널이 나온다.
        connect();

        call("초기화 연결 메시지");*/
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void connect(){
        System.out.println("connect: " + url);
    }

    public void call (String message){
        System.out.println("call: " + url + " message= " + message);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close: " + url);
    }

    @PostConstruct // 다 필요없고 이거랑 프리디스트로이 쓰면 의존성주입 이후에 실행 콜백이 여기서 실행됨.
    public void init(){
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy // 이건 종료시점 콜백
    public void close(){
        disconnect();
    }


}
