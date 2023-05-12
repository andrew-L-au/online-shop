package com.example.userservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.userservice.model.account.Account;
import com.example.userservice.model.account.MiddleAccount;
import com.example.userservice.model.account.ProfitAccount;
import com.example.userservice.model.connect.ShopOwnerToShop;
import com.example.userservice.model.shop.CloseShopRequest;
import com.example.userservice.model.shop.OpenShopRequest;
import com.example.userservice.model.shop.RequestStatus;
import com.example.userservice.model.shop.CommodityType;
import com.example.userservice.model.shop.Shop;
import com.example.userservice.model.shop.ShopBasicInfo;
import com.example.userservice.model.shop.ShopStatus;
import com.example.userservice.model.shop.connect.CloseShopRequestToShop;
import com.example.userservice.model.user.ShopOwner;
import com.example.userservice.model.user.User;
import com.example.userservice.repository.OpenShopRequestRepository;
import com.example.userservice.repository.ShopRepository;
import com.example.userservice.repository.mapper.connect.ShopOwnerToShopMapper;
import com.example.userservice.repository.mapper.connect.ShopToCommodityTypeMapper;
import com.example.userservice.repository.mapper.connect.ShopToShopBasicInfoMapper;
import com.example.userservice.repository.mapper.shop.*;
import com.example.userservice.repository.mapper.shop.connect.CloseShopRequestToShopMapper;
import com.example.userservice.repository.mapper.shop.connect.ShopToMerchandiseMapper;
import com.example.userservice.repository.mapper.user.UserMapper;
import com.example.userservice.repository.mapper.user.connect.ShopOwnerToUserMapper;
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
    private AccountService accountService;

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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OpenShopRequestMapper openShopRequestMapper;

    @Autowired
    private CommodityTypeMapper commodityTypeMapper;

    @Autowired
    private ShopOwnerToShopMapper shopOwnerToShopMapper;

    @Autowired
    private ShopOwnerToUserMapper shopOwnerToUserMapper;

    @Autowired
    private ShopToShopBasicInfoMapper shopToShopBasicInfoMapper;

    @Autowired
    private ShopToCommodityTypeMapper shopToCommodityTypeMapper;

    @Autowired
    private ShopToMerchandiseMapper shopToMerchandiseMapper;

    @Autowired
    private MerchandiseService merchandiseService;

    @Transactional
    public void requestOpenShop(Shop shop, ShopBasicInfo shopBasicInfo, String idCardNumber, List<CommodityType> commodityTypes) throws RuntimeException{
        if (shop == null || shopBasicInfo == null || idCardNumber == null || commodityTypes == null){
            return;
        }
        ShopOwner shopOwner = shopOwnerService.findShopOwner(idCardNumber);
        if (shopOwner == null){
            return;
        }
        if (shopOwnerToShopMapper.selectShopByShopOwner(shopOwner.getShopOwnerId()) != null){
            return;
        }
        if (shopBasicInfo.getName() == null){
            return;
        }
        String userId = shopOwnerToUserMapper.selectUserByShopOwner(shopOwner.getShopOwnerId());
        if (userId == null){
            return;
        }
        Account account = accountService.accountOfUser(userId);
        if (account == null){
            return;
        }
        Boolean success = accountService.transferFromTo(account.getAccountId(),MiddleAccount.MIDDLE_ACCOUNT_ID,shopBasicInfo.getTotalCapital());
        if (!success){
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
    public Boolean requestCloseShop(String shopId){
        if (shopId == null){
            return false;
        }
        Shop shop = shopRepository.selectShopWithAllInfo(shopId);
        if (shop == null){
            return false;
        }
        if (closeShopRequestToShopMapper.selectCloseShopRequestByShop(shopId) != null){
            String closeShopRequestId = closeShopRequestToShopMapper.selectCloseShopRequestByShop(shopId);
            CloseShopRequest closeShopRequest = closeShopRequestMapper.selectById(closeShopRequestId);
            if (!closeShopRequest.getRequestStatus().equals(RequestStatus.IN_REVIEW)){
                return false;
            }
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
            String shopId = closeShopRequestToShopMapper.selectShopByCloseShopRequest(closeShopRequest.getCloseShopRequestId());
            Shop shop = shopRepository.selectShopWithAllInfo(shopId);
            closeShopRequest.setShop(shop);
        }
        return closeShopRequests;
    }

    @Transactional
    public Boolean approveCloseShopRequest(String closeShopRequestId){
        if (closeShopRequestId == null){
            return false;
        }
        CloseShopRequest closeShopRequest = closeShopRequestMapper.selectById(closeShopRequestId);
        if (closeShopRequest == null){
            return false;
        }
        closeShopRequest.setRequestStatus(RequestStatus.APPROVE);
        closeShopRequestMapper.updateById(closeShopRequest);
        String shopId = closeShopRequestToShopMapper.selectShopByCloseShopRequest(closeShopRequest.getCloseShopRequestId());
        int num = shopMapper.deleteById(shopId);
        if (num <= 0){
            throw new RuntimeException();
        }
        String shopOwnerId = shopOwnerToShopMapper.selectShopOwnerByShop(shopId);
        ShopOwnerToShop shopOwnerToShop = shopOwnerToShopMapper.selectOneByShopOwnerAndShop(shopOwnerId,shopId);
        num = shopOwnerToShopMapper.deleteById(shopOwnerToShop.getId());
        if (num <= 0){
            throw new RuntimeException();
        }
        String shopBasicInfoId = shopToShopBasicInfoMapper.selectShopBasicInfoByShop(shopId);
        if (shopBasicInfoMapper.deleteById(shopBasicInfoId) <= 0){
            throw new RuntimeException();
        }
        List<String> merchandiseIds =  shopToMerchandiseMapper.selectMerchandisesByShop(shopId);
        for (String merchandiseId : merchandiseIds){
            merchandiseService.removeMerchandise(merchandiseId);
        }
        accountService.closeShopAccount(shopId);
        return true;
    }



    @Transactional
    public String approveOpenShopRequest(String shopId){
        if (shopId == null){
            throw new RuntimeException();
        }
        Shop shop = shopRepository.selectShopWithAllInfo(shopId);
        if (shop == null){
            return null;
        }
        accountService.transferFromTo(MiddleAccount.MIDDLE_ACCOUNT_ID, ProfitAccount.PROFIT_ACCOUNT_ID,shop.getShopBasicInfo().getTotalCapital());
        OpenShopRequest openShopRequest = openShopRequestRepository.selectOpenShopRequestWithAllInfoByShop(shop.getShopId());
        if (openShopRequest == null){
            throw new RuntimeException();
        }
        int updateNum = openShopRequestMapper.deleteById(openShopRequest.getOpenShopRequestId());
        if (updateNum <= 0){
            throw new RuntimeException();
        }
        Boolean num = shopRepository.updateShopStatus(openShopRequest.getShop().getShopId(), ShopStatus.NORMAL);
        if (!num){
            throw new RuntimeException();
        }
        if(!accountService.openShopAccount(shopId)){
            throw new RuntimeException();
        }
        return "success";
    }

    public List<Shop> findAllValidShops(){
        return shopRepository.selectAllShopsOfStatusWithAllInfo(ShopStatus.NORMAL);
    }

    public Shop shopWithBasicInfoAndCommodityTypesOfUser(String userId){
        if (userId == null){
            return null;
        }
        User user = userMapper.selectById(userId);
        if (user == null){
            return null;
        }
        String shopOwnerId = shopOwnerToUserMapper.selectShopOwnerByUser(user.getUserId());
        if (shopOwnerId == null){
            return null;
        }
        String shopId = shopOwnerToShopMapper.selectShopByShopOwner(shopOwnerId);
        if (shopId == null){
            return null;
        }
        Shop shop = shopMapper.selectById(shopId);
        if (shop == null){
            return null;
        }
        String shopBasicInfoId = shopToShopBasicInfoMapper.selectShopBasicInfoByShop(shop.getShopId());
        if (shopBasicInfoId == null){
            return null;
        }
        ShopBasicInfo shopBasicInfo = shopBasicInfoMapper.selectById(shopBasicInfoId);
        if (shopBasicInfo == null){
            return null;
        }
        shop.setShopBasicInfo(shopBasicInfo);
        List<String> commodityTypeId = shopToCommodityTypeMapper.selectCommodityTypeByShop(shop.getShopId());
        if (commodityTypeId == null){
            return null;
        }
        List<CommodityType> commodityTypes = commodityTypeMapper.selectBatchIds(commodityTypeId);
        if (commodityTypes == null){
            return null;
        }
        shop.setCommodityTypes(commodityTypes);
        return shop;
    }
}
