package com.fintechiq.webzaim;

import com.fintechiq.webzaim.service.StopFactorCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class StopFactorCalculatorTest {

    @Autowired
    private StopFactorCalculator calculator;

    @Test
    void testCalculatorStopFactor_SimilarNames() {
        String regPerson = "Ogada Isaak Abraham Samuel";
        String verifiedName = "OGADA Abraham ISAAK Samuel";
        boolean result = calculator.calculatorStopFactor(regPerson, verifiedName);
        assertTrue(result);
    }

    @Test
    void testCalculatorStopFactor_DifferentNames() {
        String regPerson = "Ogada Isaak Abraham Samuel";
        String verifiedName = "Solomon Awich";
        assertFalse(calculator.calculatorStopFactor(regPerson, verifiedName));
    }

    @Test
    void testGeneratWordPairs() {
        String input = "Ogada Isaac";
        List<String> pairs = List.of("OgadaIsaac");
        assertEquals(pairs, calculator.generateWordPairs(input));
    }

    @Test
    void testGeneratWordPairs_EmptyInput() {
        String input = "";
        List<String> pairs = List.of();
        assertEquals(pairs, calculator.generateWordPairs(input));
    }
}