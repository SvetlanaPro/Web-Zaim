package com.fintechiq.Web_Zaim.repository;

import com.fintechiq.Web_Zaim.entity.VerifiedName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifiedNameRepository extends JpaRepository<VerifiedName, Long> {
}
