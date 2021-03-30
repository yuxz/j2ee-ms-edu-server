<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()" icon="el-icon-search" circle></el-button>
        <el-button v-if="isAuth('teacher:teacher:save')" type="primary" @click="addOrUpdateHandle()" icon="el-icon-plus" circle></el-button>
        <el-button v-if="isAuth('teacher:teacher:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0" icon="el-icon-delete" circle></el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="id">
      </el-table-column>
      <el-table-column
        prop="institutionId"
        header-align="center"
        align="center"
        label="Institution">
      </el-table-column>
      <el-table-column
        prop="campusId"
        header-align="center"
        align="center"
        label="Campus">
      </el-table-column>
      <el-table-column
        prop="firstName"
        header-align="center"
        align="center"
        label="Family Name">
      </el-table-column>
      <el-table-column
        prop="lastName"
        header-align="center"
        align="center"
        label="Given Name">
      </el-table-column>
      <el-table-column
        prop="name"
        header-align="center"
        align="center"
        label="English Name">
      </el-table-column>
      <el-table-column
        prop="cname"
        header-align="center"
        align="center"
        label="Chinese Name">
      </el-table-column>
      <el-table-column
        prop="gender"
        header-align="center"
        align="center"
        label="Gender">
      </el-table-column>
      <el-table-column
        prop="birth"
        header-align="center"
        align="center"
        label="Date of Birth">
      </el-table-column>
      <el-table-column
        prop="mobile"
        header-align="center"
        align="center"
        label="Mobile">
      </el-table-column>
      <el-table-column
        prop="email"
        header-align="center"
        align="center"
        label="Email">
      </el-table-column>
      <el-table-column
        prop="qq"
        header-align="center"
        align="center"
        label="QQ">
      </el-table-column>
      <el-table-column
        prop="postId"
        header-align="center"
        align="center"
        label="Position">
      </el-table-column>
      <el-table-column
        prop="hired"
        header-align="center"
        align="center"
        label="Hire Date">
      </el-table-column>
      <el-table-column
        prop="leaved"
        header-align="center"
        align="center"
        label="Leaved Date">
      </el-table-column>
      <el-table-column
        prop="isLeaved"
        header-align="center"
        align="center"
        label="whether Leaved">
      </el-table-column>
      <el-table-column
        prop="isActived"
        header-align="center"
        align="center"
        label="Whether to Active Account">
      </el-table-column>
      <el-table-column
        prop="note"
        header-align="center"
        align="center"
        label="Memo">
      </el-table-column>
      <el-table-column
        prop="field1"
        header-align="center"
        align="center"
        label="field1">
      </el-table-column>
      <el-table-column
        prop="field2"
        header-align="center"
        align="center"
        label="field2">
      </el-table-column>
      <el-table-column
        prop="field3"
        header-align="center"
        align="center"
        label="field3">
      </el-table-column>
      <el-table-column
        prop="field4"
        header-align="center"
        align="center"
        label="field4">
      </el-table-column>
      <el-table-column
        prop="field5"
        header-align="center"
        align="center"
        label="field5">
      </el-table-column>
      <el-table-column
        prop="field6"
        header-align="center"
        align="center"
        label="field6">
      </el-table-column>
      <el-table-column
        prop="logicDeleted"
        header-align="center"
        align="center"
        label="whether be deleted[0-deleted，1.not deleted]">
      </el-table-column>
      <el-table-column
        prop="created"
        header-align="center"
        align="center"
        label="Created Time">
      </el-table-column>
      <el-table-column
        prop="updated"
        header-align="center"
        align="center"
        label="Updated Time">
      </el-table-column>
      <el-table-column
        prop="userId"
        header-align="center"
        align="center"
        label="Creator">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)" icon="el-icon-edit" circle></el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)" icon="el-icon-delete" circle></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './teacher-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/teacher/teacher/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/teacher/teacher/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
    }
  }
</script>
