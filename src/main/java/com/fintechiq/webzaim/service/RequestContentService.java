package com.fintechiq.webzaim.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintechiq.webzaim.entity.RequestContent;
import com.fintechiq.webzaim.entity.Settings;
import com.fintechiq.webzaim.repository.RequestContentRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fintechiq.webzaim.repository.SettingsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestContentService {

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
        System.out.println("Looking for settings with key: distanceRatioThreshold");

        Settings settings = settingsRepository.findByKey("distanceRatioThreshold")
                .orElseThrow(() -> new IllegalArgumentException("Settings for 'distanceRatioThreshold' not found or has null value"));
        System.out.println("Found settings: key=" + settings.getKey() + ", value=" + settings.getValue());

        double distanceRatioThreshold = Double.parseDouble(settings.getValue());

        // Проверка строк regPerson и verifiedName
        if (regPerson == null || regPerson.trim().isEmpty()) {
            throw new IllegalArgumentException("regPerson is empty or null");
        }
        if (verifiedName == null || verifiedName.trim().isEmpty()) {
            throw new IllegalArgumentException("verifiedName is empty or null");
        }

        System.out.println("regPerson: " + regPerson);
        System.out.println("verifiedName: " + verifiedName);

        var regPersonPairs = generateWordPairs(regPerson);
        var verifiedNamePairs = generateWordPairs(verifiedName);

        System.out.println("Generated word pairs for regPerson: " + regPersonPairs);
        System.out.println("Generated word pairs for verifiedName: " + verifiedNamePairs);

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
        System.out.println("Max similarity: " + maxSimilarity);
        System.out.println("Threshold: " + distanceRatioThreshold);
        System.out.println("Result: " + (maxSimilarity >= distanceRatioThreshold));
      return maxSimilarity >= distanceRatioThreshold;
    }

    public List<String> generateWordPairs(String input) {
        System.out.println("Input to generateWordPairs: " + input);
        String[] words = input.split(" ");
        var pairs = new ArrayList<String>();
        for (var i = 0; i < words.length; i++) {
            for (var j = i + 1; j < words.length; j++) {
                pairs.add(words[i] + " " + words[j]);
            }
        }
        System.out.println("Generated pairs: " + pairs);
        return pairs;
    }
}
