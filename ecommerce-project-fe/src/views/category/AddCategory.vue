<template>
    <div class="container">
      <div class="row">
        <div class="text-center col-12">
          <h3>Add Category</h3>
        </div>
      </div>
  
      <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
          <form @submit.prevent="addCategory">
            <div class="form-group">
              <label>Category Name</label>
              <input type="text" class="form-control" v-model="categoryName">
            </div>
            <div class="form-group">
              <label>Category Description</label>
              <textarea class="form-control" v-model="description"></textarea>
            </div>
            <div class="form-group">
              <label>Image URL</label>
              <input type="text" class="form-control" v-model="image">
            </div>
            <button class="btn btn-primary">Submit</button>
          </form>
        </div>
        <div class="col-3"></div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  import sweetalert from "sweetalert";
  
  export default {
    data() {
      return {
        categoryName: "",
        description: "",
        image: "",
      };
    },
    methods: {
      addCategory() {
        const category = {
          categoryName: this.categoryName,
          description: this.description,
          imgUrl: this.image,
        };
  
        axios
          .post("http://localhost:8082/category/create", category)
          .then(() => {
            sweetalert({
              text: 'Category added successfully',
              icon: 'success',
            });
          })
          .catch((err) => {
            console.log(err);
            console.log('hello error');
            sweetalert({
              text: err.response.data.reason,
              icon: 'error',
            });
          });
      },
    },
  };
  </script>
  
  <style scoped>
  </style>
  