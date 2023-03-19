package model.shop;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum ShopStatus {
    IN_REVIEW("IN_REVIEW"),NORMAL("NORMAL");
    @EnumValue
    private final String status;
    ShopStatus(String status){
        this.status = status;
    }
}
