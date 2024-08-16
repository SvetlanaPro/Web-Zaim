package com.fintechiq.Web_Zaim.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "verified_name")
@Data
public class VerifiedName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "credit_bureau_id", nullable = false)
    private CreditBureau creditBureau;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "other_name", nullable = false)
    private String otherName;
    @Column(name = "surname", nullable = false)
    private String surname;
}