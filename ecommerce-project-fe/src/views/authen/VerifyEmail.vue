<template>
    <div>

    </div>
</template>

<script>
import axios from 'axios';
import swal from 'sweetalert';
    export default{
        async created(){
            const token = this.$route.query.token;
            await axios.post("http://localhost:8082/auth/signup/verify?token="+token)
            .then(()=>{
                swal({
                    text:'Verify successfully',
                    icon:'success'
                })
                this.$router.push({name:'login'})
            })
            .catch((err)=>{
                swal({
                    text:err.response.data.reason,
                    icon:'error'
                })
                console.log(err.response.data.reason);
                this.$router.push({name:'login'})
            })
        }
    }
</script>