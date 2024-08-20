package com.fintechiq.webzaim;

import com.fintechiq.webzaim.entity.Settings;
import com.fintechiq.webzaim.repository.SettingsRepository;
import com.fintechiq.webzaim.service.RequestContentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class StopFactorCalculatorTest {

    @MockBean
    private SettingsRepository settingsRepository;

    @Autowired
    private RequestContentService requestContentService;

    @Test
    void testCalculatorStopFactor_SimilarNames() {
        Settings settings = new Settings();
        settings.setKey("distanceRatioThreshold");
        settings.setValue("0.9");
        when(settingsRepository.findByKey("distanceRatioThreshold")).thenReturn(Optional.of(settings));

        String regPerson = "Ogada Isaak";
        String verifiedName = "ISAAK OADA";
        boolean result = requestContentService.calculatorStopFactor(regPerson, verifiedName);
        assertTrue(result);
    }

    @Test
    void testCalculatorStopFactor_DifferentNames() {
        Settings settings = new Settings();
        settings.setKey("distanceRatioThreshold");
        settings.setValue("0.9");
        when(settingsRepository.findByKey("distanceRatioThreshold")).thenReturn(Optional.of(settings));

        String regPerson = "Ogada Isaak Abraham Samuel";
        String verifiedName = "Solomon Awich";
        boolean result = requestContentService.calculatorStopFactor(regPerson, verifiedName);
        assertFalse(result);
    }

    @Test
    void testGenerateWordPairs() {
        String input = "Solomon Awich";
        List<String> expectedPairs = List.of("Solomon Awich", "Awich Solomon");
        List<String> actualPairs = requestContentService.generateWordPairs(input);
        assertEquals(expectedPairs.size(), actualPairs.size());
        assertTrue(actualPairs.containsAll(expectedPairs));
    }

    @Test
    void testGenerateWordPairs_EmptyInput() {
        String input = "";
        List<String> expectedPairs = List.of();
        List<String> actualPairs = requestContentService.generateWordPairs(input);
        assertEquals(expectedPairs, actualPairs);
    }
}