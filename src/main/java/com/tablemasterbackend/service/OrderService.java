package com.tablemasterbackend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tablemasterbackend.controller.response.CustomerResponse;
import com.tablemasterbackend.dto.OrderModelDTO;
import com.tablemasterbackend.model.Order;
import com.tablemasterbackend.model.mapper.OrderModelMapper;
import com.tablemasterbackend.repository.OrderDetailRepository;
import com.tablemasterbackend.repository.OrderRepository;
import com.tablemasterbackend.util.SendEmail;
import com.tablemasterbackend.util.errorHandling.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CustomerService customerService;

    // Need credentials for a simple JDBC call
    // Reason for using simple JDBC is that Spring JPA was giving abnormal errors on stored procedure call
    protected static final String dbUrl = "";
    protected static final String dbUsername = "";
    protected static final String dbPassword = "";
    protected static Connection dbConnection = null;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Transactional
    public ApiResponse<Boolean> createOrder(OrderModelDTO orderModelDTO) {
        try {
            dbConnection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            ObjectMapper objectMapper = new ObjectMapper();
            String orderDetailsJson = objectMapper.writeValueAsString(orderModelDTO.getOrderDetails());
            java.sql.Date sqlDate = new Date(System.currentTimeMillis());

            try {
                String sql = "CALL CreateOrderWithDetails(?, ?, ?, ?, ?, ?, ?)";

                CallableStatement statement = dbConnection.prepareCall(sql);
                statement.setString(1, orderModelDTO.getOrderDescription());
                statement.setString(2, orderModelDTO.getOrderStatus());
                statement.setDate(3, sqlDate);
                statement.setBigDecimal(4, BigDecimal.valueOf(orderModelDTO.getOrderAmount()));
                statement.setBigDecimal(5, BigDecimal.valueOf(orderModelDTO.getOrderExtraCharges()));
                statement.setLong(6, orderModelDTO.getFkCustomerId());
                statement.setString(7, orderDetailsJson);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    sendEmails(orderModelDTO);
                    return new ApiResponse<>(true, "Order Placed Successfully", true, null);
                }

            } catch (SQLException e) {
                logger.error("Error in JDBC call -> ", e);
                return new ApiResponse<>(false, "Could not place order", false, null);
            }

        } catch (DataIntegrityViolationException e) {
            logger.error("Invalid -> updateMenuItem -> ", e);
            return new ApiResponse<>(false, "Invalid data entered", null, e);
        } catch (TransientDataAccessResourceException e) {
            logger.error("Network Error -> updateMenuItem -> ", e);
            return new ApiResponse<>(false, "Network Error", null, e);
        } catch (DataAccessException e) {
            logger.error("Data Access Error -> updateMenuItem -> ", e);
            return new ApiResponse<>(false, "Could not place order", null, e);
        } catch (Exception e) {
            logger.error("Could Not Place Order -> updateMenuItem -> ", e);
            return new ApiResponse<>(false, "Could not place order", null, e);
        }

        return new ApiResponse<>(false, "Could not place order", null, null);
    }

    private void sendEmails(OrderModelDTO orderModelDTO) {
        ApiResponse<CustomerResponse> customerData = customerService.getCustomerById(orderModelDTO.getFkCustomerId());
        CustomerResponse customer = customerData.getData();

        SendEmail email = new SendEmail();
        email.sendOrderConfirmationEmail(customer.getEmail(), customer.getFirstName() + " " + customer.getLastName(), orderModelDTO);

        email.sendOrderConfirmationEmailToAdmin(orderModelDTO);
    }
}
