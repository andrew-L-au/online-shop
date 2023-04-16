<template>
  <div class="cart">
    <h2>购物车</h2>
    <div class="cart-items">
      <div v-for="(commodity, index) in shoppingCart.commodityList" :key="index" class="cart-item">
        <img :src="commodity.images[0].base64" alt="Product Image">
        <div class="item-name">{{ commodity.merchandiseName }}</div>
        <button @click="removeItem(index)">删除</button>
        <input type="checkbox" v-model="commodity.checked"/>
      </div>
    </div>
    <button @click="removeSelectedItems">删除选中商品</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      shoppingCart:{
        shoppingCartId: '',
        commodityList:[
          {
            merchandiseId:'',
            merchandiseName:'',
            images:[
              {
                base64 : ''
              }
            ],
            description:'',
            price:''
          }
        ],
      }
    };
  },
  mounted() {
    this.getCartInfo();
  },

  methods: {


    getCartInfo() {
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/shopping-cart/get-shopping-cart',
        data: {
          userId: localStorage.getItem("userId"),
        }
      })
          .then(resp => {
            this.shoppingCart.commodityList = []
            console.log(resp.data)
            let tmp = resp.data
            this.shoppingCart.shoppingCartId = tmp.shoppingCartId
            this.shoppingCart.commodityList = tmp.merchandises

            console.log(this.shoppingCart.commodityList)
          })
          .catch(err => {
            console.log(err);
          })
    },

    removeItem(index) {
      console.log(this.shoppingCart.commodityList[index].merchandiseId)
      console.log(localStorage.getItem("userId"))
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/shopping-cart/remove-merchandise-from-shopping-cart',
        data: {
          userId : localStorage.getItem("userId"),
          merchandiseId : this.shoppingCart.commodityList[index].merchandiseId
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
      this.shoppingCart.commodityList.splice(index, 1);
    },
    removeSelectedItems() {
      this.shoppingCart.commodityList = this.shoppingCart.commodityList.filter((item) => !item.checked);
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
