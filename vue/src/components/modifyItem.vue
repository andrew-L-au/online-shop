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

// {
//   "merchandiseId" : 122341234123512351344,
//     "merchandise" : {
//   "merchandiseName" : "aaaa", //string
//       "images" : [{"a"},{"a"},{"a"}], //string array
//       "description" : "sdfasda",
//       "price" : 2142.324
// }
// }



    onSubmit() {
      let id = localStorage.getItem("commodityId")
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/vendor/modifyItem',
        data: {
          merchandiseId : id,
          merchandise: {
            merchandiseName : this.productName,
            images : this.productImages,
            description : this.productDescription,
            price : this.productPrice,
          },


        }
      })
          .then((resp) => {
            if (resp.data === "success") {
              alert('申请成功！')
            }
          })
          .catch((err) => {
            console.log(err)
          })
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