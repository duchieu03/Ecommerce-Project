<template>
      <div class="conatiner">
    <div class="row">
      <div class="col-12 justify-content-center d-flex flex-row pt-5">
        <div id="signin" class="flext-item border">
          <h2 class="pt-4">Sign-In</h2>
          <form @submit.prevent="signin" class="form-group pt-4 pl-4 pr-4">
            <div class="form-group">
              <label>Email </label>
              <input v-model="email" type="email" class="form-control" />
            </div>
            <div class="form-group">
              <label>Password </label>
              <input v-model="password" type="password" class="form-control" />
            </div>
            <button class="btn btn-primary mt-2 py-0">Continue</button>
          </form>
        </div>
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
                email: null,
                password: null
            }
        },
        methods:{
            async signin(){
                var user={
                    email: this.email,
                    password:this.password
                }
                await axios.post("http://localhost:8082/auth/login",user)
                .then((res)=>{
                    swal({
                      text:res.data.reason,
                      icon:'success'
                    })
                    localStorage.setItem("token",res.data.data)
                })
                .catch((err)=>{
                  swal({
                      text:err.data.reason,
                      icon:'error'
                    })
                    console.log(err)
                })
            }
        }
    }
</script>