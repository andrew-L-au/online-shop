package com.example.userservice.service;

import com.example.userservice.model.merchandise.Merchandise;
import com.example.userservice.model.shoppingcart.ShoppingCart;
import com.example.userservice.model.shoppingcart.connect.ShoppingCartToMerchandise;
import com.example.userservice.model.user.connect.UserToShoppingCart;
import com.example.userservice.repository.mapper.MerchandiseMapper;
import com.example.userservice.repository.mapper.shoppingcart.ShoppingCartMapper;
import com.example.userservice.repository.mapper.shoppingcart.connect.ShoppingCartToMerchandiseMapper;
import com.example.userservice.repository.mapper.user.UserMapper;
import com.example.userservice.repository.mapper.user.connect.UserToShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private UserToShoppingCartMapper userToShoppingCartMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    MerchandiseMapper merchandiseMapper;

    @Autowired
    ShoppingCartToMerchandiseMapper shoppingCartToMerchandiseMapper;

    @Transactional
    public Boolean creatShoppingCart(Long userId){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCartMapper.insert(shoppingCart);
        if (shoppingCart.getShoppingCartId() == null){
            throw new RuntimeException();
        }
        UserToShoppingCart userToShoppingCart = new UserToShoppingCart();
        if(userMapper.selectById(userId) == null){
            throw new RuntimeException();
        }
        userToShoppingCart.setUserId(userId);
        userToShoppingCart.setShoppingCartId(shoppingCart.getShoppingCartId());
        userToShoppingCartMapper.insert(userToShoppingCart);
        if (userToShoppingCart.getId() == null){
            throw new RuntimeException();
        }
        return true;
    }

    @Transactional
    public Boolean addMerchandiseToShoppingCart(Long userId, Long merchandiseId){
        if (userMapper.selectById(userId) == null){
            return false;
        }
        Long shoppingCartId = userToShoppingCartMapper.selectShoppingCartByUser(userId);
        if(shoppingCartMapper.selectById(shoppingCartId) == null){
            return false;
        }
        if (merchandiseMapper.selectById(merchandiseId) == null){
            return false;
        }
        ShoppingCartToMerchandise shoppingCartToMerchandise = new ShoppingCartToMerchandise();
        shoppingCartToMerchandise.setShoppingCartId(shoppingCartId);
        shoppingCartToMerchandise.setMerchandiseId(merchandiseId);
        shoppingCartToMerchandiseMapper.insert(shoppingCartToMerchandise);
        if (shoppingCartToMerchandise.getId() == null){
            throw new RuntimeException();
        }
        return true;
    }

    public ShoppingCart getShoppingCart(Long userId){
        if (userMapper.selectById(userId) == null){
            return null;
        }
        Long shoppingCartId = userToShoppingCartMapper.selectShoppingCartByUser(userId);
        ShoppingCart shoppingCart = shoppingCartMapper.selectById(shoppingCartId);
        if(shoppingCart == null){
            return null;
        }
        List<Long> merchandiseIds = shoppingCartToMerchandiseMapper.selectMerchandisesByShoppingCart(shoppingCartId);
        if (merchandiseIds == null){
            return null;
        }
        List<Merchandise> merchandises = merchandiseMapper.selectBatchIds(merchandiseIds);
        shoppingCart.setMerchandises(merchandises);
        return shoppingCart;
    }

}
