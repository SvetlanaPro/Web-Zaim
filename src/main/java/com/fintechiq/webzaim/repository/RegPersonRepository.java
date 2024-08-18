package com.fintechiq.webzaim.repository;

import com.fintechiq.webzaim.entity.RegPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegPersonRepository extends JpaRepository<RegPerson, Long> {
}
