import { createRouter, createWebHistory } from 'vue-router'
import { ElMessage } from 'element-plus'
import LoginTable from "@/components/LoginTable.vue";
import WelcomePage from "@/components/WelcomePage.vue";
import SignupTable from "@/components/SignupTable.vue";
import MainPage from "@/components/user/MainPage.vue";
import OpenStoreView from '../views/OpenStoreView.vue'
import AdminPage from "@/components/admin/AdminPage.vue";
import approveOpenStore from "@/components/admin/approveOpenStore.vue"
import approveCloseStore from "@/components/admin/approveCloseStore.vue"
import approveAddCommodity from "@/components/admin/approveAddCommodity.vue"
import approveModifyCommodity from "@/components/admin/approveModifyCommodity.vue"
import MainPageDefault from '@/components/user/MainPageDefault.vue'
import userBasicInfo from "@/components/user/userBasicInfo.vue"
import userPersonalAccount from "@/components/user/userPersonalAccount.vue"
import userShopAccount from "@/components/vendor/userShopAccount.vue"
import userShoppingCarts from "@/components/user/userShoppingCarts.vue"
import addNewItem from "@/components/vendor/addNewItem.vue";
import addItemRecord from "@/components/vendor/addItemRecord.vue";
import itemDisplay from "@/components/vendor/itemDisplay.vue";
import modifyItem from "@/components/vendor/modifyItem.vue";
import modifyItemRecord from "@/components/vendor/modifyItemRecord.vue";
import totalProfit from "@/components/vendor/totalProfit.vue"
import transferCapital from "@/components/admin/transferCapital.vue"
import temp from "@/components/temp.vue";
import locationManagement from "@/components/user/locationManagement.vue";
import locationEdition from "@/components/user/locationEdition.vue";
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: WelcomePage
        },
        {
            path: '/test',
            name: 'test',
            component: temp
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
            component: MainPage,
            // redirect: to => {
            //     return { path: '/user/display'}
            // },
            children: [
                {
                    path: '/user/display',
                    component: MainPageDefault
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
                    path: '/user/userCenter/basicInfo/locationManagement',
                    component: locationManagement
                },
                {
                    path: '/user/userCenter/basicInfo/locationEdition',
                    component: locationEdition
                },

                // 商户部分
                {
                    path: '/vendor/openStore',
                    name: 'openStore',
                    component: OpenStoreView
                },
                {
                    path: '/vendor/addNewItem',
                    component: addNewItem
                },
                {
                    path: '/vendor/addItemRecord',
                    component: addItemRecord
                },
                {
                    path: '/vendor/modifyItemRecord',
                    component: modifyItemRecord
                },
                {
                    path: '/vendor/itemDisplay',
                    component: itemDisplay
                },
                {
                    path: '/vendor/modifyItem',
                    name: modifyItem,
                    component: modifyItem,
                },
            ]
        },


        {
            path: '/admin',
            component: AdminPage,
            children: [
                {
                    path: '/admin/totalProfit',
                    component: totalProfit,
                },
                {
                    path: '/admin/transferCapital',
                    component: transferCapital,
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
            ]
        },

    ]
});
//拦截器
// router.beforeEach((to, from, next) => {
//     let token = localStorage.getItem("userinfo");
//     if (to.path === '/' || to.path === '/login' || to.path === '/signup') next()
//     if (to.path ==="/admin" && token.user.userRole === "ADMINISTRATOR") next("/AdminPage");
//     // else if (token.user.userRole === "CUSTOMER") next("/MainPage");
//     if (to.path ==="/vendor/openStore" && token.user.userRole === "SHOP_OWNER") next("/OpenStore");
//     else next("/");
// })

// 拦截器
// router.beforeEach((to, from, next) => {
//     var token = localStorage.getItem('token')
//
//     if(!token){
//         if (to.path === '/' || to.path === '/login' || to.path === '/signup') next()
//         else{
//             ElMessage({
//                 type: 'error',
//                 message: '请先登录！'
//             })
//             next("/login");
//         }
//     }
//     else {
//         let strings = token.split("."); //截取token，获取载体
//         var userinfo = JSON.parse(decodeURIComponent(escape(window.atob(strings[1].replace(/-/g, "+").replace(/_/g, "/"))))); //解析，需要吧‘_’,'-'进行转换否则会无法解析
//         var info = JSON.parse(userinfo.user)
//         var role = info.userRole
//         console.log(role)
//
//         var path = to.path.split('/');
//         console.log(path)
//
//         if (role === 'ADMINISTRATOR') {
//             if (path[1] === "admin") {
//                 next();
//             } else { next("/admin") }
//         }
//         else if (role === 'SHOP_OWNER') {
//             if (path[1] === "user" || path[1] === "vendor") {
//                 next();
//             } else { next("/user") }
//         }
//         else if (role === 'CUSTOMER') {
//             if (path[1] === "user") {
//                 if (path[4] === 'shop') { //普通用户不能查看商店账户
//                     ElMessage({
//                         type: 'error',
//                         message: '您当前为普通用户，无法查看'
//                     })
//                     next("/user")
//                 } else { next() }
//             } else { next("/user") }
//         }
//     }
// })

export default router
