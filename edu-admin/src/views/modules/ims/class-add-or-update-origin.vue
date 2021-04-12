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
      <el-form-item label="教育机构" prop="institutionId">
        <el-input
          v-model="dataForm.institutionId"
          placeholder="教育机构"
        ></el-input>
      </el-form-item>
      <el-form-item label="校区" prop="campusId">
        <el-input v-model="dataForm.campusId" placeholder="校区"></el-input>
      </el-form-item>
      <el-form-item label="班级类型" prop="classTypeId">
        <el-input
          v-model="dataForm.classTypeId"
          placeholder="班级类型"
        ></el-input>
      </el-form-item>
      
      <el-form-item label="班级名称" prop="name">
        <el-input v-model="dataForm.name" placeholder="班级名称"></el-input>
      </el-form-item>
	  <el-form-item label="教室">
        <el-input v-model="dataForm.classroomId" placeholder="教室"></el-input>
      </el-form-item>
      <el-form-item label="开班时间">
        <div class="block">
          <el-date-picker
            v-model="dataForm.started"
            type="date"
            placeholder="选择日期"
          >
          </el-date-picker>
        </div>
      </el-form-item>
      <el-form-item label="结班时间">
		<div class="block">
          <el-date-picker
            v-model="dataForm.ended"
            type="date"
            placeholder="选择日期"
          >
          </el-date-picker>
        </div>
      </el-form-item>     
      <el-form-item label="备注">
        <el-input type="textarea" row="2" v-model="dataForm.note" placeholder="备注"></el-input>
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
        campusId: "",
        classTypeId: "",
        classroomId: "",
        name: "",
        started: "",
        ended: "",
        status: 0,
        note: "",
      },
      dataRule: {
        institutionId: [
          { required: true, message: "教育机构不能为空", trigger: "blur" },
        ],
        campusId: [
          { required: true, message: "校区不能为空", trigger: "blur" },
        ],
        classTypeId: [
          { required: true, message: "班级类型id不能为空", trigger: "blur" },
        ],
        classroomId: [
          { required: true, message: "教室不能为空", trigger: "blur" },
        ],
        name: [
          { required: true, message: "班级名称不能为空", trigger: "blur" },
        ],
        started: [
          { required: true, message: "开班时间不能为空", trigger: "blur" },
        ],
        ended: [
          { required: true, message: "结班时间不能为空", trigger: "blur" },
        ],
        status: [
          { required: true, message: "是否结业不能为空", trigger: "blur" },
        ],
        note: [{ required: true, message: "备注不能为空", trigger: "blur" }],
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
            url: this.$http.adornUrl(`/ims/class/info/${this.dataForm.id}`),
            method: "get",
            params: this.$http.adornParams(),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.institutionId = data.class.institutionId;
              this.dataForm.campusId = data.class.campusId;
              this.dataForm.classTypeId = data.class.classTypeId;
              this.dataForm.classroomId = data.class.classroomId;
              this.dataForm.name = data.class.name;
              this.dataForm.started = data.class.started;
              this.dataForm.ended = data.class.ended;
              this.dataForm.status = data.class.status;
              this.dataForm.note = data.class.note;
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
              `/ims/class/${!this.dataForm.id ? "save" : "update"}`
            ),
            method: "post",
            data: this.$http.adornData({
              id: this.dataForm.id || undefined,
              institutionId: this.dataForm.institutionId,
              campusId: this.dataForm.campusId,
              classTypeId: this.dataForm.classTypeId,
              classroomId: this.dataForm.classroomId,
              name: this.dataForm.name,
              started: this.dataForm.started,
              ended: this.dataForm.ended,
              status: this.dataForm.status,
              note: this.dataForm.note,
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
