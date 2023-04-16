<template>
  <div class="cart">
    <h2>购物车</h2>
    <div class="cart-items">
      <div v-for="(item, index) in commodityList" :key="index" class="cart-item">
        <img :src="`data:image/png;base64, ${item.images}`" alt="商品图片"/>
        <div class="item-name">{{ item.merchandiseName }}</div>
        <button @click="removeItem(index)">删除</button>
        <input type="checkbox" v-model="item.checked"/>
      </div>
    </div>
    <button @click="removeSelectedItems">删除选中商品</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      shoppingCartId: '',
      commodityList: [
        {merchandiseId: ''},
        {merchandiseName: ''},
        {images: ''},
        {description: ''},
        {price: ''},
        {checked: ''}
      ],
      //
      // cartItems: [
      //   {
      //     name: '商品1',
      //     image: 'https://via.placeholder.com/150',
      //     checked: false,
      //   },
      //   {
      //     name: '商品2',
      //     image: 'https://via.placeholder.com/150',
      //     checked: false,
      //   },
      //   {
      //     name: '商品3',
      //     image: 'https://via.placeholder.com/150',
      //     checked: false,
      //   },
      //   {
      //     name: '商品4',
      //     image: 'https://via.placeholder.com/150',
      //     checked: false,
      //   },
      //   {
      //     name: '商品5',
      //     image: 'https://via.placeholder.com/150',
      //     checked: false,
      //   },
      //   {
      //     name: '商品6',
      //     image: 'https://via.placeholder.com/150',
      //     checked: false,
      //   },
      // ],
    };
  },
  methods: {
    mounted() {
      this.getCartInfo();
    },

    getCartInfo() {
      this.$axios({
        method: 'get',
        url: 'http://192.168.31.196:50000/user/userCenter/shoppingCarts',
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

    removeItem(index) {
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/user/userCenter/shoppingCarts',
        data: {
          userId : localStorage.getItem("userId"),
          merchandiseId : this.commodityList[index].merchandiseId
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
      this.cartItems.splice(index, 1);
    },
    removeSelectedItems() {
      this.cartItems = this.cartItems.filter((item) => !item.checked);
    },
  },
};
</script>

<style>
.cart-items {
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  /*justify-content: space-between;*/
}

.cart-item {
  width: calc(25% - 10px);
  margin-bottom: 10px;
  border: 1px solid #ccc;
  padding: 10px;
}
</style>
