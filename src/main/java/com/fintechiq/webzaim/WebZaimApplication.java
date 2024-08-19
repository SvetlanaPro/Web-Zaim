package com.fintechiq.webzaim;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintechiq.webzaim.repository.RequestContentRepository;
import com.fintechiq.webzaim.repository.SettingsRepository;
import com.fintechiq.webzaim.service.RequestContentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication

public class WebZaimApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WebZaimApplication.class, args);

        RequestContentRepository requestContentRepository = context.getBean(RequestContentRepository.class);
        SettingsRepository settingsRepository = context.getBean(SettingsRepository.class);
        ObjectMapper objectMapper = new ObjectMapper();

        RequestContentService service = new RequestContentService(requestContentRepository, objectMapper, settingsRepository);
        service.calculatorStopFactor("exampleRegPerson", "exampleVerifiedName");
    }
}