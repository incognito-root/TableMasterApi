package com.tablemasterbackend.controller.request;

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
public class AddMenuItemRequest {
    private String menuItemName;
    private String menuItemDescription;
    private double menuItemPrice;
    private int menuItemServing;
    private int fkCategoryId;
    private String menuItemImage;
}
