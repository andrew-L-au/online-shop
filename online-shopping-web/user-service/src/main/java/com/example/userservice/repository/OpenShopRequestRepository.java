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

    public Boolean insertOpenShopRequest(OpenShopRequest openShopRequest, Shop shop){
        openShopRequest.setOpenShopRequestId(null);
        openShopRequestMapper.insert(openShopRequest);
        if (openShopRequest.getOpenShopRequestId() == null){
            return false;
        }
        OpenShopRequestToShop openShopRequestToShop = new OpenShopRequestToShop(null , openShopRequest.getOpenShopRequestId(), shop.getShopId());
        openShopRequestToShopMapper.insert(openShopRequestToShop);
        if (openShopRequestToShop.getOpenShopRequestId() == null){
            return false;
        }
        return true;
    }

    public Boolean updateOpenShopRequest(Long openShopRequestId, RequestStatus requestStatus){
        int num = openShopRequestMapper.updateById(new OpenShopRequest(openShopRequestId, null, requestStatus));
        if (num <= 0){
            return false;
        }
        return true;
    }

    public List<Long> selectAllIds(){
        List<OpenShopRequest> openShopRequests = this.openShopRequestMapper.selectList(new QueryWrapper<>());
        List<Long> openShopRequestIds = new ArrayList<>();
        openShopRequests.forEach(e -> { openShopRequestIds.add(e.getOpenShopRequestId()); });
        return openShopRequestIds;
    }

    public OpenShopRequest selectOpenShopRequestWithAllInfo(Long openShopRequestId){
        if (openShopRequestId == null){
            return null;
        }
        OpenShopRequest openShopRequest = openShopRequestMapper.selectById(openShopRequestId);
        if (openShopRequest == null){
            return null;
        }
        Long shopId = openShopRequestToShopMapper.selectShopByOpenShopRequest(openShopRequestId);
        if (shopId == null){
            return null;
        }
        Shop shop = shopRepository.selectShopWithAllInfo(shopId);
        if (shop == null){
            return null;
        }
        openShopRequest.setShop(shop);
        return openShopRequest;
    }

    public OpenShopRequest selectOpenShopRequestWithAllInfoByShop(Long shopId){
        if (shopId == null){
            return null;
        }
        Long openShopRequestId = openShopRequestToShopMapper.selectOpenShopRequestByShop(shopId);
        if (openShopRequestId == null){
            return null;
        }
        return selectOpenShopRequestWithAllInfo(openShopRequestId);
    }

    public List<OpenShopRequest> selectAllOpenShopRequestWithAllInfo(){
        List<OpenShopRequest> openShopRequests = new ArrayList<>();
        List<Long> openShopRequestIds = selectAllIds();
        openShopRequestIds.forEach( e -> openShopRequests.add(selectOpenShopRequestWithAllInfo(e)) );
        return openShopRequests;
    }

}
