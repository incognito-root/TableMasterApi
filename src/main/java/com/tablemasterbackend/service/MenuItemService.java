package com.tablemasterbackend.service;

import com.tablemasterbackend.controller.request.UpdateMenuItemRequest;
import com.tablemasterbackend.controller.response.MenuItemResponse;
import com.tablemasterbackend.dto.MenuItemModelDTO;
import com.tablemasterbackend.model.MenuItem;
import com.tablemasterbackend.model.mapper.MenuItemModelMapper;
import com.tablemasterbackend.repository.MenuItemRepository;
import com.tablemasterbackend.util.errorHandling.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {
    private static final Logger logger = LoggerFactory.getLogger(MenuItemService.class);

    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public ApiResponse<Boolean> addMenuItem(MenuItemModelDTO menuItemModelDTO) {
        MenuItem menuItem = new MenuItemModelMapper().mapToModel(menuItemModelDTO);

        try {
            MenuItem savedItem = menuItemRepository.save(menuItem);

            return new ApiResponse<>(true, "Menu Item Created Successfully", true, null);
        } catch (DataIntegrityViolationException e) {
            logger.error("Menu item already exists -> add customer record -> ", e);
            return new ApiResponse<>(false, "Menu item Already Exists", null, e);
        } catch (DataAccessException e) {
            logger.error("Database error -> add customer record -> ", e);
            return new ApiResponse<>(false, "An error occurred while adding menu item", null, e);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument -> add customer record -> ", e);
            return new ApiResponse<>(false, "Invalid data entered while adding menu item", null, e);
        } catch (Exception e) {
            logger.error("An error occurred -> add customer record -> ", e);
            return new ApiResponse<>(false, "An error occurred while adding menu item", null, e);
        }
    }

    public ApiResponse<ArrayList<MenuItemResponse>> getAllMenuItems() {
        try {
            List<MenuItem> menuItems = menuItemRepository.findAll();
            ArrayList<MenuItemResponse> menuItemResponses = new ArrayList<>();

            for (MenuItem menuItem : menuItems) {
                menuItemResponses.add(new MenuItemResponse(menuItem.getMenuItemId(), menuItem.getMenuItemName(), menuItem.getMenuItemDescription(), menuItem.getMenuItemPrice(), menuItem.getMenuItemServing(), menuItem.isMenuItemIsAvailable(), menuItem.getMenuItemTotalOrders(), menuItem.getFkCategoryId(), menuItem.getMenuItemAddedDate(), menuItem.getMenuItemImage()));
            }

            return new ApiResponse<>(true, "Menu Item List", menuItemResponses, null);
        } catch (DataAccessException e) {
            logger.error("Database error -> getAllMenuItems -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving menu items", null, e);
        } catch (Exception e) {
            logger.error("An error occurred -> getAllMenuItems -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving menu items", null, e);
        }
    }

    public ApiResponse<MenuItemResponse> getMenuItemById(long menuItemId) {
        MenuItemResponse menuItemResponse = null;
        try {
            MenuItem menuItem = menuItemRepository.findById(menuItemId).orElse(null);

            menuItemResponse = new MenuItemResponse(menuItem.getMenuItemId(), menuItem.getMenuItemName(), menuItem.getMenuItemDescription(), menuItem.getMenuItemPrice(), menuItem.getMenuItemServing(), menuItem.isMenuItemIsAvailable(), menuItem.getMenuItemTotalOrders(), menuItem.getFkCategoryId(), menuItem.getMenuItemAddedDate(), menuItem.getMenuItemImage());

            return new ApiResponse<>(true, "Menu Item Returned Successfully", menuItemResponse, null);
        } catch (DataAccessException e) {
            logger.error("Database error -> getFkMenuItemMostOrdered record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving favourite menu item", null, e);
        } catch (Exception e) {
            logger.error("An error occurred -> getAllAdmins record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving customer data", null, e);
        }
    }

    @Transactional
    public ApiResponse<Boolean> updateMenuItem(UpdateMenuItemRequest request) {
        try {
            int response = menuItemRepository.updateMenuItem(request.getMenuItemPrice(), request.getMenuItemTitle(), request.getMenuItemDescription());

            if (response > 0) {
                return new ApiResponse<>(true, "Menu Item Updated Successfully", true, null);
            }

            return new ApiResponse<>(false, "Could not update menu item", false, null);
        } catch (DataIntegrityViolationException e) {
            logger.error("Invalid -> updateMenuItem -> ", e);
            return new ApiResponse<>(false, "Invalid data entered", null, e);
        } catch (TransientDataAccessResourceException e) {
            logger.error("Network Error -> updateMenuItem -> ", e);
            return new ApiResponse<>(false, "Network Error", null, e);
        } catch (DataAccessException e) {
            logger.error("Data Access Error -> updateMenuItem -> ", e);
            return new ApiResponse<>(false, "Could not update menu item", null, e);
        } catch (Exception e) {
            logger.error("Data Access Error -> updateMenuItem -> ", e);
            return new ApiResponse<>(false, "Could not update menu item", null, e);
        }
    }
}
