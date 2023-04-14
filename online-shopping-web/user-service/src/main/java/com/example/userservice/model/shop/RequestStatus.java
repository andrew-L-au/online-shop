package com.example.userservice.model.shop;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum RequestStatus {
    IN_REVIEW("IN_REVIEW"),APPROVE("APPROVE"),REJECT("REJECT");
    @EnumValue
    private final String status;
    RequestStatus(String status){
        this.status = status;
    }
}
