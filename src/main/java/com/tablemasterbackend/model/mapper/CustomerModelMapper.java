package com.tablemasterbackend.model.mapper;

import com.tablemasterbackend.dto.CustomerModelDTO;
import com.tablemasterbackend.model.Customer;

public class CustomerModelMapper {
    public Customer mapToModel(CustomerModelDTO dto) {
        return new Customer(dto.getFirstName(), dto.getLastName(), dto.getPhoneNumber(), dto.getAddress(), dto.getEmail(), dto.getPassword(), dto.getNotes(), dto.isMemberShip(), dto.isVerified(), dto.getAddedDate(), dto.getGender(), dto.getDateOfBirth(), dto.getFkMenuItemMostOrdered());
    }
}
