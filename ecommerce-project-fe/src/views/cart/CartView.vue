<template>
    <div class="container">
      <div class="row">
        <div class="col-12 text-center">
          <h3 class="pt-3">
            Shopping cart
          </h3>
        </div>
      </div>
  
      <!-- loop over the cart items and display -->
  
      <div v-for="cartItem in cartItems" :key="cartItem.id" class="row mt-2 pt-3">
        <div class="col-2"></div>
        <div class="col-md-3">
          <img
            :src="cartItem.imgUrl"
            alt=""
            class="w-100 card-image-top embed-responsive embed-responsive-16by9"
            style="object-fit: cover"
          />
        </div>
  
        <!-- display product name, quantity -->
        <div class="col-md-5 px-3">
          <div class="card-block px-3">
            <h6 class="card-title">
              {{ cartItem.name }}
            </h6>
  
            <p class="mb-0 font-weight-bold" id="item-price">
              $ {{ cartItem.price }} per unit
            </p>
            <p class="mb-0">Quantity:{{ cartItem.quantity }}</p>
          </div>
          <p class="mb-0">
            Total:
            <span class="font-weight-bold">
              $ {{ cartItem.price * cartItem.quantity }}
            </span>
          </p>
            <button @click="deleteCart(cartItem.id)" class="btn btn-primary mt-2"> Delete </button>
        </div>
        <div class="col-2"></div>
        
        <div class="col-12"><hr /></div>
      </div>
  
      <!-- display the price -->
      <div class="total-cost pt-2 text-right">
        <h5>Total : ${{ totalCost }}</h5>
      </div>

      <div class="text-right">
        <button @click="deleteAllCart()" class="btn btn-primary mr-4">Delete All</button>
        <button class="btn btn-primary ml-1">Check Out</button>
      </div>
    </div>

  </template>

  <script>
    import axios from "axios";
    import swal from 'sweetalert';
export default {
  data() {
    return {
      cartItems: [],
      totalCost: 0,
    };
  },
  methods: {
    async listCartItems() {
        const token = localStorage.getItem('token')
                await axios.request(
                    {
                        headers:{
                            Authorization: `Bearer ${token}`
                        },
                        method:"GET",
                        url:`http://localhost:8082/cart`
                    },
                )
                .then((res)=>{
                    this.cartItems = res.data.data;
                    this.totalCost=0;
                    for(let i=0 ;i<this.cartItems.length;i++){
                        this.totalCost += this.cartItems[i].quantity * this.cartItems[i].price
                    }
                    
                })
                .catch((err)=>{
                    console.log(err);
                })
            },
    async deleteCart(id){
        const token = localStorage.getItem('token')
        await axios.request(
                    {
                        headers:{
                            Authorization: `Bearer ${token}`
                        },
                        method:"POST",
                        url:`http://localhost:8082/cart/delete/${id}`,
                    },
                ).then(()=>{
                    swal({
                        text:"Delete successfully",
                        icon:"success"
                    })
                    this.listCartItems();
                }).catch((err)=>{
                    swal({
                        text:err.response.data.reason,
                        icon:"error"
                    })
                    console.log(err)
                })
                this.listCartItems();
    },
    async deleteAllCart(){
      const token = localStorage.getItem('token')
                await axios.request(
                    {
                        headers:{
                            Authorization: `Bearer ${token}`
                        },
                        method:"POST",
                        url:`http://localhost:8082/cart/delete`
                    },
                )
                .then(()=>{
                    this.listCartItems();                
                })
                .catch((err)=>{
                    console.log(err);
                })
    }
    },
  created() {
    this.listCartItems();
},
};
</script>