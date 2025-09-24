package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithProto {

    @Test
    void prototypeFind() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        /*
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        prototypeBean1.addCount();
        prototypeBean2.addCount();

        Assertions.assertThat(prototypeBean1.getCount()).isSameAs(1);
        Assertions.assertThat(prototypeBean2.getCount()).isSameAs(1);
        */

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);

        System.out.println(clientBean1.logic());
        System.out.println(clientBean2.logic());
    }

    static class ClientBean {

        private final PrototypeBean prototypeBean;

        // Prototype은 프로토타입 스코프 빈이나,
        // 이렇게 싱글톤 빈에 의존성 주입이 되면 싱글톤 컨테이너에 포함되어 버린다.. 그래서 싱글톤 관리되어버린다..
        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            System.out.println("CLient Bean 생성자 호출");
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }


    @Scope("prototype")
    static class PrototypeBean {
        private int count;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PostConstruct = " + this);
        }

        // 프로토 타입 빈이니까 어차피 자동 호출 안 됨
        @PreDestroy
        public void destroy() {
            System.out.println("Destroy");
        }

    }
}
