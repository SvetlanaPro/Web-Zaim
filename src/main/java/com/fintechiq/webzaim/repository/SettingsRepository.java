package com.fintechiq.webzaim.repository;

import com.fintechiq.webzaim.entity.Settings;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {

    Optional<Settings> findByKey(String key);
}