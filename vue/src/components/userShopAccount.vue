<template>
    <header>
        <RouterView></RouterView>
    </header>
    <div class="noShopOwned" style="display: none;">
        <p>当前没有商店</p>
        <el-button id="openStore">
            <RouterLink to="/openStore">创建商店</RouterLink>    
        </el-button>
    </div>
    <div class="hasShopOpened" style="display: none;">
        <!-- TODO 展示商店信息 商店账户充值
        该商店现有商品展示table 上架商品router 修改商品信息router-->
        <template>
            <el-button id="closeStore" @click="popMessageBox">关闭商店</el-button>
        </template>
    </div>
</template>

<script>
import { ElMessage, ElMessageBox } from 'element-plus'

export default {
    data() {
        return {};
    },
    methods: {
        getShopAccount() {
            this.$axios({
                method: "post",
                url: "https://run.mocky.io/v3/ca57d9a0-791d-4180-80f5-fc1bc9810987",
            })
                .then(resp => {
                console.log(resp.data);
                if (resp.data.hasShop === "no") {
                    var show = document.querySelector(".noShopOwned");
                    show.style.display = "";
                }
                else if (resp.data.hasShop === "yes") {
                    //resp.data
                    var show = document.querySelector('.hasShopOpened');
                    show.style.display = "";

                }
            })
                .catch(err => console.log(err));
        },
        popMessageBox() {
            ElMessageBox.confirm(
                '将关闭商店并注销商店账户，是否继续？',
                'Warning',
                {
                    confirmButtonText: '确认',
                    cancelButtonText: '取消',
                    type: 'warning', 
                }
            )
            .then(() => {
                this.$axios({
                    method: 'post',
                    // url: '/shop/request-close-shop',

                })
                .then((resp) => {
                    //检验返回值是否提交成功
                    ElMessage({
                        type: 'success',
                        message: '成功提交闭店申请，等待批准中！',
                    })
                })
                .catch(err => console.log(err))
            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消操作',
                })
            })
        },
    },
    mounted() {
        this.getShopAccount();
    },
}
</script>