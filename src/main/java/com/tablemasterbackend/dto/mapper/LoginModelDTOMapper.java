package com.tablemasterbackend.dto.mapper;

import com.tablemasterbackend.controller.request.LoginRequest;
import com.tablemasterbackend.dto.LoginModelDTO;

public class LoginModelDTOMapper {
    public LoginModelDTO mapToDTO(LoginRequest request) {
        return new LoginModelDTO(request.getUsernameOrEmail(), request.getPassword());
    }
}
