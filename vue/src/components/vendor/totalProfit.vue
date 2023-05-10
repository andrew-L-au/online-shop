<template>
    <div class="layout">
        <p>商店总利润: {{ totalProfit }}</p>
    </div>
</template>

<script>
export default {
    data() {
        return {
            totalProfit: '',
            account : {
                accountId: '',
                balance: 0,
            },
        }
    },
    methods: {
        getTotalProfit() {
            this.$axios({
                method: 'get',
                url: 'http://192.168.31.196:50000/account/profit-account',
            })
                .then(resp => {
                    console.log(resp.data)
                    this.account = resp.data
                    this.totalProfit = resp.data.balance
                    localStorage.setItem('userAccountId', this.account.accountId)
                    console.log(this.account)
                })
                .catch(err => {
                    console.log(err);
                })
        },
    },
    mounted() {
        this.getTotalProfit();
    },
}
</script>

<style scoped>
p {
    font-size: 25px;
    text-align: center;
    margin-top: 20%;
    padding: 3em 0;
}

</style>