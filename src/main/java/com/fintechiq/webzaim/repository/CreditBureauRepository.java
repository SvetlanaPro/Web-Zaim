package com.fintechiq.webzaim.repository;

import com.fintechiq.webzaim.entity.CreditBureau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditBureauRepository extends JpaRepository<CreditBureau, Long> {
}
