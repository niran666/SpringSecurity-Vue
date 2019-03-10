<!--  -->
<template>
  <div>
    <a>hello{{ data }}</a>
    <!-- <span v-show="sendAuthCode" class="auth_text auth_text_blue" @click="getAuthCode">获取验证码</span> -->
    <span v-show="!sendAuthCode" class="auth_text"> <span class="auth_text_blue">页面{{auth_time}} </span> 秒之后过期</span>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        data: "ni",
        sendAuthCode: true,
        /*布尔值，通过v-show控制显示‘获取按钮’还是‘倒计时’ */
        auth_time: 0,
      };
    },
    mounted: function () {
      this.getMessaage();
      this.getAuthCode();
    },
    methods: {
      getMessaage: function () {
        this.$http('http://localhost:8888/hello', {
          async: false
        }).then((res) => {
          console.log(res);
          this.data = res.data
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
      }
    }
  }

</script>
<style lang='scss' scoped>
</style>
