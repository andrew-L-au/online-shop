package com.example.userservice.repository.mapper.merchandise.connect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.model.merchandise.connect.RequestRecordMerchandiseToModifyMerchandiseRequest;
import com.example.userservice.model.merchandise.connect.RequestRecordMerchandiseToNewMerchandiseRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RequestRecordMerchandiseToModifyMerchandiseRequestMapper extends BaseMapper<RequestRecordMerchandiseToModifyMerchandiseRequest> {
    default Long selectRequestRecordMerchandiseByModifyMerchandiseRequest(Long modifyMerchandiseRequestId){
        if (modifyMerchandiseRequestId == null){
            return null;
        }
        RequestRecordMerchandiseToModifyMerchandiseRequest requestRecordMerchandiseToModifyMerchandiseRequest = this.selectOne(new QueryWrapper<RequestRecordMerchandiseToModifyMerchandiseRequest>().eq("modify_merchandise_request_id",modifyMerchandiseRequestId));
        if (requestRecordMerchandiseToModifyMerchandiseRequest == null){
            return null;
        }
        return requestRecordMerchandiseToModifyMerchandiseRequest.getRequestRecordMerchandiseId();
    }
}
