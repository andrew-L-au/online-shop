package com.example.userservice.model.DTO;

import lombok.Data;

@Data
public class ApproveOpenShopRequestDTO {
    private String name;
    private Boolean isApproved;
}
