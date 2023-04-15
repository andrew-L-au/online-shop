package com.example.userservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.userservice.model.merchandise.ModifyMerchandiseRequest;
import com.example.userservice.model.merchandise.connect.ModifyMerchandiseRequestToMerchandise;
import com.example.userservice.model.merchandise.connect.RequestRecordMerchandiseToModifyMerchandiseRequest;
import com.example.userservice.model.shop.RequestStatus;
import com.example.userservice.model.merchandise.Merchandise;
import com.example.userservice.model.merchandise.RequestRecordMerchandise;
import com.example.userservice.model.merchandise.NewMerchandiseRequest;
import com.example.userservice.model.merchandise.connect.RequestRecordMerchandiseToNewMerchandiseRequest;
import com.example.userservice.model.shop.connect.ShopToMerchandise;
import com.example.userservice.model.shop.connect.ShopToModifyMerchandiseRequest;
import com.example.userservice.model.shop.connect.ShopToNewMerchandiseRequest;
import com.example.userservice.repository.mapper.merchandise.*;
import com.example.userservice.repository.mapper.merchandise.connect.ModifyMerchandiseRequestToMerchandiseMapper;
import com.example.userservice.repository.mapper.merchandise.connect.RequestRecordMerchandiseToModifyMerchandiseRequestMapper;
import com.example.userservice.repository.mapper.merchandise.connect.RequestRecordMerchandiseToNewMerchandiseRequestMapper;
import com.example.userservice.repository.mapper.shop.connect.ShopToMerchandiseMapper;
import com.example.userservice.repository.mapper.shop.connect.ShopToModifyMerchandiseRequestMapper;
import com.example.userservice.repository.mapper.shop.connect.ShopToNewMerchandiseRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchandiseService {

    @Autowired
    private RequestRecordMerchandiseMapper requestRecordMerchandiseMapper;

    @Autowired
    private NewMerchandiseRequestMapper newMerchandiseRequestMapper;

    @Autowired
    private ModifyMerchandiseRequestMapper modifyMerchandiseRequestMapper;

    @Autowired
    private RequestRecordMerchandiseToNewMerchandiseRequestMapper requestRecordMerchandiseToNewMerchandiseRequestMapper;

    @Autowired
    private RequestRecordMerchandiseToModifyMerchandiseRequestMapper requestRecordMerchandiseToModifyMerchandiseRequestMapper;

    @Autowired
    private ModifyMerchandiseRequestToMerchandiseMapper modifyMerchandiseRequestToMerchandiseMapper;

    @Autowired
    private MerchandiseMapper merchandiseMapper;

    @Autowired
    private ShopToMerchandiseMapper shopToMerchandiseMapper;

    @Autowired
    private ShopToNewMerchandiseRequestMapper shopToNewMerchandiseRequestMapper;

    @Autowired
    private ShopToModifyMerchandiseRequestMapper shopToModifyMerchandiseRequestMapper;

    @Transactional
    public Boolean requestNewMerchandise(RequestRecordMerchandise requestRecordMerchandise, Long shopId){
        if (requestRecordMerchandise == null || requestRecordMerchandise.getMerchandiseName() == null || requestRecordMerchandise.getPrice() == null){
            return false;
        }
        requestRecordMerchandiseMapper.insert(requestRecordMerchandise);
        if (requestRecordMerchandise.getRequestRecordMerchandiseId() == null){
            throw new RuntimeException();
        }
        NewMerchandiseRequest newMerchandiseRequest = new NewMerchandiseRequest();
        newMerchandiseRequest.setRequestStatus(RequestStatus.IN_REVIEW);
        newMerchandiseRequestMapper.insert(newMerchandiseRequest);
        if (newMerchandiseRequest.getNewMerchandiseRequestId() == null){
            throw new RuntimeException();
        }
        ShopToNewMerchandiseRequest shopToNewMerchandiseRequest = new ShopToNewMerchandiseRequest();
        shopToNewMerchandiseRequest.setShopId(shopId);
        shopToNewMerchandiseRequest.setNewMerchandiseRequestId(newMerchandiseRequest.getNewMerchandiseRequestId());
        shopToNewMerchandiseRequestMapper.insert(shopToNewMerchandiseRequest);
        if (shopToNewMerchandiseRequest.getId() == null){
            throw new RuntimeException();
        }
        RequestRecordMerchandiseToNewMerchandiseRequest requestRecordMerchandiseToNewMerchandiseRequest = new RequestRecordMerchandiseToNewMerchandiseRequest();
        requestRecordMerchandiseToNewMerchandiseRequest.setRequestRecordMerchandiseId(requestRecordMerchandise.getRequestRecordMerchandiseId());
        requestRecordMerchandiseToNewMerchandiseRequest.setNewMerchandiseRequestId(newMerchandiseRequest.getNewMerchandiseRequestId());
        requestRecordMerchandiseToNewMerchandiseRequestMapper.insert(requestRecordMerchandiseToNewMerchandiseRequest);
        if (requestRecordMerchandiseToNewMerchandiseRequest.getId() == null){
            throw new RuntimeException();
        }
        return true;
    }

    @Transactional
    public Boolean requestMerchandiseModify(Long merchandiseId, RequestRecordMerchandise requestRecordMerchandise, Long shopId){
        if (requestRecordMerchandise == null || requestRecordMerchandise.getMerchandiseName() == null || requestRecordMerchandise.getPrice() == null){
            return false;
        }
        requestRecordMerchandiseMapper.insert(requestRecordMerchandise);
        if (requestRecordMerchandise.getRequestRecordMerchandiseId() == null){
            throw new RuntimeException();
        }
        ModifyMerchandiseRequest modifyMerchandiseRequest = new ModifyMerchandiseRequest();
        modifyMerchandiseRequest.setRequestStatus(RequestStatus.IN_REVIEW);
        modifyMerchandiseRequestMapper.insert(modifyMerchandiseRequest);
        if (modifyMerchandiseRequest.getModifyMerchandiseRequestId() == null){
            throw new RuntimeException();
        }
        ShopToModifyMerchandiseRequest shopToModifyMerchandiseRequest = new ShopToModifyMerchandiseRequest();
        shopToModifyMerchandiseRequest.setShopId(shopId);
        shopToModifyMerchandiseRequest.setModifyMerchandiseRequestId(modifyMerchandiseRequest.getModifyMerchandiseRequestId());
        shopToModifyMerchandiseRequestMapper.insert(shopToModifyMerchandiseRequest);
        if (shopToModifyMerchandiseRequest.getId() == null){
            throw new RuntimeException();
        }
        RequestRecordMerchandiseToModifyMerchandiseRequest requestRecordMerchandiseToModifyMerchandiseRequest = new RequestRecordMerchandiseToModifyMerchandiseRequest();
        requestRecordMerchandiseToModifyMerchandiseRequest.setRequestRecordMerchandiseId(requestRecordMerchandise.getRequestRecordMerchandiseId());
        requestRecordMerchandiseToModifyMerchandiseRequest.setModifyMerchandiseRequestId(modifyMerchandiseRequest.getModifyMerchandiseRequestId());
        requestRecordMerchandiseToModifyMerchandiseRequestMapper.insert(requestRecordMerchandiseToModifyMerchandiseRequest);
        if (requestRecordMerchandiseToModifyMerchandiseRequest.getId() == null){
            throw new RuntimeException();
        }
        Merchandise merchandise = merchandiseMapper.selectById(merchandiseId);
        if (merchandise == null){
            throw new RuntimeException();
        }
        ModifyMerchandiseRequestToMerchandise modifyMerchandiseRequestToMerchandise = new ModifyMerchandiseRequestToMerchandise();
        modifyMerchandiseRequestToMerchandise.setModifyMerchandiseRequestId(modifyMerchandiseRequest.getModifyMerchandiseRequestId());
        modifyMerchandiseRequestToMerchandise.setMerchandiseId(merchandise.getMerchandiseId());
        modifyMerchandiseRequestToMerchandiseMapper.insert(modifyMerchandiseRequestToMerchandise);
        if (modifyMerchandiseRequestToMerchandise.getId() == null){
            throw new RuntimeException();
        }
        return true;
    }

    @Transactional
    public Boolean approveNewMerchandiseRequest(Long newMerchandiseRequestId){
        if (newMerchandiseRequestId == null){
            return false;
        }
        NewMerchandiseRequest newMerchandiseRequest = newMerchandiseRequestMapper.selectById(newMerchandiseRequestId);
        if (newMerchandiseRequest == null){
            return false;
        }
        if (!newMerchandiseRequest.getRequestStatus().equals(RequestStatus.IN_REVIEW)){
            return false;
        }
        newMerchandiseRequest.setRequestStatus(RequestStatus.APPROVE);
        int rowNum = newMerchandiseRequestMapper.updateById(newMerchandiseRequest);
        if (rowNum <= 0){
            throw new RuntimeException();
        }
        Long shopId = shopToNewMerchandiseRequestMapper.selectShopByNewMerchandiseRequest(newMerchandiseRequest.getNewMerchandiseRequestId());
        if (shopId == null){
            throw new RuntimeException();
        }
        Long newMerchandiseId = requestRecordMerchandiseToNewMerchandiseRequestMapper.selectRequestRecordMerchandiseByNewMerchandiseRequest(newMerchandiseRequest.getNewMerchandiseRequestId());
        if (newMerchandiseId == null){
            throw new RuntimeException();
        }
        RequestRecordMerchandise requestRecordMerchandise = requestRecordMerchandiseMapper.selectById(newMerchandiseId);
        if (requestRecordMerchandise == null){
            throw new RuntimeException();
        }
        Merchandise merchandise = new Merchandise();
        merchandise.setMerchandiseName(requestRecordMerchandise.getMerchandiseName());
        merchandise.setDescription(requestRecordMerchandise.getDescription());
        merchandise.setPrice(requestRecordMerchandise.getPrice());
        merchandise.setImages(requestRecordMerchandise.getImages());
        merchandiseMapper.insert(merchandise);
        if (merchandise.getMerchandiseId() == null){
            throw new RuntimeException();
        }
        ShopToMerchandise shopToMerchandise = new ShopToMerchandise();
        shopToMerchandise.setShopId(shopId);
        shopToMerchandise.setMerchandiseId(merchandise.getMerchandiseId());
        shopToMerchandiseMapper.insert(shopToMerchandise);
        if (shopToMerchandise.getId() == null){
            throw new RuntimeException();
        }
        return true;
    }

    @Transactional
    public Boolean approveModifyMerchandiseRequest(Long modifyMerchandiseRequestId){
        if (modifyMerchandiseRequestId == null){
            return false;
        }
        ModifyMerchandiseRequest modifyMerchandiseRequest = modifyMerchandiseRequestMapper.selectById(modifyMerchandiseRequestId);
        if (modifyMerchandiseRequest == null){
            return false;
        }
        if (!modifyMerchandiseRequest.getRequestStatus().equals(RequestStatus.IN_REVIEW)){
            return false;
        }
        modifyMerchandiseRequest.setRequestStatus(RequestStatus.APPROVE);
        int rowNum = modifyMerchandiseRequestMapper.updateById(modifyMerchandiseRequest);
        if (rowNum <= 0){
            throw new RuntimeException();
        }
        Long shopId = shopToModifyMerchandiseRequestMapper.selectShopByModifyMerchandiseRequest(modifyMerchandiseRequest.getModifyMerchandiseRequestId());
        if (shopId == null){
            throw new RuntimeException();
        }
        Long requestRecordMerchandiseId = requestRecordMerchandiseToModifyMerchandiseRequestMapper.selectRequestRecordMerchandiseByModifyMerchandiseRequest(modifyMerchandiseRequest.getModifyMerchandiseRequestId());
        if (requestRecordMerchandiseId == null){
            throw new RuntimeException();
        }
        RequestRecordMerchandise requestRecordMerchandise = requestRecordMerchandiseMapper.selectById(requestRecordMerchandiseId);
        if (requestRecordMerchandise == null){
            throw new RuntimeException();
        }
        Merchandise merchandise = new Merchandise();
        merchandise.setMerchandiseName(requestRecordMerchandise.getMerchandiseName());
        merchandise.setDescription(requestRecordMerchandise.getDescription());
        merchandise.setPrice(requestRecordMerchandise.getPrice());
        merchandise.setImages(requestRecordMerchandise.getImages());
        int row = merchandiseMapper.updateById(merchandise);
        if (row <= 0){
            throw new RuntimeException();
        }
        return true;
    }

    @Transactional
    public Boolean rejectNewMerchandiseRequest(Long newMerchandiseRequestId){
        if (newMerchandiseRequestId == null){
            return false;
        }
        NewMerchandiseRequest newMerchandiseRequest = newMerchandiseRequestMapper.selectById(newMerchandiseRequestId);
        if (newMerchandiseRequest == null){
            return false;
        }
        if (!newMerchandiseRequest.getRequestStatus().equals(RequestStatus.IN_REVIEW)){
            return false;
        }
        newMerchandiseRequest.setRequestStatus(RequestStatus.REJECT);
        int rowNum = newMerchandiseRequestMapper.updateById(newMerchandiseRequest);
        if (rowNum <= 0){
            throw new RuntimeException();
        }
        return true;
    }

    @Transactional
    public Boolean rejectModifyMerchandiseRequest(Long modifyMerchandiseRequestId){
        if (modifyMerchandiseRequestId == null){
            return false;
        }
        ModifyMerchandiseRequest modifyMerchandiseRequest = modifyMerchandiseRequestMapper.selectById(modifyMerchandiseRequestId);
        if (modifyMerchandiseRequest == null){
            return false;
        }
        if (!modifyMerchandiseRequest.getRequestStatus().equals(RequestStatus.IN_REVIEW)){
            return false;
        }
        modifyMerchandiseRequest.setRequestStatus(RequestStatus.REJECT);
        int rowNum = modifyMerchandiseRequestMapper.updateById(modifyMerchandiseRequest);
        if (rowNum <= 0){
            throw new RuntimeException();
        }
        return true;
    }

    @Transactional
    public Boolean removeMerchandise(Long merchandiseId){
        if (merchandiseId == null){
            return false;
        }
        Merchandise merchandise = merchandiseMapper.selectById(merchandiseId);
        if (merchandise == null){
            return false;
        }
        int rowNum = merchandiseMapper.deleteById(merchandise.getMerchandiseId());
        if (rowNum <= 0){
            throw new RuntimeException();
        }
        List<Long> ids = modifyMerchandiseRequestToMerchandiseMapper.selectIdsByMerchandise(merchandise.getMerchandiseId());
        if (ids == null){
            throw new RuntimeException();
        }
        rowNum = modifyMerchandiseRequestToMerchandiseMapper.deleteBatchIds(ids);
        if (rowNum <= 0){
            throw new RuntimeException();
        }
        Long id = shopToMerchandiseMapper.selectIdByMerchandise(merchandise.getMerchandiseId());
        if (id == null){
            throw new RuntimeException();
        }
        rowNum = shopToMerchandiseMapper.deleteById(id);
        if (rowNum <= 0){
            throw new RuntimeException();
        }
        return true;
    }

    @Transactional
    public List<NewMerchandiseRequest> findNewMerchandiseRequestWithRequestRecordMerchandise(){
        List<NewMerchandiseRequest> newMerchandiseRequests = newMerchandiseRequestMapper.allNewMerchandiseRequests();
        if (newMerchandiseRequests == null){
            return null;
        }
        for (NewMerchandiseRequest newMerchandiseRequest : newMerchandiseRequests){
            Long newMerchandiseRequestId = newMerchandiseRequest.getNewMerchandiseRequestId();
            Long requestRecordMerchandiseId = requestRecordMerchandiseToNewMerchandiseRequestMapper.selectRequestRecordMerchandiseByNewMerchandiseRequest(newMerchandiseRequestId);
            RequestRecordMerchandise requestRecordMerchandise = requestRecordMerchandiseMapper.selectById(requestRecordMerchandiseId);
            newMerchandiseRequest.setRequestRecordMerchandise(requestRecordMerchandise);
        }
        return newMerchandiseRequests;
    }

    @Transactional
    public List<NewMerchandiseRequest> findNewMerchandiseRequestWithRequestRecordMerchandiseOfShop(Long shopId){
        if (shopId == null){
            return null;
        }
        List<Long> newMerchandiseRequestIds = shopToNewMerchandiseRequestMapper.selectNewMerchandiseRequestsByShop(shopId);
        if (newMerchandiseRequestIds == null){
            return null;
        }
        List<NewMerchandiseRequest> newMerchandiseRequests = newMerchandiseRequestMapper.selectBatchIds(newMerchandiseRequestIds);
        if (newMerchandiseRequests == null){
            return null;
        }
        for (NewMerchandiseRequest newMerchandiseRequest : newMerchandiseRequests){
            Long newMerchandiseRequestId = newMerchandiseRequest.getNewMerchandiseRequestId();
            Long requestRecordMerchandiseId = requestRecordMerchandiseToNewMerchandiseRequestMapper.selectRequestRecordMerchandiseByNewMerchandiseRequest(newMerchandiseRequestId);
            RequestRecordMerchandise requestRecordMerchandise = requestRecordMerchandiseMapper.selectById(requestRecordMerchandiseId);
            newMerchandiseRequest.setRequestRecordMerchandise(requestRecordMerchandise);
        }
        return newMerchandiseRequests;
    }

    @Transactional
    public List<ModifyMerchandiseRequest> findModifyMerchandiseRequestWithRequestRecordMerchandise(){
        List<ModifyMerchandiseRequest> modifyMerchandiseRequests = modifyMerchandiseRequestMapper.allModifyMerchandiseRequests();
        if (modifyMerchandiseRequests == null){
            return null;
        }
        for (ModifyMerchandiseRequest modifyMerchandiseRequest : modifyMerchandiseRequests){
            Long modifyMerchandiseRequestId = modifyMerchandiseRequest.getModifyMerchandiseRequestId();
            Long requestRecordMerchandiseId = requestRecordMerchandiseToModifyMerchandiseRequestMapper.selectRequestRecordMerchandiseByModifyMerchandiseRequest(modifyMerchandiseRequestId);
            if (requestRecordMerchandiseId == null){
                continue;
            }
            RequestRecordMerchandise requestRecordMerchandise = requestRecordMerchandiseMapper.selectById(requestRecordMerchandiseId);
            modifyMerchandiseRequest.setRequestRecordMerchandise(requestRecordMerchandise);
            if (modifyMerchandiseRequest.getRequestStatus().equals(RequestStatus.IN_REVIEW)){
                Long merchandiseId = modifyMerchandiseRequestToMerchandiseMapper.selectMerchandiseByModifyMerchandiseRequest(modifyMerchandiseRequestId);
                if (merchandiseId == null){
                    continue;
                }
                Merchandise merchandise = merchandiseMapper.selectById(merchandiseId);
                if (merchandise == null){
                    continue;
                }
                modifyMerchandiseRequest.setMerchandise(merchandise);
            }
        }
        return modifyMerchandiseRequests;
    }

    @Transactional
    public List<ModifyMerchandiseRequest> findModifyMerchandiseRequestWithRequestRecordMerchandiseOfShop(Long shopId){
        if (shopId == null){
            return null;
        }
        List<Long> modifyMerchandiseRequestIds = shopToModifyMerchandiseRequestMapper.selectModifyMerchandiseRequestsByShop(shopId);
        List<ModifyMerchandiseRequest> modifyMerchandiseRequests = modifyMerchandiseRequestMapper.selectBatchIds(modifyMerchandiseRequestIds);
        if (modifyMerchandiseRequests == null){
            return null;
        }
        for (ModifyMerchandiseRequest modifyMerchandiseRequest : modifyMerchandiseRequests){
            Long modifyMerchandiseRequestId = modifyMerchandiseRequest.getModifyMerchandiseRequestId();
            Long requestRecordMerchandiseId = requestRecordMerchandiseToModifyMerchandiseRequestMapper.selectRequestRecordMerchandiseByModifyMerchandiseRequest(modifyMerchandiseRequestId);
            if (requestRecordMerchandiseId == null){
                continue;
            }
            RequestRecordMerchandise requestRecordMerchandise = requestRecordMerchandiseMapper.selectById(requestRecordMerchandiseId);
            modifyMerchandiseRequest.setRequestRecordMerchandise(requestRecordMerchandise);
            if (modifyMerchandiseRequest.getRequestStatus().equals(RequestStatus.IN_REVIEW)){
                Long merchandiseId = modifyMerchandiseRequestToMerchandiseMapper.selectMerchandiseByModifyMerchandiseRequest(modifyMerchandiseRequestId);
                if (merchandiseId == null){
                    continue;
                }
                Merchandise merchandise = merchandiseMapper.selectById(merchandiseId);
                if (merchandise == null){
                    continue;
                }
                modifyMerchandiseRequest.setMerchandise(merchandise);
            }
        }
        return modifyMerchandiseRequests;
    }
}
