package com.example.shopservice.service;

import com.example.shopservice.repository.OpenShopRequestRepository;
import com.example.shopservice.repository.ShopRepository;
import com.example.shopservice.service.feign.ShopOwnerService;
import model.OpenShopRequest;
import model.RequestStatus;
import model.shop.CommodityType;
import model.shop.Shop;
import model.shop.ShopBasicInfo;
import model.shop.ShopStatus;
import model.user.ShopOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopService {
    @Autowired
    ShopOwnerService shopOwnerService;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    OpenShopRequestRepository openShopRequestRepository;

    public void requestOpenShop(Shop shop, ShopBasicInfo shopBasicInfo, String idCardNumber, List<CommodityType> commodityTypes){
        ShopOwner shopOwner = shopOwnerService.findShopOwner(idCardNumber);
        requestOpenShop(shop, shopBasicInfo, shopOwner, commodityTypes);
    }

    @NonNull
    public void requestOpenShop(Shop shop, ShopBasicInfo shopBasicInfo, ShopOwner shopOwner, List<CommodityType> commodityTypes){
        shop.setShopStatus(ShopStatus.IN_REVIEW);
        addOneShop(shop, shopBasicInfo, shopOwner, commodityTypes);
        OpenShopRequest openShopRequest = new OpenShopRequest();
        openShopRequest.setShop(shop);
        openShopRequest.setRequestStatus(RequestStatus.IN_REVIEW);
        addOneOpenShopRequest(openShopRequest, shop);
    }

    private void addOneShop(Shop shop, ShopBasicInfo shopBasicInfo, ShopOwner shopOwner, List<CommodityType> commodityTypes){
        shopRepository.insertOneShop(shop, shopBasicInfo, shopOwner, commodityTypes);
    }

    public void addOneOpenShopRequest(OpenShopRequest openShopRequest,Shop shop){
        openShopRequestRepository.insertOpenShopRequest(openShopRequest, shop);
    }

    public void approveOpenShopRequest(OpenShopRequest openShopRequest){
        openShopRequestRepository.updateOpenShopRequest(openShopRequest.getOpenShopRequestId(), RequestStatus.APPROVE);
        shopRepository.updateShopStatus(openShopRequest.getShop().getShopId(), ShopStatus.NORMAL);
    }

    public List<Shop> findAllValidShops(){
        return shopRepository.selectAllShopsOfStatusWithAllInfo(ShopStatus.NORMAL);
    }
}
