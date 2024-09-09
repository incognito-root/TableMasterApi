package com.tablemasterbackend.dto.mapper;

import com.tablemasterbackend.controller.request.AddCustomerRequest;
import com.tablemasterbackend.dto.CustomerModelDTO;
import com.tablemasterbackend.model.Customer;

public class CustomerModelDTOMapper {
    public static CustomerModelDTO mapToDTO(AddCustomerRequest customer) {
        return new CustomerModelDTO(customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber(), customer.getAddress(), customer.getEmail(), customer.getPassword(), customer.getNotes(), customer.getDateOfBirth(), customer.getGender());
    }
}
