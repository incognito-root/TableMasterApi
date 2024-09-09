package com.tablemasterbackend.dto;

import com.tablemasterbackend.util.authentication.Password;
import com.tablemasterbackend.util.baseclass.UserBaseDTO;

public class AdminModelDTO extends UserBaseDTO {
    private String adminUsername;
    private String adminPassword;
    private String adminEmail;
    private String adminRole;

    public AdminModelDTO(String firstName, String lastName, String contactNumber, String adminUsername, String adminEmail, String adminRole, String adminPassword) {
        super(firstName, lastName, contactNumber);
        this.adminUsername = adminUsername;
        this.adminEmail = adminEmail;
        this.adminRole = adminRole;
        this.adminPassword = new Password().encode(adminPassword);
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }
}
