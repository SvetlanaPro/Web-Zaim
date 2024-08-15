package com.fintechiq.Web_Zaim.repository;

import com.fintechiq.Web_Zaim.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {
}
