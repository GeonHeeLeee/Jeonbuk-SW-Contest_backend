package Jeonbuk.contest.repository;

import Jeonbuk.contest.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    public Optional<Member> findByMemberId(String memberId);
}
