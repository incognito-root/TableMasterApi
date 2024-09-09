package com.tablemasterbackend.dto;

public class DiscountModelDTO {
    private long discountId;
    private double discountAmount;
    private String discountTitle;

    public DiscountModelDTO(long discountId, double discountAmount, String discountTitle) {
        this.discountId = discountId;
        this.discountAmount = discountAmount;
        this.discountTitle = discountTitle;
    }

    public DiscountModelDTO(double discountAmount, String discountTitle) {
        this.discountAmount = discountAmount;
        this.discountTitle = discountTitle;
    }

    public DiscountModelDTO() {
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDiscountTitle() {
        return discountTitle;
    }

    public void setDiscountTitle(String discountTitle) {
        this.discountTitle = discountTitle;
    }
}
