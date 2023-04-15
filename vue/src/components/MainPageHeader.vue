<template>
  <header>
    <el-menu :default-active="activeIndex" class="el-menu-demo" background-color="#545c64" text-color="#fff"
             active-text-color="#ffd04b">
      <el-menu-item index="1">
        <RouterLink to="/user/display">首页</RouterLink>
      </el-menu-item>
      <div class="flex-grow"/>
      <el-sub-menu index="2">
        <template #title>个人中心</template>
        <el-menu-item index="2-1">
          <RouterLink to="/user/userCenter/basicInfo">个人信息</RouterLink>
        </el-menu-item>
        <el-sub-menu index="2-2">
          <template #title>账户</template>
          <el-menu-item index="2-2-1">
            <RouterLink to="/user/userCenter/accounts/personal">个人账户</RouterLink>
          </el-menu-item>
          <el-menu-item index="2-2-2">
            <RouterLink to="/user/userCenter/accounts/shop">商店账户</RouterLink>
          </el-menu-item>
        </el-sub-menu>
        <el-menu-item index="2-3">
          <RouterLink to="/user/userCenter/shoppingCarts">购物车</RouterLink>
        </el-menu-item>
      </el-sub-menu>
      <el-menu-item index="3" onclick="exit()">
        <RouterLink to="/">登出</RouterLink>
      </el-menu-item>
    </el-menu>
  </header>
  <div class="items">
    <h2>商品列表</h2>
    <div v-for="(item, index) in items" :key="index" class="item">
      <img :src="item.imageUrl" alt="商品图片">
      <div class="name">{{ item.name }}</div>
      <div class="buttons">
        <button @click="addToCart(index)">添加入购物车</button>
      </div>
    </div>
  </div>
</template>

<script>
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

    addToCart(index) {
      // TODO: 打开修改界面
      console.log(`正在修改第 ${index + 1} 个商品`);
    },


    exit() {
      localStorage.removeItem('token')
    },
  }
}
</script>

<style scoped>
.flex-grow {
  flex-grow: 1;
}  header{
  width: 200px;
  float: left;
}


.el-menu-demo {
  display: inline-block;
  margin-left: 0;
  margin-top: 5rem;
  margin-bottom: 3rem;
  width: 10rem;
  height: 35rem;
  padding: 0;
  float: left;
}

.items{
  margin-left: 200px;
  margin-top: 0;
}
</style>