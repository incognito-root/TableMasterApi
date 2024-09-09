package com.tablemasterbackend.controller.api;

import com.tablemasterbackend.controller.request.AddMenuItemRequest;
import com.tablemasterbackend.controller.request.UpdateMenuItemRequest;
import com.tablemasterbackend.controller.response.MenuItemResponse;
import com.tablemasterbackend.dto.mapper.MenuItemDTOMapper;
import com.tablemasterbackend.service.MenuItemService;
import com.tablemasterbackend.util.errorHandling.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;

@RestController
@Validated
@RequestMapping("menu")
public class MenuController {

    private final MenuItemService menuItemService;

    @Autowired
    public MenuController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping(name = "GetAllMenuItems", value = "/getAllMenuItems")
    public ResponseEntity<ApiResponse<ArrayList<MenuItemResponse>>> getAllMenuItems() {
        ApiResponse<ArrayList<MenuItemResponse>> response = menuItemService.getAllMenuItems();

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "GetMenuItemDetails", value = "/getMenuItemDetails/{menuItemId}")
    public ResponseEntity<ApiResponse<MenuItemResponse>> getMenuItemDetails(@PathVariable long menuItemId) {
        ApiResponse<MenuItemResponse> response = menuItemService.getMenuItemById(menuItemId);

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(name = "AddMenuItem", value = "/addMenuItem")
    public ResponseEntity<ApiResponse<Boolean>> creatMenuItem(@Validated @RequestBody AddMenuItemRequest request) {
        ApiResponse<Boolean> response = menuItemService.addMenuItem(new MenuItemDTOMapper().mapToDTO(request));

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (response.getError() instanceof DataIntegrityViolationException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(name = "UpdateMenuItem", value = "/updateMenuItem")
    public ResponseEntity<ApiResponse<Boolean>> updateMenuItem(@Validated @RequestBody UpdateMenuItemRequest request) {
        ApiResponse<Boolean> response = menuItemService.updateMenuItem(request);

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
