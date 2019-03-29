<!--  -->
<template>
  <div>
    <template>
      <el-table v-bind:data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="ID" width="180">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="180">
        </el-table-column>
        <el-table-column prop="sex" label="性别">
        </el-table-column>
        <el-table-column prop="age" label="年龄">
        </el-table-column>
        <el-table-column prop="address" label="地址">
        </el-table-column>
        <el-table-column prop="username" label="登录名">
        </el-table-column>
        <el-table-column prop="identity" label="身份">
        </el-table-column>
        <el-table-column label="账号状态">
          <template slot-scope="scope">
            <div v-if="scope.row.status==='正常'">
              <el-tag type="success" prop="status">正常</el-tag>
            </div>
            <div v-if="scope.row.status==='锁定'">
              <el-tag type="danger" prop="status">锁定</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <el-button v-if="scope.row.identity !== 'admin'" type="primary" size="mini"
              @click="handleUpdate(scope.row)">{{
              "编辑" }}</el-button>
            <el-popover v-if="scope.row.identity !== 'admin'" :ref="scope.row.id" placement="top" width="180">
              <p>确定删除本条数据吗？</p>
              <div style="text-align: right; margin: 0">
                <el-button size="mini" type="text">取消</el-button>
                <el-button type="primary" size="mini" @click="handleDelete(scope.row.id)">确定</el-button>
              </div>
              <el-button slot="reference" type="danger" size="mini">删除</el-button>
            </el-popover>

            <el-popover v-if="scope.row.identity === 'admin'" key="admin" trigger="hover" placement="top"
              style="display: inline-block;">
              <el-alert :closable="false" type="warning" title="权限说明">
                <div>为保证超级管理员在系统中的最高权限</div>
                <div>不允许编辑超级管理员自身的信息</div>
                <div>不允许删除超级管理员账号</div>
              </el-alert>
              <div slot="reference">
                <el-tag style="margin-left: 10px;" type="warning">权限说明</el-tag>
              </div>
            </el-popover>
          </template>
        </el-table-column>
      </el-table>
      <div>
        <el-dialog :title="textMap['update']" :visible.sync="updateDialogFormVisible" width="40%">
          <el-form ref="updateDataForm" :model="temp" label-position="left" label-width="70px"
            style="width: 400px; margin-left:100px;">
            <el-form-item label="昵称" prop="name">
              <el-input v-model="temp.name" />
            </el-form-item>
            <el-form-item label="性别" prop="sex">
              <el-radio-group v-model="temp.sex">
                <el-radio label="男"></el-radio>
                <el-radio label="女"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
              <el-input v-model="temp.age" />
            </el-form-item>
            <el-form-item label="地址" prop="address">
              <el-input v-model="temp.address" type="textarea" />
            </el-form-item>

            <el-form-item label="锁定" prop="status">
              <el-checkbox v-model="temp.status" label="锁定" />
            </el-form-item>
          </el-form>

          <div slot="footer" class="dialog-footer">
            <el-button @click="updateDialogFormVisible = false">{{ "取消" }}</el-button>
            <el-button type="primary" @click="updateData()">{{ "确认" }}</el-button>
          </div>
        </el-dialog>
      </div>
    </template>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        tableData: undefined,
        temp: {
          id: undefined,
          username: undefined,
          password: undefined,
          name: "undefined",
          sex: undefined,
          address: undefined,
          age: undefined,
          status: undefined,
        },
        textMap: {
          update: '更新',
          create: '创建'
        },
        updateDialogFormVisible: false,
      };
    },
    components: {},
    computed: {},
    mounted: function () {
      this.getUserMessage();
    },

    methods: {
      getUserMessage: function () {
        this.$http('http://localhost:8888/role/getAllUser', {
          async: false
        }).then((res) => {
          console.log(res.data);
          this.data = res.data;
          if (res.data.data != null) {
            this.tableData = res.data.data
          }
        });
      },
      handleUpdate(row) {
        alert(row.name)
        this.updateDialogFormVisible = true
        // this.resetTemp()
        this.temp.id = row.id
        this.temp.username = row.username
        this.temp.name = row.name
        this.temp.age = row.age
        this.temp.address = row.address
        this.temp.sex = row.sex
        if (row.status == "锁定") {
          this.temp.status = true
        }
      },
      handleDelete(id) {

        var readyData = {
          'id': id,
        };
        this.$http.delete('http://localhost:8888/role/' + id).then((res) => {
          if (res.data.ok) {
            this.$message({
              message: '恭喜你，修改成功消息',
              type: 'success'
            });
          } else {
            this.$message.error('修改失败');
          }

        })

      },
      lockChangedListener() {
        if (!this.temp.locked) {

        } else {

        }
      },

      updateData() {
        if (this.temp.status) {
          this.temp.status = "锁定"
        } else {
          this.temp.status = "正常"
        }
        this.$http.post('http://localhost:8888/role/updateUser', this.temp).then((res) => {
          if (res.data.ok) {
            this.$message({
              message: '恭喜你，修改成功消息',
              type: 'success'
            });
            this.updateDialogFormVisible = false;
            this.getUserMessage();
          } else {
            this.$message.error('修改失败');
          }

        })


      }
    },

  }

</script>
<style lang='scss' scoped>
  .el-dialog {
    width: 1000px;
  }

  .el-input {
    width: 150px;
  }

</style>
