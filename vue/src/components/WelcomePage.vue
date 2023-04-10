<template>
  <body>
    <div class="background"></div>
    <div class="welcomeWord">
      <h1>Welcome to online shopping mall!</h1>
    </div>
    <div class ="option">
      <router-link to="/login">Log In</router-link>
      <br>
      <router-link to="/signup">Sign Up</router-link>
    </div>

  </body>

  <!--  <el-button type="primary" plain>sign up</el-button>-->
</template>

<script>
export default {
  name: "WelcomePage"
}
</script>

<style scoped>

a {
  display: inline-block;
  padding: 0.5em 1em;
  /*background-color: #fff;*/
  color: #181818;
  text-decoration: none;
  font-weight: bold;
}
a:hover {
  border-radius: 4px;
  border: 1px solid rgba(83, 166, 252, 0.55);
  /*background-color: none;*/
  /*border-color: #999;*/
}

  body{
    background: none;
    justify-content: center;
    align-items: center;
    /*height: 100vh;*/
    flex-direction: column;
  }
  .option{
    font-size: 25px;
    font-family: "黑体", sans-serif;
  }
  .background{
    background: url("../assets/images/background1.png") no-repeat center center fixed;
    width: 100%; /* 大小设置成100% */
    height: 100%;
    opacity: 50%;
    position: fixed;
    background-size: 100% 100%;
  }
  .welcomeWord{
    font-size: 24px;
    font-family: "黑体", sans-serif;
  }

</style>