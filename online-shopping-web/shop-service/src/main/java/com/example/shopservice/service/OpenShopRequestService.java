package com.example.shopservice.service;

import com.example.shopservice.repository.OpenShopRequestRepository;
import com.example.shopservice.repository.ShopRepository;
import com.example.shopservice.repository.mapper.OpenShopRequestMapper;
import com.example.shopservice.repository.mapper.OpenShopRequestToShopMapper;
import model.OpenShopRequest;
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

    public List<OpenShopRequest> findAllOpenShopRequest(){
        return openShopRequestRepository.selectAllOpenShopRequestWithAllInfo();
    }
}
