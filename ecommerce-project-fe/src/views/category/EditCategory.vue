<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h3 class="pt-3">Edit Category</h3>
            </div>
        </div>

        <div class="row">
            <div class="col-3"></div>
            <div class="col-6">
                <form v-if="category">
                    <div class="form-group">
                        <label>Category Name</label>
                        <input type="text" class="form-control" 
                        v-model="category.categoryName" required>
                    </div>
                    <div class="form-group">
                        <label>Description</label>
                        <input type="text" class="form-control" 
                        v-model="category.description" required>
                    </div>
                    <div class="form-group">
                        <label>Imange Url</label>
                        <input type="text" class="form-control" 
                        v-model="category.imgUrl" required>
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
                category:null,
                id:null
            }
        },
        methods:{
            async editCategory(){
               await axios.post("http://localhost:8082/category/update/"+this.id,this.category)
               .then(()=>{
                    swal({
                        text:"Category has been updated successfully",
                        icon:"success"
                    })
                    this.$emit("fetchData")
                    this.$router.push({name: 'listCategory'});
               })
               .catch((err)=>{
                    swal({
                        text: err.response.data.reason,
                        icon: 'error',
                    });
               })
            }
        },
        props:["categories"],
        mounted(){
            console.log("hello edit")
            this.id=this.$route.params.id;
            this.category=this.categories.find(cate => cate.id == this.id)
        }
    }
</script>