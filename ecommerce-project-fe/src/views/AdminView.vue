<template class="body">
    <div class="container" >
        <router-link :to="{name: 'listCategory'}">
        <button class="btn btn-primary btn-lg"> Admin Category</button>
        </router-link>
    <br />
    <router-link :to="{name: 'listProduct'}" >
        <button class="btn btn-primary btn-lg" > Admin Product</button>
    </router-link>
    <br />
    <router-link :to="{name: 'listProduct'}" >
        <button class="btn btn-primary btn-lg"> Admin Order</button>
    </router-link>
    <br />
    <router-link :to="{name: 'listProduct'}" >
        <button class="btn btn-primary btn-lg"> Admin Account</button>
    </router-link>
    </div>
    
</template>
<script>
export default {

}
</script>
<style scoped>
   .container{
    margin-top: 50px;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
   }
</style>