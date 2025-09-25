package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor    // 알아서 멤버변수에 맞춰 생성자 주입 세팅해주는 어노테이션
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
    // ProxyMode 쓰면 아래처럼 Provider 필요 없다./
    //private final ObjectProvider<MyLogger> myLoggerObjectProvider;


    /*
    @RequiredArgsConstructor 어노테이션은 이 아래 생성자주입 한거와 동일한 코드를 자동으로 작성해줌!
    @Autowired
    public LogDemoController(LogDemoService logDemoService, MyLogger myLogger) {
        this.logDemoService = logDemoService;
        this.myLogger = myLogger;
    }
    */

    // /log-demo 
    // @RequestMapping request url 경로 설정
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException{

        // Provider 사용
        //MyLogger myLogger = myLoggerObjectProvider.getObject();


        String requestURL = request.getRequestURL().toString();

        myLogger.setRequestURL(requestURL);

        System.out.println("myLogger = " + myLogger.getClass());

        // 해보면 request 요청 단위에 맞춰서 각 요청 단위로 빈의 주소가 유지된다.
        myLogger.log("controller test!");
        Thread.sleep(500);
        logDemoService.logic("testId");

        return "OK";
    }
}
