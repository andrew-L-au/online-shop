<template>
  <h1>Sign up</h1>
  <el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="角色">
      <el-select v-model="form.character" placeholder="请选择身份">
        <el-option label="普通用户" value="user"></el-option>
        <el-option label="商户" value="merchant"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="用户名">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
    <el-form-item label="手机号">
      <el-input v-model="form.phone"></el-input>
    </el-form-item>
    <el-form-item label="身份证号">
      <el-input v-model="form.id"></el-input>
    </el-form-item>
    <el-form-item label="邮箱">
      <el-input v-model="form.mail"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="form.key"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">确认</el-button>
      <el-button onclick="window.location.href ='http://127.0.0.1:8000/'">取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      // UserInfo:{
      //   character: '',
      //   name: '',
      //   phone: '',
      //   id: '',
      //   mail: '',
      //   key: '',
      // },
      // key: '',
      // character: '',
      form: {
        character: '',
        name: '',
        phone: '',
        id: '',
        mail: '',
        key: '',
      },
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!')
      if (this.form.character !== "" && this.form.name !== "" && this.form.phone !== "" && this.form.id !== "" && this.form.mail !== "" && this.form.key !== "") {
        this.$axios({
          method: 'post',
          url: 'http://127.0.0.1:8080/api/user/register',
          data: {
            character: this.form.character,
            name: this.form.name,
            phone: this.form.phone,
            id: this.form.id,
            mail: this.form.mail,
            key: this.form.key,
          }
        })
            .then(resp => {
              if (resp.data.code === 1) {
                alert("注册成功！");
                //跳转
              }
            })
            .catch(err => {
              console.log(err);
            })
      } else {
        alert("填写不能为空！");
      }
      // const resp = axios.post(this.form.character, this.form.name, this.form.phone, this.form.id, this.form.mail, this.form.key);
      // console.log(resp)
    }
  },
}
</script>
