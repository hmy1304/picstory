package picstroy.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import picstroy.backend.domain.Member;
import picstroy.backend.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public Long signup(String name, String email) {
        if(memberRepository.exitsByEmail(email)) {
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }

        Member member = new Member(name, email);

        return memberRepository.save(member).getId();
    }
}
