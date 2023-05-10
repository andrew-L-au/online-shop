# Lab4

添加收货地址，提交userId,和Address对象

```json
{
    "userId" : "124235431451231341235",
    "address" : {
        "name" : "Twitch",
        "phoneNumber" : "111334242",
        "addressDetail" : "oma,fr,us"
    }
}
```

得到某个用户的收货地址，提交userId，返回address对象的数组

提交

```json
{
    "userId" : "124235431451231341235",
}
```

返回

```json
{
    "addresses" : [
        {
            "addressId" : "123434523903589238",
            "name" : "Twitch",
            "phoneNumber" : "111334242",
            "addressDetail" : "oma,fr,us"
        },
        {
            "addressId" : "123434523903589238",
            "name" : "Twitch",
            "phoneNumber" : "111334242",
            "addressDetail" : "oma,fr,us"
        },
        {
            "addressId" : "123434523903589238",
            "name" : "Twitch",
            "phoneNumber" : "111334242",
            "addressDetail" : "oma,fr,us"
        }
    ] 
}
```

删除某个收货地址，提交addressId

提交

```json
{
    "addressId" : "124235431451231341235",
}
```

增加某个已经在购物车中的商品的数量(数量只能加一)，提交shoppingCartId,merchandiseId

```json
{
    "shoppingCartId" : "124235431451231341235",
    "merchandiseId" : "124235431451231341235"
}
```

减少某个已经在购物车中的商品的数量(数量只能减一)，提交shoppingCartId,merchandiseId

```json
{
    "shoppingCartId" : "124235431451231341235",
    "merchandiseId" : "124235431451231341235"
}
```

购买商品（下订单），提交userId，merchandiseId，和商品数量, 返回订单对象

```json
{
    "userId" : "124235431451231341235",
    "merchandiseId" : "124235431451231341235",
    "number" : 5
},
{
    "orderId" : "124235431451231341235",
}
```

支付订单，提交userId,orderId

```json
{
    "userId" : "124235431451231341235",
    "orderId" : "124235431451231341235"
}
```

查看某一账户的所有流水，提交accountId

```json
{
    "accountId" : "124235431451231341235",
}
```

确认收货，提交orderId

```json
{
    "orderId" : "124235431451231341235"
}
```

退货退款，提交orderId

```json
{
    "orderId" : "124235431451231341235"
}
```

查看用户的所有订单，提交userId，返回order对象的数组

```json
[
    {
        "orderId" : "124235431451231341235",
        "merchandise" : {
            "merchandiseId" : "124235431451231341235",
        },
        "shop" : {
            "shopId" : "124235431451231341235",            
        },
        "user" : {
            "userId" : "124235431451231341235",
        },
        "number" : 5  
    },
    {
        "orderId" : "124235431451231341235",
        "merchandise" : {
            "merchandiseId" : "124235431451231341235",
        },
        "shop" : {
            "shopId" : "124235431451231341235",            
        },
        "user" : {
            "userId" : "124235431451231341235",
        },
        "number" : 5 
    },
    {
        "orderId" : "124235431451231341235",
        "merchandise" : {
            "merchandiseId" : "124235431451231341235",
        },
        "shop" : {
            "shopId" : "124235431451231341235",            
        },
        "user" : {
            "userId" : "124235431451231341235",
        },
        "number" : 5 
    }
]
```

删除订单，提交orderId

```json
{
    "orderId" : "124235431451231341235"
}
```

取得某一个商店的订单，提交shopId，返回order对象的数组

```json
[
    {
        "orderId" : "124235431451231341235",
        "merchandise" : {
            "merchandiseId" : "124235431451231341235",
        },
        "shop" : {
            "shopId" : "124235431451231341235",            
        },
        "user" : {
            "userId" : "124235431451231341235",
        },
        "number" : 5  
    },
    {
        "orderId" : "124235431451231341235",
        "merchandise" : {
            "merchandiseId" : "124235431451231341235",
        },
        "shop" : {
            "shopId" : "124235431451231341235",            
        },
        "user" : {
            "userId" : "124235431451231341235",
        },
        "number" : 5 
    },
    {
        "orderId" : "124235431451231341235",
        "merchandise" : {
            "merchandiseId" : "124235431451231341235",
        },
        "shop" : {
            "shopId" : "124235431451231341235",            
        },
        "user" : {
            "userId" : "124235431451231341235",
        },
        "number" : 5 
    }
]
```

商户选择某一订单发货，提交orderId

```json
{
    "orderId" : "124235431451231341235"
}
```

查看某一个商户的所有退款请求，提交shopId，返回RefundRequest对象数组

```json
[
    {
        "refundRequestId" : "124235431451231341235",
        "order" : {
            "orderId" : "124235431451231341235",
            "merchandise" : {
                "merchandiseId" : "124235431451231341235",
            },
            "shop" : {
                "shopId" : "124235431451231341235",            
            },
            "user" : {
                "userId" : "124235431451231341235",
            },
            "number" : 5  
        },
    },
    {
        "refundRequestId" : "124235431451231341235",
        "order" : {
            "orderId" : "124235431451231341235",
            "merchandise" : {
                "merchandiseId" : "124235431451231341235",
            },
            "shop" : {
                "shopId" : "124235431451231341235",            
            },
            "user" : {
                "userId" : "124235431451231341235",
            },
            "number" : 5  
        },
    },
    {
        "refundRequestId" : "124235431451231341235",
        "order" : {
            "orderId" : "124235431451231341235",
            "merchandise" : {
                "merchandiseId" : "124235431451231341235",
            },
            "shop" : {
                "shopId" : "124235431451231341235",            
            },
            "user" : {
                "userId" : "124235431451231341235",
            },
            "number" : 5  
        },
    }
]
```

批准某一退款请求，提交refundRequestId,

```json
{
    "refundRequestId" : "124235431451231341235"
}
```
