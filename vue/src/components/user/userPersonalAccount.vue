<template>
    <body>
        <div class="balance">
            账户余额：{{ account.balance }}
        </div>
        <el-button id="addBalance" @click="showHideBlock" round type="success">账户充值</el-button>
        <div style="display: none;" class="hidden">
            <el-input type="number" v-model="amount" placeholder="请输入充值金额" />
            <el-button style="primary" @click="addBalance">充值</el-button>
            <el-button @click="showHideBlock">取消</el-button>
        </div>
    </body>
</template>

<script>
import { ElMessage } from 'element-plus'
    export default {
        data() {
            return {
                userId: localStorage.getItem('userId'),
                account : {
                    accountId: '',
                    balance: 0,
                },
                amount: 0,
            }
        },
        methods:{
            getAccountInfo() {
                this.$axios({
                    method: 'post',
                    //TODO 核对后端接口
                    url: 'http://192.168.31.196:50000/account/account-of-user',
                    data: {
                        userId: this.userId,
                    }
                })
                .then(resp => {
                    console.log(resp)
                    console.log(resp.data)
                    this.account = resp.data
                    localStorage.setItem('userAccountId', this.account.accountId)
                    console.log(this.account)
                })
                .catch(err => {
                    console.log(err);
                })
            },
            addBalance() {
                if(this.amount === '' || this.amount <= 0){
                    ElMessage({
                        type: 'error',
                        message: '请输入正确的充值金额',
                    })
                } else{
                    this.$axios({
                        method: 'post',
                        url: 'http://192.168.31.196:50000/account/redeem-account',
                        data: {
                            accountId: this.account.accountId,
                            amount: this.amount,
                        }
                    })
                    .then(resp => {
                      console.log(resp)
                        if(resp.data === "success"){
                            ElMessage({
                                type: 'success',
                                message: '充值成功',
                            })
                        }
                        console.log(this.amount)
                        this.amount = 0
                        this.showHideBlock()
                        //重新加载显示余额
                        this.getAccountInfo()
                    })
                    .catch(err => {
                        console.log(err)
                        ElMessage({
                            type: 'error',
                            message: '充值失败，请重试',
                        })
                    })
                }
            },
            showHideBlock() {
                // 动态显示和隐蔽
                var block = document.querySelector('.hidden');
                if (block.style.display === "none") {
                    block.style.display = "block";
                }
                else {
                    block.style.display = "none";
                }
            },
        },
        mounted: function () {
            this.getAccountInfo();
        },
    }
</script>

<style scoped>
body{
    background: none;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    flex-wrap: nowrap;
}
.balance {
    font-size: 20px;
}
#addBalance {
    font-size: 25px;
    font-weight: bold;
    padding: 1em;
    margin-top: 3em;
}
</style>