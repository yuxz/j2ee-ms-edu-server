<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <category-tree @category-tree-click="categoryTreeClick"></category-tree>
    </el-col>

    <el-col :span="18">
      <div class="mod-config">
        <el-form
          :inline="true"
          :model="dataForm"
          @keyup.enter.native="getDataList()"
        >
          <el-form-item>
            <el-input
              v-model="dataForm.key"
              placeholder="请输入查询条件"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              @click="getDataList()"
              icon="el-icon-search"
              circle
            ></el-button>
            <el-button
              v-if="isAuth('exam:paper:save')"
              type="primary"
              @click="addOrUpdateHandle()"
              icon="el-icon-plus"
              circle
            ></el-button>
            <el-button
              v-if="isAuth('exam:paper:delete')"
              type="danger"
              @click="deleteHandle()"
              :disabled="dataListSelections.length <= 0"
              icon="el-icon-delete"
              circle
            ></el-button>
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
            prop="categoryName"
            header-align="center"
            align="center"
            label="试卷分类"
          >
          </el-table-column>
          <el-table-column
            prop="paperNo"
            header-align="center"
            align="center"
            label="试卷编号"
          >
          </el-table-column>
          <el-table-column
            prop="name"
            header-align="center"
            align="center"
            label="试卷名称"
            width="380"
          >
          </el-table-column>
          <el-table-column
            prop="score"
            header-align="center"
            align="center"
            label="总分"
            width="45"
          >
          </el-table-column>
          <el-table-column
            prop="items"
            header-align="center"
            align="center"
            label="题数"
            width="45"
          >
          </el-table-column>
          <el-table-column
            prop="limitedTime"
            header-align="center"
            align="center"
            label="时限"
            width="45"
          >
          </el-table-column>
          <el-table-column
            prop="browseNum"
            header-align="center"
            align="center"
            label="浏览"
          >
          </el-table-column>
          <el-table-column
            prop="testNum"
            header-align="center"
            align="center"
            label="测试"
          >
          </el-table-column>
          <el-table-column
            prop="logicDeleted"
            header-align="center"
            align="center"
            label="是否显示"
          >
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.logicDeleted"
                active-color="#13ce66"
                inactive-color="#ff4949"
                :active-value="1"
                :inactive-value="0"
                @change="changeLogicDeleted(scope.row)"
              >
              </el-switch>
            </template>
          </el-table-column>
          <el-table-column
            prop="createTime"
            header-align="center"
            align="center"
            label="date"
          >
          </el-table-column>
          <el-table-column
            prop="userId"
            header-align="center"
            align="center"
            label="创建人"
          >
          </el-table-column>
          <el-table-column
            fixed="right"
            header-align="center"
            align="center"
            width="170"
            label="操作"
          >
            <template slot-scope="scope">
              <el-button
                type="primary"
                icon="el-icon-document"
                size="small"
                @click="detailHandle(scope.row.id)"
                circle
              >
              </el-button>
              <el-button
                type="primary"
                icon="el-icon-edit"
                size="small"
                @click="addOrUpdateHandle(scope.row.id)"
                circle
              >
              </el-button>
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="small"
                @click="deleteHandle(scope.row.id)"
                circle
              ></el-button>
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
        <add-or-update
          v-if="addOrUpdateVisible"
          ref="addOrUpdate"
          @refreshDataList="getDataList"
        ></add-or-update>
        <detail v-if="detailVisible" ref="detail"></detail>
      </div>
    </el-col>
  </el-row>
</template>



<script>
/**
 * 父子组件传递数据
 * 1)、子组件给父组件传递数据，事件机制；
 *    子组件给父组件发送一个事件，携带上数据。
 * // this.$emit("事件名",携带的数据...)
 */
// 这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
// 例如：import 《组件名称》 from '《组件路径》';

import CategoryTree from "../common/category-tree";
import AddOrUpdate from "./paper-add-or-update";
import Detail from "./detail";
export default {
  components: { CategoryTree, AddOrUpdate, Detail },
  data() {
    return {
	  categoryId: 0,	
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
	// 点击树
	  categoryTreeClick(data, node, component){
		  this.categoryId = data.id;
		  this.getDataList();
	  },
    // 详情信息
    detailHandle(id) {
      this.detailVisible = true;
      this.$nextTick(() => {
        this.$refs.detail.init(id);
      });
    },
    // 更改是否显示
    changeLogicDeleted(data) {
      console.log("是否显示", data);
      let { id, logicDeleted } = data;
      this.$http({
        url: this.$http.adornUrl("/exam/paper/update"),
        method: "post",
        data: this.$http.adornData({ id, logicDeleted }, false),
      }).then(({ data }) => {
        this.$message({
          type: "success",
          message: "更新成功",
        });
      });
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      this.$http({
        url: this.$http.adornUrl(`/exam/paper/list/${this.categoryId}`),
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
          url: this.$http.adornUrl("/exam/paper/delete"),
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

<style scoped>
.el-row {
  margin-bottom: 20px;
  /* &:last-child {
      margin-bottom: 0;
    } */
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>