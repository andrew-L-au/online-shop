package com.example.userservice.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.userservice.model.connect.ShopOwnerToShop;
import com.example.userservice.model.shop.connect.ShopToCommodityType;
import com.example.userservice.model.shop.connect.ShopToShopBasicInfo;
import com.example.userservice.model.shop.CommodityType;
import com.example.userservice.model.shop.Shop;
import com.example.userservice.model.shop.ShopBasicInfo;
import com.example.userservice.model.shop.ShopStatus;
import com.example.userservice.model.user.ShopOwner;
import com.example.userservice.repository.mapper.shop.CommodityTypeMapper;
import com.example.userservice.repository.mapper.shop.ShopBasicInfoMapper;
import com.example.userservice.repository.mapper.shop.ShopMapper;
import com.example.userservice.repository.mapper.connect.ShopOwnerToShopMapper;
import com.example.userservice.repository.mapper.connect.ShopToCommodityTypeMapper;
import com.example.userservice.repository.mapper.connect.ShopToShopBasicInfoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository
public class ShopRepository {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private ShopBasicInfoMapper shopBasicInfoMapper;

    @Autowired
    private ShopToShopBasicInfoMapper shopToShopBasicInfoMapper;

    @Autowired
    private ShopOwnerToShopMapper shopOwnerToShopMapper;

    @Autowired
    private CommodityTypeMapper commodityTypeMapper;

    @Autowired
    private ShopToCommodityTypeMapper shopToCommodityTypeMapper;

    public Boolean insertOneShop(Shop shop, ShopBasicInfo shopBasicInfo, ShopOwner shopOwner, List<CommodityType> commodityTypes){
        if (shop == null || shopBasicInfo == null || shopOwner == null || commodityTypes == null) {
            return false;
        }
        shop.setShopId(null);
        shopBasicInfo.setShopBasicInfoId(null);
        shopMapper.insert(shop);
        shopBasicInfoMapper.insert(shopBasicInfo);
        if (shop.getShopId() == null || shopBasicInfo.getShopBasicInfoId() == null){
            return false;
        }
        shopToShopBasicInfoMapper.insert(new ShopToShopBasicInfo(null, shop.getShopId(), shopBasicInfo.getShopBasicInfoId()));
        shopOwnerToShopMapper.insert(new ShopOwnerToShop(null, shopOwner.getShopOwnerId(), shop.getShopId()));
        AtomicBoolean commodityTypeSuccess = new AtomicBoolean(true);
        commodityTypes.forEach(e -> {
            e.setCommodityTypeId(null);
            commodityTypeMapper.insert(e);
            if (e.getCommodityTypeId() == null){
                commodityTypeSuccess.set(false);
            }
        });
        if (!commodityTypeSuccess.get()){
            return false;
        }
        commodityTypes.forEach(e -> {
            ShopToCommodityType shopToCommodityType = new ShopToCommodityType(null , shop.getShopId(), e.getCommodityTypeId());
            shopToCommodityTypeMapper.insert(shopToCommodityType);
            if (shopToCommodityType.getId() == null){
                commodityTypeSuccess.set(false);
            }
        });
        return commodityTypeSuccess.get();
    }

    public Boolean updateShopStatus(String shopId, ShopStatus shopStatus){
        if (shopId == null){
            return false;
        }
        Shop shop = shopMapper.selectById(shopId);
        if (shop == null){
            return false;
        }
        shop.setShopStatus(shopStatus);
        int updateNum = shopMapper.updateById(shop);
        return updateNum > 0;
    }

    public Shop selectShopWithAllInfo(String shopId){
        if (shopId == null){
            return null;
        }
        Shop shop = shopMapper.selectById(shopId);
        if (shop == null){
            return null;
        }
        List<String> commodityTypeIds = shopToCommodityTypeMapper.selectCommodityTypeByShop(shopId);
        if (commodityTypeIds == null){
            return null;
        }
        List<CommodityType> commodityTypes = commodityTypeMapper.selectBatchIds(commodityTypeIds);
        shop.setCommodityTypes(commodityTypes);
        String shopBasicInfoId = shopToShopBasicInfoMapper.selectShopBasicInfoByShop(shopId);
        if (shopBasicInfoId == null){
            return null;
        }
        ShopBasicInfo shopBasicInfo = shopBasicInfoMapper.selectById(shopBasicInfoId);
        shop.setShopBasicInfo(shopBasicInfo);
        return shop;
    }

    public List<String> selectAllShopIds(){
        return selectAllShopOfStatusIds(null);
    }

    public List<String> selectAllShopOfStatusIds(ShopStatus shopStatus){
        List<Shop> shops;
        if (shopStatus == null){
            shops = this.shopMapper.selectList(new QueryWrapper<>());
        }else {
            shops = this.shopMapper.selectList(new QueryWrapper<Shop>().eq("shop_status", shopStatus));
        }
        List<String> shopIds = new ArrayList<>();
        shops.forEach(e -> { shopIds.add(e.getShopId()); });
        return shopIds;
    }

    public List<Shop> selectAllShopsOfStatusWithAllInfo(ShopStatus shopStatus){
        List<Shop> shopsOfStatus = new ArrayList<>();
        List<String> shopOfStatusIds = selectAllShopOfStatusIds(shopStatus);
        shopOfStatusIds.forEach( e -> shopsOfStatus.add(selectShopWithAllInfo(e)) );
        return shopsOfStatus;
    }
}
