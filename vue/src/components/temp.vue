<!--<template>-->
<!--  <div>-->
<!--    <h2>商品列表</h2>-->
<!--    <div v-for="(commodity, index) in commodityList" :key="index" class="item">-->
<!--      &lt;!&ndash;      bug here&ndash;&gt;-->
<!--      <img :src="commodityList[index].images" alt="商品图片">-->
<!--      <div class="name">{{ commodityList[index].merchandiseName }}</div>-->
<!--      <div class="buttons">-->
<!--        <button @click="editItem(index)">修改</button>-->
<!--        <button @click="deleteItem(index)">删除</button>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import router from "@/router";-->

<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      commodityList:[-->
<!--        {merchandiseId:''},-->
<!--        {merchandiseName:''},-->
<!--        {images:''},-->
<!--        {description:''},-->
<!--        {price:''},-->
<!--      ],-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    mounted() {-->
<!--      this.getItems()-->
<!--    },-->

<!--    getItems(){-->
<!--      this.$axios({-->
<!--        method: 'get',-->
<!--        url: 'http://192.168.31.196:50000/vendor/itemDisplay',-->
<!--      })-->
<!--          .then(resp => {-->
<!--            this.commodityList.pop()-->
<!--            console.log(resp.data)-->
<!--            for (let i = 0; i < resp.data.length; i++) {-->
<!--              let tmp = resp.data[i];-->
<!--              this.commodityList[i].merchandiseId = tmp.merchandiseId-->
<!--              this.commodityList[i].merchandiseName = tmp.merchandiseName-->
<!--              this.commodityList[i].images = tmp.images-->
<!--              this.commodityList[i].description = tmp.description-->
<!--              this.commodityList[i].price = tmp.price-->
<!--            }-->
<!--          })-->
<!--          .catch(err => {-->
<!--            console.log(err);-->
<!--          })-->
<!--    },-->

<!--    editItem(index) {-->
<!--      // TODO: 打开修改界面-->
<!--      router.push({name:"modifyItem",params:{commodityId:this.commodityList[index].merchandiseId}})-->
<!--      console.log(`正在修改第 ${index + 1} 个商品`);-->
<!--    },-->
<!--    deleteItem(index) {-->
<!--      // TODO: 发送删除请求-->
<!--      this.items.splice(index, 1);-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style>-->
<!--.item {-->
<!--  display: inline-block;-->
<!--  margin: 10px;-->
<!--  border: 1px solid #ccc;-->
<!--  padding: 10px;-->
<!--}-->

<!--img {-->
<!--  max-width: 200px;-->
<!--}-->

<!--.name {-->
<!--  margin-top: 5px;-->
<!--  font-size: 16px;-->
<!--}-->

<!--.buttons {-->
<!--  margin-top: 10px;-->
<!--}-->
<!--</style>-->


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
        url: 'http://192.168.31.196:50000/shop/current-shops',
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