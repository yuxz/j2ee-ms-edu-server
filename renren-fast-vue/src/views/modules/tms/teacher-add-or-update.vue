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
      <!-- <el-form-item label="教育机构" prop="institutionId">
        <el-input
          v-model="dataForm.institutionId"
          placeholder="教育机构"
        ></el-input>
      </el-form-item> -->
      <el-form-item label="教师姓名" prop="name">
        <el-input v-model="dataForm.name" placeholder="教师姓名"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
      </el-form-item>
      <el-form-item label="手机号码" prop="mobile">
        <el-input v-model="dataForm.mobile" placeholder="手机号码"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-radio-group v-model="dataForm.gender">
          <el-radio :label="1">男</el-radio>
          <el-radio :label="2">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="生日">
        <el-date-picker
          v-model="dataForm.birth"
          type="date"
          placeholder="选择日期"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="入职时间">
        <el-date-picker
          v-model="dataForm.hired"
          type="date"
          placeholder="选择日期"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="离职时间">
        <el-date-picker
          v-model="dataForm.leaved"
          type="date"
          placeholder="选择日期"
        >
        </el-date-picker>
      </el-form-item>
	   <el-form-item label="状态" prop="isLeaved">
        <el-switch
          v-model="dataForm.isLeaved"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="0"
          :inactive-value="1"
		  active-text="在职"
      	  inactive-text="离职"
        >
        </el-switch>
      </el-form-item>
      <el-form-item label="备注">
        <el-input
          type="textarea"
          :rows="2"
          v-model="dataForm.note"
          placeholder="备注"
        ></el-input>
      </el-form-item>
      <el-form-item label="账户" prop="isActived">
        <el-switch
          v-model="dataForm.isActived"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="1"
          :inactive-value="0"
		  active-text="激活"
      	  inactive-text="关闭"
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
      visible: false,
      dataForm: {
        id: 0,
        institutionId: "",
        name: "",
        gender: 1,
        birth: "",
        mobile: "",
        email: "",
        hired: new Date(),
        leaved: "",
		isLeaved: 0,
        note: "",
        isActived: 1
      },
      dataRule: {
        // institutionId: [
        //   { required: true, message: "教育机构不能为空", trigger: "blur" },
        // ],
        name: [
          { required: true, message: "教师姓名不能为空", trigger: "blur" },
        ],
        gender: [{ required: true, message: "性别不能为空", trigger: "blur" }],
        birth: [{ required: true, message: "生日不能为空", trigger: "blur" }],
        mobile: [
          {
            pattern: /^\d{10}$/,
            message: "手机号不能为空",
            trigger: "blur",
          },
        ],
        email: [
          {
            required: true, //是否必填
            message: "请输入邮箱地址", //错误提示信息
            trigger: "blur", //检验方式（blur为鼠标点击其他地方，）
          },
          {
            type: "email", //要检验的类型（number，email，date等）
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
        hired: [
          { required: true, message: "入职时间不能为空", trigger: "blur" },
        ],
        // leaved: [
        //   { required: true, message: "离职时间不能为空", trigger: "blur" },
        // ],
        // note: [{ required: true, message: "备注不能为空", trigger: "blur" }],

        isActived: [
          { required: true, message: "是否激活不能为空", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    init(id) {
      this.dataForm.id = id || 0;
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/tms/teacher/info/${this.dataForm.id}`),
            method: "get",
            params: this.$http.adornParams(),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.institutionId = data.teacher.institutionId;
              this.dataForm.name = data.teacher.name;             
              this.dataForm.gender = data.teacher.gender;
              this.dataForm.birth = data.teacher.birth;
              this.dataForm.mobile = data.teacher.mobile;
              this.dataForm.email = data.teacher.email;
              this.dataForm.hired = data.teacher.hired;
              this.dataForm.leaved = data.teacher.leaved;
			  this.dataForm.isLeaved = data.teacher.isLeaved;
              this.dataForm.note = data.teacher.note;
              this.dataForm.isActived = data.teacher.isActived;
            }
          });
        }
      });
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/tms/teacher/${!this.dataForm.id ? "save" : "update"}`
            ),
            method: "post",
            data: this.$http.adornData({
              id: this.dataForm.id || undefined,
              institutionId: this.dataForm.institutionId,
              name: this.dataForm.name,
              isLeaved: this.dataForm.isLeaved,
              gender: this.dataForm.gender,
              birth: this.dataForm.birth,
              mobile: this.dataForm.mobile,
              email: this.dataForm.email,
              hired: this.dataForm.hired,
              leaved: this.dataForm.leaved,
              note: this.dataForm.note,
              isActived: this.dataForm.isActived,
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
};
</script>
