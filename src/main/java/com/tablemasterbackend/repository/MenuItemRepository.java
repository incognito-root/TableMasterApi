package com.tablemasterbackend.repository;

import com.tablemasterbackend.controller.request.UpdateMenuItemRequest;
import com.tablemasterbackend.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MenuItemRepository  extends JpaRepository<MenuItem, Long> {
    public MenuItem findByMenuItemId(long id);

    @Modifying
    @Transactional
    @Query("UPDATE MenuItem m SET m.menuItemPrice = :menuItemPrice, m.menuItemDescription = :menuItemDescription WHERE m.menuItemName = :menuItemTitle")
    public int updateMenuItem(double menuItemPrice, String menuItemTitle, String menuItemDescription);
}
