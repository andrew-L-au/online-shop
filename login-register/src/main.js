import { createApp } from 'vue'
// import Vue from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
const app = createApp(App)
import './assets/main.css'

import axios from 'axios'

// 将axios挂载到原型对象上
app.config.globalProperties.$axios = axios;
// 设置axios请求的地址默认是'/api'，后续会通过代理转移
axios.defaults.baseURL = "/api";
// 请求时带上cookie
axios.defaults.withCredentials = true;



app.use(router)
app.use(ElementPlus)
app.mount('#app')
