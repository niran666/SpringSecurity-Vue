<!--  -->
<template>
  <div class="container">

    <div class="img" style="margin：0px auto;">
      <div class="pan-item">
        <div class="pan-info" />
        <img :src="url" class="pan-thumb">
      </div>
      <div class="info-container">
        <div>
          <span style="font-size:20px;padding-top:20px;display:inline-block;">角色：{{data.data.username}}</span>
        </div>
        <div>
          <span style="font-size:20px;padding-top:20px;display:inline-block;">权限：{{data.data.identity}}</span>
        </div>
      </div>
      <el-button type="primary" icon="el-icon-edit" circle @click="dialogFormVisible = true"></el-button>
    </div>
    <el-dialog title="编辑信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form ref="form" :model="form" label-width="80px" class="el-form">
        <el-form-item label="姓名">
          <el-input v-model="form.name" class="el-input"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="地址">
          <el-input type="textarea" v-model="form.address"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">提交</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        data: "ni",
        sendAuthCode: true,
        dialogFormVisible: false,
        /*布尔值，通过v-show控制显示‘获取按钮’还是‘倒计时’ */
        auth_time: 0,
        url: "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1552472710091&di=14c706a028cfdf83f36870cad559e5e7&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2Fc4c3b3bf3feac8bbd89671504a1835ab8b62db26.jpg",

        form: {
          name: '',
          age: '',
          sex: '',
          address: '',
        },
      };

    },
    mounted: function () {
      this.getMessaage();
      this.getAuthCode();
    },
    methods: {
      getMessaage: function () {
        this.$http('http://localhost:8888/role/hello', {
          async: false
        }).then((res) => {
          console.log(res.data);
          this.data = res.data;
          if(res.data.data.role!=null)
          {
            this.form.name=res.data.data.role.name;
            this.form.sex=res.data.data.role.sex;
            this.form.age=res.data.data.role.age;
            this.form.address=res.data.data.role.address;
          }
        });
      },
      getAuthCode: function () {
        this.sendAuthCode = false;
        this.auth_time = 60;
        var auth_timetimer = setInterval(() => {
          this.auth_time--;
          if (this.auth_time <= 0) {
            this.sendAuthCode = true;
            clearInterval(auth_timetimer);
          }
        }, 1000);
      },
      onSubmit(){
            this.$http.post('http://localhost:8888/role/save',this.form).then((res) => {
                if (res.data.ok) {
            this.$message({
              message: '恭喜你，修改成功消息',
              type: 'success'
            });
            this.dialogFormVisible=false;
            this.getUserMessage();
          }
          else{
            this.$message.error('修改失败');
          }
        })
     
      },
    }
  }

</script>
<style rel="stylesheet/scss" lang="scss" scoped>
  // .container {
  //   text-align: center;
  // }

  .img {

    width: 500px;
    margin-top: 100px;
    // margin:auto;
    margin-left: auto;
    margin-right: auto
  }

  .el-dialog {
    width: 1000px;
  }

  .el-input {
    width: 150px;
  }

  .pan-item {
    float: left;
    z-index: 1;
    width: 150px;
    height: 150px;
    border-radius: 50%;
    display: inline-block;
    position: relative;
    cursor: default;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
  }

  .pan-thumb {
    width: 100%;
    height: 100%;
    background-size: 100%;
    border-radius: 50%;
    overflow: hidden;
    position: absolute;
    transform-origin: 95% 40%;
    transition: all 0.3s ease-in-out;
  }
</style>
