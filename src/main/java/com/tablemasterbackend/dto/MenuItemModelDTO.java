package com.tablemasterbackend.dto;

import java.time.LocalDateTime;

public class MenuItemModelDTO {
    private String menuItemName;
    private String menuItemDescription;
    private double menuItemPrice;
    private int menuItemServing;
    private boolean menuItemIsAvailable;
    private int menuItemTotalOrders;
    private int fkCategoryId;
    private LocalDateTime menuItemAddedDate;
    private String menuItemImage;

    public MenuItemModelDTO(String menuItemName, String menuItemDescription, double menuItemPrice, int menuItemServing, int fkCategoryId, String menuItemImage) {
        this.menuItemName = menuItemName;
        this.menuItemDescription = menuItemDescription;
        this.menuItemPrice = menuItemPrice;
        this.menuItemServing = menuItemServing;
        this.fkCategoryId = fkCategoryId;
        this.menuItemImage = menuItemImage;
        this.setMenuItemIsAvailable();
        this.setMenuItemTotalOrders();
        this.setMenuItemAddedDate();
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public String getMenuItemDescription() {
        return menuItemDescription;
    }

    public void setMenuItemDescription(String menuItemDescription) {
        this.menuItemDescription = menuItemDescription;
    }

    public double getMenuItemPrice() {
        return menuItemPrice;
    }

    public void setMenuItemPrice(double menuItemPrice) {
        this.menuItemPrice = menuItemPrice;
    }

    public int getMenuItemServing() {
        return menuItemServing;
    }

    public void setMenuItemServing(int menuItemServing) {
        this.menuItemServing = menuItemServing;
    }

    public boolean isMenuItemIsAvailable() {
        return menuItemIsAvailable;
    }

    public void setMenuItemIsAvailable() {
        this.menuItemIsAvailable = true;
    }

    public int getMenuItemTotalOrders() {
        return menuItemTotalOrders;
    }

    public void setMenuItemTotalOrders() {
        this.menuItemTotalOrders = 0;
    }

    public int getFkCategoryId() {
        return fkCategoryId;
    }

    public void setFkCategoryId(int fkCategoryId) {
        this.fkCategoryId = fkCategoryId;
    }

    public LocalDateTime getMenuItemAddedDate() {
        return menuItemAddedDate;
    }

    public void setMenuItemAddedDate() {
        this.menuItemAddedDate = LocalDateTime.now();
    }

    public String getMenuItemImage() {
        return menuItemImage;
    }

    public void setMenuItemImage(String menuItemImage) {
        this.menuItemImage = menuItemImage;
    }
}
