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
          <input type="number" v-model="commodity.quantity" min="1" max="999">
          <button @click="addToCart(index,i)">添加到购物车</button>
        </div>
      </div>
    </div>

    <h1>购物车</h1>
    <div v-for="(item, index) in cart" :key="index">
      <div>{{ item.commodity.merchandiseName }}</div>
      <div>{{ item.commodity.price }} 元 × {{ item.quantity }} 件</div>
      <div class="buttons">
        <button @click="decrementQuantity(index)">-</button>
        <button @click="incrementQuantity(index)">+</button>
        <button @click="removeFromCart(index)">移除</button>
      </div>
    </div>
    <div>总价：{{ totalPrice }} 元</div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus';
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
                  base64: ''
                }
              ],
              description: '1',
              price: '1',
              quantity: 1
            },
          ]
        }
      ],
      cart: []
    }
  },
  mounted() {
    this.getShops()
  },

  computed: {
    totalPrice() {
      let total = 0
      for (let i = 0; i < this.cart.length; i++) {
        total += this.cart[i].commodity.price * this.cart[i].quantity
      }
      return total
    }
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
                    commodities: []
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
          shopId: this.shops[index].shopId
        }
      })
          .then(resp => {
            console.log(resp.data)
            this.shops[index].commodities = []
            for (let i = 0; i < resp.data.length; i++) {
              let tmp = resp.data[i];
              tmp.quantity = 1
              this.shops[index].commodities.push(tmp)
            }
            console.log(this.shops[index])
          })
          .catch((err) => {
            console.log(err)
          })
    },

    addToCart(index, i) {
      let item = {
        commodity: this.shops[index].commodities[i],
        quantity: this.shops[index].commodities[i].quantity
      }
      let existingItem = this.cart.find(item => item.commodity.merchandiseId === this.shops[index].commodities[i].merchandiseId)
      if (existingItem) {
        existingItem.quantity += this.shops[index].commodities[i].quantity
      } else {
        this.cart.push(item)
      }
      ElMessage({
        type: 'success',
        message: '添加成功！',
      })
      console.log(`正在添加第 ${index + 1} 个商品`);
    },

    incrementQuantity(index) {
      this.cart[index].quantity++
    },

    decrementQuantity(index) {
      if (this.cart[index].quantity > 1) {
        this.cart[index].quantity--
      } else {
        this.removeFromCart(index)
      }
    },

    removeFromCart(index) {
      this.cart.splice(index, 1)
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