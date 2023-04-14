package com.example.userservice.service;

import com.example.userservice.model.shop.OpenShopRequest;
import com.example.userservice.repository.OpenShopRequestRepository;
import com.example.userservice.repository.ShopRepository;
import com.example.userservice.repository.mapper.shop.connect.OpenShopRequestToShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenShopRequestService {

    @Autowired
    OpenShopRequestRepository openShopRequestRepository;

    @Autowired
    OpenShopRequestToShopMapper openShopRequestToShopMapper;

    @Autowired
    ShopRepository shopRepository;

    public List<OpenShopRequest> selectAllOpenShopRequest(){
        return openShopRequestRepository.selectAllOpenShopRequestWithAllInfo();
    }
}
