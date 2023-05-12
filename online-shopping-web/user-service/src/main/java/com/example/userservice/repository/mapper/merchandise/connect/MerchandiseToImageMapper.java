package com.example.userservice.repository.mapper.merchandise.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.merchandise.connect.MerchandiseToImage;
import com.example.userservice.model.merchandise.connect.RequestRecordMerchandiseToImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface MerchandiseToImageMapper extends BaseMapper<MerchandiseToImage> {
    default List<String> selectImagesByMerchandise(String merchandiseId){
        List<String> imageIds = new ArrayList<>();
        List<MerchandiseToImage> merchandiseToImages = this.selectList(new QueryWrapper<MerchandiseToImage>().eq("merchandise_id",merchandiseId));
        if (merchandiseToImages == null){
            return null;
        }
        for (MerchandiseToImage merchandiseToImage : merchandiseToImages){
            imageIds.add(merchandiseToImage.getImageId());
        }
        return imageIds;
    }
    default String selectOneByMerchandiseAndImage(String merchandiseId, String imageId){
        MerchandiseToImage merchandiseToImage = this.selectOne(new QueryWrapper<MerchandiseToImage>().eq("merchandise_id",merchandiseId).eq("image_id",imageId));
        if (merchandiseToImage == null){
            return null;
        }
        return merchandiseToImage.getId();
    }
}
