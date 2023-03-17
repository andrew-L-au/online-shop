<template>
  <h1>Log in</h1>
  <el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="账号">
      <el-input placeholder="请输入账号" v-model="form.account" clearable></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input placeholder="请输入密码" v-model="form.key" show-password></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">确认</el-button>
      <el-button onclick="window.location.href ='http://127.0.0.1:8000/'">取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import router from "@/router";

export default {
  data() {
    return {
      form: {
        account: '',
        key: '',
      },
      userToken:'',
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!')
      if(this.form.account !== "" && this.form.key !== ""){
        this.$axios({
          method:'post',
          url: 'http://192.168.31.196:53539/user/Login',
          // url: 'http://127.0.0.1:8080/api/user/login',
          data: {
            // principal: "desfweffew",
            // credential: "Zz1234567"
            principal: this.form.account,
            credential: this.form.key
          }
        })
            .then(resp =>{
              // console.log("1")
              if(resp.data === "success"){
                alert("登陆成功！");
                this.userToken = resp.data.token;
                localStorage.setItem("token",this.userToken);
                router.replace('/user')
                //跳转
              }
              // console.log("2")
            })
            .catch( err => {
              console.log(err);
            })
      }
      else{
        alert("填写不能为空！");
      }
      //axios.post('/api/user/login',this.form.account,this.form.key);

    },
  },
}
</script>

<style scoped>

</style>

