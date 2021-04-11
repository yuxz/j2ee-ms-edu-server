<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()" icon="el-icon-search" circle></el-button>
        <!-- <el-button v-if="isAuth('cms:content:save')" type="primary" @click="addOrUpdateHandle()" icon="el-icon-plus" circle></el-button> -->
        <el-button v-if="isAuth('cms:content:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0" icon="el-icon-delete" circle></el-button>
		<el-dropdown   @command="handleCommand">
  <el-button type="primary" icon="el-icon-plus" round>
    New...
  </el-button>
  <el-dropdown-menu slot="dropdown" >
    <el-dropdown-item command="a" icon="el-icon-document"> Start a new doc</el-dropdown-item>
    <el-dropdown-item command="b" icon="el-icon-folder-add"> Make a new folder</el-dropdown-item>
    <el-dropdown-item command="b" icon="el-icon-upload2"> Upload files</el-dropdown-item>
    <el-dropdown-item command="d" icon="el-icon-mobile"> Dropbox</el-dropdown-item>
    <el-dropdown-item command="e" icon="el-icon-goods"> OneDirve</el-dropdown-item>
  </el-dropdown-menu>
</el-dropdown>
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
        prop="catagoryId"
        header-align="center"
        align="center"
        label="分类">
      </el-table-column>      
      <el-table-column
        prop="title"
        header-align="center"
        align="center"
        label="标题">
      </el-table-column>
       <el-table-column
        prop="ispublished"
        header-align="center"
        align="center"
        label="是否发布">
      </el-table-column>
    <el-table-column
        prop="published"
        header-align="center"
        align="center"
        label="发布日期">
      </el-table-column>   
      <el-table-column
        prop="userId"
        header-align="center"
        align="center"
        label="发布人">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="detail(scope.row.id)" icon="el-icon-document" circle></el-button>
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
  import AddOrUpdate from './content-add-or-update'
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
	handleCommand(command) {
        this.$message('click on item ' + command);
      },
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/cms/content/list'),
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
            url: this.$http.adornUrl('/cms/content/delete'),
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
<style>
  .el-dropdown {
    vertical-align: top;
  }
  .el-dropdown + .el-dropdown {
    margin-left: 15px;
  }
  .el-icon-arrow-down {
    font-size: 12px;
	/* width: 800px; */
  }
</style>