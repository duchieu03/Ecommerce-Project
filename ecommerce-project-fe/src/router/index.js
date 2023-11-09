import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AddCategory from '../views/category/AddCategory.vue'
import CategoryList from '../views/category/CategoryList.vue'
import EditCategory from '../views/category/EditCategory.vue'
import AdminView from '../views/AdminView.vue'
import ProductList from '../views/product/ProductList.vue'
import AddProduct from '../views/product/AddProduct.vue'
import EditProduct from '../views/product/EditProduct.vue'
import ProductDetail from '../views/product/ProductDetail.vue'
import SigninView from '../views/authen/SigninView.vue'
import SignupView from '../views/authen/SignupView.vue'
import VerifyEmail from '../views/authen/VerifyEmail.vue'
import ResetPassword from '../views/authen/ResetPassword.vue'
import VerifyResetPassword from '../views/authen/VerifyResetPassword.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/admin/category/add',
    name: 'addCategory',
    component: AddCategory
  }
  ,
  {
    path: '/admin/category/:id',
    name: 'editCategory',
    component: EditCategory
  }
  ,
  {
    path: '/admin/category',
    name: 'listCategory',
    component: CategoryList
  } ,
  {
    path: '/admin',
    name: 'admin',
    component: AdminView
  },
  {
    path: '/admin/product',
    name: 'listProduct',
    component: ProductList
  },
  {
    path: '/admin/product/add',
    name: 'addProduct',
    component: AddProduct
  },
  {
    path: '/admin/product/:id',
    name: 'editProduct',
    component: EditProduct
  }
  ,
  {
    path: '/product/:id',
    name: 'detailProduct',
    component: ProductDetail
  }
  ,
  {
    path: '/auth/signin',
    name: 'login',
    component: SigninView
  }
  ,
  {
    path: '/auth/signup',
    name: 'register',
    component: SignupView
  },
  {
    path: '/auth/signup/verify',
    name: 'verify',
    component: VerifyEmail
  },
  {
    path: '/auth/reset',
    name: 'reset',
    component: ResetPassword
  },
  {
    path: '/auth/reset/verify',
    name: 'verifyReset',
    component: VerifyResetPassword
  }
]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
