<template>
  <div>
    <h1>default</h1>
    <div v-for="(shop, index) in shops" :key="index">
      <h2>{{ shop.shopId }}</h2>
      <div v-for="(commodity,i) in shop.commodities" :key="i">
        <img :src="commodity.images[0].base64" alt="Product Image">
        <div>{{ commodity.merchandiseName }}</div>
        <div>{{ commodity.price }}</div>
        <div class="buttons">
          <button @click="addToCart(index,i)">添加入购物车</button>
        </div>
      </div>
    </div>

  </div>
</template>


<script>
export default {
  data() {
    return {
      shops: [
        {
          shopId: '',
          commodities: [
            {
              merchandiseId: '',
              merchandiseName: '',
              images: [
                {
                  base64 : ''
                }
              ],
              description: '1',
              price: '1'
            },
          ]
        }
      ]
      // shopIds: [{shopId: ''}],
      // commodityList: [
      //   {shopId: ''},
      //   [
      //     {merchandiseId: ''},
      //     {merchandiseName: ''},
      //     {images: ''},
      //     {description: ''},
      //     {price: ''},
      //   ]
      // ],
      //二维数组，index为店序号，i为商品序号

    }
  },
  mounted() {
    this.getShops()
  },

  methods: {
    getShops() {
      this.$axios({
        method: 'get',
        url: 'http://192.168.31.196:50000/shop/current-shops',
      })
          .then(resp => {
            this.shops = []
            for (let i = 0; i < resp.data.length; i++) {
              let tmp = resp.data[i];
              this.shops.push(
                  {
                    shopId: tmp.shopId,
                    commodities : []
                  }
              )
            }
            for (let index = 0; index < this.shops.length; index++) {
              console.log(this.shops)
              this.getItems(index);
            }
          })
          .catch(err => {
            console.log(err);
          })
    },

    getItems(index) {
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/merchandise/merchandises-of-shop',
        data: {
          shopId: this.shops[index].shopId//传index的shopId
        }
      })
          .then(resp => {
            console.log(resp.data)
            this.shops[index].commodities = []
            for (let i = 0; i < resp.data.length; i++) {
              let tmp = resp.data[i];
              this.shops[index].commodities.push(tmp)
              // this.shops[index].commodities[i].merchandiseId = tmp.merchandiseId
              // this.shops[index].commodities[i].merchandiseName = tmp.merchandiseName
              // this.shops[index].commodities[i].images = tmp.images
              // this.shops[index].commodities[i].description = tmp.description
              // this.shops[index].commodities[i].price = tmp.price
            }
            console.log(this.shops[index])
          })
          .catch((err) => {
            console.log(err)
          })


    },

    addToCart(index, i) {
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/shopping-cart/add-merchandise-to-shopping-cart',
        data: {
          userId: localStorage.getItem("userId"),
          merchandiseId: this.shops[index].commodities[i].merchandiseId
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
      console.log('exit!')
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