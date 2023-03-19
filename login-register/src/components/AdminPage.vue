<template>
  <el-button link type="primary" @click="getShopInfo">更新数据</el-button>
  <section>
     <el-table :data="shopRequest" height="500" id="shopRequest">
      <el-table-column prop="storeName" label="商店名称" width="180"></el-table-column>
      <el-table-column prop="commodityTypes" label="商品类别" width="200"></el-table-column>
      <el-table-column prop="IdCardNumber" label="身份证号" width="200"></el-table-column>
      <el-table-column prop="description" label="商店简介" width="300"></el-table-column>
      <el-table-column prop="recordAddress" label="备案地址" width="200"></el-table-column>
      <el-table-column prop="money" label="注册资金" width="150"></el-table-column>
      <el-table-column prop="date" label="注册时间" width="150"></el-table-column>
      <el-table-column fixed="right" prop="requestStatus" label="申请状态" width="120"></el-table-column>
      <el-table-column fixed="right" label="操作" width="120">
        <template #default>
          <el-button link type="primary" @click="onSubmit">批准申请</el-button>
        </template>
      </el-table-column>
    </el-table>

  </section>
</template>

<script>

export default {
  data() {
    return {
      shopRequest:[],
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
          .then(resp =>{
            console.log(resp.data)
            for (let i = 0; i < resp.data.length; i++){
              this.shopRequest.push(resp.data[i])
            }
            // for (let i = 0; i < resp.data.length; i++) {
            //   this.shopRequest.push({
            //     openShopRequestId : resp.data[i].openShopRequestId,
            //     shop:{
            //       shopId :resp.data[i].shopId,
            //       shopBasicInfo: {
            //         shopBasicInfoId : resp.data[i].shopBasicInfoId,
            //         shop : resp.data[i].shop,
            //         profile : resp.data[i].profile,
            //         address : resp.data[i].address,
            //         totalCapital: resp.data[i].totalCapital,
            //         registrationDate: resp.data[i].registrationDate,
            //       },
            //       shopOwner : resp.data[i].shopOwner,
            //       commodityTypes: resp.data[i].commodityTypes,
            //       shopStatus :resp.data[i].shopStatus,
            //     },
            //     requestStatus : resp.data[i].requestStatus,
            //   })
            // }
           // this.shopRequest = resp.data
            console.log(this.shopRequest)
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

    onSubmit(){
      console.log('submit!')
      this.$axios({
        method: 'post',
        url:'http://101.200.57.208:39419/shop/approve-open-shop-request',
        data: {
          openShopRequestId : this.shopRequest.openShopRequestId,
          shop : {
            shopId : this.shopRequest.shopId,
          }
        }
      })
          .then(resp =>{
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