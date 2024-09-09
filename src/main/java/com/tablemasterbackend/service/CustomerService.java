package com.tablemasterbackend.service;

import com.tablemasterbackend.controller.request.AddCustomerAddressRequest;
import com.tablemasterbackend.controller.response.CustomerResponse;
import com.tablemasterbackend.controller.response.CustomerDashboardResponse;
import com.tablemasterbackend.dto.CustomerModelDTO;
import com.tablemasterbackend.dto.LoginModelDTO;
import com.tablemasterbackend.dto.OrderDetailModelDTO;
import com.tablemasterbackend.dto.OrderModelDTO;
import com.tablemasterbackend.model.Customer;
import com.tablemasterbackend.model.Order;
import com.tablemasterbackend.model.OrderDetail;
import com.tablemasterbackend.model.mapper.CustomerModelMapper;
import com.tablemasterbackend.repository.CustomerRepository;
import com.tablemasterbackend.repository.OrderDetailRepository;
import com.tablemasterbackend.repository.OrderRepository;
import com.tablemasterbackend.util.SendEmail;
import com.tablemasterbackend.util.authentication.Password;
import com.tablemasterbackend.util.errorHandling.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tablemasterbackend.service.OrderService.*;

@Service
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private CustomerRepository customerRepository;
    private DiscountService discountService;
    private MenuItemService menuItemService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, DiscountService discountService, MenuItemService menuItemService) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.discountService = discountService;
        this.menuItemService = menuItemService;
    }

    public ApiResponse<CustomerResponse> createCustomer(CustomerModelDTO customer) {
        Customer customerToSave = new CustomerModelMapper().mapToModel(customer);

        try {
            Customer addedCustomer = customerRepository.saveAndFlush(customerToSave);
            SendEmail sendEmail = new SendEmail();
            sendEmail.sendWelcomeEmail(customer.getEmail(), customer.getFirstName() + " " + customer.getLastName());

            CustomerResponse customerResponse = getCustomerByEmail(customer.getEmail()).getData();

            return new ApiResponse<>(true, "Account Created Successfully", customerResponse, null);
        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            logger.error("Email already exists -> add customer record -> ", e);
            return new ApiResponse<>(false, "Email Already Exists", null, e);
        } catch (DataAccessException e) {
            logger.error("Database error -> add customer record -> ", e);
            return new ApiResponse<>(false, "An error occurred while adding customer record", null, e);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument -> add customer record -> ", e);
            return new ApiResponse<>(false, "Invalid data entered while creating customer account", null, e);
        } catch (Exception e) {
            logger.error("An error occurred -> add customer record -> ", e);
            return new ApiResponse<>(false, "An error occurred while creating customer account", null, e);
        }
    }

    public ApiResponse<Long> customerLogin(LoginModelDTO loginModelDTO) throws NullPointerException {
        try {
            Customer customer = customerRepository.findCustomerByCustomerEmail(loginModelDTO.getUsernameOrEmail());

            if (customer == null) {
                return new ApiResponse<>(false, "Email not found", 0L, null);
            }

            Password password = new Password();
            boolean passwordMatches = password.matches(customer.getCustomerPassword(), loginModelDTO.getPassword());

            if (passwordMatches) {
                return new ApiResponse<>(true, "Login successful", customer.getCustomerId(), null);
            } else {
                return new ApiResponse<>(false, "Incorrect password", 0L, null);
            }
        } catch (Exception e) {
            logger.error("Error occurred -> customer login -> ", e);
            return new ApiResponse<>(false, "Unexpected error occurred", 0L, e);
        }
    }

    public ApiResponse<ArrayList<CustomerResponse>> getAllCustomers() {
        try {
            List<Customer> customers = customerRepository.findAll();
            ArrayList<CustomerResponse> customerResponse = new ArrayList<>();

            for (Customer customer : customers) {
                customerResponse.add(new CustomerResponse(
                        customer.getCustomerId(),
                        customer.getCustomerFirstName(),
                        customer.getCustomerLastName(),
                        customer.getCustomerEmail(),
                        customer.getCustomerPhoneNumber(),
                        customer.getCustomerAddress(),
                        customer.getCustomerNotes(),
                        customer.getCustomerGender(),
                        customer.getCustomerDateOfBirth(),
                        customer.getFkMenuItemMostOrdered(),
                        customer.isCustomerMembership(),
                        customer.isCustomerIsVerified())
                );
            }

            return new ApiResponse<>(true, "Customer List returned successfully", customerResponse, null);
        } catch (DataAccessException e) {
            logger.error("Database error -> getAllCustomers record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving customer data", null, e);
        } catch (Exception e) {
            logger.error("An error occurred -> getAllAdmins record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving customer data", null, e);
        }
    }

    public ApiResponse<CustomerResponse> getCustomerById(long customerId) {
        try {
            Customer customer = customerRepository.findCustomerByCustomerId(customerId);

            CustomerResponse customerToReturn = new CustomerResponse(customer.getCustomerId(), customer.getCustomerFirstName(), customer.getCustomerLastName(), customer.getCustomerEmail(), customer.getCustomerPhoneNumber(), customer.getCustomerAddress(), customer.getCustomerNotes(), customer.getCustomerGender(), customer.getCustomerDateOfBirth(), customer.getFkMenuItemMostOrdered(), customer.isCustomerMembership(), customer.isCustomerIsVerified());

            if (customerToReturn.getCustomerId() != 0) {
                return new ApiResponse<>(true, "Customer Found", customerToReturn, null);
            }

            return new ApiResponse<>(false, "Customer Not Found", customerToReturn, null);
        } catch (NullPointerException e) {
            logger.error("Invalid Customer ID -> getCustomerById record -> ", e);
            return new ApiResponse<>(false, "Customer not found", null, null);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Customer ID -> getCustomerById record -> ", e);
            return new ApiResponse<>(false, "Customer Not Found, Invalid Id", null, e);
        } catch (DataAccessException e) {
            logger.error("Database error occurred -> getCustomerById record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving customer data", null, e);
        } catch (Exception e) {
            logger.error("An error occurred -> getCustomerById record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving customer data", null, e);
        }
    }

    public ApiResponse<CustomerResponse> getCustomerByEmail(String customerEmail) {
        try {
            Customer customer = customerRepository.findCustomerByCustomerEmail(customerEmail);

            CustomerResponse customerToReturn = new CustomerResponse(customer.getCustomerId(), customer.getCustomerFirstName(), customer.getCustomerLastName(), customer.getCustomerEmail(), customer.getCustomerPhoneNumber(), customer.getCustomerAddress(), customer.getCustomerNotes(), customer.getCustomerGender(), customer.getCustomerDateOfBirth(), customer.getFkMenuItemMostOrdered(), customer.isCustomerMembership(), customer.isCustomerIsVerified());

            if (customerToReturn.getCustomerId() != 0) {
                return new ApiResponse<>(true, "Customer Found", customerToReturn, null);
            }

            return new ApiResponse<>(false, "Customer Not Found", customerToReturn, null);
        } catch (NullPointerException e) {
            logger.error("Invalid Customer ID -> getCustomerById record -> ", e);
            return new ApiResponse<>(false, "Customer not found", null, null);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Customer ID -> getCustomerById record -> ", e);
            return new ApiResponse<>(false, "Customer Not Found, Invalid Id", null, e);
        } catch (DataAccessException e) {
            logger.error("Database error occurred -> getCustomerById record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving customer data", null, e);
        } catch (Exception e) {
            logger.error("An error occurred -> getCustomerById record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving customer data", null, e);
        }
    }

    public ApiResponse<Boolean> saveCustomerAddress(AddCustomerAddressRequest addressToSave) {
        try {
            int saveAddressSuccessfully = customerRepository.updateCustomerAddress(addressToSave.getCustomerId(), addressToSave.getAddress());

            if (saveAddressSuccessfully > 0) {
                return new ApiResponse<>(true, "Updated Address Successfully", null, null);
            }

            return new ApiResponse<>(false, "Could not update address", null, null);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Customer ID -> getCustomerById record -> ", e);
            return new ApiResponse<>(false, "Customer Not Found, Invalid Id", null, e);
        } catch (DataAccessException e) {
            logger.error("Database error occurred -> getCustomerById record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving customer data", null, e);
        } catch (Exception e) {
            logger.error("Error While Saving Updating -> saveCustomerAddress -> ", e);
            return new ApiResponse<>(false, "Could Not Update Address", null, e);
        }
    }

    public ApiResponse<Long> getFkMenuItemMostOrdered(long customerId) {
        List<OrderDetail> allOrderDetails = null;

        try {
            List<Order> allOrders = orderRepository.findByFkCustomerId(customerId);

            if (allOrders.isEmpty()) {
                return new ApiResponse<>(false, "No Orders Found", null, null);
            }

            allOrderDetails = new ArrayList<>();
            for (Order order : allOrders) {
                List<OrderDetail> orderDetails = orderDetailRepository.findByFkOrderId(order.getOrderId());
                allOrderDetails.addAll(orderDetails);
            }

            return new ApiResponse<>(true, "Get Most Ordered Menu Item Successful", getMostOrderedItem(allOrderDetails), null);
        } catch (DataAccessException e) {
            logger.error("Database error -> getFkMenuItemMostOrdered record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving favourite menu item", null, e);
        } catch (Exception e) {
            logger.error("An error occurred -> getAllAdmins record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving customer data", null, e);
        }
    }

    private long getMostOrderedItem(List<OrderDetail> orderDetails) {
        Map<Long, Integer> itemQuantityMap = new HashMap<>();

        for (OrderDetail orderDetail : orderDetails) {
            long menuItemId = orderDetail.getFkMenuItemId();
            int quantity = orderDetail.getOrderDetailQuantity();
            itemQuantityMap.put(menuItemId, itemQuantityMap.getOrDefault(menuItemId, 0) + quantity);
        }

        long mostOrderedItem = -1;
        int maxCount = 0;

        for (Map.Entry<Long, Integer> entry : itemQuantityMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostOrderedItem = entry.getKey();
            }
        }

        return mostOrderedItem;
    }

    public ApiResponse<Integer> getTotalOrders(long customerId) {
        try {
            Integer totalOrders = orderRepository.countByFkCustomerId(customerId);

            if (totalOrders == 0) {
                return new ApiResponse<>(false, "No Orders Found", null, null);
            }

            return new ApiResponse<>(true, "Total Orders Found", totalOrders, null);
        } catch (DataAccessException e) {
            logger.error("Database error -> getTotalOrders -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving total orders", null, e);
        } catch (Exception e) {
            logger.error("An error occurred -> getTotalOrders -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving total orders", null, e);
        }
    }

    public ApiResponse<CustomerDashboardResponse> getDashboardData(long customerId) {
        try {
            CustomerDashboardResponse customerDashboardResponse = new CustomerDashboardResponse();

            customerDashboardResponse.setTotalOrders(getTotalOrders(customerId).getData());

            customerDashboardResponse.setOrders(orderRepository.findByFkCustomerId(customerId).reversed());

            return new ApiResponse<>(true, "Dashboard Data", customerDashboardResponse, null);
        } catch (DataAccessException e) {
            logger.error("Database error -> getDashboardData record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving dashboard data", null, e);
        } catch (NullPointerException e) {
            logger.error("No Data For New User Error -> getDashboardData record -> ", e);
            return new ApiResponse<>(false, "Dashboard not available yet. Please place some orders to see your metrics. Thank you", null, e);
        } catch (Exception e) {
            logger.error("An error occurred -> getDashboardData -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving dashboard data", null, e);
        }
    }

    public ApiResponse<List<OrderModelDTO>> getCustomerOrders(long customerId) {
        try {
            String sql = "CALL GetCustomerOrders(?)";

            Connection dbConnection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            CallableStatement statement = dbConnection.prepareCall(sql);
            statement.setLong(1, customerId);

            ResultSet resultSet = statement.executeQuery();

            Map<Long, OrderModelDTO> orderMap = new HashMap<>();

            while (resultSet.next()) {
                long orderId = resultSet.getLong("order_id");

                OrderModelDTO order = orderMap.get(orderId);

                if (order == null) {
                    order = new OrderModelDTO(
                            orderId,
                            resultSet.getString("order_description"),
                            resultSet.getString("order_status"),
                            resultSet.getDate("order_placed_date").toLocalDate(),
                            resultSet.getDouble("order_amount"),
                            resultSet.getDouble("order_extra_charges"),
                            resultSet.getLong("fk_customer_id")
                    );
                    orderMap.put(orderId, order);
                }

                OrderDetailModelDTO orderDetail = new OrderDetailModelDTO(
                        resultSet.getInt("order_detail_quantity"),
                        resultSet.getLong("fk_menu_item_id")
                );

                order.getOrderDetails().add(orderDetail);
            }

            List<OrderModelDTO> orders = new ArrayList<>(orderMap.values());

            if (orders.isEmpty()) {
                return new ApiResponse<>(false, "No Orders Found", null, null);
            }

            return new ApiResponse<>(true, "Get customer orders successful", orders, null);

        } catch (SQLException e) {
            logger.error("Error in JDBC call -> ", e);
            return new ApiResponse<>(false, "Could not get all customer orders", null, null);
        }
    }

    public ApiResponse<OrderModelDTO> getMostFrequentOrder(long customerId) {
        List<OrderModelDTO> orders = getCustomerOrders(customerId).getData();

        if (orders == null || orders.isEmpty()) {
            return new ApiResponse<>(false, "No Orders Found", null, null);
        }

        Map<OrderModelDTO, Integer> orderFrequencyMap = new HashMap<>();

        for (OrderModelDTO order : orders) {
            orderFrequencyMap.put(order, orderFrequencyMap.getOrDefault(order, 0) + 1);
        }

        OrderModelDTO mostFrequentOrder = null;
        int maxFrequency = 0;

        for (Map.Entry<OrderModelDTO, Integer> entry : orderFrequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mostFrequentOrder = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        return new ApiResponse<>(true, "Most frequent order", mostFrequentOrder, null);
    }

}
