package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws IOException {
        //given
        Member member = new Member();
        member.setName("kim");
        //when
        long memberId = memberService.join(member);
        //then
        Assertions.assertEquals(member, memberRepository.findMember(memberId));
        
    }
    
    @Test
    public void 회원_중복_예외() throws IOException{
        //given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //then
        Assertions.assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());

    }

}