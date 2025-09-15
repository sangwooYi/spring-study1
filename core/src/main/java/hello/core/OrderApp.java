package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {

        // 이제 생성자 주입 및 연결을 AppConfig 에 넘기면서 아래처럼 관심사 분리가 가능해짐
        AppConfig appConfig = AppConfig.getInstance();

        // 싱글톤으로 변경
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "상우", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(1L, "노트북", 1000000);
        
        // toString() 오버라이딩 덕에 가능한 부분 
        // toString 오버라이딩 안해주면 그냥 주소값 출력 됨
        System.out.println("이번에 order = " + order);
    }
}
