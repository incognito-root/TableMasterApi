package com.tablemasterbackend.controller.response;

import java.time.LocalDate;

public class CustomerResponse {

    long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String notes;
    private String gender;
    private LocalDate dateOfBirth;
    private Long fkMenuItemMostOrdered;
    private boolean membership;
    private boolean isVerified;

    public CustomerResponse(long customerId, String firstName, String lastName, String email, String phoneNumber, String address, String notes, String gender, LocalDate dateOfBirth, Long fkMenuItemMostOrdered, boolean membership, boolean isVerified) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.notes = notes;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.fkMenuItemMostOrdered = fkMenuItemMostOrdered;
        this.membership = membership;
        this.isVerified = isVerified;
    }

    public CustomerResponse() {
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getFkMenuItemMostOrdered() {
        return fkMenuItemMostOrdered;
    }

    public void setFkMenuItemMostOrdered(Long fkMenuItemMostOrdered) {
        this.fkMenuItemMostOrdered = fkMenuItemMostOrdered;
    }

    public boolean getIsMembership() {
        return membership;
    }

    public void setIsMembership(boolean membership) {
        this.membership = membership;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean verified) {
        isVerified = verified;
    }
}
