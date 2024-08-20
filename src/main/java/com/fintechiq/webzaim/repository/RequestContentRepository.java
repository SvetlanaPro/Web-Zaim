package com.fintechiq.webzaim.repository;

import com.fintechiq.webzaim.entity.RequestContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestContentRepository extends JpaRepository<RequestContent, Long> {
}
