package repository;

import domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    void delete(Long memberId);
    Optional<Member> findByUserId(String userId);
}
