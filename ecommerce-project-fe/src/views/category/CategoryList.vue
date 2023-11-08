<template>
    <div class="container">
        <div class="row">
            <div class="text-center col-12">
                <h2>Our Category</h2>
                <router-link :to="{ name: 'addCategory' }">
                <button class="btn" style="float:right">Add Category</button>
                </router-link>
            </div>
        </div>
         <div class="row">
            <div
            v-for="category of categories"
            :key="category.id"
            class="col-xl-4 col-md-6 col-12 pt-3 d-flex"
            >
            <CategoryBox :category="category"> </CategoryBox>
            </div>
          </div> 
    </div>
  </template>
  
  <script>
import CategoryBox from "../../components/category/CategoryBox";
  export default {
    components:{
      CategoryBox
    },
    props:["categories"]
  };
  </script>
  
  <style scoped>
  </style>
  