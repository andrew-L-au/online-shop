package com.example.userservice.model.merchandise.connect;

import lombok.Data;

@Data
public class NewMerchandiseToNewMerchandiseRequest {
    private Long id;
    private Long newMerchandiseId;
    private Long newMerchandiseRequestId;
}
