package com.tablemasterbackend.service;

import com.tablemasterbackend.controller.response.AdminDashboardResponse;
import com.tablemasterbackend.controller.response.AdminResponse;
import com.tablemasterbackend.dto.AdminModelDTO;
import com.tablemasterbackend.dto.LoginModelDTO;
import com.tablemasterbackend.dto.mapper.AdminDTOMapper;
import com.tablemasterbackend.model.Admin;
import com.tablemasterbackend.model.Order;
import com.tablemasterbackend.model.mapper.AdminModelMapper;
import com.tablemasterbackend.repository.AdminRepository;
import com.tablemasterbackend.repository.CustomerRepository;
import com.tablemasterbackend.repository.OrderRepository;
import com.tablemasterbackend.util.authentication.Password;
import com.tablemasterbackend.util.errorHandling.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
    private AdminRepository adminRepository;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.adminRepository = adminRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public ApiResponse<AdminResponse> createAdmin(AdminModelDTO adminModelDTO) {
        Admin adminToSave = new AdminModelMapper().mapToModel(adminModelDTO);

        try {
            Admin admin = adminRepository.save(adminToSave);
            AdminResponse adminResponse = new AdminResponse(admin.getAdminId(), admin.getAdminFirstName(), admin.getAdminLastName(), admin.getAdminContactNumber(), admin.getAdminRole(), admin.getAdminEmail(), admin.getAdminUsername());
            return new ApiResponse<>(true, "Admin Added Successfully", adminResponse, null);
        } catch (DataIntegrityViolationException e) {
            logger.error("Email already exists -> add email record -> ", e);
            return new ApiResponse<>(false, "Email Already Exists", null, null);
        } catch (DataAccessException e) {
            logger.error("Database error -> add admin record -> ", e);
            return new ApiResponse<>(false, "An error occurred while adding admin record", null, e);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument -> add admin record -> ", e);
            return new ApiResponse<>(false, "Invalid Argument while adding admin record", null, e);
        } catch (Exception e) {
            logger.error("An error occurred -> add admin record -> ", e);
            return new ApiResponse<>(false, "An error occurred while adding admin record", null, e);
        }
    }

    public ApiResponse<List<AdminResponse>> getAllAdmins() {
        List<AdminResponse> adminsToReturn = null;

        try {
            List<Admin> adminsToGet = adminRepository.findAll();
            if (adminsToGet.isEmpty()) {
                return new ApiResponse<>(true, "No admin records found", new ArrayList<>(), null);
            }

            adminsToReturn = new ArrayList<>();

            for (Admin admin : adminsToGet) {
                adminsToReturn.add(new AdminResponse(
                        admin.getAdminId(),
                        admin.getAdminFirstName(),
                        admin.getAdminLastName(),
                        admin.getAdminContactNumber(),
                        admin.getAdminRole(),
                        admin.getAdminEmail(),
                        admin.getAdminUsername())
                );
            }

            return new ApiResponse<>(true, "Admins Returned Successfully", adminsToReturn, null);
        } catch (DataAccessException e) {
            logger.error("Database error -> getAllAdmins record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving admin data", null, e);
        } catch (Exception e) {
            logger.error("An error occurred -> getAllAdmins record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving admin data", null, e);
        }
    }

    public ApiResponse<Boolean> adminLogin(LoginModelDTO loginModelDTO) {
        try {
            Admin admin = adminRepository.findAdminByAdminUsername(loginModelDTO.getUsernameOrEmail());

            if (admin == null) {
                return new ApiResponse<>(false, "Username not found", false, null);
            }

            Password password = new Password();
            boolean passwordMatches = password.matches(admin.getAdminPassword(), loginModelDTO.getPassword());

            if (passwordMatches) {
                return new ApiResponse<>(true, "Login successful", true, null);
            } else {
                return new ApiResponse<>(false, "Incorrect password", false, null);
            }
        } catch (Exception e) {
            logger.error("Error occurred -> admin login -> ", e);
            return new ApiResponse<>(false, "Unexpected error occurred", false, e);
        }
    }

    public ApiResponse<AdminDashboardResponse> dashboardResponse() {
        try {
            AdminDashboardResponse adminDashboardResponse = new AdminDashboardResponse();

            adminDashboardResponse.setRevenue(orderRepository.sumAmount());
            adminDashboardResponse.setTotalCustomers(customerRepository.count());
            adminDashboardResponse.setTotalOrders(orderRepository.count());
            adminDashboardResponse.setOrders(orderRepository.findAll());

            return new ApiResponse<>(true, "Dashboard data retrieved successfully", adminDashboardResponse, null);
        } catch (DataAccessException e) {
            logger.error("Database error -> get admin dashboard data", e);
            return new ApiResponse<>(false, "Database error occurred", null, e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while fetching dashboard data", e);
            return new ApiResponse<>(false, "Unexpected error occurred", null, e);
        }
    }

}
