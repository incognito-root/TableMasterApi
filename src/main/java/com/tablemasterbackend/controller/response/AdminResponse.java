package com.tablemasterbackend.controller.response;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class AdminResponse {
    private long adminId;
    private String adminFirstName;
    private String adminLastName;
    private String adminContactNumber;
    private String adminRole;
    private String adminEmail;
    private String adminUsername;

    public AdminResponse(long adminId, String adminFirstName, String adminLastName, String adminContactNumber, String adminRole, String adminEmail, String adminUsername) {
        this.adminId = adminId;
        this.adminFirstName = adminFirstName;
        this.adminLastName = adminLastName;
        this.adminContactNumber = adminContactNumber;
        this.adminRole = adminRole;
        this.adminEmail = adminEmail;
        this.adminUsername = adminUsername;
    }

    public AdminResponse() {

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

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }
}
