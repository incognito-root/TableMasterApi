package com.tablemasterbackend.controller.request;

public class AddCustomerAddressRequest {
    private long customerId;
    private String address;

    public AddCustomerAddressRequest(String address) {
        this.address = address;
    }

    public AddCustomerAddressRequest(long customerId, String address) {
        this.customerId = customerId;
        this.address = address;
    }

    public AddCustomerAddressRequest() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
