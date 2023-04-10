<template>
  <h1>Sign up</h1>
  <el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="角色">
      <el-select v-model="form.userRole" placeholder="请选择身份">
        <el-option label="普通用户" value="CUSTOMER"></el-option>
        <el-option label="商户" value="SHOP_OWNER"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="用户名">
      <el-input v-model="form.username"></el-input>
    </el-form-item>
    <el-form-item label="手机号">
      <el-input v-model="form.phoneNumber"></el-input>
    </el-form-item>
    <el-form-item label="身份证号">
      <el-input v-model="form.idCardNumber"></el-input>
    </el-form-item>
    <el-form-item label="邮箱">
      <el-input v-model="form.email"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="form.credential" show-password></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">确认</el-button>
      <el-button onclick="window.location.href ='http://127.0.0.1:8000/'">取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import router from "@/router";

function isValidName(str){
  const regex = /^[\w]{3,10}$/;
  return regex.test(str);
}

function isValidPhone(str) {
  const regex = /^\d{11}$/;
  return regex.test(str);
}

function isValidId(str) {
  const regex = /^\d{17}(\d|X)$/;
  return regex.test(str);
}

function isValidEmail(email) {
  const regex = /^\S+@\S+\.\S+$/;
  return regex.test(email);
}

function isValidPassword(str) {
  // ^ 表示匹配字符串的开始，$ 表示匹配字符串的结尾
  // (?=.*[a-z]) 匹配包含至少一个小写字母的字符串
  // (?=.*[A-Z]) 匹配包含至少一个大写字母的字符串
  // (?=.*\d) 匹配包含至少一个数字的字符串
  // [\w-]{6,32} 匹配包含 6 到 32 个英文字符、数字、下划线或短横线的字符串
  const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[\w-]{6,32}$/;
  return regex.test(str);
}

export default {
  data() {
    return {
      form: {
        userRole: '',
        username: '',
        phoneNumber: '',
        idCardNumber: '',
        email: '',
        credential: '',
      },
    }
  },
  methods: {

    onSubmit() {
      console.log('submit!')
      let flag = 0;
      if(!isValidName(this.form.username)) alert("不符合格式的用户名！")
      else if(!isValidPhone(this.form.phoneNumber)) alert("不符合格式的手机号！")
      else if(!isValidId(this.form.idCardNumber)) alert("不符合格式的身份证号！")
      else if(!isValidEmail(this.form.email)) alert("不符合格式的邮箱！")
      else if(!isValidPassword(this.form.credential)) alert("不符合格式的密码！")
      else flag = 1;
      if (this.form.userRole !== "" && this.form.username !== "" && this.form.phoneNumber !== "" && this.form.idCardNumber !== "" && this.form.email !== "" && this.form.credential !== "" &&flag === 1) {
        this.$axios({
          method: 'post',
          url: 'http://101.200.57.208:34529/user/registration',
          data: {
            userBasicInfo:{
              username: this.form.username,
              phoneNumber: this.form.phoneNumber,
              idCardNumber: this.form.idCardNumber,
              email: this.form.email,
            },
            userAuthentication:{
              principal: this.form.username,
              credential: this.form.credential,
            },
            userRole: this.form.userRole,
          }
        })
            .then(resp => {
              console.log("succeed")
              console.log(resp)
              if (resp.data === "success") {
                alert("注册成功！");
                router.replace('/login')
                //跳转
              }
            })
            .catch(err => {
              console.log(err);
            })
      } else {
        if(flag !== 0) alert("填写不能为空！");
      }
      // const resp = axios.post(this.form.character, this.form.name, this.form.phone, this.form.id, this.form.mail, this.form.key);
      // console.log(resp)
    }
  },
}
</script>
