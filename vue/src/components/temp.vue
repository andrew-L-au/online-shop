<template>
  <div>
    <h2>商品上架</h2>
    <form @submit.prevent="submitProduct">
      <div>
        <label for="description">商品描述：</label>
        <input type="text" id="description" v-model="state.product.description">
      </div>
      <div>
        <label for="price">商品价格：</label>
        <input type="number" id="price" v-model="state.product.price" step="0.01" min="0">
      </div>
      <div>
        <label for="images">商品图片：</label>
        <input type="file" id="images" multiple @change="handleFileUpload">
      </div>
      <button type="submit">提交</button>
    </form>
  </div>
</template>

<script>
import { reactive } from 'vue'
import axios from 'axios'

export default {
  setup() {
    const state = reactive({
      // 定义商品信息对象，包含商品图片、商品描述和商品价格等属性
      product: {
        images: [],
        description: '',
        price: 0
      }
    })

    // 处理文件上传
    const handleFileUpload = (event) => {
      state.product.images = [...event.target.files]
    }

    // 提交上架申请
    const submitProduct = async () => {
      try {
        const formData = new FormData()
        for (let i = 0; i < state.product.images.length; i++) {
          formData.append('images', state.product.images[i])
        }
        formData.append('description', state.product.description)
        formData.append('price', state.product.price)
        await axios.post('/api/submit', formData)
        router.push('/merchant/records')
      } catch (error) {
        console.error(error)
      }
    }

    return {
      state,
      handleFileUpload,
      submitProduct
    }
  }
}
</script>
