<template>
  <body>
  <div class="background"></div>
  <h1>Log in</h1>
  <el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="账号">
      <el-input placeholder="请输入用户名" v-model="form.account" clearable></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input placeholder="请输入密码" v-model="form.key" show-password></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">确认</el-button>
      <el-button onclick="window.location.href ='http://127.0.0.1:8000/'">取消</el-button>
    </el-form-item>
  </el-form>
  </body>

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
      userToken: '',
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!')
      if (this.form.account !== "" && this.form.key !== "") {
        this.$axios({
          method: 'post',
          // url: 'http://192.168.31.196:50000/user/login',
          //test
          // url: 'https://run.mocky.io/v3/a343ccf3-1d6f-417f-a9da-e852595acf7b', //admin
          url: 'https://run.mocky.io/v3/4a1c4b75-7616-4305-84df-ef7108dc3aca', //shop_owner
          data: {
            // principal: "desfweffew",
            // credential: "Zz1234567"
            principal: this.form.account,
            credential: this.form.key
          }
        })
            .then(resp => {
              console.log(resp)
              if (resp.data.token) {
                alert("登录成功！");
                this.userToken = resp.data.token;

                localStorage.setItem("token", this.userToken);
                //判断为商户和用户
                // var token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoie1widXNlcklkXCI6MTYzNjk3Njg4MDU5NjgxNTg3MyxcInVzZXJCYXNpY0luZm9cIjp7XCJ1c2VyQmFzaWNJbmZvSWRcIjoxNjM2OTc2ODgwNTY3NDU1NzQ1LFwidXNlclwiOm51bGwsXCJ1c2VybmFtZVwiOlwiYWJjXCIsXCJwaG9uZU51bWJlclwiOlwiMTIzNDU2XCIsXCJpZENhcmROdW1iZXJcIjpcIjMyMzUzMjEyMzRcIixcImVtYWlsXCI6XCIzNDI1QGRmc2Fkcy5jb21cIn0sXCJ1c2VyQXV0aGVudGljYXRpb25cIjp7XCJ1c2VyQXV0aGVudGljYXRpb25JZFwiOm51bGwsXCJ1c2VyXCI6bnVsbCxcInByaW5jaXBhbFwiOlwiYWJjXCIsXCJjcmVkZW50aWFsXCI6XCIzMjkwODQyM1wifSxcInVzZXJSb2xlXCI6XCJTSE9QX09XTkVSXCJ9In0.4tgrJAyQ_K92YxtxV-Kdk1IclkKC2Oa6POxgRYOsEyc'
                let strings = this.userToken.split("."); //截取token，获取载体
                var userinfo = JSON.parse(decodeURIComponent(escape(window.atob(strings[1].replace(/-/g, "+").replace(/_/g, "/"))))); //解析，需要吧‘_’,'-'进行转换否则会无法解析
                var info = JSON.parse(userinfo.user)
                console.log(info.userId)
                localStorage.setItem('userId', info.userId);
                console.log(info.userRole)
                let role = info.userRole
                console.log(role)
                if(role === "CUSTOMER" || role === 'SHOP_OWNER') router.replace('/user')
                else if (role === "ADMINISTRATOR") router.replace('/admin')
                //跳转
              }
              else{
                alert("登录失败！");
              }
              // console.log("2")
            })
            .catch(err => {
              console.log(err);
            })
      } else {
        alert("填写不能为空！");
      }

    },
  },
}
</script>

<style scoped>
.background{
  background: url("../assets/images/background1.png") no-repeat center center fixed;
  width: 100%; /* 大小设置成100% */
  height: 100%;
  opacity: 50%;
  position: fixed;
  background-size: 100% 100%;
}
body{
  background: none;
  justify-content: center;
  align-items: center;
  /*height: 100vh;*/
  flex-direction: column;
}
</style>

