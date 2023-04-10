<template style="max-width:100%">
    <body>
      <section>
        <el-table :data="shopInfo" border height="500" style="width: 100%;">
          <el-table-column type="index" label="No." width="50"></el-table-column>
          <el-table-column prop="storeName" label="商店名称" width="180"></el-table-column>
          <el-table-column prop="commodityTypes" label="商品类别" width="200"></el-table-column>
          <el-table-column prop="profile" label="商店简介" width="300"></el-table-column>
        </el-table>
      </section>
    </body>
  </template>
  
  
  <script>
  
  export default {
    mounted() {
      this.getShopInfo()
    },
    data() {
      return {
        shopInfo: [
          {storeName: ''},
          {commodityTypes: ''},
          {profile: ''},
        ],
      }
      
    },
  
    methods: {
      getShopInfo() {
        this.$axios({
          method: 'get',
          url: '/shop/current-shops',
        })
          .then(resp => {
            console.log(resp.data.length)
            console.log(resp.data)
            this.shopInfo[0].commodityTypes = ' '
            for (let i = 0; i < this.shopInfo.length; i++) {
              this.shopInfo[i].commodityTypes = ' '
            }
            console.log(this.shopInfo.commodityTypes)
            for (let i = 0; i < resp.data.length; i++) {
              let tmp = resp.data[i]
              
              this.shopInfo[i].storeName = tmp.shopBasicInfo.name
              for (let j = 0; j < tmp.commodityTypes.length; j++){
                console.log(this.shopInfo[i].commodityTypes)
                this.shopInfo[i].commodityTypes += tmp.commodityTypes[j].commodityType;
                this.shopInfo[i].commodityTypes += ' ';
              }
              this.shopInfo[i].profile = tmp.shopBasicInfo.profile
              console.log(resp.data[i])
            }
  
            // console.log(this.shopInfo)
          })
          .catch(err => {
            console.log(err);
          })
      }
    }
  
  }
  
  
  </script>
  
  <style scoped>

  </style>