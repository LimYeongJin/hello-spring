package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 스프링 데이터 JPA가 JpaRepository를 상속하고 있으면 구현체를 자동으로 생성
 * 따라서 스프링 데이터 JPA Spring Bean을 자동으로 등록
 * SpringConfig 참조
 * JpaRepository Ctrl + 클릭하여 어떤 JPQL 있는지 확인
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
