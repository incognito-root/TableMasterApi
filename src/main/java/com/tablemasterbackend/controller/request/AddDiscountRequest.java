package com.tablemasterbackend.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class AddDiscountRequest {
    private String discountTitle;
    private double discountPercentage;
}
