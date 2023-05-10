package com.example.userservice.model.merchandise;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Merchandise {
    @TableId(value = "merchandise_id",type = IdType.ASSIGN_ID)
    private String merchandiseId;
    private String merchandiseName;
    @TableField(exist = false)
    private List<Image> images;
    private String description;
    private Double price;
}
