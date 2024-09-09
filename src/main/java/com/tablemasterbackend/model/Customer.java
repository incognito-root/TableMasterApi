package com.tablemasterbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
public class Customer {
    private @Id
    long customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhoneNumber;
    private String customerAddress;
    private String customerEmail;
    private String customerPassword;
    private String customerNotes;
    private boolean customerMembership;
    private boolean customerIsVerified;
    private LocalDateTime customerAddedDate;
    private String customerGender;
    private LocalDate customerDateOfBirth;
    private Long fkMenuItemMostOrdered;

    public Customer(String customerFirstName, String customerLastName, String customerPhoneNumber, String customerAddress, String customerEmail, String customerPassword, String customerNotes, boolean customerMembership, boolean customerIsVerified, LocalDateTime customerAddedDate, String customerGender, LocalDate customerDateOfBirth, Long fkMenuItemMostOrdered) {
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.customerNotes = customerNotes;
        this.customerMembership = customerMembership;
        this.customerIsVerified = customerIsVerified;
        this.customerAddedDate = customerAddedDate;
        this.customerGender = customerGender;
        this.customerDateOfBirth = customerDateOfBirth;
        this.fkMenuItemMostOrdered = fkMenuItemMostOrdered;
    }

    public Customer() {
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerNotes() {
        return customerNotes;
    }

    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

    public boolean isCustomerMembership() {
        return customerMembership;
    }

    public void setCustomerMembership(boolean customerMembership) {
        this.customerMembership = customerMembership;
    }

    public boolean isCustomerIsVerified() {
        return customerIsVerified;
    }

    public void setCustomerIsVerified(boolean customerIsVerified) {
        this.customerIsVerified = customerIsVerified;
    }

    public LocalDateTime getCustomerAddedDate() {
        return customerAddedDate;
    }

    public void setCustomerAddedDate(LocalDateTime customerAddedDate) {
        this.customerAddedDate = customerAddedDate;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public LocalDate getCustomerDateOfBirth() {
        return customerDateOfBirth;
    }

    public void setCustomerDateOfBirth(LocalDate customerDateOfBirth) {
        this.customerDateOfBirth = customerDateOfBirth;
    }

    public Long getFkMenuItemMostOrdered() {
        return fkMenuItemMostOrdered;
    }

    public void setFkMenuItemMostOrdered(Long fkMenuItemMostOrdered) {
        this.fkMenuItemMostOrdered = fkMenuItemMostOrdered;
    }
}
