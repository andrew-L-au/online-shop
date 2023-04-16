<template>
  <header>
    <RouterView></RouterView>
  </header>

  <body>
  <div class="noShopOwned" style="display: none;">
    <p style="font-size: 24px;">当前没有商店</p>
    <el-button id="openStore">
      <RouterLink to="/vendor/openStore">创建商店</RouterLink>
    </el-button>
  </div>
  <div class="hasShopOpened" style="display: none;">
    <section>
      <el-descriptions title="商店基本信息" :column="3" border>
        <el-descriptions-item label="商店名称">{{ shop.shopBasicInfo.name }}</el-descriptions-item>
        <el-descriptions-item label="商店简介">{{ shop.shopBasicInfo.profile }}</el-descriptions-item>
        <el-descriptions-item label="备案地址">{{ shop.shopBasicInfo.address }}</el-descriptions-item>
        <el-descriptions-item label="注册资金">{{ shop.shopBasicInfo.totalCapital }}</el-descriptions-item>
        <el-descriptions-item label="注册时间">{{ shop.shopBasicInfo.registrationDate }}</el-descriptions-item>
        <el-descriptions-item label="商品类别">{{ strCommodityTypes }}</el-descriptions-item>
        <el-descriptions-item label="商店账户余额">{{ account.balance }}</el-descriptions-item>
        <el-descriptions-item label="商店状态">{{ shop.shopStatus }}</el-descriptions-item>
      </el-descriptions>
    </section>
    <el-button type="primary" @click="showHideBlock" round>商店账户充值</el-button>
    <div style="display: none;" class="hidden">
      <el-input type="number" v-model="amount" placeholder="请输入充值金额"/>
      <el-button style="primary" @click="addBalance">充值</el-button>
      <el-button @click="showHideBlock">取消</el-button>
    </div>
    <el-button>
      <RouterLink to='/vendor/addNewItem' type="primary" plain>新增商店商品</RouterLink>
    </el-button>
    <el-button>
      <RouterLink to='/vendor/itemDisplay' type="primary" plain>管理商店商品</RouterLink>
    </el-button>
    <el-button id="closeStore" @click="popMessageBox">关闭商店</el-button>
  </div>
  </body>
</template>

<script>
import {ElMessage, ElMessageBox} from 'element-plus'

export default {
  data() {
    return {
      userId: localStorage.getItem('userId'),
      shop: {
        shopId: '',
        shopBasicInfo: {
          name: '',
          profile: '',
          address: '',
          totalCapital: '',
          registrationDate: '',
        },
        commodityTypes: [],
        shopStatus: '',
      },
      account: {
        accountId: '',
        balance: 0,
      },
      strCommodityTypes: '',
      amount: '',
    }
  },
  methods: {
    getShopInfo() {
      this.$axios({
        method: "post",
        //test
        url: "http://192.168.31.196:50000/shop/shop-of-user",
        data: {
          userId: this.userId,
        }
      })
          .then(resp => {
            console.log(resp.data)
            console.log(this.$data)
            if (resp.data === '') {   //当前商户未开商店
              var show = document.querySelector(".noShopOwned");
              show.style.display = "";
            } else {
              show = document.querySelector('.hasShopOpened');
              show.style.display = "";
              this.shop = resp.data
              localStorage.setItem('shopId', this.shop.shopId)
              this.strCommodityTypes = '';
              for (let i = 0; i < this.shop.commodityTypes.length; i++) {
                console.log(this.shop.commodityTypes[i])

                this.strCommodityTypes += this.shop.commodityTypes[i].commodityType;
                console.log(this.strCommodityTypes)

                this.strCommodityTypes += ' ';
              }

            }
          })
          .catch(err => console.log(err));
    },
    popMessageBox() {
      ElMessageBox.confirm(
          '将关闭商店并注销商店账户，是否继续？',
          'Warning',
          {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
          }
      )
          .then(() => {
            this.$axios({
              method: 'post',
              url: 'http://192.168.31.196:50000/shop/request-close-shop',
              data: {
                shopId: this.shop.shopId,
              }
            })
                .then((resp) => {
                  console.log("closure")
                  if (resp.data === 'success') {
                    ElMessage({
                      type: 'success',
                      message: '成功提交闭店申请，等待批准中！',
                    })
                    //商店状态更新
                    this.getShopInfo();
                    console.log("closure")
                  }
                })
                .catch(err => {
                  console.log(err)
                  ElMessage({
                    type: 'error',
                    message: '申请失败，请稍后重试！',
                  })
                })
          })
          .catch(() => {
            ElMessage({
              type: 'info',
              message: '取消操作',
            })
          })
    },
    getAccountInfo() {
      this.$axios({
        method: 'post',
        //TODO 核对后端接口
        url: 'http://192.168.31.196:50000/account/account-of-shop',
        data: {
          shopId: localStorage.getItem("shopId"),
        }
      })
          .then(resp => {
            console.log(resp.data)
            this.account = resp.data
            localStorage.setItem('shopAccountId', this.account.accountId)
            console.log(this.account)
          })
          .catch(err => {
            console.log(err);
          })
    },
    addBalance() {
      if (this.amount === '' || this.amount <= 0) {
        ElMessage({
          type: 'error',
          message: '请输入正确的充值金额',
        })
      } else {
        this.$axios({
          method: 'post',
          url: 'http://192.168.31.196:50000/account/redeem-account',
          data: {
            accountId: this.account.accountId,
            amount: 0,
          }
        })
            .then(resp => {
              if (resp.data === "success") {
                ElMessage({
                  type: 'success',
                  message: '充值成功',
                })
              }
              console.log(this.amount)
              this.amount = 0
              //重新加载显示余额
              this.showHideBlock()
              this.getAccountInfo()
            })
            .catch(err => {
              console.log(err)
              ElMessage({
                type: 'error',
                message: '充值失败，请重试',
              })
            })
      }
    },
    showHideBlock() {
      // 动态显示和隐蔽
      var block = document.querySelector('.hidden');
      if (block.style.display === "none") {
        block.style.display = "block";
      } else {
        block.style.display = "none";
      }
    },
  },

  mounted() {
    this.getShopInfo();
    this.getAccountInfo();
  },
}
</script>

<style scoped>
body {
  background: none;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  flex-wrap: nowrap;
}

#openStore {
  font-size: 25px;
  font-weight: bold;
  padding: 1em;
  margin-top: 3em;
}

.el-descriptions {
  margin-top: 20px;
  text-align: center;
}

.el-button {
  margin-top: 3em;
  margin-left: 1em;
  padding: 1em;
  font-size: 20px;
}
</style>