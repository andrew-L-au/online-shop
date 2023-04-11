<template>
  <body>
  <section>
    <el-table :data="shopRequest" height="500" id="shopRequest">
      <el-table-column type="index"/>
      <el-table-column prop="storeName" label="商店名称" width="180"></el-table-column>
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
      shopRequest: [
        {storeName: ''},
        {commodityTypes: ' '},
        {profile: ''},
        {address: ''},
        {totalCapital: ''},
        {registrationDate: ''},
        {requestStatus: ''},
      ],
      isApprove: 0,
      // openShopRequestId: '',
      // shop: {
      //   shopId: '',
      // }
    }
  },
  methods: {
    getShopInfo() {
      console.log("update!")
      this.$axios({
        method: 'get',
        url: 'http://192.168.31.196:50000/shop/open-shop-requests',
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
              this.shopRequest[i].index = i
              this.shopRequest[i].storeName = tmp.shop.shopBasicInfo.name
              this.shopRequest[i].commodityTypes = string
              this.shopRequest[i].profile = tmp.shop.shopBasicInfo.profile
              this.shopRequest[i].address = tmp.shop.shopBasicInfo.address
              this.shopRequest[i].totalCapital = tmp.shop.shopBasicInfo.totalCapital
              this.shopRequest[i].registrationDate = tmp.shop.shopBasicInfo.registrationDate
              this.shopRequest[i].requestStatus = tmp.requestStatus

              console.log(this.shopRequest[i])

            }
          })
          .catch(err => {
            console.log(err);
          })
      console.log(this.shopRequest)
    },

  },
  mounted: function () {
    this.getShopInfo();
  },

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