<template>
  <section>
    <el-table :data="shopRequest" height="500" id="shopRequest">
      <el-table-column type="index" />
      <el-table-column label="更新数据" width="120">
        <template #default>
          <el-button link type="primary" @click="getShopInfo()">更新数据</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="storeName" label="商店名称" width="180"></el-table-column>
      <el-table-column prop="commodityTypes" label="商品类别" width="200"></el-table-column>
      <el-table-column prop="profile" label="商店简介" width="300"></el-table-column>
      <el-table-column prop="address" label="备案地址" width="200"></el-table-column>
      <el-table-column prop="totalCapital" label="注册资金" width="150"></el-table-column>
      <el-table-column prop="registrationDate" label="注册时间" width="150"></el-table-column>
      <el-table-column fixed="right" prop="requestStatus" label="申请状态" width="120"></el-table-column>
      <el-table-column fixed="right" label="操作" width="120">
        <template #default>
          <el-button type="primary" @click="onSubmit()">批准申请</el-button>
        </template>
      </el-table-column>
    </el-table>

  </section>
</template>

<script>

export default {
  data() {
    return {
      shopRequest: [
        { storeName: '' },
        { commodityTypes: ' ' },
        { profile: '' },
        { address: '' },
        { totalCapital: '' },
        { registrationDate: '' },
        { requestStatus: '' },
      ]
      //openShopRequestId:'',
      // shop : {
      //   shopId :'',
      // }
    }
  },
  methods: {
    getShopInfo() {
      console.log("update!")
      this.$axios({
        method: 'get',
        url: 'http://101.200.57.208:39419/shop/open-shop-requests',
      })
        .then(resp => {
          this.shopRequest.pop()
          console.log(resp.data)
          for (let i = 0; i < resp.data.length; i++) {
            let tmp = resp.data[i];
            let string = ' ';
            for (let j = 0; j < tmp.shop.commodityTypes.length; j++) {
              string += tmp.shop.commodityTypes[j].commodityType;
              string += ' ';
            }
            console.log(string)
            this.shopRequest[i].storeName = tmp.shop.shopBasicInfo.name
            this.shopRequest[i].commodityTypes = string
            this.shopRequest[i].profile = tmp.shop.shopBasicInfo.profile
            this.shopRequest[i].address = tmp.shop.shopBasicInfo.address
            this.shopRequest[i].totalCapital = tmp.shop.shopBasicInfo.totalCapital
            this.shopRequest[i].registrationDate = tmp.shop.shopBasicInfo.registrationDate
            this.shopRequest[i].requestStatus = tmp.requestStatus

            console.log(this.shopRequest[i])

          }

          // this.openShopRequestId = this.shopRequest.openShopRequestId
          // this.shop.shopId = this.shopRequest.shop.shopId
          // console.log(this.shopRequest[0].shop.profile);
          // console.log(this.shopRequest[0].shop.shopId);
        })
        .catch(err => {
          console.log(err);
        })
      console.log(this.shopRequest)
    },

    onSubmit() {
      console.log('submit!')
      this.$axios({
        method: 'post',
        url: 'http://101.200.57.208:39419/shop/approve-open-shop-request',
        data: {
          openShopRequestId: this.shopRequest.openShopRequestId,
          shop: {
            shopId: this.shopRequest.shopId,
          }
        }
      })
        .then(resp => {
          console.log(this.shopRequest[0].openShopRequestId)
          console.log(this.shopRequest[0].shop.shopId)
          alert("批准成功！");
        })
        .catch(err => {
          console.log(err);
        })
    }
  }


}

</script>

<style scoped>
#shopRequest {
  table-layout: auto;
  width: 50%;
  margin-left: 30rem;
  border: 2px solid red;
}
</style>