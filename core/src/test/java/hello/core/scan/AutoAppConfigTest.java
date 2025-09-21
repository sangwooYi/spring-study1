package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 자동빈 vs 자동빈 Component 이름 겹치는 경우 .ConflictingBeanDefinitionException 발생
// 자동빈 vs 수동빈 => 수동빈이 우선적으로 오버라이딩 됨
public class AutoAppConfigTest {

    @Test
    void basicScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        // @SpringBootApplication 에 @CommponentScan이 걸려있다! 따라서 사실 그냥 별도로 Config 클래스 없어도 됨!
        //ApplicationContext ac = new AnnotationConfigApplicationContext(CoreApplication.class);
        String[] beanKeys = ac.getBeanDefinitionNames();
        for (String key : beanKeys) {
            System.out.println(key);    // 알아서 등록 되었다!

        }
        MemberService memberService = ac.getBean(MemberService.class);
        DiscountPolicy discountPolicy = ac.getBean(DiscountPolicy.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        // @Bean 과 @Component의 이름 충돌시키면 이렇게 @Bean 즉 수동빈이 우선권을 가진다.
        // 근데 @SpringBootApplication 붙어있는 파일에서 부트 실행하면 이런경우도 그냥 에러 발생시켜버림
        //Assertions.assertThat(discountPolicy).isInstanceOf(FixDiscountPolicy.class);
    }
}
