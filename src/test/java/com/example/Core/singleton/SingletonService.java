package com.example.Core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    //static 영역에 객체를 딱 1개만 생성해둔다.
    //외부에서 인스턴스를 쓰려면 이 메서드를 통해서만 가능하다.
    public static SingletonService getInstance(){
        return instance;
    }

    //밖에서 생성하지 못하게 막음
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}

//싱글톤은 너무 코드도 길고 유연성이 떨어진다. 인스턴스 한개를 공유하는건 좋지만.. 매번 이렇게 직접 만들기에는..
//테스트도 힘들고 클라이언트가 구체 클래스에 의존하게 되어버린다. (.getInstance() 이런식으로 불러야해서)