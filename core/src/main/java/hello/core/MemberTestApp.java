package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberTestApp {
    public static void main(String[] args) {
        
        // appConfig 싱글톤 적용
        AppConfig appConfig = AppConfig.getInstance();

        MemberService memberService = appConfig.memberService();

        Member testMember1 = new Member(1000000L, "상우", Grade.NORMAL);
        Member testMember2 = new Member(1000001L, "아이묭", Grade.NORMAL);
        Member testMember3 = new Member(1000002L, "아이미", Grade.VIP);

        memberService.join(testMember1);
        memberService.join(testMember2);
        memberService.join(testMember3);

        Member fMember = memberService.findMember(1000002L);

        System.out.println("findMember Name : " + testMember3.getName());
        System.out.println("findMember find Name : " + fMember.getName());
    }
}
