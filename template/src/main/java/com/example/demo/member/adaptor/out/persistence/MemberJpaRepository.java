package com.example.demo.member.adaptor.out.persistence;

import com.example.demo.member.adaptor.out.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

}
