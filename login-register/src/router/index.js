import {createRouter, createWebHistory} from 'vue-router'
import LoginTable from "@/components/LoginTable.vue";
import WelcomePage from "@/components/WelcomePage.vue";
import SignupTable from "@/components/SignupTable.vue";
import MainPage from "@/components/MainPage.vue";
import OpenStoreView from '../views/OpenStoreView.vue'
import AdminPage from "@/components/AdminPage.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: WelcomePage
        },

        {
            path: '/login',
            component: LoginTable
        },

        {
            path: '/signup',
            component: SignupTable
        },
        {
            path: '/user',
            component: MainPage
        },
        {
            path: '/openStore',
            name: 'openStore',
            component: OpenStoreView
        },
        {
            path: '/admin',
            component: AdminPage
        },

    ]
});
//拦截器
router.beforeEach((to, from, next) => {
    let token = localStorage.getItem("token");
    if (token || to.path === '/' || to.path === '/login' || to.path === '/signup') next();
    else next("/");
})


export default router
