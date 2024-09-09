package com.tablemasterbackend.dto.mapper;

import com.tablemasterbackend.controller.request.AddDiscountRequest;
import com.tablemasterbackend.dto.DiscountModelDTO;

public class DiscountModelDTOMapper {
    public static DiscountModelDTO mapToDTO(AddDiscountRequest discount) {
        return new DiscountModelDTO(discount.getDiscountPercentage(), discount.getDiscountTitle());
    }
}
