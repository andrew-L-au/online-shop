package com.example.userservice.service;

import com.example.userservice.model.merchandise.*;
import com.example.userservice.model.merchandise.connect.*;
import com.example.userservice.model.shop.RequestStatus;
import com.example.userservice.model.shop.Shop;
import com.example.userservice.model.shop.connect.ShopToMerchandise;
import com.example.userservice.model.shop.connect.ShopToModifyMerchandiseRequest;
import com.example.userservice.model.shop.connect.ShopToNewMerchandiseRequest;
import com.example.userservice.repository.mapper.merchandise.*;
import com.example.userservice.repository.mapper.merchandise.connect.*;
import com.example.userservice.repository.mapper.shop.connect.ShopToMerchandiseMapper;
import com.example.userservice.repository.mapper.shop.connect.ShopToModifyMerchandiseRequestMapper;
import com.example.userservice.repository.mapper.shop.connect.ShopToNewMerchandiseRequestMapper;
import com.example.userservice.repository.mapper.shoppingcart.connect.ShoppingCartToMerchandiseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchandiseService {

    @Autowired
    private RequestRecordMerchandiseMapper requestRecordMerchandiseMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private RequestRecordMerchandiseToImageMapper requestRecordMerchandiseToImageMapper;

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
    private MerchandiseToImageMapper merchandiseToImageMapper;

    @Autowired
    private ShopToNewMerchandiseRequestMapper shopToNewMerchandiseRequestMapper;

    @Autowired
    private ShopToModifyMerchandiseRequestMapper shopToModifyMerchandiseRequestMapper;

    @Autowired
    private ShoppingCartToMerchandiseMapper shoppingCartToMerchandiseMapper;

    @Transactional
    public Boolean requestNewMerchandise(RequestRecordMerchandise requestRecordMerchandise, String shopId){
        if (requestRecordMerchandise == null || requestRecordMerchandise.getMerchandiseName() == null || requestRecordMerchandise.getPrice() == null){
            return false;
        }
        requestRecordMerchandiseMapper.insert(requestRecordMerchandise);
        if (requestRecordMerchandise.getRequestRecordMerchandiseId() == null){
            throw new RuntimeException();
        }
        List<Image> images = requestRecordMerchandise.getImages();
        for (Image image : images){
            imageMapper.insert(image);
            RequestRecordMerchandiseToImage requestRecordMerchandiseToImage = new RequestRecordMerchandiseToImage();
            requestRecordMerchandiseToImage.setImageId(image.getImageId());
            requestRecordMerchandiseToImage.setRequestRecordMerchandiseId(requestRecordMerchandise.getRequestRecordMerchandiseId());
            requestRecordMerchandiseToImageMapper.insert(requestRecordMerchandiseToImage);
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
    public Boolean requestMerchandiseModify(String merchandiseId, RequestRecordMerchandise requestRecordMerchandise, String shopId){
         if (requestRecordMerchandise == null || requestRecordMerchandise.getMerchandiseName() == null || requestRecordMerchandise.getPrice() == null){
            return false;
        }
        requestRecordMerchandiseMapper.insert(requestRecordMerchandise);
        if (requestRecordMerchandise.getRequestRecordMerchandiseId() == null){
            throw new RuntimeException();
        }
        if (requestRecordMerchandise.getRequestRecordMerchandiseId() == null){
            throw new RuntimeException();
        }
        List<Image> images = requestRecordMerchandise.getImages();
        for (Image image : images){
            imageMapper.insert(image);
            RequestRecordMerchandiseToImage requestRecordMerchandiseToImage = new RequestRecordMerchandiseToImage();
            requestRecordMerchandiseToImage.setImageId(image.getImageId());
            requestRecordMerchandiseToImage.setRequestRecordMerchandiseId(requestRecordMerchandise.getRequestRecordMerchandiseId());
            requestRecordMerchandiseToImageMapper.insert(requestRecordMerchandiseToImage);
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
    public Boolean approveNewMerchandiseRequest(String newMerchandiseRequestId){
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
        String shopId = shopToNewMerchandiseRequestMapper.selectShopByNewMerchandiseRequest(newMerchandiseRequest.getNewMerchandiseRequestId());
        if (shopId == null){
            throw new RuntimeException();
        }
        String requestRecordMerchandiseId = requestRecordMerchandiseToNewMerchandiseRequestMapper.selectRequestRecordMerchandiseByNewMerchandiseRequest(newMerchandiseRequest.getNewMerchandiseRequestId());
        if (requestRecordMerchandiseId == null){
            throw new RuntimeException();
        }
        RequestRecordMerchandise requestRecordMerchandise = requestRecordMerchandiseMapper.selectById(requestRecordMerchandiseId);
        if (requestRecordMerchandise == null){
            throw new RuntimeException();
        }
        List<String> imageIds = requestRecordMerchandiseToImageMapper.selectImagesByRequestRecordMerchandise(requestRecordMerchandiseId);
        List<Image> images = null;
        if (imageIds != null && imageIds.size() != 0){
            images = imageMapper.selectBatchIds(imageIds);
        }
        Merchandise merchandise = new Merchandise();
        merchandise.setMerchandiseName(requestRecordMerchandise.getMerchandiseName());
        merchandise.setDescription(requestRecordMerchandise.getDescription());
        merchandise.setPrice(requestRecordMerchandise.getPrice());
        merchandiseMapper.insert(merchandise);
        if (merchandise.getMerchandiseId() == null){
            throw new RuntimeException();
        }
        for (Image image : images){
            MerchandiseToImage merchandiseToImage = new MerchandiseToImage();
            merchandiseToImage.setImageId(image.getImageId());
            merchandiseToImage.setMerchandiseId(merchandise.getMerchandiseId());
            merchandiseToImageMapper.insert(merchandiseToImage);
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
    public Boolean approveModifyMerchandiseRequest(String modifyMerchandiseRequestId){
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
        String shopId = shopToModifyMerchandiseRequestMapper.selectShopByModifyMerchandiseRequest(modifyMerchandiseRequest.getModifyMerchandiseRequestId());
        if (shopId == null){
            throw new RuntimeException();
        }
        String requestRecordMerchandiseId = requestRecordMerchandiseToModifyMerchandiseRequestMapper.selectRequestRecordMerchandiseByModifyMerchandiseRequest(modifyMerchandiseRequest.getModifyMerchandiseRequestId());
        if (requestRecordMerchandiseId == null){
            throw new RuntimeException();
        }
        RequestRecordMerchandise requestRecordMerchandise = requestRecordMerchandiseMapper.selectById(requestRecordMerchandiseId);
        if (requestRecordMerchandise == null){
            throw new RuntimeException();
        }
        String merchandiseId = modifyMerchandiseRequestToMerchandiseMapper.selectMerchandiseByModifyMerchandiseRequest(modifyMerchandiseRequest.getModifyMerchandiseRequestId());
        if (merchandiseId == null){
            throw new RuntimeException();
        }
        Merchandise merchandise = new Merchandise();
        merchandise.setMerchandiseId(merchandiseId);
        merchandise.setMerchandiseName(requestRecordMerchandise.getMerchandiseName());
        merchandise.setDescription(requestRecordMerchandise.getDescription());
        merchandise.setPrice(requestRecordMerchandise.getPrice());
        merchandiseMapper.updateById(merchandise);
        List<String> imageIds = merchandiseToImageMapper.selectImagesByMerchandise(merchandiseId);
        for (String imageId : imageIds){
            String id = merchandiseToImageMapper.selectOneByMerchandiseAndImage(merchandiseId, imageId);
            merchandiseToImageMapper.deleteById(id);
        }
        imageMapper.deleteBatchIds(imageIds);
        imageIds = requestRecordMerchandiseToImageMapper.selectImagesByRequestRecordMerchandise(requestRecordMerchandiseId);
        List<Image> images = imageMapper.selectBatchIds(imageIds);
        for (Image image : images){
            MerchandiseToImage merchandiseToImage = new MerchandiseToImage();
            merchandiseToImage.setImageId(image.getImageId());
            merchandiseToImage.setMerchandiseId(merchandise.getMerchandiseId());
            merchandiseToImageMapper.insert(merchandiseToImage);
        }
        return true;
    }

    @Transactional
    public Boolean rejectNewMerchandiseRequest(String newMerchandiseRequestId){
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
    public Boolean rejectModifyMerchandiseRequest(String modifyMerchandiseRequestId){
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
    public Boolean removeMerchandise(String merchandiseId){
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
        List<String> ids = modifyMerchandiseRequestToMerchandiseMapper.selectIdsByMerchandise(merchandise.getMerchandiseId());
        if (ids != null){
            modifyMerchandiseRequestToMerchandiseMapper.deleteBatchIds(ids);
        }
        String id = shopToMerchandiseMapper.selectIdByMerchandise(merchandise.getMerchandiseId());
        shopToMerchandiseMapper.deleteById(id);
        List<String> shoppingCartIds = shoppingCartToMerchandiseMapper.selectShoppingCartsByMerchandise(merchandiseId);
        for (String shoppingCartId : shoppingCartIds){
            id = shoppingCartToMerchandiseMapper.selectOneByShoppingCartAndMerchandises(shoppingCartId, merchandiseId);
            if (shoppingCartToMerchandiseMapper.deleteById(id) <= 0){
                throw new RuntimeException();
            }
        }
        List<String> imageIds = merchandiseToImageMapper.selectImagesByMerchandise(merchandiseId);
        for (String imageId : imageIds){
            id = merchandiseToImageMapper.selectOneByMerchandiseAndImage(merchandiseId, imageId);
            merchandiseToImageMapper.deleteById(id);
        }
        return true;
    }

    @Transactional
    public List<NewMerchandiseRequest> findNewMerchandiseRequestWithRequestRecordMerchandise(){
        List<NewMerchandiseRequest> newMerchandiseRequests = newMerchandiseRequestMapper.allNewMerchandiseRequests();
        for (NewMerchandiseRequest newMerchandiseRequest : newMerchandiseRequests){
            String newMerchandiseRequestId = newMerchandiseRequest.getNewMerchandiseRequestId();
            String requestRecordMerchandiseId = requestRecordMerchandiseToNewMerchandiseRequestMapper.selectRequestRecordMerchandiseByNewMerchandiseRequest(newMerchandiseRequestId);
            RequestRecordMerchandise requestRecordMerchandise = requestRecordMerchandiseMapper.selectById(requestRecordMerchandiseId);
            List<String> imageIds = requestRecordMerchandiseToImageMapper.selectImagesByRequestRecordMerchandise(requestRecordMerchandise.getRequestRecordMerchandiseId());
            List<Image> images = null;
            if (imageIds != null && imageIds.size() != 0){
                images = imageMapper.selectBatchIds(imageIds);
            }
            requestRecordMerchandise.setImages(images);
            newMerchandiseRequest.setRequestRecordMerchandise(requestRecordMerchandise);
        }
        return newMerchandiseRequests;
    }

    @Transactional
    public List<NewMerchandiseRequest> findNewMerchandiseRequestWithRequestRecordMerchandiseOfShop(String shopId){
        if (shopId == null){
            return null;
        }
        List<String> newMerchandiseRequestIds = shopToNewMerchandiseRequestMapper.selectNewMerchandiseRequestsByShop(shopId);
        if (newMerchandiseRequestIds == null){
            return null;
        }
        List<NewMerchandiseRequest> newMerchandiseRequests = newMerchandiseRequestMapper.selectBatchIds(newMerchandiseRequestIds);
        if (newMerchandiseRequests == null){
            return null;
        }
        for (NewMerchandiseRequest newMerchandiseRequest : newMerchandiseRequests){
            String newMerchandiseRequestId = newMerchandiseRequest.getNewMerchandiseRequestId();
            String requestRecordMerchandiseId = requestRecordMerchandiseToNewMerchandiseRequestMapper.selectRequestRecordMerchandiseByNewMerchandiseRequest(newMerchandiseRequestId);
            RequestRecordMerchandise requestRecordMerchandise = requestRecordMerchandiseMapper.selectById(requestRecordMerchandiseId);
            List<String> imageIds = requestRecordMerchandiseToImageMapper.selectImagesByRequestRecordMerchandise(requestRecordMerchandise.getRequestRecordMerchandiseId());
            List<Image> images = imageMapper.selectBatchIds(imageIds);
            requestRecordMerchandise.setImages(images);
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
            String modifyMerchandiseRequestId = modifyMerchandiseRequest.getModifyMerchandiseRequestId();
            String requestRecordMerchandiseId = requestRecordMerchandiseToModifyMerchandiseRequestMapper.selectRequestRecordMerchandiseByModifyMerchandiseRequest(modifyMerchandiseRequestId);
            if (requestRecordMerchandiseId == null){
                continue;
            }
            RequestRecordMerchandise requestRecordMerchandise = requestRecordMerchandiseMapper.selectById(requestRecordMerchandiseId);
            List<String> imageIds = requestRecordMerchandiseToImageMapper.selectImagesByRequestRecordMerchandise(requestRecordMerchandise.getRequestRecordMerchandiseId());
            List<Image> images = imageMapper.selectBatchIds(imageIds);
            requestRecordMerchandise.setImages(images);
            modifyMerchandiseRequest.setRequestRecordMerchandise(requestRecordMerchandise);
            if (modifyMerchandiseRequest.getRequestStatus().equals(RequestStatus.IN_REVIEW)){
                String merchandiseId = modifyMerchandiseRequestToMerchandiseMapper.selectMerchandiseByModifyMerchandiseRequest(modifyMerchandiseRequestId);
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
    public List<ModifyMerchandiseRequest> findModifyMerchandiseRequestWithRequestRecordMerchandiseOfShop(String shopId){
        if (shopId == null){
            return null;
        }
        List<String> modifyMerchandiseRequestIds = shopToModifyMerchandiseRequestMapper.selectModifyMerchandiseRequestsByShop(shopId);
        List<ModifyMerchandiseRequest> modifyMerchandiseRequests = modifyMerchandiseRequestMapper.selectBatchIds(modifyMerchandiseRequestIds);
        if (modifyMerchandiseRequests == null){
            return null;
        }
        for (ModifyMerchandiseRequest modifyMerchandiseRequest : modifyMerchandiseRequests){
            String modifyMerchandiseRequestId = modifyMerchandiseRequest.getModifyMerchandiseRequestId();
            String requestRecordMerchandiseId = requestRecordMerchandiseToModifyMerchandiseRequestMapper.selectRequestRecordMerchandiseByModifyMerchandiseRequest(modifyMerchandiseRequestId);
            if (requestRecordMerchandiseId == null){
                continue;
            }
            RequestRecordMerchandise requestRecordMerchandise = requestRecordMerchandiseMapper.selectById(requestRecordMerchandiseId);
            List<String> imageIds = requestRecordMerchandiseToImageMapper.selectImagesByRequestRecordMerchandise(requestRecordMerchandise.getRequestRecordMerchandiseId());
            List<Image> images = imageMapper.selectBatchIds(imageIds);
            requestRecordMerchandise.setImages(images);
            modifyMerchandiseRequest.setRequestRecordMerchandise(requestRecordMerchandise);
            if (modifyMerchandiseRequest.getRequestStatus().equals(RequestStatus.IN_REVIEW)){
                String merchandiseId = modifyMerchandiseRequestToMerchandiseMapper.selectMerchandiseByModifyMerchandiseRequest(modifyMerchandiseRequestId);
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
    public List<Merchandise> merchandisesOfShop(String shopId){
        if (shopId == null){
            throw new NullPointerException();
        }
        List<String> merchandiseIds = shopToMerchandiseMapper.selectMerchandisesByShop(shopId);
        List<Merchandise> merchandises = merchandiseMapper.selectBatchIds(merchandiseIds);
        for (Merchandise merchandise : merchandises){
            List<String> imageIds = merchandiseToImageMapper.selectImagesByMerchandise(merchandise.getMerchandiseId());
            List<Image> images = imageMapper.selectBatchIds(imageIds);
            merchandise.setImages(images);
        }
        return merchandises;
    }
}
