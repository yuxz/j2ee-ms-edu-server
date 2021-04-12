<template>
  <el-dialog
    :title="'Detail'"
    :close-on-click-modal="false"
    :visible.sync="visible"
    width="85%"
  >
    <!-- <el-form-item label="教育机构" prop="institutionId">
        <el-input
          v-model="dataForm.institutionId"
          placeholder="教育机构"
        ></el-input>
      </el-form-item> -->

    <el-row :gutter="20">
      <el-col :span="4"> <div class="el-col-align-right">Campus</div> </el-col>
      <el-col :span="20">
        <span>{{ dataForm.campusName }}</span>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4"> Campus </el-col>
      <el-col :span="20">
        {{ dataForm.campusName }}
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4"> Class Type </el-col>
      <el-col :span="20">
        {{ dataForm.classTypeName }}
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4"> Class Name </el-col>
      <el-col :span="20">
        {{ dataForm.name }}
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4"> Classroom </el-col>
      <el-col :span="20">
        {{ dataForm.classroomName }}
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4"> Start Date </el-col>
      <el-col :span="20">
        {{ dataForm.ended }}
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4"> End Date </el-col>
      <el-col :span="20">
        {{ dataForm.started }}
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4"> Notes </el-col>
      <el-col :span="20">
        {{ dataForm.note }}
      </el-col>
    </el-row>
    <br />
    <h6>Teachers</h6>
    <teacher-class-existed ref="teacherClassExisted"></teacher-class-existed>
    <br />
    <h6>Students</h6>
    <student-class-existed ref="studentClassExisted"></student-class-existed>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
import TeacherClassExisted from "../tms/teacherclass-existed";
import StudentClassExisted from "../sms/studentclass-existed";
export default {
  components: {
    TeacherClassExisted,
    StudentClassExisted,
  },
  data() {
    return {
      visible: false,
      dataForm: {
        id: 0,
        institutionId: "",
        campusId: "",
        campusName: "",
        classTypeId: "",
        classTypeName: "",
        classroomId: "",
        classroomName: "",
        name: "",
        started: "",
        ended: "",
        status: 0,
        note: "",
      },
    };
  },
  methods: {
    teacherClassHandle(classId) {
      //this.teacherClassVisible = true;
      this.$nextTick(() => {
        this.$refs.teacherClassExisted.init(classId);
      });
    },
    studentClassHandle(classId) {
      //this.teacherClassVisible = true;
      this.$nextTick(() => {
        this.$refs.studentClassExisted.init(classId);
      });
    },
    init(id) {
      this.dataForm.id = id || 0;
      this.visible = true;
      this.$nextTick(() => {
        // this.$refs["dataForm"].resetFields();
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/ims/class/info/${this.dataForm.id}`),
            method: "get",
            params: this.$http.adornParams(),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.institutionId = data.class.institutionId;
              this.dataForm.campusId = data.class.campusId;
              this.dataForm.campusName = data.class.campusName;
              this.dataForm.classTypeId = data.class.classTypeId;
              this.dataForm.classTypeName = data.class.classTypeName;
              this.dataForm.classroomId = data.class.classroomId;
              this.dataForm.classroomName = data.class.classroomName;
              this.dataForm.name = data.class.name;
              this.dataForm.started = data.class.started;
              this.dataForm.ended = data.class.ended;
              this.dataForm.status = data.class.status;
              this.dataForm.note = data.class.note;
            }
          });
        }

        this.teacherClassHandle(id);
        this.studentClassHandle(id);
      });
    },
  },
};
</script>
<style>
.el-row {
  margin-bottom: 20px;
  /* last-child {
      margin-bottom: 0;
    } */
}
.el-col {
  border-radius: 4px;
}
.el-col-align-right{
	width:"100%";
	text-align: right;
	background: #99a9bf;
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
