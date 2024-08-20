package com.fintechiq.webzaim.repository;

import com.fintechiq.webzaim.entity.VerifiedName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifiedNameRepository extends JpaRepository<VerifiedName, Long> {
}
