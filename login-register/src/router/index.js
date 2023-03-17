import { createRouter, createWebHistory } from 'vue-router'
import LoginTable from "@/components/LoginTable.vue";
import WelcomePage from "@/components/WelcomePage.vue";
import SignupTable from "@/components/SignupTable.vue";
import MainPage from "@/components/MainPage.vue";
import OpenStoreView from '../views/OpenStoreView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: WelcomePage
    },

    {
      path:'/login',
      component: LoginTable
    },

    {
      path:'/signup',
      component:SignupTable
    },
    {
      path:'/user',
      component:MainPage
    },
    {
      path: '/openStore',
      name: 'openStore',
      component: OpenStoreView
    },

  ]
});
//拦截器
router.beforeEach((to,from,next)=>{
  let token = localStorage.getItem("token");
  if(token || to.path === '/login') next();
  else next();
  // else next("/login");
})




export default router
