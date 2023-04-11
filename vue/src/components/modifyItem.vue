<template>
  <div>
    <h2>修改商品信息</h2>
    <form @submit.prevent="onSubmit">
      <div>
        <label for="name">商品名称：</label>
        <input id="name" type="text" v-model="productName" required>
      </div>
      <div>
        <label for="description">商品描述：</label>
        <textarea id="description" v-model="productDescription" maxlength="128" required></textarea>
      </div>
      <div>
        <label for="price">商品价格：</label>
        <input id="price" type="number" step="0.01" v-model="productPrice" min="0.01" required>
      </div>
      <div>
        <label for="images">商品图片：</label>
        <div v-for="(image, index) in productImages" :key="index">
          <input type="file" accept="image/*" @change="onFileChange($event, index)">
        </div>
        <button type="button" @click="addImageUploader">添加图片</button>
      </div>
      <button type="submit">添加商品</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      productName: '',
      productDescription: '',
      productPrice: 0,
      productImages: []
    }
  },
  methods: {
    mounted: function () {
      this.getItemInfo();
    },

    getItemInfo(){
      //TODO: 获取商品原信息
    },

    onSubmit() {
      // 发送请求将商品信息保存到服务器
      const formData = new FormData();
      formData.append('name', this.productName);
      formData.append('description', this.productDescription);
      formData.append('price', this.productPrice);
      this.productImages.forEach((file, index) => {
        formData.append(`image${index}`, file);
      });
      // TODO: 发送请求将 formData 提交到服务器

      // 清空表单
      this.productName = '';
      this.productDescription = '';
      this.productPrice = 0;
      this.productImages = [];
    },
    onFileChange(event, index) {
      const file = event.target.files[0];
      this.$set(this.productImages, index, file);
    },
    addImageUploader() {
      this.productImages.push(null);
    }
  }
}
</script>