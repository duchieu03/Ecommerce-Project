<template>
  <NavbarComponent />
 
  <router-view v-if="categories && products" style="min-height: 60vh;"
  :categories="categories"
  :products="products"
  @fetchData="fetchData"
  >
  </router-view>
  <FooterComponent/>
</template>

<script>
  const axios = require("axios")
  import NavbarComponent from "./components/Nav.vue";
  import FooterComponent from "./components/FooterComponent.vue";
  export default{
    components:{NavbarComponent, FooterComponent},
    data(){
      return{
        categories: null,
        products: null,
      }
    },
    methods:{
       async fetchData(){
        console.log("get data")
        await axios.get("http://localhost:8082/category/list")
            .then((res) =>{
                this.categories = res.data.data;
            } )
            .catch(err => console.log(err))

        await axios.get("http://localhost:8082/product/list")
            .then(res => {
              this.products = res.data.data;
            })
            .catch(err => console.log(err));
      }
    },

    created() {
      this.fetchData();
    },
  }
</script>

<style>


#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
