<template>
  <div>
    <h2>商品列表</h2>
    <div v-for="(commodity, index) in commodityList" :key="index" class="item">
      <img :src="commodity.images[0].base64" alt="Product Image">
      <div class="name">{{ commodityList[index].merchandiseName }}</div>
      <div class="buttons">
        <button @click="editItem(index)">修改</button>
        <button @click="deleteItem(index)">删除</button>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
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
  },

  mounted() {
    this.getItems()
  },
  methods: {


    getItems(){
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/merchandise/merchandises-of-shop',
        data: {
          shopId : localStorage.getItem("shopId")
        }
      })
          .then(resp => {
            this.commodityList = []
            console.log(resp.data)
            for (let i = 0; i < resp.data.length; i++) {
              let tmp = resp.data[i];
              console.log(tmp)
              this.commodityList.push(tmp)
              // this.commodityList[i].merchandiseId = tmp.merchandiseId
              // this.commodityList[i].merchandiseName = tmp.merchandiseName
              // this.commodityList[i].images = tmp.images
              // this.commodityList[i].description = tmp.description
              // this.commodityList[i].price = tmp.price
            }
            console.log(this.commodityList)
          })
          .catch(err => {
            console.log(err);
          })
    },

    editItem(index) {
      // TODO: 打开修改界面
      router.replace('/vendor/modifyItem');
      localStorage.setItem("commodityId",this.commodityList[index].merchandiseId)
      console.log(`正在修改第 ${index + 1} 个商品`);
    },
    deleteItem(index) {
      // TODO: 发送删除请求
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/merchandise/remove-merchandise',
        data: {
          merchandiseId: this.commodityList[index].merchandiseId
        }
      })
          .then((resp) => {
            console.log(1)
            if (resp.data === "success") {
              ElMessage({
                type: 'success',
                message: '删除成功！',
              })
            }
          })
          .catch((err) => {
            console.log(err)
          })
      this.commodityList.splice(index, 1);
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
