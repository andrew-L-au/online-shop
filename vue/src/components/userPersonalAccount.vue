<template>
    账户余额：{{ balance }}
    账户充值：
</template>

<script>
    export default {
        data() {
            return {
                balance: 0,
            }
        },
        methods:{
            getAccoutBalance() {
                this.$axios({
                    method: 'post',
                    url: '',
                })
                .then(resp => {
                    this.balance = resp.data
                    console.log(this.balance)
                })
                .catch(err => {
                    console.log(err);
                })
            },
            addBalance() {
                
            },
        },
        mounted: function () {
            this.getAccoutBalance();
        },
    }
</script>