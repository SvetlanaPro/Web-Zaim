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

import java.time.LocalDate;

@Entity
@Table(name = "account_info")
@Data
public class AccountInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "credit_bureau_id", nullable = false)
    private CreditBureau creditBureau;
    @Column(name = " account_number",nullable = false)
    private String accountNumber;
    @Column(name = "account_status", nullable = false)
    private String accountStatus;
    @Column(name = "current_balance", nullable = false)
    private double currentBalance;
    @Column(name = "date_opened", nullable = false)
    private LocalDate dateOpened;
    @Column(name = "days_in_arrears", nullable = false)
    private int daysInArrears;
    @Column(name = "delinquency_code", nullable = false)
    private String delinquencyCode;
    @Column(name = "highest_days_in_arrears", nullable = false)
    private int highestDaysInArrears;
    @Column(name = "is_your_account", nullable = false)
    private boolean isYourAccount;
    @Column(name = "last_payment_amount", nullable = false)
    private double lastPaymentAmount;
    @Column (name = "last_payment_date")
    private LocalDate lastPaymentDate;
    @Column(name = "loaded_at", nullable = false)
    private LocalDate loadedAt;
    @Column(name = "original_amount", nullable = false)
    private double originalAmount;
    @Column(name = "overdue_balance", nullable = false)
    private double overdueBalance;
    @Column(name = "overdue_date")
    private LocalDate overdueDate;
    @Column(name = " product_type_id", nullable = false)
    private int productTypeId;
}
