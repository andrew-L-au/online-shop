import { createRouter, createWebHistory } from 'vue-router'
import LoginTable from "@/components/LoginTable.vue";
import WelcomePage from "@/components/WelcomePage.vue";
import SignupTable from "@/components/SignupTable.vue";
<<<<<<< HEAD
import UserPage from "@/components/MainPage.vue";
=======
import UserPage from "@/components/UserPage.vue";
>>>>>>> f608db424c8b35a867558e29256332b78ae9b4a7
import OpenStoreView from '../views/OpenStoreView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      username: 'home',
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
      component:UserPage
    },
    {
      path: '/openStore',
      name: 'openStore',
      component: OpenStoreView
    },

  ]
})

export default router
