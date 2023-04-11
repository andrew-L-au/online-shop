<template>
  <div>
    <h2>商品列表</h2>
    <div v-for="(item, index) in items" :key="index" class="item">
      <img :src="item.imageUrl" alt="商品图片">
      <div class="name">{{ item.name }}</div>
      <div class="buttons">
        <button @click="editItem(index)">修改</button>
        <button @click="deleteItem(index)">删除</button>
      </div>
    </div>
  </div>
</template>

<script>
import * as url from "url";

export default {
  data() {
    return {
      items: [
        { imageUrl: 'https://example.com/image1.jpg', name: '商品1' },
        { imageUrl: 'https://example.com/image2.jpg', name: '商品2' },
        { imageUrl: 'https://example.com/image3.jpg', name: '商品3' }
      ]
    }
  },
  methods: {
    mounted() {
      this.getItems()
    },

    getItems(){

    },

    editItem(index) {
      // TODO: 打开修改界面
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
