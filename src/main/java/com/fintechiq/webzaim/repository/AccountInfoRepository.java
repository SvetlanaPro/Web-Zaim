package com.fintechiq.webzaim.repository;

import com.fintechiq.webzaim.entity.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long> {
}
