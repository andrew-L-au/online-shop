<template>
  <section>
    <el-table :data="commodityRequest" height="500" width="1000" id="commodityRequest">
      <el-table-column type="index"/>
<!--      <el-table-column prop="productName" label="商品名称" width="180"></el-table-column>-->
      <el-table-column prop="requestRecordMerchandise.productName" label="商品名称" width="180"></el-table-column>
      <el-table-column prop="requestRecordMerchandise.productDescription" label="商品描述" width="180"></el-table-column>
<!--      <el-table-column prop="requestRecordMerchandise.productImage.base64" label="商品图片" width="180"></el-table-column>-->
      <el-table-column prop="requestRecordMerchandise.productPrice" label="商品价格" width="180"></el-table-column>
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

      commodityRequest: [
        {
          index :'',
          newMerchandiseRequestId: '',
          requestRecordMerchandise: {
            productDescription: '',
            productImage: [
              {
                base64:''
              }
            ],
            productName: '',
            productPrice: '',
            requestRecordMerchandiseId: ''
          },
          requestStatus: ''
        },
      ],
    }
  },
  methods: {
    getShopInfo() {
      console.log("update!")
      this.$axios({
        method: 'get',
        url: 'http://192.168.31.196:50000/merchandise/all-new-merchandise-requests',
      })
          .then(resp => {
            this.commodityRequest = []
            console.log(resp.data)
            for (let i = 0; i < resp.data.length; i++) {
              let tmp = resp.data[i];
              this.commodityRequest.push({
                index: i,
                newMerchandiseRequestId: tmp.newMerchandiseRequestId,
                requestRecordMerchandise: {
                  productDescription: tmp.requestRecordMerchandise.description,
                  productImage: tmp.requestRecordMerchandise.images,
                  productName: tmp.requestRecordMerchandise.merchandiseName,
                  productPrice: tmp.requestRecordMerchandise.price,
                  requestRecordMerchandiseId: tmp.requestRecordMerchandise.requestRecordMerchandiseId
                },
                requestStatus: tmp.requestStatus

              })
              // this.commodityRequest[i].newMerchandiseRequestId = tmp.newMerchandiseRequestId
              // this.commodityRequest[i].productName = tmp.requestRecordMerchandise.merchandiseName
              // this.commodityRequest[i].productImage = tmp.requestRecordMerchandise.images
              // this.commodityRequest[i].productDescription = tmp.requestRecordMerchandise.description
              // this.commodityRequest[i].productPrice = tmp.requestRecordMerchandise.price
              // this.commodityRequest[i].requestStatus = tmp.requestStatus
            }
            console.log(this.commodityRequest)
          })
          .catch(err => {
            console.log(err);
          })
    },
    rejectShop: function (row) {
      this.requestStatus = -1;
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/merchandise/reject-new-merchandise-request',
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
        url: 'http://192.168.31.196:50000/merchandise/approve-new-merchandise-request',
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