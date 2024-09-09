package com.tablemasterbackend.util.baseclass;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserBaseRequest {
    @NotNull(message = "firstname cannot be null")
    @NotBlank(message = "firstname cannot be blank")
    @Size(min = 2, max = 30, message = "firstname length should be between 4 and 30")
    private String firstName;

    @NotNull(message = "lastname cannot be null")
    @NotBlank(message = "lastname cannot be blank")
    @Size(min = 2, max = 30, message = "lastname length should be between 4 and 30")
    private String lastName;

    @Null
    private String addedDateTime;

    @NotNull(message = "contact number cannot be null")
    @NotBlank(message = "contact number cannot be blank")
    @Size(min = 11, max = 11, message = "invalid contact number format")
    private String contactNumber;
}
