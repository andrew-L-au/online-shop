<template>
  <div class="address-manager">
    <h2>收货地址管理</h2>
    <form class="address-form" v-if="showForm" @submit.prevent="addAddress">
      <div class="form-group">
        <label for="name">姓名：</label>
        <input type="text" v-model="newAddress.name" required>
      </div>
      <div class="form-group">
        <label for="phone">手机号码：</label>
        <input type="tel" v-model="newAddress.phone">
      </div>
      <div class="form-group">
        <label for="address">具体地址：</label>
        <textarea v-model="newAddress.address" required></textarea>
      </div>
      <button class="btn btn-primary" type="submit">添加地址</button>
      <button class="btn btn-default" type="button" @click.prevent="cancelAdd">取消</button>
    </form>
    <ul class="address-list">
      <li class="address-item" v-for="(address, index) in addresses" :key="index">
        <div class="address-info" v-if="!address.editing">
          <span class="name">{{ address.name }}</span>
          <span class="phone">{{ address.phone }}</span>
          <span class="address">{{ address.address }}</span>
          <button class="btn btn-link" @click="editAddress(address)">编辑</button>
          <button class="btn btn-link" @click="removeAddress(index)">删除</button>
        </div>
        <form class="address-form" v-else @submit.prevent="saveAddress(address)">
          <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" v-model="address.name" required>
          </div>
          <div class="form-group">
            <label for="phone">手机号码：</label>
            <input type="tel" v-model="address.phone" pattern="^\d{11}$" required>
          </div>
          <div class="form-group">
            <label for="address">具体地址：</label>
            <textarea v-model="address.address" required></textarea>
          </div>
          <button class="btn btn-primary" type="submit">保存</button>
          <button class="btn btn-default" type="button" @click.prevent="cancelEdit(address)">取消</button>
        </form>
      </li>
    </ul>
    <button class="btn btn-primary" @click.prevent="showForm = true">添加地址</button>
  </div>
</template>

<script>
import router from "@/router";

export default {
  data() {
    return {
      userId: localStorage.getItem('userId'),
      addresses: [
        {addressId: '', name: '', phone: '', address: '', editing: false},
        {name: '张三', phone: '13812345678', address: '北京市朝阳区xx街道xx小区', editing: false},
        {name: '李四', phone: '13912345678', address: '北京市海淀区xx街道xx小区', editing: false}
      ],
      newAddress: {
        name: '',
        phone: '',
        address: ''
      },
      showForm: false
    }
  },
  mounted() {
    this.getAddress()
  },

  methods: {
    getAddress(){
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/merchandise/merchandises-of-shop',
        data: {
          userId: this.userId
        }
      })
          .then(resp => {
            console.log(resp.data)
            this.addresses = []
            for (let i = 0; i < resp.data.length; i++) {
              let tmp = resp.data[i];
              console.log(tmp)
              this.addresses.push({addressId: tmp.addressId, name: tmp.name, phone: tmp.phoneNumber, address: tmp.addressDetail, editing: false})
            }
          })
          .catch(err => {
            console.log(err);
          })
    },

    addAddress() {
      if (this.validatePhone(this.newAddress.phone)) {
        this.$axios({
          method: 'post',
          url: 'http://192.168.31.196:50000/merchandise/merchandises-of-shop',
          data: {
            userId: this.userId,
            address: this.newAddress,
          }
        })
            .then(resp => {
              console.log(resp.data)
              this.getAddress()
            })
            .catch(err => {
              console.log(err);
            })

        // this.addresses.push({
        //   name: this.newAddress.name,
        //   phone: this.newAddress.phone,
        //   address: this.newAddress.address,
        //   editing: false
        // })
        this.newAddress = {
          name: '',
          phone: '',
          address: ''
        }
        this.showForm = false


      } else {
        alert('请输入正确的手机号码')
      }
    },
    editAddress(address) {
      address.editing = true
    },
    saveAddress(address) {
      if (this.validatePhone(address.phone)) {
        address.editing = false
        this.$axios({
          method: 'post',
          url: 'http://192.168.31.196:50000/merchandise/merchandises-of-shop',
          data: {
            addressId :address.addressId,
            address : {
              name : address.name,
              phoneNumber : address.phone,
              addressDetail : "oma,fr,us"
            }
          }
        })
            .then(resp => {
              console.log(resp.data)
              this.getAddress()
            })
            .catch(err => {
              console.log(err);
            })
      } else {
        alert('请输入正确的手机号码')
      }
    },
    cancelEdit(address) {
      address.editing = false
    },
    removeAddress(index) {
      this.addresses.splice(index, 1)
      this.$axios({
        method: 'post',
        url: 'http://192.168.31.196:50000/merchandise/merchandises-of-shop',
        data: {
          addressId :this.addresses[index].addressId
        }
      })
          .then(resp => {
            console.log(resp.data)
            this.getAddress()
          })
          .catch(err => {
            console.log(err);
          })
    },
    cancelAdd() {
      this.showForm = false
      this.newAddress = {
        name: '',
        phone: '',
        address: ''
      }
    },
    validatePhone(phone) {
      console.log(/^1[3456789]\d{9}$/.test(phone))
      return /^1[3456789]\d{9}$/.test(phone)
    }
  }
}
</script>

<style>
.address-manager {
  font-family: 'Helvetica Neue', sans-serif;
  margin: 0 auto;
  max-width: 500px;
}

h2 {
  color: #333;
  font-size: 1.5rem;
  margin-bottom: 1.5rem;
}

.form-group {
  margin-bottom: 1rem;
}

label {
  display: inline-block;
  margin-right: 0.5rem;
  vertical-align: top;
}

input[type="text"],
input[type="tel"],
textarea {
  border: 1px solid #ccc;
  border-radius: 3px;
  box-sizing: border-box;
  display: block;
  font-size: 1rem;
  padding: 0.5rem;
  width: 100%;
}

input[type="text"]:focus,
input[type="tel"]:focus,
textarea:focus {
  border-color: #4d90fe;
  outline: none;
}

.btn {
  background-color: #f5f5f5;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  display: inline-block;
  font-size: 1rem;
  margin-right: 0.5rem;
  padding: 0.5rem 1rem;
  text-align: center;
  text-decoration: none;
}

.btn-primary {
  background-color: #4d90fe;
  color: #fff;
}

.btn-primary:hover {
  background-color: #357ae8;
}

.btn-link {
  color: #4d90fe;
  margin-right: 0.5rem;
  text-decoration: underline;
}

.btn-link:hover {
  color: #357ae8;
  text-decoration: none;
}

ul.address-list {
  list-style: none;
  margin: 0;
  padding: 0;
}

li.address-item {
  margin-bottom: 1rem;
  padding: 1rem;
  background-color: #f5f5f5;
  border-radius: 3px;
}

li.address-item:hover {
  background-color: #ebebeb;
}

.address-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.address-info .name {
  font-weight: bold;
}

.address-info .phone {
  margin-left: 1rem;
}

.address-info .address {
  margin-left: 1rem;
  flex: 1;
}

.address-form {
  display: flex;
  flex-direction: column;
}

.address-form button {
  margin-top: 1rem;
}
</style>