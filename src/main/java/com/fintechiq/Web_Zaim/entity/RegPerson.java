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
@Table(name = "reg_person")
@Data
public class RegPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "loan_request_id", nullable = false)
    private UUID loanRequestId;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = " middle_name")
    private String middlename;
    @Column(name = "last_name", nullable = false)
    private String lastname;
}
