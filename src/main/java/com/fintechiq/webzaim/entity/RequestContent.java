package com.fintechiq.webzaim.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;

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