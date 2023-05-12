package com.example.userservice.controller;

import com.example.userservice.controller.shopDTO.RequestCloseShopDTO;
import com.example.userservice.controller.shopDTO.ShopOfUserDTO;
import com.example.userservice.model.DTO.ApproveOpenShopRequestDTO;
import com.example.userservice.model.DTO.RequestOpenShopDTO;
import com.example.userservice.model.shop.*;
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
    public String approveOpenShopRequest(@RequestBody ApproveOpenShopRequestDTO approveOpenShopRequestDTO){
        try {
            shopService.approveOpenShopRequest(approveOpenShopRequestDTO.getShopId());
        }catch (RuntimeException e){
            return "fail";
        }
        return "success";
    }

    @GetMapping("/open-shop-requests")
    public List<OpenShopRequest> openShopRequests(){
        return openShopRequestService.selectAllOpenShopRequest();
    }

    @GetMapping("/current-shops")
    public List<Shop> currentShops(){
        return shopService.findAllValidShops();
    }

    @PostMapping("/request-close-shop")
    public Boolean requestCloseShop(@RequestBody RequestCloseShopDTO requestCloseShopDTO){
        if (requestCloseShopDTO == null || requestCloseShopDTO.getShopId() == null){
            return false;
        }
        String shopId = requestCloseShopDTO.getShopId();
        try {
            return shopService.requestCloseShop(shopId);
        }catch (RuntimeException e){
            return false;
        }
    }

    @GetMapping("/get-close-shop-requests")
    public List<CloseShopRequest> getCloseShopRequests(){
        try {
            return shopService.getCloseShopRequests();
        }catch (RuntimeException e){
            return null;
        }

    }

    @PostMapping("approve-close-shop-request")
    public Boolean approveCloseShopRequest(@RequestBody CloseShopRequest closeShopRequest){
        if (closeShopRequest == null ||closeShopRequest.getCloseShopRequestId() == null){
            return false;
        }
        try {
            return shopService.approveCloseShopRequest(closeShopRequest.getCloseShopRequestId());
        }catch (RuntimeException e){
            return false;
        }
    }

    @PostMapping("/shop-of-user")
    public Shop shopOfUser(@RequestBody ShopOfUserDTO shopOfUserDTO){
        String userId = shopOfUserDTO.getUserId();
        if (userId == null){
            return null;
        }
        try {
            return shopService.shopWithBasicInfoAndCommodityTypesOfUser(userId);
        }catch (RuntimeException e){
            return null;
        }
    }

}
