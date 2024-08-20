package com.fintechiq.webzaim.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintechiq.webzaim.entity.RequestContent;
import com.fintechiq.webzaim.entity.Settings;
import com.fintechiq.webzaim.repository.RequestContentRepository;
import com.fintechiq.webzaim.repository.SettingsRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestContentService {

    private static final String SEPARATOR = "-------------------------------------------------------------";

    private final RequestContentRepository requestContentRepository;
    private final ObjectMapper objectMapper;
    private final SettingsRepository settingsRepository;

    public void saveRequestContent(String jsonContent) throws IOException {
        RequestContent requestContent = objectMapper.readValue(jsonContent, RequestContent.class);
        requestContentRepository.save(requestContent);
    }

    public RequestContent getRequestContent(Long id) {
        return requestContentRepository.findById(id).orElse(null);
    }

    public boolean calculatorStopFactor(String regPerson, String verifiedName) {
        log.info("Looking for settings with key: distanceRatioThreshold");

        Settings settings = settingsRepository.findByKey("distanceRatioThreshold").orElseThrow(() -> 
                new IllegalArgumentException("Settings for 'distanceRatioThreshold' not found or has null value"));
        log.info("Found settings: key={}, value={}", settings.getKey(), settings.getValue());

        if (regPerson == null || regPerson.trim().isEmpty()) {
            throw new IllegalArgumentException("regPerson is empty or null");
        }
        if (verifiedName == null || verifiedName.trim().isEmpty()) {
            throw new IllegalArgumentException("verifiedName is empty or null");
        }
        regPerson = regPerson.toLowerCase();
        verifiedName = verifiedName.toLowerCase();

        log.info(SEPARATOR);
        log.info("regPerson: {}", regPerson);
        log.info("verifiedName: {}", verifiedName);

        var regPersonPairs = generateWordPairs(regPerson);
        var verifiedNamePairs = generateWordPairs(verifiedName);

        log.info(SEPARATOR);
        log.info("Generated word pairs for regPerson: {}", regPersonPairs);
        log.info("Generated word pairs for verifiedName: {}", verifiedNamePairs);

        var levenshtein = new LevenshteinDistance();
        double maxSimilarity = 0.0;

        for (var regPair : regPersonPairs) {
            for (var verPair : verifiedNamePairs) {
                int distance = levenshtein.apply(regPair, verPair);
                int maxLength = Math.max(regPair.length(), verPair.length());
                double similarity = 1.0 - (double) distance / maxLength;

                if (similarity > maxSimilarity) {
                    maxSimilarity = similarity;
                }
            }
        }

        double distanceRatioThreshold = Double.parseDouble(settings.getValue());

        log.info(SEPARATOR);
        log.info("Max similarity: {}", maxSimilarity);
        log.info("Threshold: {}", distanceRatioThreshold);
        log.info("Result: {}", maxSimilarity >= distanceRatioThreshold);
        return maxSimilarity >= distanceRatioThreshold;
    }

    public List<String> generateWordPairs(String input) {
        log.info("Input to generateWordPairs: {}", input);
        var pairs = new ArrayList<String>();
        if (input.contains(" ")) {
            String[] words = input.split(" ");
            for (var i = 0; i < words.length; i++) {
                for (var j = i + 1; j < words.length; j++) {
                    pairs.add(words[i] + " " + words[j]);
                    pairs.add(words[j] + " " + words[i]);
                }
            }
        } else {
            for (var i = 0; i < input.length(); i++) {
                for (var j = i + 1; j < input.length(); j++) {
                    pairs.add(input.charAt(i) + input.substring(j, j + 1));
                    pairs.add(input.charAt(j) + input.substring(i, i + 1));
                }
            }
        }
        log.info("Generated pairs: {}", pairs);
        return pairs;
    }
}