<template>
  <div>
    <el-dialog
      :close-on-click-modal="false"
      :visible.sync="visible"
      @closed="dialogClose"
	   title="班級已有學生"
    >
	<el-dialog
        width="40%"
        title="选择學生"
        :visible.sync="innerVisible"
        append-to-body
      >
        <div>
          <el-form
            :inline="true"
            :model="dataForm"
            @keyup.enter.native="getInnerDataList()"
          >
            <el-form-item>
              <el-input
                v-model="dataForm.key"
                placeholder="请输入查询关键字"
                clearable
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="getInnerDataList()" icon="el-icon-search"
              circle></el-button>
            </el-form-item>
          </el-form>
          <el-table
            :data="DataListIncludingByClass"
            border
            v-loading="dataListLoading"
            @selection-change="innerSelectionChangeHandle"
            style="width: 100%"
          >
            <el-table-column
              type="selection"
              header-align="center"
              align="center"
            ></el-table-column>            
            <el-table-column
              prop="name"
              header-align="center"
              align="center"
              label="姓名"
            ></el-table-column>
             <el-table-column
            prop="mobile"
            header-align="center"
            align="center"
            label="電話"
          >
          </el-table-column>
		  <el-table-column
            prop="email"
            header-align="center"
            align="center"
            label="郵箱"
          >  </el-table-column>        
          </el-table>
          <el-pagination
            @size-change="sizeChangeHandle"
            @current-change="currentChangeHandle"
            :current-page="pageIndex"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="pageSize"
            :total="totalPage"
            layout="total, sizes, prev, pager, next, jumper"
          ></el-pagination>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="innerVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitAddRealtion"
            >分配</el-button
          >
        </div>
      </el-dialog>

      <div class="mod-config">
        <el-form
          :inline="true"
          :model="dataForm"
          @keyup.enter.native="getDataList()"
        >
          <el-form-item>
            
            <el-button
              v-if="isAuth('sms:studentclass:save')"
              type="primary"
              @click="addStudentHandle()"
               icon="el-icon-plus"
              circle></el-button
            >
            <el-button
              v-if="isAuth('sms:studentclass:delete')"
              type="danger"
              @click="deleteHandle()"
              :disabled="dataListSelections.length <= 0"
               icon="el-icon-delete"
              circle></el-button
            >
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
            prop="studentName"
            header-align="center"
            align="center"
            label="学生"
          ></el-table-column>
		   <el-table-column
            prop="mobile"
            header-align="center"
            align="center"
            label="電話"
          >
          </el-table-column>
		  <el-table-column
            prop="email"
            header-align="center"
            align="center"
            label="郵箱"
          >
          </el-table-column>
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
                @click="deleteHandle(scope.row.id)"
                >移除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
       
        
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import AddOrUpdate from "./studentclass-add-or-update";

export default {
  components: {
    // AddOrUpdate,
  },
  data() {
    return {
      classId: 0,
      visible: false,
	  studentClass:[],      
      dataList: [],   
      dataListLoading: false,
      dataListSelections: [],
      

	  //內部彈出窗口   
 	  innerVisible: false,
	  dataForm: {
        key: "",
      },
	  DataListIncludingByClass:[],
	  pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
	  innerDataListSelections: [],

    };
  },

  activated() {
    this.getDataList();
  },
  methods: {
    init(id) {
      this.classId = id || 0;
      this.visible = true;
      this.$http({
        url: this.$http.adornUrl(
         "/sms/studentclass/list/" + this.classId
        ),
        method: "get",
        params: this.$http.adornParams({})
      }).then(({ data }) => {
        this.dataList = data.data;
      });
    },
    dialogClose() {},
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      this.$http({
        url: this.$http.adornUrl("/sms/studentclass/list/" + this.classId),
        method: "get",
        // params: this.$http.adornParams({
        //   page: this.pageIndex,
        //   limit: this.pageSize,
        //   key: this.dataForm.key,
        // }),
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data;
        //   this.totalPage = data.page.totalCount;
        } else {
          this.dataList = [];
        //   this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },

    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val;
    },
    // // 新增 / 修改
    // addOrUpdateHandle(id) {
    //   this.addOrUpdateVisible = true;
    //   this.$nextTick(() => {
    //     this.$refs.addOrUpdate.init(id);
    //   });
    // },
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
          url: this.$http.adornUrl("/sms/studentclass/delete"),
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
		//////////////為班級添加學生窗口///////////////////
	//為班級添加學生：彈出窗口
	addStudentHandle(){
	  this.getInnerDataList();
      this.innerVisible = true;
	},
	dialogClose() {},
    //為班級添加學生：每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    //為班級添加學生：当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    //為班級添加學生：多选    
	innerSelectionChangeHandle(val) {
      this.innerdataListSelections = val;
    },
    //為班級添加學生：從服務端獲取數據
	getInnerDataList() {
      this.dataListLoading = true;
      this.$http({
        url: this.$http.adornUrl(
			// /tms/studentclass/list/" + this.classId
          "/sms/studentclass/list/" + this.classId + "/includingByClass"
        ),
        method: "get",
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize,
          key: this.dataForm.key
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.DataListIncludingByClass = data.page.list;
          this.totalPage = data.page.totalCount;
        } else {
          this.DataListIncludingByClass = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
	 //為班級添加學生：分配學生
    submitAddRealtion() {
      this.innerVisible = false;
      //准备数据
      console.log("准备新增的数据", this.innerdataListSelections);
      if (this.innerdataListSelections.length > 0) {
        let postData = [];
        this.innerdataListSelections.forEach(item => {
          postData.push({ studentId: item.id, classId: this.classId });
		  
        });
		console.log("studentClassData"+ postData)
        this.$http({
          url: this.$http.adornUrl("/sms/studentclass/saveBatch"),
          method: "post",
          data: this.$http.adornData(postData, false)
        }).then(({ data }) => {
          if (data.code == 0) {
            this.$message({ type: "success", message: "新增关联成功" });
          }
          this.$emit("refreshData");
          this.init(this.classId);
        });
      } else {
      }
    },
  },
};
</script>
