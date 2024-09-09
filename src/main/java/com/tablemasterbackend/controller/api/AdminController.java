package com.tablemasterbackend.controller.api;

import com.tablemasterbackend.controller.request.AdminRequest;
import com.tablemasterbackend.controller.request.LoginRequest;
import com.tablemasterbackend.controller.response.AdminDashboardResponse;
import com.tablemasterbackend.controller.response.AdminResponse;
import com.tablemasterbackend.dto.mapper.AdminDTOMapper;
import com.tablemasterbackend.dto.mapper.LoginModelDTOMapper;
import com.tablemasterbackend.service.AdminService;
import com.tablemasterbackend.util.errorHandling.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(name = "CreateAdminRecord", value = "/createAdmin")
    public ResponseEntity<ApiResponse<AdminResponse>> createAdminRecord(@Validated @RequestBody AdminRequest request) {
        ApiResponse<AdminResponse> response = adminService.createAdmin(new AdminDTOMapper().mapToDTO(request));

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(name = "AdminLogin", value = "/loginAdmin")
    public ResponseEntity<ApiResponse<Boolean>> adminLogin(@Validated @RequestBody LoginRequest request) {
        ApiResponse<Boolean> response = adminService.adminLogin(new LoginModelDTOMapper().mapToDTO(request));

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(name = "GetAllAdmins", value = "/getAllAdmins")
    public ResponseEntity<ApiResponse<List<AdminResponse>>> getAllAdmins() {
        ApiResponse<List<AdminResponse>> response = adminService.getAllAdmins();

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (response.getError() instanceof IllegalArgumentException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name = "GetAdminDashboardData", value = "/getAdminDashboardData")
    public ResponseEntity<ApiResponse<AdminDashboardResponse>> getAdminDashboardData() {
        ApiResponse<AdminDashboardResponse> response = adminService.dashboardResponse();

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
