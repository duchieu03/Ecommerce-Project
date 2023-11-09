<template>
     <div class="conatiner">
        <div class="row">
        <div class="col-12 text-center pt-3">
          <!-- display logo -->
        </div>
      </div>
        <div class="row">
        <div class="col-4"></div>
        <div class="col-4 justify-content-center d-flex pt-3">
            
          <div id="signup" class="flex-item border w-100">
            <h2 class="pt-4 pl-4">Reset Password</h2>
            <form @submit.prevent="reset" class="pt-4 pl-4 pr-4">
              <div class="form-group">
                <label for="Email">Email</label>
                <input
                  type="email"
                  v-model="email"
                  class="form-control"
                  required
                />
              </div>
  
              <button class="btn btn-primary my-2">Send Link</button>
            </form>
          </div>
        </div>
        <div class="col-4"></div>
      </div>
  </div>
</template>

<script>
import axios from 'axios';
import swal from 'sweetalert';
    export default{
        data(){
            return {
                email: ""
            }
        },
        methods:{
            async reset(){
                await axios.post("http://localhost:8082/auth/reset",{email: this.email})
                .then(()=>{
                    swal({
                        text:'Verify your reset password request in email',
                        icon:'success'
                    })
                }).catch((err)=>{
                        swal({
                            text:err.response.data.reason,
                            icon:'error'
                        })
                })
            }
        }
    }
</script>