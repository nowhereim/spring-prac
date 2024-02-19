package com.example.Core.web;

import com.example.Core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Provider;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
//    /*FIXME 이러면 에러 터짐. 이유는 생성시점에 이 마이로거를 주입받아야하는데 마이로거 스코프는 리퀘스트임. 리퀘스트가 없으니 에러터짐*/
//    private final MyLogger myLogger;
//    private Provider<MyLogger> myLoggerProvider;
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){//고객 요청정보를 받을 수 있음
        System.out.println("myLogger = " + myLogger.getClass());
//    MyLogger myLogger = this.myLoggerProvider.getObject();
    String requestURL = request.getRequestURL().toString();
    myLogger.setRequestURL(requestURL);

    myLogger.log("controller test");
    logDemoService.logic("testId");
    return "OK";
    }
}
