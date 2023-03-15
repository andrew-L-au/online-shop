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
export default {
  data() {
    return {
      form: {
        account: '',
        key: '',
      },
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!')
      if(this.form.account !== "" && this.form.key !== ""){
        this.$axios({
          method:'post',
          url: 'http://127.0.0.1:8080/api/user/login',
          data: {
            account: this.form.account,
            key: this.form.key
          }
        })
            .then(resp =>{
              if(resp.data.code === 1){
                alert("登陆成功！");
                //跳转
              }
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

