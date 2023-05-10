<template>
  <header>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#545c64"
      text-color="#fff" active-text-color="#ffd04b">
      <el-menu-item index="1">
        <RouterLink to="/user/display">首页</RouterLink>
      </el-menu-item>
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
      <el-menu-item index="3">
        <RouterLink to="/" @click="exit()">登出</RouterLink>
      </el-menu-item>
    </el-menu>
  </header>
</template>

<script>
export default {
  methods: {
    exit() {
      console.log('exit!')
      localStorage.removeItem('token')
    },
  }
}
</script>

<style scoped>


.el-menu-demo {
  width: 1200px;
}

.items {
  margin-left: 200px;
  margin-top: 0;
}
</style>