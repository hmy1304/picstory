package picstroy.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import picstroy.backend.domain.Member;
import picstroy.backend.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long signup(
            String name,
            String email,
            String password,
            String passwordConfirm,
            String phone) {
        if(memberRepository.exitsByEmail(email)) {
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }

        if(password==null || password.length()<6) {
            throw new RuntimeException("비밀번호는 최소 6글자 이사이어야 합니다.");
        }

        if(!password.equals(passwordConfirm)) {
            throw new RuntimeException("비밀번호 확인이 일치하지 않습니다.");
        }

        String hash = passwordEncoder.encode(password);

        Member member = new Member(name, email, hash, phone);

        return memberRepository.save(member).getId();
    }

    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
