<template>
  <section>
    <el-table :data="commodityRequest" height="500" width="1000" id="commodityRequest">
      <el-table-column type="index"/>
      <el-table-column prop="productName" label="商品名称" width="180"></el-table-column>
      <el-table-column prop="productImage" label="商品图片" width="180"></el-table-column>
      <el-table-column prop="productDescription" label="商品描述" width="180"></el-table-column>
      <el-table-column prop="productPrice" label="商品价格" width="180"></el-table-column>
      <el-table-column fixed="right" prop="requestStatus" label="申请状态" width="120"></el-table-column>
      <el-table-column fixed="right" label="操作" width="240">
        <template #default="{ row }">
          <el-button type="success" @click="approveShop(row)">批准申请</el-button>
          <el-button type="danger" @click="rejectShop(row)">驳回申请</el-button>
        </template>
      </el-table-column>
    </el-table>

  </section>
</template>

<script>

export default {
//TODO:修改data&method
  data() {
    return {
      index: '',
      commodityRequest: [
        {newMerchandiseRequestId: ''},
        {productName: ''},
        {productImage: []},
        {productDescription: ''},
        {productPrice: ''},
        {requestStatus: ''},
      ],
    }
  },
  methods: {
    getShopInfo() {
      console.log("update!")
      this.$axios({
        method: 'get',
        url: 'http://192.168.31.196:50000/admin/approveAddCommodity',
      })
          .then(resp => {
            this.commodityRequest.pop()
            console.log(resp.data)
            for (let i = 0; i < resp.data.length; i++) {
              let tmp = resp.data[i];
              this.commodityRequest[i].index = i
              this.commodityRequest[i].newMerchandiseRequestId = tmp.newMerchandiseRequestId
              this.commodityRequest[i].productName = tmp.requestRecordMerchandise.merchandiseName
              this.commodityRequest[i].productImage = tmp.requestRecordMerchandise.images
              this.commodityRequest[i].productDescription = tmp.requestRecordMerchandise.description
              this.commodityRequest[i].productPrice = tmp.requestRecordMerchandise.price
              this.commodityRequest[i].requestStatus = tmp.requestStatus
            }
          })
          .catch(err => {
            console.log(err);
          })
    },
    rejectShop: function (row) {
      this.requestStatus = -1;
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/admin/approveAddCommodity',
        data: {
          newMerchandiseRequestId: row.newMerchandiseRequestId,
          requestStatus: row.requestStatus,
        }
      })
          .then(resp => {
            // console.log(this.shopRequest[0].openShopRequestId)
            // console.log(this.shopRequest[0].shop.shopId)
            alert("驳回成功！");
          })
          .catch(err => {
            console.log(err);
          })
    },

    approveShop: async function (row) {
      console.log(row)
      console.log(row.storeName)
      this.requestStatus = 1;
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/admin/approveAddCommodity',
        data: {
          newMerchandiseRequestId: row.newMerchandiseRequestId,
          requestStatus: row.requestStatus,
        }
      })
          .then(resp => {
            // console.log(this.shopRequest[0].openShopRequestId)
            // console.log(this.shopRequest[0].shop.shopId)
            alert("批准成功！");
            console.log(resp)
          })
          .catch(err => {
            console.log(err);
          })
    }
  },
  mounted: function () {
    this.getShopInfo();
  },

}

</script>

<style scoped>
#commodityRequest {
  table-layout: auto;
  width: 1200px;
  /*margin-left: 30rem;*/
  border: 2px solid red;
}
</style>