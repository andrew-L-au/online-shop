<template>
  <body>
  <section>
    <el-table :data="commodityRequest" height="500" id="commodityRequest">
      <el-table-column type="index"/>
      <el-table-column prop="productName" label="商品名称" width="180"></el-table-column>
      <el-table-column fixed="right" prop="requestStatus" label="申请状态" width="120"></el-table-column>
    </el-table>
  </section>
  </body>

</template>

<script>

export default {

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
        url: 'http://192.168.31.196:50000/merchandise/findModify',
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

  },
  mounted: function () {
    this.getShopInfo();
  },

}

</script>

<style scoped>
#commodityRequest {
  table-layout: auto;
  width: 50%;
  margin-left: 30rem;
  border: 2px solid red;
}



</style>