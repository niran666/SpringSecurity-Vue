<!--  -->
<template>
  <div>
    <el-form ref="form" v-model="form" label-width="80px" class="login-box">
      <el-form-item label="账号">
        <el-input type="text" placeholder="请输入账号" v-model="form.username"> </el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input type="password" placeholder="请输入密码" v-model="form.password"> </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "Login",
    data() {
      return {
          form:{
              username:'',
              password:''
          }
      };
    },


    methods: {
          onSubmit(){
            this.$http.post('http://localhost:8888/login',this.form).then((res) => {
                console.log(res);
                console.log(this);
        })
     
      },

    },
    
  }

</script>
<style lang="scss" scoped>
.login-box{
    width: 350px;
    margin: 180px auto;
    border: 1px solid #DCDFE6;
    padding: 35px;
    border-radius: 5px;
    box-shadow: 0 0 25px #DCDFE6; 
}

</style>
