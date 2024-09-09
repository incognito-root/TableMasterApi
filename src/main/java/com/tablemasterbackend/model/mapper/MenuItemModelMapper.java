package com.tablemasterbackend.model.mapper;

import com.tablemasterbackend.dto.MenuItemModelDTO;
import com.tablemasterbackend.model.MenuItem;

public class MenuItemModelMapper {
    public MenuItem mapToModel(MenuItemModelDTO dto) {
        return new MenuItem(dto.getMenuItemName(),dto.getMenuItemDescription(), dto.getMenuItemPrice(), dto.getMenuItemServing(), dto.isMenuItemIsAvailable(), dto.getMenuItemTotalOrders(), dto.getFkCategoryId(), dto.getMenuItemAddedDate(), dto.getMenuItemImage());
    }
}
