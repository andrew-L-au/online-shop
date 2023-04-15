<!--<template>-->
<!--  <div class="items">-->
<!--    <h2>商品列表</h2>-->
<!--    <div v-for="(item, index) in items" :key="index" class="item">-->
<!--      <img :src="item.imageUrl" alt="商品图片">-->
<!--      <div class="name">{{ item.name }}</div>-->
<!--      <div class="buttons">-->
<!--        <button @click="addToCart(index)">添加入购物车</button>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->
<!--<template>-->
<!--  <div class="items">-->
<!--    <div v-for="(item, index) in commodityList" :key="index">-->
<!--      <img :src="item.images" alt="product-image" />-->
<!--      <p>{{ item.merchandiseName }}</p>-->
<!--      <p>{{ item.price }}</p>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->
<template>
  <div>
    <div v-for="(commodity, index,i) in commodityList" :key="index">
      <img :src="`data:image/png;base64, ${commodity[index][i].images}`" alt="image">
      <div>{{commodity[index][i].merchandiseName}}</div>
      <div>{{commodity[index][i].price}}</div>
      <div class="buttons">
        <button @click="addToCart(index,i)">添加入购物车</button>
      </div>
    </div>
  </div>
</template>



<script>
export default {
  data() {
    return {
      shopIds: [{shopId: ''}],
      commodityList: [
          [
        {merchandiseId: ''},
        {merchandiseName: ''},
        {images: ''},
        {description: ''},
        {price: ''},
        ]
      ],
      //二维数组，index为店序号，i为商品序号

      items: [
        {imageUrl: 'https://example.com/image1.jpg', name: '商品1'},
        {imageUrl: 'https://example.com/image2.jpg', name: '商品2'},
        {imageUrl: 'https://example.com/image3.jpg', name: '商品3'}
      ]
    }
  },

  methods: {
    mounted() {
      this.getShops()
      for (let index = 0; index < this.shopIds.length; index++) {
        this.getItems(index);
      }
    },

    getShops() {
      this.$axios({
        method: 'get',
        url: 'http://192.168.31.196:50000/shop/current-shops',
      })
          .then(resp => {
            for (let i = 0; i < resp.data.length; i++) {
              let tmp = resp.data[i];
              this.shopIds[i].shopId = tmp.shopId
            }
          })
          .catch(err => {
            console.log(err);
          })
    },

    getItems(index) {
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/shop/find',
        data: {
          shopId: this.shopIds[index].shopId//传index的shopId
        }
      })
          .then((resp) => {
            if (resp.data === "success") {
              console.log('发送成功！')
            }
          })
          .catch((err) => {
            console.log(err)
          })
      this.$axios({
        method: 'get',
        url: 'http://192.168.31.196:50000/shop/find',
      })
          .then(resp => {
            this.commodityList.pop()
            console.log(resp.data)
            for (let i = 0; i < resp.data.length; i++) {
              let tmp = resp.data[i];
              this.commodityList[index][i].merchandiseId = tmp.merchandiseId
              this.commodityList[index][i].merchandiseName = tmp.merchandiseName
              this.commodityList[index][i].images = tmp.images
              this.commodityList[index][i].description = tmp.description
              this.commodityList[index][i].price = tmp.price
            }
          })
          .catch((err) => {
            console.log(err)
          })


    },

    addToCart(index,i) {
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/shopping-cart/add-merchandise',
        data: {
          userId : localStorage.getItem("userId"),
          merchandiseId : this.commodityList[index][i].merchandiseId
          // merchandiseId :
        }
      })
          .then((resp) => {
            if (resp.data === "success") {
              alert('删除成功！')
            }
          })
          .catch((err) => {
            console.log(err)
          })
      console.log(`正在添加第 ${index + 1} 个商品`);
    },


    exit() {
      localStorage.removeItem('token')
    },
  }
}
</script>

<style scoped>
.flex-grow {
  flex-grow: 1;
}

header {
  width: 200px;
  float: left;
}


.el-menu-demo {
  display: inline-block;
  margin-left: 0;
  margin-top: 5rem;
  margin-bottom: 3rem;
  width: 10rem;
  height: 35rem;
  padding: 0;
  float: left;
}

.items {
  margin-left: 200px;
  margin-top: 0;
}
</style>