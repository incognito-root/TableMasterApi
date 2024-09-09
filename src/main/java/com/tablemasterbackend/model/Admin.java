package com.tablemasterbackend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin")
public class Admin {
    private @Id
    long adminId;
    private String adminFirstName;
    private String adminLastName;
    private String adminContactNumber;
    private LocalDateTime adminAddedDate;
    private String adminPassword;
    private String adminRole;
    private String adminEmail;
    private String adminUsername;

    public Admin() {
    }

    public Admin(String adminFirstName, String adminLastName, String adminContactNumber, String adminPassword, String adminRole, String adminEmail, String adminUsername) {
        this.adminFirstName = adminFirstName;
        this.adminLastName = adminLastName;
        this.adminContactNumber = adminContactNumber;
        this.adminPassword = adminPassword;
        this.adminRole = adminRole;
        this.adminEmail = adminEmail;
        this.adminUsername = adminUsername;
        this.setAdminAddedDate();
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long id) {
        this.adminId = id;
    }

    public String getAdminFirstName() {
        return adminFirstName;
    }

    public void setAdminFirstName(String adminFirstName) {
        this.adminFirstName = adminFirstName;
    }

    public String getAdminLastName() {
        return adminLastName;
    }

    public void setAdminLastName(String adminLastName) {
        this.adminLastName = adminLastName;
    }

    public String getAdminContactNumber() {
        return adminContactNumber;
    }

    public void setAdminContactNumber(String adminContactNumber) {
        this.adminContactNumber = adminContactNumber;
    }

    public LocalDateTime getAdminAddedDate() {
        return adminAddedDate;
    }

    public void setAdminAddedDate() {
        this.adminAddedDate = LocalDateTime.now();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }
}

