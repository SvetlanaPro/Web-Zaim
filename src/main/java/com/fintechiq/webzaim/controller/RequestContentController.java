package com.fintechiq.webzaim.controller;

import com.fintechiq.webzaim.entity.RequestContent;
import com.fintechiq.webzaim.service.RequestContentService;

import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/request-content")
@RequiredArgsConstructor
public class RequestContentController {

    private final RequestContentService requestContentService;

    @PostMapping
    public void saveRequestContent(@RequestBody String jsonContent) throws IOException {
        requestContentService.saveRequestContent(jsonContent);
    }

    @GetMapping("/{id}")
    public String getRequestContent(@PathVariable Long id) {
        RequestContent requestContent = requestContentService.getRequestContent(id);
        return requestContent != null ? requestContent.getContent() : null;
    }

    @GetMapping("/calculator")
    public boolean calculatorStopFactor(@RequestParam String regPerson, @RequestParam String verifiedName) {
        return requestContentService.calculatorStopFactor(regPerson, verifiedName);
    }
    @GetMapping("/generate-pairs")
    public List<String> generateWordPairs(@RequestParam String input) {
        return requestContentService.generateWordPairs(input);
    }
}