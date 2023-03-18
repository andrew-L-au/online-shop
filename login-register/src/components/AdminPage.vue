<template>
  <section>
    <!-- <el-table :data="shopRequest" height="500" id="shopRequest">
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

    </el-table> -->
  </section>
</template>

<script>

export default {
  data() {
    return {
      shopRequest:[],
      openShopRequestId:'',
      shop : {
        shopId :'',
      }
    }
  },
  methods: {
    getShopInfo() {
      this.$axios({
        method: 'get',
        url: 'http://101.200.57.208:33909/shop/get-open-shop-requests',
      })
          .then(resp =>{
            this.shopRequest = resp.data
            this.openShopRequestId = this.shopRequest.openShopRequestId
            this.shop.shopId = this.shopRequest.shop.shopId
            console.log(this.shopRequest[0].shop.profile);
            console.log(this.shopRequest[0].shop.shopId);
          })
          .catch(err => {
            console.log(err);
          })
    },

    onSubmit(){
      console.log('submit!')
      this.$axios({
        method: 'post',
        url:'http://101.200.57.208:33909/shop/approve-open-shop-request',
        data: {
          openShopRequestId : this.openShopRequestId,
          shop : {
            shopId : this.shop.shopId,
          }
        }
      })
          .then(resp =>{
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