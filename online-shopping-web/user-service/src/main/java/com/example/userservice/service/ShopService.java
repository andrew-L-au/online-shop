package com.example.userservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.userservice.model.shop.CloseShopRequest;
import com.example.userservice.model.shop.OpenShopRequest;
import com.example.userservice.model.shop.RequestStatus;
import com.example.userservice.model.shop.CommodityType;
import com.example.userservice.model.shop.Shop;
import com.example.userservice.model.shop.ShopBasicInfo;
import com.example.userservice.model.shop.ShopStatus;
import com.example.userservice.model.shop.connect.CloseShopRequestToShop;
import com.example.userservice.model.user.ShopOwner;
import com.example.userservice.repository.OpenShopRequestRepository;
import com.example.userservice.repository.ShopRepository;
import com.example.userservice.repository.mapper.shop.CloseShopRequestMapper;
import com.example.userservice.repository.mapper.shop.ShopBasicInfoMapper;
import com.example.userservice.repository.mapper.shop.ShopMapper;
import com.example.userservice.repository.mapper.shop.connect.CloseShopRequestToShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ShopBasicInfoMapper shopBasicInfoMapper;

    @Autowired
    private CloseShopRequestMapper closeShopRequestMapper;

    @Autowired
    private CloseShopRequestToShopMapper closeShopRequestToShopMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Transactional
    public void requestOpenShop(Shop shop, ShopBasicInfo shopBasicInfo, String idCardNumber, List<CommodityType> commodityTypes) throws RuntimeException{
        if (shop == null || shopBasicInfo == null || idCardNumber == null || commodityTypes == null){
            return;
        }
        ShopOwner shopOwner = shopOwnerService.findShopOwner(idCardNumber);
        if (shopOwner == null){
            throw new RuntimeException();
        }
        if (shopBasicInfo.getName() == null){
            return;
        }
        if (shopBasicInfoMapper.selectShopBasicInfo(shopBasicInfo.getName()) != null){
            throw new RuntimeException();
        }
        shop.setShopStatus(ShopStatus.IN_REVIEW);
        Boolean insertReturn;
        insertReturn = shopRepository.insertOneShop(shop, shopBasicInfo, shopOwner, commodityTypes);
        if (!insertReturn){
            throw new RuntimeException();
        }
        OpenShopRequest openShopRequest = new OpenShopRequest();
        openShopRequest.setShop(shop);
        openShopRequest.setRequestStatus(RequestStatus.IN_REVIEW);
        if(openShopRequestRepository.insertOpenShopRequest(openShopRequest, shop).equals(false)){
            throw new RuntimeException();
        }
        return;
    }

    @Transactional
    public Boolean requestCloseShop(Long shopId){
        if (shopId == null){
            return false;
        }
        Shop shop = shopRepository.selectShopWithAllInfo(shopId);
        if (shop == null){
            return false;
        }
        CloseShopRequest closeShopRequest = new CloseShopRequest();
        closeShopRequest.setRequestStatus(RequestStatus.IN_REVIEW);
        closeShopRequestMapper.insert(closeShopRequest);
        if (closeShopRequest.getCloseShopRequestId() == null){
            throw new RuntimeException();
        }
        CloseShopRequestToShop closeShopRequestToShop = new CloseShopRequestToShop(null, closeShopRequest.getCloseShopRequestId(), shopId);
        closeShopRequestToShopMapper.insert(closeShopRequestToShop);
        if (closeShopRequestToShop.getId() == null){
            throw new RuntimeException();
        }
        return true;
    }

    public List<CloseShopRequest> getCloseShopRequests(){
        List<CloseShopRequest> closeShopRequests = closeShopRequestMapper.selectList(new QueryWrapper<>());
        for (CloseShopRequest closeShopRequest : closeShopRequests){
            if (closeShopRequest.getCloseShopRequestId() == null){
                return null;
            }
            Long shopId = closeShopRequestToShopMapper.selectShopByCloseShopRequest(closeShopRequest.getCloseShopRequestId());
            Shop shop = shopRepository.selectShopWithAllInfo(shopId);
            if (shop == null){
                return null;
            }
            closeShopRequest.setShop(shop);
        }
        return closeShopRequests;
    }

    @Transactional
    public Boolean approveCloseShopRequest(Long closeShopRequestId){
        if (closeShopRequestId == null){
            return false;
        }
        CloseShopRequest closeShopRequest = closeShopRequestMapper.selectById(closeShopRequestId);
        if (closeShopRequest == null){
            return false;
        }
        Long shopId = closeShopRequestToShopMapper.selectShopByCloseShopRequest(closeShopRequest.getCloseShopRequestId());
        int num = shopMapper.deleteById(shopId);
        if (num <= 0){
            throw new RuntimeException();
        }
        return true;
    }



    @Transactional
    public String approveOpenShopRequest(String shopName){
        if (shopName == null){
            throw new RuntimeException();
        }
        Shop shop = shopRepository.selectShopWithAllInfo(shopName);
        if (shop == null){
            return null;
        }
        OpenShopRequest openShopRequest = openShopRequestRepository.selectOpenShopRequestWithAllInfoByShop(shop.getShopId());
        if (openShopRequest == null){
            throw new RuntimeException();
        }
        Boolean updateNum = openShopRequestRepository.updateOpenShopRequest(openShopRequest.getOpenShopRequestId(), RequestStatus.APPROVE);
        if (!updateNum){
            throw new RuntimeException();
        }
        updateNum = shopRepository.updateShopStatus(openShopRequest.getShop().getShopId(), ShopStatus.NORMAL);
        if (!updateNum){
            throw new RuntimeException();
        }
        return "success";
    }

    public List<Shop> findAllValidShops(){
        return shopRepository.selectAllShopsOfStatusWithAllInfo(ShopStatus.NORMAL);
    }
}
