<template>
  <div class="cart">
    <h2>购物车</h2>
    <div class="cart-items">
      <div v-for="(item, index) in cartItems" :key="index" class="cart-item">
        <img :src="item.image" alt="商品图片" />
        <div class="item-name">{{ item.name }}</div>
        <button @click="removeItem(index)">删除</button>
        <input type="checkbox" v-model="item.checked" />
      </div>
    </div>
    <button @click="removeSelectedItems">删除选中商品</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cartItems: [
        {
          name: '商品1',
          image: 'https://via.placeholder.com/150',
          checked: false,
        },
        {
          name: '商品2',
          image: 'https://via.placeholder.com/150',
          checked: false,
        },
        {
          name: '商品3',
          image: 'https://via.placeholder.com/150',
          checked: false,
        },
        {
          name: '商品4',
          image: 'https://via.placeholder.com/150',
          checked: false,
        },
        {
          name: '商品5',
          image: 'https://via.placeholder.com/150',
          checked: false,
        },
        {
          name: '商品6',
          image: 'https://via.placeholder.com/150',
          checked: false,
        },
      ],
    };
  },
  methods: {
    removeItem(index) {
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
