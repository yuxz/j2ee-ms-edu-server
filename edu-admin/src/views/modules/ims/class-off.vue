<template>
  <div class="mod-config">
    <el-form
      :inline="true"
      :model="dataForm"
      @keyup.enter.native="getDataList()"
    >
      <el-form-item>
        <el-input
          v-model="dataForm.key"
          placeholder="请输入查询关键字"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()" icon="el-icon-search"
      circle></el-button>
        <!-- <el-button
          v-if="isAuth('ims:class:save')"
          type="primary"
          @click="addOrUpdateHandle()"
           icon="el-icon-plus"
      circle></el-button
        >
        <el-button
          v-if="isAuth('ims:class:delete')"
          type="danger"
          @click="deleteHandle()"
          :disabled="dataListSelections.length <= 0"
          icon="el-icon-delete"
      circle></el-button
        > -->
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%"
    >
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50"
      >
      </el-table-column>
      <el-table-column
        prop="campusName"
        header-align="center"
        align="center"
        label="校区"
      >
      </el-table-column>
      <el-table-column
        prop="name"
        header-align="center"
        align="center"
        label="班级名称"
      >
      </el-table-column>
      <el-table-column
        prop="started"
        header-align="center"
        align="center"
        label="开班时间"
      >
      </el-table-column>
      <el-table-column
        prop="ended"
        header-align="center"
        align="center"
        label="结班时间"
      >
      </el-table-column>
      <el-table-column
        prop="classTypeName"
        header-align="center"
        align="center"
        label="班级类型"
      >
      </el-table-column>
      <el-table-column
        prop="classroomName"
        header-align="center"
        align="center"
        label="所在教室"
      >
      </el-table-column>
     
      <!-- <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="创建时间"
      > 
      </el-table-column>
-->
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作"
      >
        <template slot-scope="scope">
			<el-button
            type="text"
            size="small"
            @click="detailHandle(scope.row.id)"
            >Detail</el-button
          >
          <!-- <el-button
            type="text"
            size="small"
            @click="teacherClassHandle(scope.row.id)"
            >分配老师</el-button
          >
          <el-button
            type="text"
            size="small"
            @click="studentClassHandle(scope.row.id)"
            >分配学生</el-button
          >
          <el-button
            type="text"
            size="small"
            @click="addOrUpdateHandle(scope.row.id)"
            >修改</el-button
          > 
          <el-button
            type="text"
            size="small"
            @click="deleteHandle(scope.row.id)"
            >删除</el-button
          >
		  -->
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
      layout="total, sizes, prev, pager, next, jumper"
    >
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
	<class-detail
      v-if="detailVisible"
      ref="detail"
    ></class-detail>
    <!-- <add-or-update
      v-if="addOrUpdateVisible"
      ref="addOrUpdate"
      @refreshDataList="getDataList"
    ></add-or-update>
    <teacher-class v-if="teacherClassVisible" ref="teacherClassUpdate" @refreshData="getDataList"></teacher-class>
    <student-class v-if="studentClassVisible" ref="studetnClassUpdate" @refreshData="getDataList"></student-class>
   -->
   </div>
</template>

<script>
// import AddOrUpdate from "./class-add-or-update";
import ClassDetail from "./class-detail";
// import TeacherClass from "../tms/teacherclass";
// import StudentClass from "../sms/studentclass";
export default {
  components: {
	ClassDetail,
    // AddOrUpdate,
    // TeacherClass,
    // StudentClass,
  },
  data() {
    return {
      //显示关联老师窗口
      teacherClassVisible: false,
      //显示学生关联窗口
      studentClassVisible: false,
      dataForm: {
        key: "",
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
	  detailVisible: false,
    };
  },

  activated() {
    this.getDataList();
  },
  methods: {
    // 分配老师
    teacherClassHandle(classId) {
      this.teacherClassVisible = true;
	  this.$nextTick(() => {
        this.$refs.teacherClassUpdate.init(classId);
      });
    },

    // 分配学生
    studentClassHandle(classId) {
      this.studentClassVisible = true;
	  this.$nextTick(() => {
        this.$refs.studetnClassUpdate.init(classId);
      });
    },

    // 结业
    changeIsfihished() {},
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      this.$http({
        url: this.$http.adornUrl("/ims/class/list/2"),
        method: "get",
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize,
          key: this.dataForm.key,
        }),
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.page.list;
          this.totalPage = data.page.totalCount;
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val;
    },

	//detail
	detailHandle(id) {
      this.detailVisible = true;
      this.$nextTick(() => {
        this.$refs.detail.init(id);
      });
    },
    // 新增 / 修改
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true;
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id);
      });
    },
    // 删除
    deleteHandle(id) {
      var ids = id
        ? [id]
        : this.dataListSelections.map((item) => {
            return item.id;
          });
      this.$confirm(
        `确定对[id=${ids.join(",")}]进行[${id ? "删除" : "批量删除"}]操作?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).then(() => {
        this.$http({
          url: this.$http.adornUrl("/ims/class/delete"),
          method: "post",
          data: this.$http.adornData(ids, false),
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.getDataList();
              },
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },
  },
};
</script>
