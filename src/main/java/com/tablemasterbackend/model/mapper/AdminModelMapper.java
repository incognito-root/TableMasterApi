package com.tablemasterbackend.model.mapper;

import com.tablemasterbackend.dto.AdminModelDTO;
import com.tablemasterbackend.model.Admin;

public class AdminModelMapper {
    public Admin mapToModel(AdminModelDTO dto) {
        return new Admin(dto.getFirstName(), dto.getLastName(), dto.getContactNumber(), dto.getAdminPassword(), dto.getAdminRole(), dto.getAdminEmail(), dto.getAdminUsername());
    }
}
