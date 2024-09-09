package com.tablemasterbackend.controller.api;

import com.tablemasterbackend.controller.request.AddDiscountRequest;
import com.tablemasterbackend.controller.response.CustomerResponse;
import com.tablemasterbackend.dto.DiscountModelDTO;
import com.tablemasterbackend.dto.mapper.DiscountModelDTOMapper;
import com.tablemasterbackend.service.DiscountService;
import com.tablemasterbackend.util.errorHandling.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("discount")
public class DiscountController {
    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping(name = "CreateDiscount", value = "/createDiscount")
    public ResponseEntity<ApiResponse<Boolean>> createDiscount(@RequestBody AddDiscountRequest request) {
        ApiResponse<Boolean> response = discountService.addDiscount(new DiscountModelDTOMapper().mapToDTO(request));

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "GetActiveDiscount", value = "/getActiveDiscount")
    public ResponseEntity<ApiResponse<DiscountModelDTO>> getAllCustomers() {
        ApiResponse response = discountService.getActiveDiscount();

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
