<template>
  <header>
    <h1>Welcome to online shopping system!</h1>
  </header>
<!--  <el-button type="primary">log in</el-button>-->
  <router-link to="/login">log in</router-link>
  <br>
  <router-link to="/signup">sign up</router-link>
  <br>
<!--  <el-button type="primary" plain>sign up</el-button>-->
</template>

<script>
export default {
  name: "WelcomePage"
}
</script>

<style scoped>

</style>