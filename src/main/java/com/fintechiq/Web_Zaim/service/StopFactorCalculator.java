package com.fintechiq.Web_Zaim.service;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StopFactorCalculator {

    private static final double DISTANCE_RATIO_THRESHOLD = 0.9;

    public boolean calculatorStopFactor(String regPerson, String verifiedName) {

        var regPersonPairs = generateWordPairs(regPerson);
        var verifiedNamePairs = generateWordPairs(verifiedName);

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
        return maxSimilarity >= DISTANCE_RATIO_THRESHOLD;
    }

    public List<String> generateWordPairs(String input) {
        String[] words = input.split(" ");
        var pairs = new ArrayList<String>();
        for (var i = 0; i < words.length; i++) {
            for (var j = i + 1; j < words.length; j++) {
                pairs.add(words[i] + words[j]);
            }
        }
        return pairs;
    }
}