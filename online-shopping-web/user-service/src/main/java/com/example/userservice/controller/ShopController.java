package com.example.userservice.controller;

import com.example.userservice.model.DTO.ApproveOpenShopRequestDTO;
import com.example.userservice.model.DTO.RequestOpenShopDTO;
import com.example.userservice.model.shop.OpenShopRequest;
import com.example.userservice.model.shop.CommodityType;
import com.example.userservice.model.shop.Shop;
import com.example.userservice.model.shop.ShopBasicInfo;
import com.example.userservice.service.OpenShopRequestService;
import com.example.userservice.service.ShopOwnerService;
import com.example.userservice.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private OpenShopRequestService openShopRequestService;

    @Autowired
    private ShopOwnerService shopOwnerService;

    @PostMapping("/request-open-shop")
    String requestOpenShop(@RequestBody RequestOpenShopDTO requestOpenShopDTO){
        if (requestOpenShopDTO == null){
            return "format error";
        }
        if (requestOpenShopDTO.getShop() == null){
            return "shop format error";
        }
        if (requestOpenShopDTO.getShop().getShopBasicInfo() == null){
            return "shopBasicInfo format error";
        }
        if (requestOpenShopDTO.getShop().getCommodityTypes() == null){
            return "CommodityTypes format error";
        }
        if (requestOpenShopDTO.getIdCardNumber() == null){
            return "IdCardNumber format error";
        }
        if (shopOwnerService.findShopOwner(requestOpenShopDTO.getIdCardNumber()) == null){
            return "no shop owner";
        }
        Shop shop = requestOpenShopDTO.getShop();
        ShopBasicInfo shopBasicInfo = shop.getShopBasicInfo();
        List<CommodityType> commodityTypes = shop.getCommodityTypes();
        String idCardNumber = requestOpenShopDTO.getIdCardNumber();
        try{
            shopService.requestOpenShop(shop, shopBasicInfo, idCardNumber, commodityTypes);
        }catch (RuntimeException e){
            return "fail";
        }
        return "success";
    }

    @PostMapping("/approve-open-shop-request")
    String approveOpenShopRequest(@RequestBody ApproveOpenShopRequestDTO approveOpenShopRequestDTO){
        try {
            shopService.approveOpenShopRequest(approveOpenShopRequestDTO.getName());
        }catch (RuntimeException e){
            return "fail";
        }
        return "success";
    }

    @GetMapping("/open-shop-requests")
    List<OpenShopRequest> openShopRequests(){
        return openShopRequestService.selectAllOpenShopRequest();
    }

    @GetMapping("/current-shops")
    List<Shop> currentShops(){
        return shopService.findAllValidShops();
    }

}
