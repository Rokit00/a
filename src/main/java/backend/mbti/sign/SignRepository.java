package backend.mbti.sign;

import backend.mbti.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignRepository extends JpaRepository<Member, Long> {
    Optional<Member> findAllByEmail(String username);
    Optional<Member> findByUsername(String username);
}
