<template>
  <div class="main">
    <div  id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			</ol>
      <div class="carousel-inner">
					<div class="carousel-item active">
						<img class="d-block w-100" src="../assets/images/banner-image-1-1920x500.jpg" alt="First slide">
					</div>
          <div class="carousel-item">
            <img class="d-block w-100" src="../assets/images/slider-image-2-1920x700.jpg" alt="Second slide">
					</div>
					<div class="carousel-item">
            <img class="d-block w-100" src="../assets/images/slider-image-3-1920x700.jpg" alt="Third slide">
					</div>
        </div>  
      <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
    <br>
		<br>

    <div class="container">
        <div class="row">
            <div class="text-center col-12">
                <h3>Our Category</h3>
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
    <br>
    <br>
    <div class="container">
        <div class="row">
            <div class="text-center col-12">
                <h3>Our Product</h3>
            </div>
        </div>
         <div class="row">
            <div
            v-for="product of products"
            :key="product.id"
            class="col-xl-4 col-md-6 col-12 pt-3 d-flex"
            >
            <ProductBox :product="product"> </ProductBox>
            </div>
          </div> 
    </div>
    
  </div>
</template>


<script>

import CategoryBox from "../components/category/CategoryBox.vue"
import ProductBox from "@/components/ProductBox.vue";
export default {
  name: 'HomeView',
  props:["categories","products"],
  components: {
    CategoryBox,
    ProductBox
  },

  
}
</script>

<style scoped>

</style>