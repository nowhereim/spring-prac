package com.example.Core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLom = new HelloLombok();
        helloLom.setAge(20);
        helloLom.setName("lombok");
        System.out.println(helloLom.getAge());
        System.out.println(helloLom.getName());


    }
}
