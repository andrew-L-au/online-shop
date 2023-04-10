package com.example.userservice.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.userservice.model.OpenShopRequest;
import com.example.userservice.model.RequestStatus;
import com.example.userservice.model.connect.OpenShopRequestToShop;
import com.example.userservice.model.shop.Shop;
import com.example.userservice.repository.mapper.OpenShopRequestMapper;
import com.example.userservice.repository.mapper.OpenShopRequestToShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OpenShopRequestRepository {

    @Autowired
    private OpenShopRequestMapper openShopRequestMapper;

    @Autowired
    private OpenShopRequestToShopMapper openShopRequestToShopMapper;

    @Autowired
    private ShopRepository shopRepository;

    public void insertOpenShopRequest(OpenShopRequest openShopRequest, Shop shop){
        openShopRequestMapper.insert(openShopRequest);
        openShopRequestToShopMapper.insert(new OpenShopRequestToShop(null , openShopRequest.getOpenShopRequestId(), shop.getShopId()));
    }

    public void updateOpenShopRequest(Long openShopRequestId, RequestStatus requestStatus){
        openShopRequestMapper.updateById(new OpenShopRequest(openShopRequestId, null, requestStatus));
    }

    public List<Long> selectAllIds(){
        List<OpenShopRequest> openShopRequests = this.openShopRequestMapper.selectList(new QueryWrapper<>());
        List<Long> openShopRequestIds = new ArrayList<>();
        openShopRequests.forEach(e -> { openShopRequestIds.add(e.getOpenShopRequestId()); });
        return openShopRequestIds;
    }

    public OpenShopRequest selectOpenShopRequestWithAllInfo(Long openShopRequestId){
        OpenShopRequest openShopRequest = openShopRequestMapper.selectById(openShopRequestId);
        Long shopId = openShopRequestToShopMapper.selectShopByOpenShopRequest(openShopRequestId);
        Shop shop = shopRepository.selectShopWithAllInfo(shopId);
        openShopRequest.setShop(shop);
        return openShopRequest;
    }

    public List<OpenShopRequest> selectAllOpenShopRequestWithAllInfo(){
        List<OpenShopRequest> openShopRequests = new ArrayList<>();
        List<Long> openShopRequestIds = selectAllIds();
        openShopRequestIds.forEach( e -> openShopRequests.add(selectOpenShopRequestWithAllInfo(e)) );
        return openShopRequests;
    }
}
