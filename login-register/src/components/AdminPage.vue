<template>
  <h1>Admin</h1>
</template>

<script>

export default {
  data() {
    return {
      shopRequest:[],
      openShopRequestId:'',
      shop : {
        shopId :'',
      }
    }
  },
  methods: {
    getShopInfo() {
      this.$axios({
        method: 'get',
        url: 'http://101.200.57.208:33909/shop/get-open-shop-requests',
      })
          .then(resp =>{
            this.shopRequest = resp.data
            this.openShopRequestId = this.shopRequest.openShopRequestId
            this.shop.shopId = this.shopRequest.shop.shopId
            console.log(this.shopRequest[0].shop.profile);
            console.log(this.shopRequest[0].shop.shopId);
          })
          .catch(err => {
            console.log(err);
          })
    },

    onSubmit(){
      console.log('submit!')
      this.$axios({
        method: 'post',
        url:'http://101.200.57.208:33909/shop/approve-open-shop-request',
        data: {
          openShopRequestId : this.openShopRequestId,
          shop : {
            shopId : this.shop.shopId,
          }
        }
      })
          .then(resp =>{
              alert("批准成功！");
          })
          .catch(err => {
            console.log(err);
          })
    }
  }


}


</script>

<style scoped>

</style>