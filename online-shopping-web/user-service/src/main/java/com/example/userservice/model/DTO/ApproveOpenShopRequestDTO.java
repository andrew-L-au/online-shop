package com.example.userservice.model.DTO;

import lombok.Data;

@Data
public class ApproveOpenShopRequestDTO {
    private String shopId;
    private Boolean isApproved;
}
