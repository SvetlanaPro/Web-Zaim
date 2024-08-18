package com.fintechiq.webzaim.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintechiq.webzaim.entity.RequestContent;
import com.fintechiq.webzaim.repository.RequestContentRepository;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestContentService {

    private final RequestContentRepository requestContentRepository;
    private final ObjectMapper objectMapper;

    public void saveRequestContent(String jsonContent) throws IOException {
        RequestContent requestContent = objectMapper.readValue(jsonContent, RequestContent.class);
        requestContent.setLoanRequestId(UUID.randomUUID());
        requestContentRepository.save(requestContent);
    }

    public RequestContent getRequestContent(Long id) {
        return requestContentRepository.findById(id).orElse(null);
    }
}