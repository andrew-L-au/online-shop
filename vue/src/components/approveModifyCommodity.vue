<template>
  <section>
    <el-table :data="commodityRequest" height="500" width="1000" id="commodityRequest">
      <el-table-column type="index"/>
      <el-table-column prop="requestRecordMerchandise.productName" label="商品名称" width="180"></el-table-column>
      <el-table-column prop="requestRecordMerchandise.productDescription" label="商品描述"
                       width="180"></el-table-column>

<!--      <el-table-column prop="requestRecordMerchandise.productImage" label="商品图片" width="180">-->
<!--        <template #default="scope">-->
<!--          {{scope}}}-->
<!--&lt;!&ndash;          <el-button type="success" @click="console.log(scope)">批准申请</el-button>&ndash;&gt;-->
<!--&lt;!&ndash;          <img :src="scope.row.productImage.length > 0 ? null : scope.row.productImage[0].base64" alt="111"/>&ndash;&gt;-->
<!--        </template>-->
<!--          &lt;!&ndash; <img-->
<!--            :src="'data:image/png;base64,'+scope.row.photo"-->
<!--            min-width="70"-->
<!--            height="70"-->
<!--            alt="加载失败"-->
<!--        /> &ndash;&gt;-->
<!--      </el-table-column>-->
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
      index: '',

      commodityRequest: [
        {
          modifyMerchandiseRequestId: '',
          requestRecordMerchandise: {
            requestRecordMerchandiseId: '',
            productName: '',
            productImage: [
              {
                imageId : '',
                base64: '',
              }
            ],
            productDescription: '',
            productPrice: '',
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
        url: 'http://192.168.31.196:50000/merchandise/all-modify-merchandise-requests',
      })
          .then(resp => {
            this.commodityRequest = []
            for (let i = 0; i < resp.data.length; i++) {
              let tmp = resp.data[i];
              this.commodityRequest.push({
                modifyMerchandiseRequestId: tmp.modifyMerchandiseRequestId,
                requestRecordMerchandise: {
                  productDescription: tmp.requestRecordMerchandise.description,
                  productImage: tmp.requestRecordMerchandise.images,
                  productName: tmp.requestRecordMerchandise.merchandiseName,
                  productPrice: tmp.requestRecordMerchandise.price,
                  requestRecordMerchandiseId: tmp.requestRecordMerchandise.requestRecordMerchandiseId
                },
                requestStatus: tmp.requestStatus
              })
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
        url: 'http://192.168.31.196:50000/merchandise/reject-modify-merchandise-request',
        data: {
          modifyMerchandiseRequestId: row.modifyMerchandiseRequestId,
          // requestStatus: row.requestStatus,
        }
      })
          .then(resp => {
            console.log(resp)
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
        url: 'http://192.168.31.196:50000/merchandise/approve-modify-merchandise-request',
        data: {
          modifyMerchandiseRequestId: row.modifyMerchandiseRequestId,
          // requestStatus: row.requestStatus,
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