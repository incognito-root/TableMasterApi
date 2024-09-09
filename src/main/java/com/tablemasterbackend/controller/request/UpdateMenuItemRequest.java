package com.tablemasterbackend.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UpdateMenuItemRequest {
    String menuItemTitle;
    double menuItemPrice;
    String menuItemDescription;
}
