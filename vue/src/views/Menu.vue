<!--  -->
<template>
  <div class="container">
    <div class="left">
      <el-row class="tac">
        <el-col :span="12">
          <el-menu :default-active="activeIndex" class="el-menu-vertical-demo" router @open="handleOpen" @close="handleClose"
            background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>导航一</span>
              </template>
              <el-menu-item-group>
                <template slot="title">主页</template>
                <el-menu-item index="/menu/hello" route="/menu/hello">主页</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-col>
      </el-row>
    </div>
    <div class="right">
      <router-view />
    </div>

  </div>
</template>

<script>
  export default {
    data() {
      return {
        url: "/hello"
      };
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    },
    computed: {
      activeIndex() {
        return this.$route.path.replace('/', '/menu')
      }
    },
  }

</script>
<style lang='scss' scoped>

body { height: 100%; padding: 0; margin: 0; }
  .container {
    display: table;
    width: 100%;
  }

  .container .left{
    width: 200px;
  }
  .container .right {
    display: table-cell;
  }



  .container .right {
    width: 100%;
  }
  .el-menu-vertical-demo {
    width: 200px;
    // position: absolute;
    // top: 0px;
    // bottom: 0px;
    // left: 0px;
    // height: 700px;
    position:fixed;
    height:100%;
  }


</style>
