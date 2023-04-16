<template>
    <section>
      <el-table :data="shopRequest" height="500" id="shopRequest">
        <el-table-column type="index"/>
        <el-table-column prop="storeName" label="商店名称" width="180"></el-table-column>
        <el-table-column prop="commodityTypes" label="商品类别" width="200"></el-table-column>
        <el-table-column prop="profile" label="商店简介" width="300"></el-table-column>
        <el-table-column prop="address" label="备案地址" width="200"></el-table-column>
        <el-table-column prop="totalCapital" label="注册资金" width="150"></el-table-column>
        <el-table-column prop="registrationDate" label="注册时间" width="150"></el-table-column>
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
      rejectShop: function (row) {
        this.isApprove = -1;
        this.$axios({
          method: 'post',
          url: 'http://192.168.31.196:50000/shop/approve-open-shop-request',
          data: {
            name: row.storeName,
            isApprove: this.isApprove,
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
        this.isApprove = 1;
        this.$axios({
          method: 'post',
          url: 'http://192.168.31.196:50000/shop/approve-open-shop-request',
          data:{
            name: row.storeName,
            isApprove: this.isApprove,
          }
          //   isApprove: this.isApprove,
          //   name: row.storeName
          // }
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
  #shopRequest {
    table-layout: auto;
    width: 1200px;
    border: 2px solid red;
  }
  </style>