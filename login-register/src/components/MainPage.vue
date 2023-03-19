<template>
  <body>
    <header>
      <el-button link type="primary" @click="getShopInfo">更新数据</el-button>
      <nav>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#545c64"
          text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="1">
            <RouterLink to="/user">首页</RouterLink>
          </el-menu-item>
          <div class="flex-grow" />
          <el-menu-item index="2">欢迎:<span v-bind=""></span></el-menu-item>
          <el-menu-item index="3">
            <RouterLink to="/">登出</RouterLink>
          </el-menu-item>
        </el-menu>
      </nav>
    </header>


    <section>
      <el-table :data="tableData" border="true" height="500" style="width: 100%;">
        <el-table-column prop="storeName" label="商店名称" width="180"></el-table-column>
        <el-table-column prop="commodityTypes" label="商品类别" width="200"></el-table-column>
        <el-table-column prop="description" label="商店简介" width="300"></el-table-column>
      </el-table>
    </section>
  </body>
</template>


<script>

import { ref, onMounted } from 'vue'
import axios from 'axios';
const activeIndex = ref('1')
// const tableData = [
//   {
//     storeName: '',
//     commodityTypes: '',
//     description: '',
//   },
// ]

export default{
  data(){
    return{
      shopInfo : [],
    }
  },

  methods :{
    getShopInfo(){
      this.$axios({
        method: 'get',
        url: 'http://101.200.57.208:36007/shop/current-shops',
      })
          .then(resp =>{
            console.log(resp.data)
            for (let i = 0; i < resp.data.length; i++){
              this.shopInfo.push(resp.data[i])
            }
            console.log(this.shopInfo)
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
</style>