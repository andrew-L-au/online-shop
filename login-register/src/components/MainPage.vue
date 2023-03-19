<template style="max-width:100%">
  <body>
    <header>
      <el-button link type="primary" @click="getShopInfo">更新数据</el-button>
      <nav>
        <el-menu :default-active="activeIndex" class="el-menu-demo" background-color="#545c64"
          text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="1">
            <RouterLink to="/user">首页</RouterLink>
          </el-menu-item>
          <div class="flex-grow" />
          <el-menu-item index="2">欢迎<span v-bind=""></span></el-menu-item>
          <el-menu-item index="3">
            <RouterLink to="/">登出</RouterLink>
          </el-menu-item>
        </el-menu>
      </nav>
    </header>


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
        url: 'http://101.200.57.208:39419/shop/current-shops',
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
            // if (i !== 0) {
            //   this.shopInfo.push(this.shopInfo[i - 1])
            // }
            //
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
.flex-grow {
  flex-grow: 1;
}
.el-menu-demo {
  margin-left: 0;
  margin-top: 5rem;
  margin-bottom: 3rem;
  width: 10rem;
  height: 35rem;
  padding: 0;
  float: left;
}
</style>