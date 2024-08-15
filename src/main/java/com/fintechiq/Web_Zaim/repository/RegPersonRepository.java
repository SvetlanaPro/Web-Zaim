package com.fintechiq.Web_Zaim.repository;

import com.fintechiq.Web_Zaim.entity.RegPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegPersonRepository extends JpaRepository<RegPerson, Long> {
}
