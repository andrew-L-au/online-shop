<template>
  <body>
  <ul>
    <li class="username">
      用户名：{{ userBasicInfo.username }}
      <el-button @click="showHideModifyBlock('username')">修改用户名</el-button>
      <div style="display: none;" class="modify modifyName">
        原用户名
        <el-input disabled :value="userBasicInfo.username"></el-input>
        新用户名
        <el-input v-model="newBasicInfo.username" minlength="3" maxlength="10"
                  placeholder="请输入3到10位字符"></el-input>
        <el-button @click="modifyBasicInfo('username')">提交修改</el-button>
        <el-button @click="showHideModifyBlock('username')">取消</el-button>
      </div>
    </li>
    <li class="idCardNumber">
      身份证号：{{ userBasicInfo.idCardNumber }}
      <el-button disabled>身份证号不可更改</el-button>
    </li>
    <li class="phoneNumber">
      手机号：{{ userBasicInfo.phoneNumber }}
      <el-button @click="showHideModifyBlock('phone')">修改手机号</el-button>
      <div style="display: none;" class="modify modifyPhone">
        原手机号
        <el-input disabled :value="userBasicInfo.phoneNumber"></el-input>
        新手机号
        <el-input v-model="newBasicInfo.phoneNumber" placeholder="请输入新手机号"></el-input>
        <el-button @click="modifyBasicInfo('phone')">提交修改</el-button>
        <el-button @click="showHideModifyBlock('phone')">取消</el-button>
      </div>
    </li>
    <li class="mail">
      邮箱：{{ userBasicInfo.email }}
      <el-button @click="showHideModifyBlock('mail')">修改邮箱</el-button>
      <div style="display: none;" class="modify modifyEmail">
        原邮箱
        <el-input disabled :value="userBasicInfo.email"></el-input>
        新邮箱
        <el-input v-model="newBasicInfo.email" placeholder="请输入新邮箱"></el-input>
        <el-button @click="modifyBasicInfo('mail')">提交修改</el-button>
        <el-button @click="showHideModifyBlock('mail')">取消</el-button>
      </div>
    </li>
    <li class="password">
      <el-button @click="showHideModifyBlock('password')">修改密码</el-button>
      <div style="display: none;" class="modify modifyPassword">
        新密码
        <el-input v-model="userAuthentication.credential" placeholder="请输入新密码"></el-input>
        <el-button @click="modifyPassword">提交修改</el-button>
        <el-button @click="showHideModifyBlock('password')">取消</el-button>
      </div>
    </li>
  </ul>
  <el-button @click="locationManagement()">收货地址管理</el-button>
  </body>
</template>

<script>
import {ElMessage} from 'element-plus';
import router from "@/router";

function isValidName(str) {
  const regex = /^[\w]{3,10}$/;
  return regex.test(str);
}

function isValidPhone(str) {
  const regex = /^\d{11}$/;
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
  mounted() {
    this.getUserBasicInfo()
  },
  data() {
    return {
      userBasicInfo: {
        username: '',
        phoneNumber: '',
        idCardNumber: '',
        email: '',
      },
      newBasicInfo: {
        username: '',
        phoneNumber: '',
        idCardNumber: '',
        email: '',
      },
      userId: localStorage.getItem('userId'),
      userAuthentication: {
        principal: '',
        credential: '',
      },
    }

  },

  methods: {

    getUserBasicInfo() {
      let userToken = localStorage.getItem('token')
      let strings = userToken.split("."); //截取token，获取载体
      var userinfo = JSON.parse(decodeURIComponent(escape(window.atob(strings[1].replace(/-/g, "+").replace(/_/g, "/"))))); //解析，需要吧‘_’,'-'进行转换否则会无法解析
      var user = JSON.parse(userinfo.user)
      this.userBasicInfo = user.userBasicInfo
      this.userAuthentication.principal = user.userAuthentication.principal
      this.userAuthentication.credential = user.userAuthentication.credential

    },
    locationManagement(){
      router.replace('/user/userCenter/basicInfo/locationManagement')
    },


    modifyBasicInfo(item) {
      //检查提交信息
      var msg = '', flag = 1;
      if (item === 'username') {
        if (!isValidName(this.newBasicInfo.username)) {
          msg = '不符合格式的用户名，请重新输入';
          flag = 0;
        }
      } else if (item === 'phone') {
        if (!isValidPhone(this.newBasicInfo.phoneNumber)) {
          msg = '不正确的手机号，请重新输入';
          flag = 0;
        }
      } else if (item === 'mail') {
        if (!(isValidEmail(this.newBasicInfo.email))) {
          msg = '不正确的邮箱，请重新输入';
          flag = 0;
        }
      }
      if (flag) {
        if (!this.newBasicInfo.email) this.newBasicInfo.email = this.userBasicInfo.email
        if (!this.newBasicInfo.phoneNumber) this.newBasicInfo.phoneNumber = this.userBasicInfo.phoneNumber
        if (!this.newBasicInfo.username) this.newBasicInfo.username = this.userBasicInfo.username
        if (!this.newBasicInfo.idCardNumber) this.newBasicInfo.idCardNumber = this.userBasicInfo.idCardNumber
        this.$axios({
          method: 'post',
          url: 'http://192.168.31.196:50000/user/change-user-basic-info',
          data: {
            userId: this.userId,
            userBasicInfo: this.newBasicInfo,
          }
        })
            .then(resp => {
              console.log(resp.data)
              if (resp.data === "success") {

                console.log(this.userBasicInfo);
                ElMessage({
                  type: 'success',
                  message: '修改成功',
                })
                this.hideAllModifyBlock();
                this.getUserBasicInfo();
              }
              this.exit()
            })
            .catch(err => {
              console.log(err);
            })
      } else {
        ElMessage({
          type: 'error',
          message: msg,
        })
      }
    },
    modifyPassword() {
      // console.log(this.userAuthentication.credential)
      if (isValidPassword(this.userAuthentication.credential)) {
        this.userAuthentication.principal = this.userBasicInfo.username
        this.$axios({
          method: 'post',
          url: 'http://192.168.31.196:50000/user/change-password',
          data: {
            userId: this.userId,
            password:this.userAuthentication.credential,
          }
        })
            .then(response => {
              console.log(response.data)
              if (response.data === 'success') {
                ElMessage({
                  type: 'success',
                  message: '修改成功',
                })
                this.hideAllModifyBlock();
                this.getUserBasicInfo();
              }
            })
            .catch(err => {
              console.log(err);
            })
      } else {
        ElMessage({
          type: 'error',
          message: '密码不符合格式',
        })
      }
      this.exit()
    },
    showHideModifyBlock(item) {
      //判断显示那个块
      var block;
      if (item === 'username') {
        block = document.querySelector('.modifyName');
      } else if (item === 'phone') {
        block = document.querySelector('.modifyPhone');
      } else if (item === 'mail') {
        block = document.querySelector('.modifyEmail');
      } else if (item === 'password') {
        block = document.querySelector('.modifyPassword');
      }

      if (block.style.display === "none") {
        block.style.display = "block";
      } else {
        block.style.display = "none";
      }
    },
    hideAllModifyBlock() {
      var blocks = document.querySelectorAll('.modify')
      for (let i = 0; i < blocks.length; i++) {
        blocks[i].style.display = "none";
      }
    },
    exit() {
      alert("修改成功！")
      console.log('exit!')
      localStorage.removeItem('token')
      router.replace("/")
    },
  }
}
</script>
//TODO 布局调整
<style scoped>
body {
  background: none;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

ul li {
  list-style: none;
}
</style>