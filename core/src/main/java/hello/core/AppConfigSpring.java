package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigSpring {

    // new AnnotationConfigApplicationContext(AppConfigSpring.class) 가 호출 되는 순간
    // AppConfigSpring 에서 @Bean 어노테이션이 붙은 모든 메서드를 호출하여 등록 함
    // @Bean(name="memberServiceNew") 이런식으로 빈의 이름을 지정할수도 있다. 기본 디폴트는 메서드명과 동일
    // 그리고 내가 설정한 관계들을 분석하여 알아서 의존관계를 주입해준다
    @Bean
    public MemberService memberService() {
       return new MemberServiceImpl(memberRepository());
   }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
