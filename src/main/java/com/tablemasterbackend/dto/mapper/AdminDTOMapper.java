package com.tablemasterbackend.dto.mapper;

import com.tablemasterbackend.controller.request.AdminRequest;
import com.tablemasterbackend.dto.AdminModelDTO;

public class AdminDTOMapper {
    public AdminModelDTO mapToDTO(AdminRequest request) {
        return new AdminModelDTO(request.getFirstName(), request.getLastName(), request.getContactNumber(), request.getAdminUsername(), request.getAdminEmail(), request.getAdminRole(), request.getAdminPassword());
    }
}
