package picstroy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import picstroy.backend.domain.Member;

public interface MemberRepository  extends JpaRepository<Member,Long> {

    boolean existsByEmail(String email);
}

