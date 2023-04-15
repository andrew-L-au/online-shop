<template>
  <nav>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#545c64"
      text-color="#fff" active-text-color="#ffd04b">
      <el-sub-menu index="1">
        <template #title>申请处理</template>
        <el-menu-item index="1-1">
          <RouterLink to="/admin/approveOpenStore">开店申请</RouterLink>
        </el-menu-item>
        <el-menu-item index="1-2">
          <RouterLink to="/admin/approveCloseStore">闭店申请</RouterLink>
        </el-menu-item>
        <el-menu-item index="1-3">
          <RouterLink to="/admin/approveAddCommodity">商品上架申请</RouterLink>
        </el-menu-item>
        <el-menu-item index="1-4">
          <RouterLink to="/admin/approveModifyCommodity">商品信息修改申请</RouterLink>
        </el-menu-item>
      </el-sub-menu>
      <div class="flex-grow" />
      <el-sub-menu index="2">
        <template #title>admin账户</template>
        <el-menu-item index="2-1">
          <RouterLink to="/admin/totalProfit">利润</RouterLink>
        </el-menu-item>
        <el-menu-item index="2-2">
          <RouterLink to="/admin/transferCapital">中间资金</RouterLink>
        </el-menu-item>
      </el-sub-menu>
    </el-menu>
  </nav>
</template>

<script>

</script>

<style scoped>
  
</style>