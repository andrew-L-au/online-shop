package com.example.userservice.model.merchandise;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Merchandise {
    @TableId(value = "merchandise_id")
    private Long merchandiseId;
    private String merchandiseName;
    @TableField(exist = false)
    private List<String> images;
    private String description;
    private Double price;
}
