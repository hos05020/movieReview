package org.zerock.moviereview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.moviereview.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
