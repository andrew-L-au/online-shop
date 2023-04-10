package com.example.userservice.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.userservice.model.connect.ShopOwnerToShop;
import com.example.userservice.model.connect.ShopToCommodityType;
import com.example.userservice.model.connect.ShopToShopBasicInfo;
import com.example.userservice.model.shop.CommodityType;
import com.example.userservice.model.shop.Shop;
import com.example.userservice.model.shop.ShopBasicInfo;
import com.example.userservice.model.shop.ShopStatus;
import com.example.userservice.model.user.ShopOwner;
import com.example.userservice.repository.mapper.CommodityTypeMapper;
import com.example.userservice.repository.mapper.ShopBasicInfoMapper;
import com.example.userservice.repository.mapper.ShopMapper;
import com.example.userservice.repository.mapper.connect.ShopOwnerToShopMapper;
import com.example.userservice.repository.mapper.connect.ShopToCommodityTypeMapper;
import com.example.userservice.repository.mapper.connect.ShopToShopBasicInfoMapper;
import com.example.userservice.repository.mapper.ShopMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    public void insertOneShop(Shop shop, ShopBasicInfo shopBasicInfo, ShopOwner shopOwner, List<CommodityType> commodityTypes){
        shopMapper.insert(shop);
        shopBasicInfoMapper.insert(shopBasicInfo);
        shopToShopBasicInfoMapper.insert(new ShopToShopBasicInfo(null, shop.getShopId(), shopBasicInfo.getShopBasicInfoId()));
        shopOwnerToShopMapper.insert(new ShopOwnerToShop(null, shopOwner.getShopOwnerId(), shop.getShopId()));
        commodityTypes.forEach(e -> {commodityTypeMapper.insert(e);});
        commodityTypes.forEach(e -> {shopToCommodityTypeMapper.insert(new ShopToCommodityType(null , shop.getShopId(), e.getCommodityTypeId() ));});
    }

    public void updateShopStatus(Long shopId, ShopStatus shopStatus){
        shopMapper.updateById(new Shop(shopId, null, null, null, shopStatus));
    }

    public Shop selectShopWithAllInfo(Long shopId){
        Shop shop = shopMapper.selectById(shopId);
        List<Long> commodityTypeIds = shopToCommodityTypeMapper.selectCommodityTypeByShop(shopId);
        List<CommodityType> commodityTypes = commodityTypeMapper.selectBatchIds(commodityTypeIds);
        shop.setCommodityTypes(commodityTypes);
        Long shopBasicInfoId = shopToShopBasicInfoMapper.selectShopBasicInfoByShop(shopId);
        ShopBasicInfo shopBasicInfo = shopBasicInfoMapper.selectById(shopBasicInfoId);
        shop.setShopBasicInfo(shopBasicInfo);
        return shop;
    }

    public List<Long> selectAllShopIds(){
        return selectAllShopOfStatusIds(null);
    }

    public List<Long> selectAllShopOfStatusIds(ShopStatus shopStatus){
        List<Shop> shops;
        if (shopStatus == null){
            shops = this.shopMapper.selectList(new QueryWrapper<>());
        }else {
            shops = this.shopMapper.selectList(new QueryWrapper<Shop>().eq("shop_status", shopStatus));
        }
        List<Long> shopIds = new ArrayList<>();
        shops.forEach(e -> { shopIds.add(e.getShopId()); });
        return shopIds;
    }

    public List<Shop> selectAllShopsOfStatusWithAllInfo(ShopStatus shopStatus){
        List<Shop> shopsOfStatus = new ArrayList<>();
        List<Long> shopOfStatusIds = selectAllShopOfStatusIds(shopStatus);
        shopOfStatusIds.forEach( e -> shopsOfStatus.add(selectShopWithAllInfo(e)) );
        return shopsOfStatus;
    }

}
