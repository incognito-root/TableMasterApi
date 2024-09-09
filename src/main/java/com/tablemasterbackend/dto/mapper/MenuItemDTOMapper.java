package com.tablemasterbackend.dto.mapper;

import com.tablemasterbackend.controller.request.AddMenuItemRequest;
import com.tablemasterbackend.dto.MenuItemModelDTO;
import com.tablemasterbackend.model.MenuItem;

public class MenuItemDTOMapper {
    public MenuItemModelDTO mapToDTO(AddMenuItemRequest menuItem) {
        return new MenuItemModelDTO(menuItem.getMenuItemName(), menuItem.getMenuItemDescription(), menuItem.getMenuItemPrice(), menuItem.getMenuItemServing(), menuItem.getFkCategoryId(), menuItem.getMenuItemImage());
    }
}
