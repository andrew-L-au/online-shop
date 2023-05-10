package com.example.userservice.repository.mapper.merchandise.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.merchandise.connect.RequestRecordMerchandiseToImage;
import com.example.userservice.model.shop.connect.ShopToMerchandise;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RequestRecordMerchandiseToImageMapper extends BaseMapper<RequestRecordMerchandiseToImage> {
    default List<String> selectImagesByRequestRecordMerchandise(String requestRecordMerchandiseId){
        List<String> imageIds = new ArrayList<>();
        if (requestRecordMerchandiseId == null){
            return null;
        }
        List<RequestRecordMerchandiseToImage> requestRecordMerchandiseToImages = this.selectList(new QueryWrapper<RequestRecordMerchandiseToImage>().eq("request_record_merchandise_id",requestRecordMerchandiseId));
        if (requestRecordMerchandiseToImages == null){
            return null;
        }
        for (RequestRecordMerchandiseToImage requestRecordMerchandiseToImage : requestRecordMerchandiseToImages){
            imageIds.add(requestRecordMerchandiseToImage.getImageId());
        }
        return imageIds;
    }
}
