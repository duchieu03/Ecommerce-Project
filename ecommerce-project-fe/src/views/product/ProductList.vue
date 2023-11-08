<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h2> Our Products</h2>
                <router-link :to="{name: 'addProduct'}" style="float: right">
                    <button class="btn" >
                        Add Product
                    </button> </router-link>
            </div>
        </div>
        <div class="row">
            <div v-for="product of products" :key="product.id"
                 class="col-md-6 col-xl-4 col-12 pt-3 d-flex">
                <ProductBox :product="product"/>
            </div>
        </div>
    </div>
</template>
<script>
    import ProductBox from "../../components/ProductBox";
    export default {
        components: {ProductBox},
        props:["products"]
    }
</script>