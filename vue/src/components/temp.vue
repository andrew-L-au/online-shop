<!--&lt;!&ndash;<template>&ndash;&gt;-->
<!--&lt;!&ndash;  <div>&ndash;&gt;-->
<!--&lt;!&ndash;    <h2>商品列表</h2>&ndash;&gt;-->
<!--&lt;!&ndash;    <div v-for="(commodity, index) in commodityList" :key="index" class="item">&ndash;&gt;-->
<!--&lt;!&ndash;      &lt;!&ndash;      bug here&ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;      <img :src="commodityList[index].images" alt="商品图片">&ndash;&gt;-->
<!--&lt;!&ndash;      <div class="name">{{ commodityList[index].merchandiseName }}</div>&ndash;&gt;-->
<!--&lt;!&ndash;      <div class="buttons">&ndash;&gt;-->
<!--&lt;!&ndash;        <button @click="editItem(index)">修改</button>&ndash;&gt;-->
<!--&lt;!&ndash;        <button @click="deleteItem(index)">删除</button>&ndash;&gt;-->
<!--&lt;!&ndash;      </div>&ndash;&gt;-->
<!--&lt;!&ndash;    </div>&ndash;&gt;-->
<!--&lt;!&ndash;  </div>&ndash;&gt;-->
<!--&lt;!&ndash;</template>&ndash;&gt;-->

<!--&lt;!&ndash;<script>&ndash;&gt;-->
<!--&lt;!&ndash;import router from "@/router";&ndash;&gt;-->

<!--&lt;!&ndash;export default {&ndash;&gt;-->
<!--&lt;!&ndash;  data() {&ndash;&gt;-->
<!--&lt;!&ndash;    return {&ndash;&gt;-->
<!--&lt;!&ndash;      commodityList:[&ndash;&gt;-->
<!--&lt;!&ndash;        {merchandiseId:''},&ndash;&gt;-->
<!--&lt;!&ndash;        {merchandiseName:''},&ndash;&gt;-->
<!--&lt;!&ndash;        {images:''},&ndash;&gt;-->
<!--&lt;!&ndash;        {description:''},&ndash;&gt;-->
<!--&lt;!&ndash;        {price:''},&ndash;&gt;-->
<!--&lt;!&ndash;      ],&ndash;&gt;-->
<!--&lt;!&ndash;    }&ndash;&gt;-->
<!--&lt;!&ndash;  },&ndash;&gt;-->
<!--&lt;!&ndash;  methods: {&ndash;&gt;-->
<!--&lt;!&ndash;    mounted() {&ndash;&gt;-->
<!--&lt;!&ndash;      this.getItems()&ndash;&gt;-->
<!--&lt;!&ndash;    },&ndash;&gt;-->

<!--&lt;!&ndash;    getItems(){&ndash;&gt;-->
<!--&lt;!&ndash;      this.$axios({&ndash;&gt;-->
<!--&lt;!&ndash;        method: 'get',&ndash;&gt;-->
<!--&lt;!&ndash;        url: 'http://192.168.31.196:50000/vendor/itemDisplay',&ndash;&gt;-->
<!--&lt;!&ndash;      })&ndash;&gt;-->
<!--&lt;!&ndash;          .then(resp => {&ndash;&gt;-->
<!--&lt;!&ndash;            this.commodityList.pop()&ndash;&gt;-->
<!--&lt;!&ndash;            console.log(resp.data)&ndash;&gt;-->
<!--&lt;!&ndash;            for (let i = 0; i < resp.data.length; i++) {&ndash;&gt;-->
<!--&lt;!&ndash;              let tmp = resp.data[i];&ndash;&gt;-->
<!--&lt;!&ndash;              this.commodityList[i].merchandiseId = tmp.merchandiseId&ndash;&gt;-->
<!--&lt;!&ndash;              this.commodityList[i].merchandiseName = tmp.merchandiseName&ndash;&gt;-->
<!--&lt;!&ndash;              this.commodityList[i].images = tmp.images&ndash;&gt;-->
<!--&lt;!&ndash;              this.commodityList[i].description = tmp.description&ndash;&gt;-->
<!--&lt;!&ndash;              this.commodityList[i].price = tmp.price&ndash;&gt;-->
<!--&lt;!&ndash;            }&ndash;&gt;-->
<!--&lt;!&ndash;          })&ndash;&gt;-->
<!--&lt;!&ndash;          .catch(err => {&ndash;&gt;-->
<!--&lt;!&ndash;            console.log(err);&ndash;&gt;-->
<!--&lt;!&ndash;          })&ndash;&gt;-->
<!--&lt;!&ndash;    },&ndash;&gt;-->

<!--&lt;!&ndash;    editItem(index) {&ndash;&gt;-->
<!--&lt;!&ndash;      // TODO: 打开修改界面&ndash;&gt;-->
<!--&lt;!&ndash;      router.push({name:"modifyItem",params:{commodityId:this.commodityList[index].merchandiseId}})&ndash;&gt;-->
<!--&lt;!&ndash;      console.log(`正在修改第 ${index + 1} 个商品`);&ndash;&gt;-->
<!--&lt;!&ndash;    },&ndash;&gt;-->
<!--&lt;!&ndash;    deleteItem(index) {&ndash;&gt;-->
<!--&lt;!&ndash;      // TODO: 发送删除请求&ndash;&gt;-->
<!--&lt;!&ndash;      this.items.splice(index, 1);&ndash;&gt;-->
<!--&lt;!&ndash;    }&ndash;&gt;-->
<!--&lt;!&ndash;  }&ndash;&gt;-->
<!--&lt;!&ndash;}&ndash;&gt;-->
<!--&lt;!&ndash;</script>&ndash;&gt;-->

<!--&lt;!&ndash;<style>&ndash;&gt;-->
<!--&lt;!&ndash;.item {&ndash;&gt;-->
<!--&lt;!&ndash;  display: inline-block;&ndash;&gt;-->
<!--&lt;!&ndash;  margin: 10px;&ndash;&gt;-->
<!--&lt;!&ndash;  border: 1px solid #ccc;&ndash;&gt;-->
<!--&lt;!&ndash;  padding: 10px;&ndash;&gt;-->
<!--&lt;!&ndash;}&ndash;&gt;-->

<!--&lt;!&ndash;img {&ndash;&gt;-->
<!--&lt;!&ndash;  max-width: 200px;&ndash;&gt;-->
<!--&lt;!&ndash;}&ndash;&gt;-->

<!--&lt;!&ndash;.name {&ndash;&gt;-->
<!--&lt;!&ndash;  margin-top: 5px;&ndash;&gt;-->
<!--&lt;!&ndash;  font-size: 16px;&ndash;&gt;-->
<!--&lt;!&ndash;}&ndash;&gt;-->

<!--&lt;!&ndash;.buttons {&ndash;&gt;-->
<!--&lt;!&ndash;  margin-top: 10px;&ndash;&gt;-->
<!--&lt;!&ndash;}&ndash;&gt;-->
<!--&lt;!&ndash;</style>&ndash;&gt;-->


<!--<template style="max-width:100%">-->
<!--  <body>-->
<!--  <section>-->
<!--    <el-table :data="shopInfo" border height="500" style="width: 100%;">-->
<!--      <el-table-column type="index" label="No." width="50"></el-table-column>-->
<!--      <el-table-column prop="storeName" label="商店名称" width="180"></el-table-column>-->
<!--      <el-table-column prop="commodityTypes" label="商品类别" width="200"></el-table-column>-->
<!--      <el-table-column prop="profile" label="商店简介" width="300"></el-table-column>-->
<!--    </el-table>-->
<!--  </section>-->
<!--  </body>-->
<!--</template>-->


<!--<script>-->

<!--export default {-->
<!--  mounted() {-->
<!--    this.getShopInfo()-->
<!--  },-->
<!--  data() {-->
<!--    return {-->
<!--      shopInfo: [-->
<!--        {storeName: ''},-->
<!--        {commodityTypes: ''},-->
<!--        {profile: ''},-->
<!--      ],-->
<!--    }-->

<!--  },-->

<!--  methods: {-->
<!--    getShopInfo() {-->
<!--      this.$axios({-->
<!--        method: 'get',-->
<!--        url: 'http://192.168.31.196:50000/shop/current-shops',-->
<!--      })-->
<!--          .then(resp => {-->
<!--            console.log(resp.data.length)-->
<!--            console.log(resp.data)-->
<!--            this.shopInfo[0].commodityTypes = ' '-->
<!--            for (let i = 0; i < this.shopInfo.length; i++) {-->
<!--              this.shopInfo[i].commodityTypes = ' '-->
<!--            }-->
<!--            console.log(this.shopInfo.commodityTypes)-->
<!--            for (let i = 0; i < resp.data.length; i++) {-->
<!--              let tmp = resp.data[i]-->

<!--              this.shopInfo[i].storeName = tmp.shopBasicInfo.name-->
<!--              for (let j = 0; j < tmp.commodityTypes.length; j++){-->
<!--                console.log(this.shopInfo[i].commodityTypes)-->
<!--                this.shopInfo[i].commodityTypes += tmp.commodityTypes[j].commodityType;-->
<!--                this.shopInfo[i].commodityTypes += ' ';-->
<!--              }-->
<!--              this.shopInfo[i].profile = tmp.shopBasicInfo.profile-->
<!--              console.log(resp.data[i])-->
<!--            }-->

<!--            // console.log(this.shopInfo)-->
<!--          })-->
<!--          .catch(err => {-->
<!--            console.log(err);-->
<!--          })-->
<!--    }-->
<!--  }-->

<!--}-->


<!--</script>-->

<!--<style scoped>-->

<!--</style>-->

<template>
  <div>
    <input type="file" multiple @change="handleFileUpload">
  </div>
</template>

<script>
export default {
  data() {
    return {
      images: [], // 用于保存用户上传的图片
      base64Images: [] // 用于保存转换后的base64编码
    }
  },
  methods: {
    handleFileUpload(event) {
      this.images = event.target.files; // 保存用户上传的图片数组
      this.imagesToBase64(); // 转换为base64编码
    },
    imagesToBase64() {
      for (let i = 0; i < this.images.length; i++) {
        const reader = new FileReader();
        reader.readAsDataURL(this.images[i]); // 转为base64编码
        reader.onload = () => {
          this.base64Images.push(reader.result); // 将转换后的base64编码保存到数组中
        };
      }
    }
  }
}
</script>

