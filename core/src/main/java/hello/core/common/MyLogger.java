package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

// proxyMode 관련 설명
// 적용 대상이 인터페이스가 아닌 클래스면   TARGET_CLASS
// 적용 대상이 인터페이스면  INTERFACES
// proxyMode 쓰면 굳이 Provider 쓸 필요가 없다.
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)   // request 스코프 사용 (HTTP request 후 응답 반환할때까지만 관리하는 스코프)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.printf("[ %s ] [ %s ] [ %s ] \n", this.uuid, this.requestURL, message);
    }

    @PostConstruct
    public void init() {
        this.uuid = UUID.randomUUID().toString();
        System.out.printf("[ %s ] request scope bean create! [ %s ]\n", this.uuid, this);
    }

    // request 스코프는 @PreDestroy 도 자동으로 호출해준다. ( http 응답 후 이거 호출하고 빈 해제해줌 )
    @PreDestroy
    public void destroy() {
        System.out.printf("[ %s ] request scope bean close!! [ %s ]\n", this.uuid, this);
    }

}
