
# Lab2实现

## api

服务器ip地址 ip-address : 101.200.57.208

* 用户服务 user  
    服务端口 ：  
    user-uri : <http://{ip-address}:{user-post}/user>
  * 用户注册 user registration
    * uri : user-uri/registration
    * 方法 ： post
    * 请求与返回api格式：
      * 请求：
        * 请求体：

        ```json
        {
            "userBasicInfo" : {
                "username" : "abc",
                "phoneNumber" : "123456",
                "idCardNumber" : "3235321234",
                "email" : "3425@dfsads.com"
            },
            "userAuthentication" : {
                "principal" : "abc",
                "credential" : "32908423"
            },
            "userRole": "SHOP_OWNER"
        }
        ```

        *响应体：  

        ```json
           "success"
        ```

  * 用户登录 user login
    * uri ：user-uri/login
    * 方法 ： post
    * 请求与返回api格式：
      * 请求：
        * 请求体：

            ```json
              {
                  "principal" : "abc",
                  "credential" : "32908423"
              }
            ```

        * 响应体：

          ```json
          {
            "token" : "XXX.XXX.XXX",
            "loginSuccess" : true
          }
          ```

* 商店服务  
    服务端口 ：  
    shop-uri : <http://{ip-address}:{shop-post}/shop>
  * 开店请求 request open shop
    * uri : shop-uri/request-open-shop
    * 方法 ： post
    * 请求与返回api格式：
      * 请求：
        * 请求体：

            ```json
             {
                 "shop" : {
                     "shopBasicInfo" : {
                         "profile" : "is a store",
                         "address" : "shanghai china",
                         "totalCapital" : "12412.12",
                         "registrationDate" : "1231-4123-21412"
                 },
                 "commodityTypes" : [
                     {
                         "commodityType" : "apple"
                     },
                     {
                         "commodityType" : "phone"
                     },
                     {
                         "commodityType" : "lab"
                     }]
                 },
                 "idCardNumber" : "3235321234"
             }
           ```

        * 响应体： 无
  * 批准开店请求 approve open shop request
    * uri : shop-uri/approve-open-shop-request
    * 方法 ： post
    * 请求与返回api格式：
      * 请求：
        * 请求体：

            ```json
            {
                "openShopRequestId" : 1636982243912081411,
                    "shop" : {
                    "shopId" : 1636982243865944065
                }
            }
          ```

        * 响应体：无
    * 查看所有开店请求 get open shop requests
      * uri : shop-uri/get-open-shop-requests
      * 方法 ： get
      * 请求与返回api格式：
        * 请求：
          * 请求体：无
          * 响应体：

              ```json
                [
                    {
                        "openShopRequestId": 1636982243912081411,
                        "shop": {
                            "shopId": 1636982243865944065,
                            "shopBasicInfo": {
                                "shopBasicInfoId": 1636982243886915586,
                                "shop": null,
                                "profile": "is a store",
                                "address": "shanghai china",
                                "totalCapital": 12412,
                                "registrationDate": "1231-4123-21412"
                            },
                            "shopOwner": null,
                            "commodityTypes": [
                                {
                                    "commodityTypeId": 1636982243899498498,
                                    "commodityType": "apple"
                                },
                                {
                                    "commodityTypeId": 1636982243899498499,
                                    "commodityType": "phone"
                                },
                                {
                                    "commodityTypeId": 1636982243903692801,
                                    "commodityType": "lab"
                                }
                            ],
                            "shopStatus": "NORMAL"
                        },
                        "requestStatus": "APPROVE"
                    },
                    {
                        "openShopRequestId": 1636982243912081411,
                        "shop": {
                            "shopId": 1636982243865944065,
                            "shopBasicInfo": {
                                "shopBasicInfoId": 1636982243886915586,
                                "shop": null,
                                "profile": "is a store",
                                "address": "shanghai china",
                                "totalCapital": 12412,
                                "registrationDate": "1231-4123-21412"
                            },
                            "shopOwner": null,
                            "commodityTypes": [
                                {
                                    "commodityTypeId": 1636982243899498498,
                                    "commodityType": "apple"
                                },
                                {
                                    "commodityTypeId": 1636982243899498499,
                                    "commodityType": "phone"
                                },
                                {
                                    "commodityTypeId": 1636982243903692801,
                                    "commodityType": "lab"
                                }
                            ],
                            "shopStatus": "NORMAL"
                        },
                        "requestStatus": "APPROVE"
                    }
                ]
              ```

    * 查看所有存在店铺 get current shops
      * uri : shop-uri/get-current-shops
      * 方法 ： get
      * 请求与返回api格式：
        * 请求：
          * 请求体：无
          * 响应体：

              ```json
                [
                    {
                        "shopId": 1636982243865944065,
                        "shopBasicInfo": {
                            "shopBasicInfoId": 1636982243886915586,
                            "shop": null,
                            "profile": "is a store",
                            "address": "shanghai china",
                            "totalCapital": 12412,
                            "registrationDate": "1231-4123-21412"
                        },
                        "shopOwner": null,
                        "commodityTypes": [
                            {
                                "commodityTypeId": 1636982243899498498,
                                "commodityType": "apple"
                            },
                            {
                                "commodityTypeId": 1636982243899498499,
                                "commodityType": "phone"
                            },
                            {
                                "commodityTypeId": 1636982243903692801,
                                "commodityType": "lab"
                            }
                        ],
                        "shopStatus": "NORMAL"
                    }
                ]
              ```

### 实现

本项目通过领域将服务分为独立的实例，使用spring-boot实现每一项应用的独立开发，使用分布式架构进行部署，将实例注册在spring cloud eureka 服务器上，并通过注册服务器实现远程的服务调用。

```java
eureka-service
user-service
shop-service
```

## 认证和鉴权：拦截器

本项目使用拦截器实现权限的认证和鉴权，在用户通过post请求调用登录接口后，验证用户并在响应体中加入生成的token。  
之后调用需要权限的接口时，在请求头中加入token，在拦截器解析token完成校验。

登录

```java
    LoginResponse login(@RequestBody UserAuthentication userAuthentication) throws JsonProcessingException {
        User user = userService.findUser(userAuthentication);
        if (user == null){
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setLoginSuccess(false);
            return loginResponse;
        }
        UserBasicInfo userBasicInfo = userService.findUserBasicInfo(user.getUserId());
        user.setUserBasicInfo(userBasicInfo);
        user.setUserAuthentication(userAuthentication);
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        String token = JWT.create().withClaim("user",userJson).sign(Algorithm.HMAC256("1"));
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setLoginSuccess(true);
        return loginResponse;
    }
```

拦截器

```java
@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null){
            response.setStatus(400);
            return false;
        }else {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("1")).build();
            DecodedJWT jwt = jwtVerifier.verify(token);
            ObjectMapper objectMapper = new ObjectMapper();
            String userAuthenticationJson = jwt.getClaim("userAuthentication").toString();
            UserAuthentication userAuthentication = objectMapper.readValue(userAuthenticationJson, UserAuthentication.class);
            User user = userService.findUser(userAuthentication);
            if (user == null){
                response.setStatus(400);
                return false;
            }
            request.setAttribute("user",user);
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
    }
```

## 业务层

在service层，只处理实体类，实体类的查询和更新都放在数据层，服务层只实现业务逻辑。  
在lab2中，仅有的业务逻辑只有登录，其他业务诸如注册，开店等重点都在于数据库的更新和插入操作。  
当然，本lab的业务中有一些判断新增用户和店铺是否与现有信息重复的逻辑，但都较为简单。

## 数据层

本项目使用MyBatis与MyBatisPlus作为数据连接层的框架，这里重点介绍一下数据层的处理。  
对于复杂的多表业务，也就是内嵌引用类型的实体类。本项目一律使用中间表。连接表储存两个实体类的id值，实现实体类之间的关系。

比如，对于：

```java
public class A {
    private Long aId;
    private B b;
}
```

建一个中间表：  
  a_to_b  
  id , a_id , b_id  

并且建立映射类：

```java
public class AToB {
    private Long id;
    private Long aId;
    private Long bId;
}
```

这样便可以使用 MyBatisPlus 的BaseMapper，在不创建xml文件的情况下实现多表的查询。  
在数据层中，除了操作单表的mapper，再定义repository类，负责 MyBatisPlus 中条件构造器语句的封装以及多表业务

```java
@Mapper
public interface AMapper extends BaseMapper<A> {
    
}

@Mapper
public interface BMapper extends BaseMapper<B> {
    
}

@Mapper
public interface AToBMapper extends BaseMapper<AToB> {
    //一般接口中不定义方法，CRUD方法定义在Repository类中，但对于中间表的Mapper，由于不会有多表业务，可以直接定义
    default Long selectBIdByAId(Long aId){
      return this.selectOne(new QueryWrapper().eq("a_id",aId));
    }
}

@Repository
public class ARepository{

  @Autowired
  private AMapper aMapper;

  @Autowired
  private BMapper bMapper;

  @Autowired
  private AToBMapper aToBMapper;

  public A findAWithAll(Long aId){
    A a = aMapper.selectById(aId);
    Long bId = aToBMapper.selectBIdByAId(aId);
    B b = bMapper.selectById(bId);
    a.setB(b);
    return a;
  }  
}
```

insert 等操作也是同理的，通过中间表来实现嵌套对象的insert

## Lab3

### 个人信息修改

#### 修改普通信息，提交Long userId 和 UserBasicInfo

```json
    {
        "userId" : 122341234123512351344,
        "userBasicInfo" : {
            "username" : "abc",
            "phoneNumber" : "123456",
            "idCardNumber" : "3235321234",
            "email" : "3425@dfsads.com"
        }
    }       
```

#### 修改密码,提交Long userId 和 UserAuthentication

```json
    {
        "userId" : 122341234123512351344,
        "userAuthentication" : {
            "principal" : "abc",
            "credential" : "32908423"
        }
    }    
```

### 账户充值，提交Long accountId 和 Double amount(充值金额)

```json
    {
        "userId" : 122341234123512351344,
        "userAuthentication" : {
            "principal" : "abc",
            "credential" : "32908423"
        }
    }    
```
