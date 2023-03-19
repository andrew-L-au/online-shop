package com.example.shopservice.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shopservice.repository.mapper.CommodityTypeMapper;
import com.example.shopservice.repository.mapper.ShopBasicInfoMapper;
import com.example.shopservice.repository.mapper.ShopMapper;
import com.example.shopservice.repository.mapper.connect.ShopOwnerToShopMapper;
import com.example.shopservice.repository.mapper.connect.ShopToCommodityTypeMapper;
import com.example.shopservice.repository.mapper.connect.ShopToShopBasicInfoMapper;
import model.connect.ShopOwnerToShop;
import model.connect.ShopToCommodityType;
import model.connect.ShopToShopBasicInfo;
import model.shop.CommodityType;
import model.shop.Shop;
import model.shop.ShopBasicInfo;
import model.shop.ShopStatus;
import model.user.ShopOwner;
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
