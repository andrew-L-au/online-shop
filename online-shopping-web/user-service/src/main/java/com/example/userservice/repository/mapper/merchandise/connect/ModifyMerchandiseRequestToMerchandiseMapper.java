package com.example.userservice.repository.mapper.merchandise.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.merchandise.connect.ModifyMerchandiseRequestToMerchandise;
import com.example.userservice.model.merchandise.connect.RequestRecordMerchandiseToModifyMerchandiseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ModifyMerchandiseRequestToMerchandiseMapper extends BaseMapper<ModifyMerchandiseRequestToMerchandise> {
    default String selectMerchandiseByModifyMerchandiseRequest(String modifyMerchandiseRequestId){
        if (modifyMerchandiseRequestId == null){
            return null;
        }
        ModifyMerchandiseRequestToMerchandise modifyMerchandiseRequestToMerchandise = this.selectOne(new QueryWrapper<ModifyMerchandiseRequestToMerchandise>().eq("modify_merchandise_request_id",modifyMerchandiseRequestId));
        if (modifyMerchandiseRequestToMerchandise == null){
            return null;
        }
        return modifyMerchandiseRequestToMerchandise.getMerchandiseId();
    }

    default List<String> selectModifyMerchandiseRequestsByMerchandise(String merchandiseId){
        List<String> modifyMerchandiseRequestIds = new ArrayList<>();
        if (merchandiseId == null){
            return null;
        }
        List<ModifyMerchandiseRequestToMerchandise> modifyMerchandiseRequestToMerchandises = this.selectList(new QueryWrapper<ModifyMerchandiseRequestToMerchandise>().eq("merchandise_id",merchandiseId));
        if (modifyMerchandiseRequestToMerchandises == null){
            return null;
        }
        for (ModifyMerchandiseRequestToMerchandise modifyMerchandiseRequestToMerchandise : modifyMerchandiseRequestToMerchandises){
            if (modifyMerchandiseRequestToMerchandise.getModifyMerchandiseRequestId() == null){
                continue;
            }
            modifyMerchandiseRequestIds.add(modifyMerchandiseRequestToMerchandise.getModifyMerchandiseRequestId());
        }
        return modifyMerchandiseRequestIds;
    }

    default List<String> selectIdsByMerchandise(String merchandiseId){
        List<String> ids = new ArrayList<>();
        if (merchandiseId == null){
            return null;
        }
        List<ModifyMerchandiseRequestToMerchandise> modifyMerchandiseRequestToMerchandises = this.selectList(new QueryWrapper<ModifyMerchandiseRequestToMerchandise>().eq("merchandise_id",merchandiseId));
        if (modifyMerchandiseRequestToMerchandises == null){
            return null;
        }
        for (ModifyMerchandiseRequestToMerchandise modifyMerchandiseRequestToMerchandise : modifyMerchandiseRequestToMerchandises){
            if (modifyMerchandiseRequestToMerchandise.getId() == null){
                continue;
            }
            ids.add(modifyMerchandiseRequestToMerchandise.getId());
        }
        return ids.size() == 0 ? null : ids;
    }
}
