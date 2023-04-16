<template>
  <div>
    <h2>新增商品信息</h2>
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
import { ElMessage } from 'element-plus'
export default {
  data() {
    return {
      productName: '',
      productDescription: '',
      productPrice: 0,
      productImages: [],
      base64Images: [],
    }
  },
  methods: {
    onSubmit() {
      // 发送请求将商品信息保存到服务器
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/vendor/addNewItem',
        data: {
          shopId: "",
          merchandise: {
            merchandiseName : this.productName,
            images : this.base64Images,
            description : this.productDescription,
            price : this.productPrice,
          },

        }
      })
          .then((resp) => {
            if (resp.data === "success") {
              ElMessage({
                  type: 'success',
                  message: '申请成功！',
              })
            }
          })
          .catch((err) => {
            console.log(err)
          })
    },
    onFileChange(event, index) {
      const file = event.target.files[0];
      this.$set(this.productImages, index, file);
      this.imagesToBase64();
    },
    imagesToBase64() {
      for (let i = 0; i < this.productImages.length; i++) {
        const reader = new FileReader();
        reader.readAsDataURL(this.productImages[i]); // 转为base64编码
        reader.onload = () => {
          this.base64Images.push(reader.result); // 将转换后的base64编码保存到数组中
        };
      }
    },
    addImageUploader() {
      this.productImages.push(null);
    }
  }
}
</script>
