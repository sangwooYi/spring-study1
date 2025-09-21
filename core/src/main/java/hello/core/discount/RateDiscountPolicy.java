package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements  DiscountPolicy{

    private int discountRate = 10;

    @Override
    public int discount(Member member, int price) {

        int resPrice = price;

        if (member.getGrade() == Grade.VIP) {
            resPrice = price * discountRate / 100;
        }
        return resPrice;
    }
}
