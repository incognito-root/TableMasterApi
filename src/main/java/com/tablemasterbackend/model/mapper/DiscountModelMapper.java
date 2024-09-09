package com.tablemasterbackend.model.mapper;

import com.tablemasterbackend.dto.DiscountModelDTO;
import com.tablemasterbackend.model.Discount;

public class DiscountModelMapper {
    public Discount mapToModel(DiscountModelDTO discount) {
        return new Discount(discount.getDiscountAmount(), discount.getDiscountTitle());
    }
}
