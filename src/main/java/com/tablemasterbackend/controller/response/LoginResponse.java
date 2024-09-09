package com.tablemasterbackend.controller.response;

public class LoginResponse {
    private long id;
    private String message;

    public LoginResponse(long id, String message) {
        this.id = id;
        this.message = message;
    }

    public LoginResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
