<template>
    <main class="layout">
        <p>中间资金：{{ transferCapital }}</p>
        <el-button id="addBalance" @click="showHideBlock" round type="success">账户充值</el-button>
        <div style="display: none;" class="hidden">
            <el-input type="number" v-model="amount" placeholder="请输入充值金额" style="width: 300px;" />
            <el-button style="primary" @click="addBalance">充值</el-button>
            <el-button @click="showHideBlock">取消</el-button>
        </div>
    </main>
</template>

<script>
import { ElMessage } from 'element-plus'
export default {
    data() {
        return {
            transferCapital: '',
            amount: '',
        }
    },
    methods: {
        getTransferCapital() {
            this.$axios({
                method: 'get',
                url: 'https://run.mocky.io/v3/15da6574-a440-4dce-aa97-2fbbe2252751',
            })
                .then(resp => {
                    console.log(resp.data)
                    this.transferCapital = resp.data
                })
                .catch(err => {
                    console.log(err)
                })
        },
        addBalance() {
            if (this.amount === '' || this.amount <= 0) {
                ElMessage({
                    type: 'error',
                    message: '请输入正确的充值金额',
                })
            } else {
                this.$axios({
                    method: 'post',
                    url: 'https://run.mocky.io/v3/cf42c64f-d066-4658-98fe-a04e399eee2b',
                    data: {
                        amount: 0,
                    }
                })
                    .then(resp => {
                        if (resp.data === "success") {
                            ElMessage({
                                type: 'success',
                                message: '充值成功',
                            })
                        }
                        console.log(this.amount)
                        this.amount = 0
                        this.showHideBlock()
                        //重新加载显示余额
                        this.getTransferCapital()
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
                block.style.display = "";
            }
            else {
                block.style.display = "none";
            }
        },
    },
    mounted() {
        this.getTransferCapital();
    },
}
</script>

<style scoped>
.layout {
    text-align: center;
    margin-top: 15%;
}
p {
    font-size: 25px;
}

#addBalance {
    font-size: 25px;
    font-weight: bold;
    padding: 1em;
    margin-top: 3em;
}
.hidden {
    margin-top: 5em;
}
</style>