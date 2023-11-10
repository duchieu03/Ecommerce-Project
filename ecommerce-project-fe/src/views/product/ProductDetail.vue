<template>
    <div class="container">
        <div class="row pt-5">
      <div class="col-md-1"></div>
      <!--            display image-->
      <div class="col-md-4 col-12">
        <img :src="product.imgUrl" class="img-fluid" />
      </div>
      <!--            display product details-->
      <div class="col-md-6 col-12 pt-3 pt-md-0">
        <h4>{{ product.name }}</h4>
        <h6 class="catgory font-italic">{{ category.categoryName }}</h6>
        <h6 class="font-weight-bold">$ {{ product.price }}</h6>
        <p>
          {{ product.description }}
        </p>
        <div class="d-flex flex-row justify-content-between">
          <div class="input-group col-md-3 col-4 p-0">
            <div class="input-group-prepend">
              <span class="input-group-text">Quantity</span>
            </div>
            <input type="number" class="form-control" v-model="quantity" />
          </div>

          <div class="input-group col-md-3 col-4 p-0">
            <button class="btn" id="add-to-cart-button" @click="addToCart">
              Add to Cart
            </button>
          </div>
        </div>
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
                category:{},
                quantity: 1
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
                },
            async addToCart(){
                const token = localStorage.getItem("token")
                await axios.request(
                    {
                        headers:{
                            Authorization: `Bearer ${token}`
                        },
                        method:"POST",
                        url:`http://localhost:8082/cart/add/${this.id}?quantity=${this.quantity}`,
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