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
      label-width="80px"
    >
      <!-- <el-form-item label="教育机构" prop="institutionId">
        <el-input
          v-model="dataForm.institutionId"
          placeholder="教育机构"
        ></el-input>
      </el-form-item> -->
	  
	  <el-form-item label="校区" prop="campusId">
        <campus-select
          ref="campus" @campus-select-change="campusSelectChange"
        ></campus-select>
      </el-form-item>
	  <!-- <el-form-item label="科目" prop="classTypeId">
        <el-input
          v-model="dataForm.classTypeId"
          placeholder="科目"
        ></el-input>
      </el-form-item> -->
      <el-form-item label="学生姓名" prop="name">
        <el-input v-model="dataForm.name" placeholder="学生姓名"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="dataForm.gender">
          <el-radio :label="1">男</el-radio>
          <el-radio :label="2">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="生日" prop="birth">
        <el-date-picker
          v-model="dataForm.birth"
          type="date"
          placeholder="选择日期"
        >
        </el-date-picker>
      </el-form-item>
	  <el-form-item label="邮箱" prop="email">
        <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
      </el-form-item>
      <el-form-item label="手机号码" prop="mobile">
        <el-input v-model="dataForm.mobile" placeholder="手机号码"></el-input>
      </el-form-item>      
	   <el-form-item label="报名时间">
        <el-date-picker
          v-model="dataForm.registered"
          type="date"
          placeholder="选择日期"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="备注" >
        <el-input
          type="textarea"
          :rows="2"
          v-model="dataForm.note"
          placeholder="备注"
        ></el-input>
      </el-form-item>
      <el-form-item label="是否激活" prop="isActived">
        <el-switch
          v-model="dataForm.isActived"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="1"
          :inactive-value="0"
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
import CampusSelect from "../common/campus-select";
export default {
  components: {
    CampusSelect,
  },
  data() {
    return {
      visible: true,
      dataForm: {
        id: 0,
        institutionId: "",
		campusId:"",
		classTypeId:"",
        name: "",
        gender: 1,
        birth: "",
        mobile: "",
        email: "",
        note: "",
		registered: new Date(),
        isActived: 1,
      },
      dataRule: {
        // institutionId: [
        //   { required: true, message: "教育机构id不能为空", trigger: "blur" },
        // ],
		 campusId: [
          { required: true, message: "校区不能为空", trigger: "blur" },
        ],
        name: [
          { required: true, message: "学生姓名不能为空", trigger: "blur" },
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
            url: this.$http.adornUrl(`/sms/student/info/${this.dataForm.id}`),
            method: "get",
            params: this.$http.adornParams(),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.institutionId = data.student.institutionId;
			  this.dataForm.campusId = data.student.campusId;
			  this.dataForm.classTypeId = data.student.classTypeId;
              this.dataForm.name = data.student.name;
              this.dataForm.gender = data.student.gender;
              this.dataForm.birth = data.student.birth;
              this.dataForm.mobile = data.student.mobile;
              this.dataForm.email = data.student.email;
              this.dataForm.note = data.student.note;
			   this.dataForm.registered = data.student.registered;
              this.dataForm.isActived = data.student.isActived;

			 this.$refs.campus.value = this.dataForm.campusId;
			 this.$refs.schedule.value = this.dataForm.campusScheduleId;
            }
          });
        }
      });
    },
	campusSelectChange(data){
		this.dataForm.campusId = data;	

		// update classroom data	
		// this.$refs.classroom.getClassroom(data);		
	},
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/sms/student/${!this.dataForm.id ? "save" : "update"}`
            ),
            method: "post",
            data: this.$http.adornData({
              id: this.dataForm.id || undefined,
              institutionId: this.dataForm.institutionId,
			  campusId: this.dataForm.campusId,
			  classTypeId: this.dataForm.classTypeId,
              name: this.dataForm.name,
              gender: this.dataForm.gender,
              birth: this.dataForm.birth,
              mobile: this.dataForm.mobile,
              email: this.dataForm.email,
              note: this.dataForm.note,
			  registered: this.dataForm.registered,
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
			  this.dataForm.name = "";
              this.dataForm.gender = 1;
              this.dataForm.birth = "";
              this.dataForm.mobile = "";
              this.dataForm.email = "";
              this.dataForm.note = "";
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    },
  },
  created:{
  }
};
</script>
