<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="140px"
    >
      <el-form-item label="题库" prop="categoryId">
        <el-cascader
          v-model="categoryFullPath"
          :options="categories"
          :props="props"
          size="medium"
        ></el-cascader>
      </el-form-item>
      <el-form-item label="试卷类型" prop="paperTypeId">
        <el-select v-model="dataForm.paperTypeId" placeholder="请选择">
          <el-option
            v-for="paperType in paperTypes"
            :key="paperType.value"
            :label="paperType.label"
            :value="paperType.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="试卷编号" prop="name">
        <el-input v-model="dataForm.paperNo" placeholder="试卷编号"></el-input>
      </el-form-item>
      <el-form-item label="试卷名称" prop="name">
        <el-input v-model="dataForm.name" placeholder="试卷名称"></el-input>
      </el-form-item>
      <el-form-item label="总分" prop="score">
        <el-input v-model="dataForm.score" placeholder="总分"></el-input>
      </el-form-item>
      <el-form-item label="试题总数" prop="items">
        <el-input v-model="dataForm.items" placeholder="试题总数"></el-input>
      </el-form-item>
      <el-form-item label="答题时限" prop="limitedTime">
        <el-input
          v-model="dataForm.limitedTime"
          placeholder="答题时限"
        ></el-input>
      </el-form-item>

      <el-form-item label="是否显示" prop="logicDeleted">
        <el-switch
          v-model="dataForm.logicDeleted"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="0"
          :inactive-value="1"
        >
        </el-switch>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
		paperTypes:[
			{value:"1",label:"单选"},
			{value:"2",label:"多选"},
			{value:"3",label:"输入"},
			{value:"4",label:"其他"},
		],
      visible: false,
      categories: [],
      props: {
        value: "id",
        label: "name",
        children: "children",
      },
      categoryFullPath: [],
      dataForm: {
        id: 0,
        categoryId: 0,
        paperTypeId: [],
        paperNo: "",
        name: "",
        score: 100,
        items: 60,
        limitedTime: 60,
        browseNum: 0,
        testNum: 0,
        logicDeleted: 0,
        createTime: "",
        updateTime: "",
        userId: "",
      },
      dataRule: {
        categoryId: [
          { required: true, message: "题库分类不能为空", trigger: "blur" },
        ],
        paperTypeId: [
          { required: true, message: "试卷类型不能为空", trigger: "blur" },
        ],
        paperNo: [
          { required: true, message: "试卷编号不能为空", trigger: "blur" },
        ],
        name: [
          { required: true, message: "试卷名称不能为空", trigger: "blur" },
        ],
        score: [{ required: true, message: "总分不能为空", trigger: "blur" }],
        items: [
          { required: true, message: "试题总数不能为空", trigger: "blur" },
        ],
        limitedTime: [
          { required: true, message: "答题时限不能为空", trigger: "blur" },
        ],
        logicDeleted: [
          {
            required: true,
            message: "是否被删除[0-已删，1未删]不能为空",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    getCategories() {
      this.$http({
        url: this.$http.adornUrl("/exam/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
        console.log("成功获取分类级联数据...", data.data);
        this.categories = data.data;
      });
    },

    init(id) {
      this.dataForm.id = id || 0;
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/exam/paper/info/${this.dataForm.id}`),
            method: "get",
            params: this.$http.adornParams(),
          }).then(({ data }) => {
            console.log("成功获取修改数据", data);
            if (data && data.code === 0) {
              this.dataForm.categoryId = data.paper.categoryId;
              this.dataForm.paperTypeId = data.paper.paperTypeId;
              this.dataForm.paperNo = data.paper.paperNo;
              this.dataForm.name = data.paper.name;
              this.dataForm.score = data.paper.score;
              this.dataForm.items = data.paper.items;
              this.dataForm.limitedTime = data.paper.limitedTime;
              this.dataForm.browseNum = data.paper.browseNum;
              this.dataForm.testNum = data.paper.testNum;
              this.dataForm.logicDeleted = data.paper.logicDeleted;
              this.dataForm.createTime = data.paper.createTime;
              this.dataForm.updateTime = data.paper.updateTime;
              this.dataForm.userId = data.paper.userId;
              this.categoryFullPath = data.paper.categoryFullPath;
            }
          });
        }
      });
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate((valid) => {
        console.log(
          "-----:",
          this.categoryFullPath[this.categoryFullPath.length - 1]
        );
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/exam/paper/${!this.dataForm.id ? "save" : "update"}`
            ),
            method: "post",
            data: this.$http.adornData({
              id: this.dataForm.id || undefined,
              categoryId: this.categoryFullPath[
                this.categoryFullPath.length - 1
              ],
              paperTypeId: this.dataForm.paperTypeId,
              paperNo: this.dataForm.paperNo,
              name: this.dataForm.name,
              score: this.dataForm.score,
              items: this.dataForm.items,
              limitedTime: this.dataForm.limitedTime,
              browseNum: this.dataForm.browseNum,
              testNum: this.dataForm.testNum,
              logicDeleted: this.dataForm.logicDeleted,
              createTime: this.dataForm.createTime,
              updateTime: this.dataForm.updateTime,
              userId: this.dataForm.userId,
            }),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.visible = false;
                  this.$emit("refreshDataList");
                },
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    },
  },
  created() {
    this.getCategories();
  },
};
</script>
