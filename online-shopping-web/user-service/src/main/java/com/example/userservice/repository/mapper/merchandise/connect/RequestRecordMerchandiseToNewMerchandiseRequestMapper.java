package com.example.userservice.repository.mapper.merchandise.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.merchandise.connect.RequestRecordMerchandiseToNewMerchandiseRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RequestRecordMerchandiseToNewMerchandiseRequestMapper extends BaseMapper<RequestRecordMerchandiseToNewMerchandiseRequest> {
    default Long selectRequestRecordMerchandiseByNewMerchandiseRequest(Long newMerchandiseRequestId){
        if (newMerchandiseRequestId == null){
            return null;
        }
        RequestRecordMerchandiseToNewMerchandiseRequest requestRecordMerchandiseToNewMerchandiseRequest = this.selectOne(new QueryWrapper<RequestRecordMerchandiseToNewMerchandiseRequest>().eq("new_merchandise_request_id",newMerchandiseRequestId));
        if (requestRecordMerchandiseToNewMerchandiseRequest == null){
            return null;
        }
        return requestRecordMerchandiseToNewMerchandiseRequest.getRequestRecordMerchandiseId();
    }
}
