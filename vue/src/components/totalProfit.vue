<template>
    <p>商店总利润: {{ totalProfit }}</p>
</template>

<script>
export default {
    data() {
        return {
            userId: localStorage.getItem('userId'),
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
                url: 'https://run.mocky.io/v3/198447c2-1cca-4b3b-8ef4-7f2b25ede756',
                data: {
                    userId: this.userId,
                }
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
}
</style>