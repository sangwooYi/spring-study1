package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    // 따라서 이렇게 인스턴스명까지만 선언 후, {의존성을 주입}하는 방식으로 가야 아래와 같이 클라이언트를 수정하는 문제가 사라짐
    // 의존성 주입 방식 : 생성자 주입 / setter 주입 / 필드 주입  ( 왠만하면 생성자 주입으로! )
    // 이게 가능한것이 다형성 덕분에 ( 모든 자식클래스는 부모클래스로 변환이 가능하다는 것 )
    private final MemberRepository memberRepository; // 멤버정보
    private DiscountPolicy discountPolicy;


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    /*
    // 현재 상태. Client에 해당하는 이 OrderServiceImpl 을 건드려야한다. ( 이러면 DIP/OCP 원칙에 위배됨.. )
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();  // 할인정책
    */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
