package com.tablemasterbackend.controller.api;

import com.tablemasterbackend.controller.request.AddCustomerAddressRequest;
import com.tablemasterbackend.controller.request.AddCustomerRequest;
import com.tablemasterbackend.controller.request.LoginRequest;
import com.tablemasterbackend.controller.response.CustomerResponse;
import com.tablemasterbackend.controller.response.CustomerDashboardResponse;
import com.tablemasterbackend.dto.OrderModelDTO;
import com.tablemasterbackend.dto.mapper.CustomerModelDTOMapper;
import com.tablemasterbackend.dto.mapper.LoginModelDTOMapper;
import com.tablemasterbackend.service.CustomerService;
import com.tablemasterbackend.util.errorHandling.ApiResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(name = "CreateCustomerRecord", value = "/createCustomerRecord")
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomerRecord(@Validated @RequestBody AddCustomerRequest request) {
        ApiResponse<CustomerResponse> response = customerService.createCustomer(new CustomerModelDTOMapper().mapToDTO(request));

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else if (response.getError() instanceof DataIntegrityViolationException ||
                response.getError() instanceof ConstraintViolationException) {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else if (response.getError() instanceof DataAccessException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(name = "CustomerLogin", value = "/loginCustomer")
    public ResponseEntity<ApiResponse<Long>> customerLogin(@Validated @RequestBody LoginRequest request) {

        ApiResponse<Long> response = customerService.customerLogin(new LoginModelDTOMapper().mapToDTO(request));

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(name = "SaveCustomerAddress", value = "/saveCustomerAddress")
    public ResponseEntity<ApiResponse<Boolean>> saveCustomerAddress(@RequestBody AddCustomerAddressRequest address) {
        ApiResponse<Boolean> response = customerService.saveCustomerAddress(address);

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(name = "GetCustomerByEmail", value = "/getCustomerByEmail")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerByEmail(@RequestBody String customerEmail) {
        ApiResponse<CustomerResponse> response = customerService.getCustomerByEmail(customerEmail);

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "GetAllCustomers", value = "/getAllCustomers")
    public ResponseEntity<ApiResponse<ArrayList<CustomerResponse>>> getAllCustomers() {

        ApiResponse<ArrayList<CustomerResponse>> response = customerService.getAllCustomers();

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "GetCustomerById", value = "/getCustomerById/{customerId}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable long customerId) {
        ApiResponse<CustomerResponse> response = customerService.getCustomerById(customerId);

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "GetMostOrderedItemId", value = "/getMostOrderedItemId/{customerId}")
    public ResponseEntity<ApiResponse<Long>> getMostOrderedItemId(@PathVariable long customerId) {
        ApiResponse<Long> response = customerService.getFkMenuItemMostOrdered(customerId);

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "GetAllOrdersCount", value = "/getAllOrdersCount/{customerId}")
    public ResponseEntity<ApiResponse<Integer>> getAllOrdersCount(@PathVariable long customerId) {
        ApiResponse<Integer> response = customerService.getTotalOrders(customerId);

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "GetDashboardData", value = "/getDashboardData/{customerId}")
    public ResponseEntity<ApiResponse<CustomerDashboardResponse>> getDashboardData(@PathVariable long customerId) {
        ApiResponse<CustomerDashboardResponse> response = customerService.getDashboardData(customerId);

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "GetAllCustomerOrders", value = "/getAllCustomerOrders/{customerId}")
    public ResponseEntity<ApiResponse<List<OrderModelDTO>>> getAllCustomerOrders(@PathVariable long customerId) {
        ApiResponse<List<OrderModelDTO>> response = customerService.getCustomerOrders(customerId);

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "getCustomerMostFrequentOrder", value = "/getCustomerMostFrequentOrder/{customerId}")
    public ResponseEntity<ApiResponse<OrderModelDTO>> getCustomerMostFrequentOrder(@PathVariable long customerId) {
        ApiResponse<OrderModelDTO> response = customerService.getMostFrequentOrder(customerId);

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
