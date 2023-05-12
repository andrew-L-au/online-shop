package com.example.userservice.controller;

import com.example.userservice.controller.merchandiseDTO.*;
import com.example.userservice.model.merchandise.Merchandise;
import com.example.userservice.model.merchandise.ModifyMerchandiseRequest;
import com.example.userservice.model.merchandise.NewMerchandiseRequest;
import com.example.userservice.model.merchandise.RequestRecordMerchandise;
import com.example.userservice.model.shop.Shop;
import com.example.userservice.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchandise")
public class MerchandiseController {

    @Autowired
    private MerchandiseService merchandiseService;

    @PostMapping(path = "/request-new-merchandise")
    public Boolean requestNewMerchandise(@RequestBody RequestNewMerchandiseDTO requestNewMerchandiseDTO){
        if (requestNewMerchandiseDTO == null){
            return null;
        }
        RequestRecordMerchandise requestRecordMerchandise = requestNewMerchandiseDTO.getRequestRecordMerchandise();
        String shopId = requestNewMerchandiseDTO.getShopId();
        if (requestRecordMerchandise == null || shopId == null){
            return false;
        }
        try {
            return merchandiseService.requestNewMerchandise(requestRecordMerchandise, shopId);
        }catch (RuntimeException e){
            return false;
        }
    }

    @PostMapping(path = "/request-modify-merchandise")
    public Boolean requestModifyMerchandise(@RequestBody RequestModifyMerchandiseDTO requestModifyMerchandiseDTO){
        if (requestModifyMerchandiseDTO == null){
            return null;
        }
        String merchandiseId = requestModifyMerchandiseDTO.getMerchandiseId();
        RequestRecordMerchandise requestRecordMerchandise = requestModifyMerchandiseDTO.getRequestRecordMerchandise();
        String shopId = requestModifyMerchandiseDTO.getShopId();
        if (merchandiseId == null || requestRecordMerchandise == null || shopId == null){
            return false;
        }
        try {
            return merchandiseService.requestMerchandiseModify(merchandiseId,requestRecordMerchandise, shopId);
        }catch (RuntimeException e){
            return false;
        }
    }
    @PostMapping(path = "/remove-merchandise")
    public Boolean removeMerchandise(@RequestBody RemoveMerchandiseDTO removeMerchandiseDTO){
        if (removeMerchandiseDTO == null || removeMerchandiseDTO.getMerchandiseId() == null){
            return false;
        }
        String merchandiseId = removeMerchandiseDTO.getMerchandiseId();
        try {
            return merchandiseService.removeMerchandise(merchandiseId);
        }catch (RuntimeException e){
            return false;
        }
    }

    @GetMapping("/all-new-merchandise-requests")
    public List<NewMerchandiseRequest> allNewMerchandiseRequestWithRequestRecordMerchandise(){
        try {
            return merchandiseService.findNewMerchandiseRequestWithRequestRecordMerchandise();
        }catch (RuntimeException e){
            return null;
        }
    }

    @PostMapping("/new-merchandise-requests-of-shop")
    public List<NewMerchandiseRequest> newMerchandiseRequestWithRequestRecordMerchandiseOfShop(String shopId){
        try {
            return merchandiseService.findNewMerchandiseRequestWithRequestRecordMerchandiseOfShop(shopId);
        }catch (RuntimeException e){
            return null;
        }
    }

    @GetMapping("/all-modify-merchandise-requests")
    public List<ModifyMerchandiseRequest> allModifyMerchandiseRequestWithRequestRecordMerchandise(){
        try {
            return merchandiseService.findModifyMerchandiseRequestWithRequestRecordMerchandise();
        }catch (RuntimeException e){
            return null;
        }
    }

    @PostMapping("/modify-merchandise-requests-of-shop")
    public List<ModifyMerchandiseRequest> modifyMerchandiseRequestWithRequestRecordMerchandiseOfShop(@RequestBody String shopId){
        try {
            return merchandiseService.findModifyMerchandiseRequestWithRequestRecordMerchandiseOfShop(shopId);
        }catch (RuntimeException e){
            return null;
        }
    }

    @PostMapping("/approve-new-merchandise-request")
    public Boolean approveNewMerchandiseRequest(@RequestBody ApproveNewMerchandiseRequestDTO approveNewMerchandiseRequestDTO){
        if (approveNewMerchandiseRequestDTO == null || approveNewMerchandiseRequestDTO.getNewMerchandiseRequestId() == null){
            return false;
        }
        String newMerchandiseRequestId = approveNewMerchandiseRequestDTO.getNewMerchandiseRequestId();
        try {
            return merchandiseService.approveNewMerchandiseRequest(newMerchandiseRequestId);
        }catch (RuntimeException e){
            return false;
        }
    }

    @PostMapping("/reject-new-merchandise-request")
    public Boolean rejectNewMerchandiseRequest(@RequestBody RejectNewMerchandiseRequestDTO rejectNewMerchandiseRequestDTO){
        if (rejectNewMerchandiseRequestDTO == null || rejectNewMerchandiseRequestDTO.getNewMerchandiseRequestId() == null){
            return false;
        }
        String newMerchandiseRequestId = rejectNewMerchandiseRequestDTO.getNewMerchandiseRequestId();
        try {
            return merchandiseService.rejectNewMerchandiseRequest(newMerchandiseRequestId);
        }catch (RuntimeException e){
            return false;
        }
    }

    @PostMapping("/approve-modify-merchandise-request")
    public Boolean approveModifyMerchandiseRequest(@RequestBody ApproveModifyMerchandiseRequestDTO approveModifyMerchandiseRequestDTO){
        if (approveModifyMerchandiseRequestDTO == null || approveModifyMerchandiseRequestDTO.getModifyMerchandiseRequestId() == null){
            return false;
        }
        String modifyMerchandiseRequestId = approveModifyMerchandiseRequestDTO.getModifyMerchandiseRequestId();
        try {
            return merchandiseService.approveModifyMerchandiseRequest(modifyMerchandiseRequestId);
        }catch (RuntimeException e){
            return false;
        }
    }

    @PostMapping("/reject-modify-merchandise-request")
    public Boolean rejectModifyMerchandiseRequest(@RequestBody RejectModifyMerchandiseRequestDTO requestModifyMerchandiseDTO){
        if (requestModifyMerchandiseDTO == null ||requestModifyMerchandiseDTO.getModifyMerchandiseRequestId() == null){
            return false;
        }
        String modifyMerchandiseRequestId = requestModifyMerchandiseDTO.getModifyMerchandiseRequestId();
        try {
            return merchandiseService.rejectModifyMerchandiseRequest(modifyMerchandiseRequestId);
        }catch (RuntimeException e){
            return false;
        }
    }

    @PostMapping("/merchandises-of-shop")
    public List<Merchandise> merchandisesOfShop(@RequestBody Shop shop){
        if (shop == null || shop.getShopId() == null){
            return null;
        }
        String shopId = shop.getShopId();
        try{
            return merchandiseService.merchandisesOfShop(shopId);
        }catch (RuntimeException e){
            return null;
        }
    }
}
