package com.tablemasterbackend.service;

import com.tablemasterbackend.dto.DiscountModelDTO;
import com.tablemasterbackend.dto.mapper.DiscountModelDTOMapper;
import com.tablemasterbackend.model.Discount;
import com.tablemasterbackend.model.mapper.DiscountModelMapper;
import com.tablemasterbackend.repository.CustomerRepository;
import com.tablemasterbackend.repository.DiscountRepository;
import com.tablemasterbackend.util.errorHandling.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    private static final Logger logger = LoggerFactory.getLogger(DiscountService.class);

    private DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public ApiResponse<Boolean> addDiscount(DiscountModelDTO discountModelDTO) {
        try {
            Discount discount = new DiscountModelMapper().mapToModel(discountModelDTO);

            discountRepository.save(discount);

            return new ApiResponse<>(true, "Discount Updated Successfully", true, null);
        } catch (DataIntegrityViolationException e) {
            logger.error("Email already exists -> add discount record -> ", e);
            return new ApiResponse<>(false, "Email Already Exists", null, null);
        } catch (DataAccessException e) {
            logger.error("Database error -> add discount record -> ", e);
            return new ApiResponse<>(false, "An error occurred while updating discount", null, e);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid Argument -> add discount record -> ", e);
            return new ApiResponse<>(false, "Invalid data entered while updating discount", null, e);
        } catch (Exception e) {
            logger.error("An error occurred -> add discount record -> ", e);
            return new ApiResponse<>(false, "An error occurred while updating discount", null, e);
        }
    }

    public ApiResponse<DiscountModelDTO> getActiveDiscount() {
        try {
            Discount discount = discountRepository.findTopByOrderByDiscountIdDesc();

            if (discount != null) {
                DiscountModelDTO activeDiscount = new DiscountModelDTO(discount.getDiscountId(), discount.getDiscountAmount(), discount.getDiscountTitle());
                return new ApiResponse<>(true, "Discount Get Successful", activeDiscount, null);
            }

            return new ApiResponse<>(false, "No Discounts", null, null);
        } catch (DataAccessException e) {
            logger.error("Database error -> getActiveDiscount record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving discount data", null, e);
        } catch (Exception e) {
            logger.error("An error occurred -> getActiveDiscount record -> ", e);
            return new ApiResponse<>(false, "An error occurred while retrieving discount data", null, e);
        }
    }
}
