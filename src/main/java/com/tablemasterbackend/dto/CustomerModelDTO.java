package com.tablemasterbackend.dto;

import com.tablemasterbackend.util.authentication.Password;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerModelDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;
    private String password;
    private String notes;
    private boolean memberShip;
    private boolean isVerified;
    private LocalDateTime addedDate;
    private String gender;
    private LocalDate dateOfBirth;
    private Long fkMenuItemMostOrdered;

    public CustomerModelDTO(String firstName, String lastName, String phoneNumber, String address, String email, String password, String notes, String dateOfBirth, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = new Password().encode(password);
        this.notes = notes;
        this.gender = gender;

        setDateOfBirth(dateOfBirth);
        this.memberShip = false;
        this.isVerified = false;
        this.addedDate = LocalDateTime.now();
        fkMenuItemMostOrdered = null;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isMemberShip() {
        return memberShip;
    }

    public void setMemberShip(boolean memberShip) {
        this.memberShip = memberShip;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
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

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }

    public Long getFkMenuItemMostOrdered() {
        return fkMenuItemMostOrdered;
    }

    public void setFkMenuItemMostOrdered(Long fkMenuItemMostOrdered) {
        this.fkMenuItemMostOrdered = fkMenuItemMostOrdered;
    }
}
