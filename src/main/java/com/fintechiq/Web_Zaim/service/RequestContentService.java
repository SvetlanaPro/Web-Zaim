package com.fintechiq.Web_Zaim.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintechiq.Web_Zaim.entity.RequestContent;
import com.fintechiq.Web_Zaim.repository.RequestContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class RequestContentService {
    @Autowired
    private RequestContentRepository requestContentRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public void saveRequestContent(String jsonContent) throws IOException {
        RequestContent requestContent = objectMapper.readValue(jsonContent, RequestContent.class);
        requestContent.setLoanRequestId(UUID.randomUUID());
        requestContentRepository.save(requestContent);
    }
    public RequestContent getRequestContent(Long id){
        return requestContentRepository.findById(id).orElse(null);
    }
    }



