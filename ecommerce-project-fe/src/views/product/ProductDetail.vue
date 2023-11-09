<template>
    <div class="container">
        <div class="row pt-5">
            <div class="col-md-1"></div>
            <!-- Image -->
            <div class="col-md-4 col-12">
                <img :src="product.imgUrl" alt="" class="img-fluid">
            </div>
            <!-- Product -->
            <div class="col-md-6 col-12 pt-3 pt-md-0">
        <h4>{{ product.name }}</h4>
        <h6 class="catgory font-italic">{{ category.categoryName }}</h6>
        <h6 class="font-weight-bold">$ {{ product.price }}</h6>
        <p>
          {{ product.description }}
        </p>
        <div class="features pt-3">
          <h5><strong>Features</strong></h5>
          <ul>
            <li>Lorem ipsum dolor sit amet consectetur adipisicing elit.</li>
            <li>Officia quas, officiis eius magni error magnam voluptatem</li>
            <li>nesciunt quod! Earum voluptatibus quaerat dolorem doloribus</li>
            <li>molestias ipsum ab, ipsa consectetur laboriosam soluta et</li>
            <li>ut doloremque dolore corrupti, architecto iusto beatae.</li>
          </ul>
        </div>
        <button
          id="wishlist-button"
          class="btn mr-3 p-1 py-0"
          @click="addToWishlist()"
        >
          Add to wishlist
        </button>
      </div>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import swal from 'sweetalert';
    export default{
        data(){
            return {
                id:null,
                product : {},
                category:{}
            }
        },
        props:["products","categories"],
        methods:{
            async addToWishlist(){
                const token = localStorage.getItem("token")
                await axios.request(
                    {
                        headers:{
                            Authorization: `Bearer ${token}`
                        },
                        method:"POST",
                        url:`http://localhost:8082/wishlist/add/${this.id}`,
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
                }
        },
        mounted() {
            this.id = this.$route.params.id;
            this.product = this.products.find(pro=> pro.id==this.id);
            this.category = this.categories.find(cate => cate.id ==this.product.categoryId);
        },
    }
</script>
<style>
.category {
        font-weight: 400;
    }
</style>