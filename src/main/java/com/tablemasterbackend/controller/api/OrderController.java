package com.tablemasterbackend.controller.api;

import com.tablemasterbackend.controller.request.AddMenuItemRequest;
import com.tablemasterbackend.controller.request.AddOrderRequest;
import com.tablemasterbackend.dto.mapper.MenuItemDTOMapper;
import com.tablemasterbackend.dto.mapper.OrderModelDTOMapper;
import com.tablemasterbackend.service.OrderService;
import com.tablemasterbackend.util.errorHandling.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(name = "CreateOrder", value = "/createOrder")
    public ResponseEntity<ApiResponse<Boolean>> createAdminRecord(@Validated @RequestBody AddOrderRequest request) {
        ApiResponse<Boolean> response = orderService.createOrder(new OrderModelDTOMapper().mapToDTO(request));

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
