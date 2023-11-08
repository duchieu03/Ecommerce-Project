<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h3 class="pt-3">Edit Product</h3>
            </div>
        </div>

        <div class="row">
            <div class="col-3"></div>
            <div class="col-6">
                <form v-if="product">
                    <div class="form-group">
                        <label>Category</label>
                        <select class="form-control" v-model="product.categoryId" required>
                            <option v-for="category in categories" :key="category.id"
                                    :value="category.id" 
                                    :selected="category.id === product.categoryId"
                                    >{{ category.categoryName }}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Product Name</label>
                        <input type="text" class="form-control" 
                        v-model="product.name" required>
                    </div>
                    <div class="form-group">
                        <label>Description</label>
                        <input type="text" class="form-control" 
                        v-model="product.description" required>
                    </div>
                    <div class="form-group">
                        <label>Imange Url</label>
                        <input type="text" class="form-control" 
                        v-model="product.imgUrl" required>
                    </div>
                    <div class="form-group">
                        <label>Price</label>
                        <input type="text" class="form-control" 
                        v-model="product.price" required>
                    </div>
                    <button type="button" class="btn btn-primary" @click="editCategory()">Save</button>
                </form>
            </div>
            <div class="col-3"></div>
        </div>
    </div>

    
</template>

<script>
import axios from 'axios';
import swal from 'sweetalert'

    export default{
        data(){
            return {
                product:null,
                id:null
            }
        },
        methods:{
            async editCategory(){
                console.log(this.product);
               await axios.post("http://localhost:8082/product/update/"+this.id,this.product)
               .then(()=>{
                    swal({
                        text:"Product has been updated successfully",
                        icon:"success"
                    })
                    this.$emit("fetchData")
                    this.$router.push({name: 'listProduct'});
               })
               .catch((err)=>{
                    swal({
                        text: err.response.data.reason,
                        icon: 'error',
                    });
               })
            }
            },
        props:["products","categories"],
        mounted(){
            console.log("hello edit")
            this.id=this.$route.params.id;
            this.product=this.products.find(pro => pro.id == this.id)
        }
    }
</script>