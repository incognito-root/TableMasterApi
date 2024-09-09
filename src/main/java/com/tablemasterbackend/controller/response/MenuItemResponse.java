package com.tablemasterbackend.controller.response;

import java.time.LocalDateTime;

public class MenuItemResponse {
    private long menuItemId;
    private String menuItemName;
    private String menuItemDescription;
    private double menuItemPrice;
    private int menuItemServing;
    private boolean menuItemIsAvailable;
    private int menuItemTotalOrders;
    private int fkCategoryId;
    private LocalDateTime menuItemAddedDate;
    private String menuItemImage;

    public MenuItemResponse(long menuItemId, String menuItemName, String menuItemDescription, double menuItemPrice, int menuItemServing, boolean menuItemIsAvailable, int menuItemTotalOrders, int fkCategoryId, LocalDateTime menuItemAddedDate, String menuItemImage) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.menuItemDescription = menuItemDescription;
        this.menuItemPrice = menuItemPrice;
        this.menuItemServing = menuItemServing;
        this.menuItemIsAvailable = menuItemIsAvailable;
        this.menuItemTotalOrders = menuItemTotalOrders;
        this.fkCategoryId = fkCategoryId;
        this.menuItemAddedDate = menuItemAddedDate;
        this.menuItemImage = menuItemImage;
    }

    public long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(long menuItemId) {
        this.menuItemId = menuItemId;
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

    public void setMenuItemIsAvailable(boolean menuItemIsAvailable) {
        this.menuItemIsAvailable = menuItemIsAvailable;
    }

    public int getMenuItemTotalOrders() {
        return menuItemTotalOrders;
    }

    public void setMenuItemTotalOrders(int menuItemTotalOrders) {
        this.menuItemTotalOrders = menuItemTotalOrders;
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

    public void setMenuItemAddedDate(LocalDateTime menuItemAddedDate) {
        this.menuItemAddedDate = menuItemAddedDate;
    }

    public String getMenuItemImage() {
        return menuItemImage;
    }

    public void setMenuItemImage(String menuItemImage) {
        this.menuItemImage = menuItemImage;
    }
}
