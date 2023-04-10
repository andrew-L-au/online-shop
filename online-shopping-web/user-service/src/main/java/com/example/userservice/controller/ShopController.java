package com.example.userservice.controller;

import com.example.userservice.model.OpenShopRequest;
import com.example.userservice.model.shop.CommodityType;
import com.example.userservice.model.shop.Shop;
import com.example.userservice.model.shop.ShopBasicInfo;
import com.example.userservice.service.OpenShopRequestService;
import com.example.userservice.service.ShopService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private OpenShopRequestService openShopRequestService;

    @PostMapping("/request-open-shop")
    @Transactional
    String requestOpenShop(@RequestBody JsonNode jsonNode){
        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop = objectMapper.convertValue(jsonNode.get("shop"),Shop.class);
        if (shop == null || shop.getShopBasicInfo() == null || shop.getCommodityTypes() == null){
            return "format error";
        }
        ShopBasicInfo shopBasicInfo = shop.getShopBasicInfo();
        List<CommodityType> commodityTypes = shop.getCommodityTypes();
        String idCardNumber = objectMapper.convertValue(jsonNode.get("idCardNumber"), String.class);
        if (idCardNumber == null){
            return "format error";
        }
        shopService.requestOpenShop(shop, shopBasicInfo, idCardNumber, commodityTypes);
        return "success";
    }

    @PostMapping("/approve-open-shop-request")
    @Transactional
    String approveOpenShopRequest(@RequestBody OpenShopRequest openShopRequest){
        return shopService.approveOpenShopRequest(openShopRequest);
    }

    @GetMapping("/open-shop-requests")
    List<OpenShopRequest> openShopRequests(){
        return openShopRequestService.findAllOpenShopRequest();
    }

    @GetMapping("/current-shops")
    List<Shop> currentShops(){
        return shopService.findAllValidShops();
    }

}
