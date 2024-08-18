package com.fintechiq.Web_Zaim.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "request_content")
@Data
public class RequestContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "loan_request_id", unique = true, nullable = false)
    private UUID loanRequestId;
    @Column(columnDefinition = "text", nullable = false)
    private String content;
}