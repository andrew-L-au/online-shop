package com.example.userservice.model.merchandise;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Image {
    @TableId
    private String imageId;
    private String base64;
}
