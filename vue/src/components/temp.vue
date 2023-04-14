<template>
  <div>
    <h2>商品列表</h2>
    <div v-for="(commodity, index) in commodityList" :key="index" class="item">
      <!--      bug here-->
      <img :src="commodityList[index].images" alt="商品图片">
      <div class="name">{{ commodityList[index].merchandiseName }}</div>
      <div class="buttons">
        <button @click="editItem(index)">修改</button>
        <button @click="deleteItem(index)">删除</button>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";

export default {
  data() {
    return {
      commodityList:[
        {merchandiseId:''},
        {merchandiseName:''},
        {images:''},
        {description:''},
        {price:''},
      ],
    }
  },
  methods: {
    mounted() {
      this.getItems()
    },

    getItems(){
      this.$axios({
        method: 'get',
        url: 'http://192.168.31.196:50000/vendor/itemDisplay',
      })
          .then(resp => {
            this.commodityList.pop()
            console.log(resp.data)
            for (let i = 0; i < resp.data.length; i++) {
              let tmp = resp.data[i];
              this.commodityList[i].merchandiseId = tmp.merchandiseId
              this.commodityList[i].merchandiseName = tmp.merchandiseName
              this.commodityList[i].images = tmp.images
              this.commodityList[i].description = tmp.description
              this.commodityList[i].price = tmp.price
            }
          })
          .catch(err => {
            console.log(err);
          })
    },

    editItem(index) {
      // TODO: 打开修改界面
      router.push({name:"modifyItem",params:{commodityId:this.commodityList[index].merchandiseId}})
      console.log(`正在修改第 ${index + 1} 个商品`);
    },
    deleteItem(index) {
      // TODO: 发送删除请求
      this.items.splice(index, 1);
    }
  }
}
</script>

<style>
.item {
  display: inline-block;
  margin: 10px;
  border: 1px solid #ccc;
  padding: 10px;
}

img {
  max-width: 200px;
}

.name {
  margin-top: 5px;
  font-size: 16px;
}

.buttons {
  margin-top: 10px;
}
</style>
