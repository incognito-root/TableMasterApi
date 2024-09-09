package com.tablemasterbackend.controller.request;

import com.tablemasterbackend.util.baseclass.UserBaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class AdminRequest extends UserBaseRequest {

    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    @Size(min = 4, max = 30, message = "admin username length should be between 4 and 30")
    private String adminUsername;

    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
    @Size(min = 8, max = 128, message = "admin password should be between 4 and 30")
    private String adminPassword;

    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    @Size(min = 10, max = 320, message = "admin password should be between 4 and 30")
    private String adminEmail;

    @NotNull(message = "role cannot be null")
    @NotBlank(message = "role cannot be blank")
    @Size(min = 4, max = 20, message = "role invalid")
    private String adminRole;
}
