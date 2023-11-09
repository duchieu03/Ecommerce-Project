<template>
    <div class="container">
      <div class="row">
        <div class="col-12 text-center pt-3">
          <!-- display logo -->
        </div>
      </div>
  
      <!-- header -->
  
      <div class="row">
        <div class="col-12 justify-content-center d-flex pt-3">
          <div id="signup" class="flex-item border">
            <h2 class="pt-4 pl-4">Create Account</h2>
            <form @submit.prevent="signup" class="pt-4 pl-4 pr-4">
              <div class="form-group">
                <label for="Email">Email</label>
                <input
                  type="email"
                  v-model="email"
                  class="form-control"
                  required
                />
              </div>
              <div class="form-row">
                <div class="col">
                  <div class="form-group">
                    <label> First Name</label>
                    <input
                      type="text"
                      v-model="firstName"
                      class="form-control"
                      required
                    />
                  </div>
                </div>
                <div class="col">
                  <div class="form-group">
                    <label> Last Name</label>
                    <input
                      type="text"
                      v-model="lastName"
                      class="form-control"
                      required
                    />
                  </div>
                </div>
              </div>
  
              <!-- password -->
              <div class="form-group">
                <label for="Password"> Password</label>
                <input
                  type="password"
                  v-model="password"
                  class="form-control"
                  required
                />
              </div>
  
              <!-- confirm password -->
              <div class="form-group">
                <label for="Password"> Confirm password</label>
                <input
                  type="password"
                  v-model="confirmPassword"
                  class="form-control"
                  required
                />
              </div>
  
              <button class="btn btn-primary mt-2">Create Account</button>
            </form>
          </div>
        </div>
      </div>
  
      <!-- form -->
    </div>
  </template>
  <script>
  import swal from 'sweetalert';
  import axios from 'axios';
    export default{
        data(){
            return {
                email: "",
                firstName:"",
                lastName:"",
                password:"",
                confirmPassword:""
            }
        },
        methods:{
                signup(){
                if(this.password!=this.confirmPassword){
                    swal({
                        text:'Password is not same as confirm password',
                        icon:'error'
                    })
                }else{
                    var user = {
                        firstName : this.firstName,
                        lastName: this.lastName,
                        email: this.email,
                        password: this.password
                    }
                 axios.post("http://localhost:8082/auth/signup",user)
                    .then((res)=>{
                        swal({
                            text:res.data.reason,
                            icon:'success'
                        })
                        console.log(res);
                    })
                    .catch((err)=>{
                        swal({
                            text:err.data.reason,
                            icon:'error'
                        })
                        console.log(err);
                    })
                }
            }
        }
    }
</script>