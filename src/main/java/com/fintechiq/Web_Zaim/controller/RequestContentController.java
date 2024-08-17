package com.fintechiq.Web_Zaim.controller;

import com.fintechiq.Web_Zaim.service.RequestContentService;
import com.fintechiq.Web_Zaim.service.StopFactorCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/request-content")
@RequiredArgsConstructor
public class RequestContentController {

    private final RequestContentService requestContentService;
    private final StopFactorCalculator stopFactorCalculator;

    @PostMapping
    public void saveRequestContent(@RequestBody String jsonContent) throws IOException {
        requestContentService.saveRequestContent(jsonContent);
    }

    @GetMapping("/{id}")
    public String getRequestContent(@PathVariable Long id) {
        var requestContent = requestContentService.getRequestContent(id);
        return requestContent != null ? requestContent.getContent() : null;
    }
    @GetMapping("/calculator")
    public boolean calculatorStopFactor( String regPerson, String verifiedName){
        return stopFactorCalculator.calculatorStopFactor(regPerson, verifiedName);
    }
}