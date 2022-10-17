package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
/**
스프링이 실행될 때 스프링 컨테이너가 @Repository를 찾아 MemoryMemberRepository 등록(컴포넌트 스캔)
@Repository (자바 코드로 스프링 빈 등록하는 방식 사용하여 주석 처리)
 */
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    /**
        Optional.ofNullable()은 store.get(id)가 NULL이어도 감쌀 수 있음
     */
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    /**
        store 변수 비워준다. test 부분 보고 오삼.
     */
    public void clearStore() {
        store.clear();
    }
}
