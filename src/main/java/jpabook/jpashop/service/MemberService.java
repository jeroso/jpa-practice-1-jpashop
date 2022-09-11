package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor    // final 필드의 생성자를 만들어 줌
public class MemberService {
    
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * readOnly = false : default
     */
    //
    @Transactional
    public long join(Member member) {
        validateDuplicateMember(member);    //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 한건만 조회
    public Member findOne(Long id) {
        return memberRepository.findMember(id);
    }

    public void update(Long id, String name) {
        Member member = memberRepository.findMember(id);
        member.setName(name);
    }
}
