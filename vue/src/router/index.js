import {createRouter, createWebHistory} from 'vue-router'
import LoginTable from "@/components/LoginTable.vue";
import WelcomePage from "@/components/WelcomePage.vue";
import SignupTable from "@/components/SignupTable.vue";
import MainPage from "@/components/MainPage.vue";
import OpenStoreView from '../views/OpenStoreView.vue'
import AdminPage from "@/components/AdminPage.vue";
import approveOpenStore from "@/components/approveOpenStore.vue"
import approveCloseStore from "@/components/approveCloseStore.vue"
import approveAddCommodity from "@/components/approveAddCommodity.vue"
import approveModifyCommodity from "@/components/approveModifyCommodity.vue"
import MainPageDefault from '@/components/MainPageDefault.vue'
import userBasicInfo from "@/components/userBasicInfo.vue"
import userPersonalAccount from "@/components/userPersonalAccount.vue"
import userShopAccount from "@/components/userShopAccount.vue"
import userShoppingCarts from "@/components/userShoppingCarts.vue"
import addNewItem from "@/components/addNewItem.vue";
import itemRecord from "@/components/itemRecord.vue";
import itemDisplay from "@/components/itemDisplay.vue";
import modifyItem from "@/components/modifyItem.vue";

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
            path: '/user/display',
            component: MainPageDefault
        },
        {
            path: '/user',
            component: MainPage,
            // redirect: to => {
            //     return { path: '/user/display'}
            // },
        },
        {
            path: '/user/userCenter/basicInfo',
            component: userBasicInfo
        },
        {
            path: '/user/userCenter/accounts/personal',
            component: userPersonalAccount
        },
        {
            path: '/user/userCenter/accounts/shop',
            component: userShopAccount
        },
        {
            path: '/user/userCenter/shoppingCarts',
            component: userShoppingCarts
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
        {
            path: '/admin/approveOpenStore',
            component: approveOpenStore
        },
        {
            path: '/admin/approveCloseStore',
            component: approveCloseStore
        },
        {
            path: '/admin/approveAddCommodity',
            component: approveAddCommodity
        },
        {
            path: '/admin/approveModifyCommodity',
            component: approveModifyCommodity
        },
        // 商户部分
        {
            path:'/vendor/addNewItem',
            component: addNewItem
        },
        {
            path:'/vendor/itemRecord',
            component: itemRecord
        },
        {
            path:'/vendor/itemDisplay',
            component: itemDisplay
        },
        {
            path:'/vendor/modifyItem',
            name:modifyItem,
            component:modifyItem,
        },

    ]
});
//拦截器
// router.beforeEach((to, from, next) => {
//     let token = localStorage.getItem("userinfo");
//     if (to.path === '/' || to.path === '/login' || to.path === '/signup') next()
//     if (to.path ==="/admin" && token.user.userRole === "ADMINISTRATOR") next("/AdminPage");
//     // else if (token.user.userRole === "CUSTOMER") next("/MainPage");
//     if (to.path ==="/openStore" && token.user.userRole === "SHOP_OWNER") next("/OpenStore");
//     else next("/");
// })

export default router
