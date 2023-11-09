<template>
    <div class="card h-100 w-100">
        <div class="embed-responsive embed-responsive-16by9">
            <img
                class="card-img-top embed-responsive-item"
                :src="product.imgUrl"
                alt="Card image cap"
            />
        </div>
        <div class="card-body">
            <router-link :to="{name:'detailProduct', params:{id:product.id}}">
                <h5 class="card-title">{{ product.name }}</h5>
            </router-link>
            <p class="card-text">
                {{ product.description.substring(0, 65) }}...
            </p>
           <router-link v-if="this.$route.name == 'listProduct'" :to="{name: 'editProduct', params: { id: product.id }}">
            <button class="btn btn-primary btn-lg">Edit</button>
           </router-link>
           <button @click="removeWishlist" v-if="this.$route.name == 'wishlist'" class="btn btn-primary btn-lg">Remove</button>
        </div>
    </div>
</template>
<script>
    import axios from 'axios';
    import swal from 'sweetalert';
    export default {
        name: "ProductBox",
        props: ["product"],
        methods:{
            async removeWishlist(){
                const token = localStorage.getItem("token")
                
                await axios.request(
                    {
                        headers:{
                            Authorization: `Bearer ${token}`
                        },
                        method:"POST",
                        url:`http://localhost:8082/wishlist/delete/${this.product.id}`,
                    },
                ).then(()=>{
                    swal({
                        text:"Add successfully",
                        icon:"success"
                    })
                }).catch((err)=>{
                    swal({
                        text:err.response.data.reason,
                        icon:"error"
                    })
                    console.log(err)
                })
                this.$emit("removeWishlist")
            }
        }
    }
</script>
<style scoped>
    .card-img-top {
        object-fit: cover;
    }
</style>