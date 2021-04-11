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
	  <el-form-item label="Schedule" prop="campusScheduleId">
        <schedule-select
          ref="schedule"
          @schedule-select-change="scheduleSelectChange"
        ></schedule-select>
      </el-form-item>
      <el-form-item label="班级类型" prop="classTypeId">
        <class-type-select
          ref="classtype" @class-type-select-change="classTypeSelectChange"
        ></class-type-select>      
      </el-form-item>
	   <el-form-item label="年级类型" prop="classLevelId">
		  <class-level-select @class-level-select-change="classLevelSelectChange"></class-level-select>
      </el-form-item>
      <el-form-item label="班级名称" prop="name">
        <el-input v-model="dataForm.name" placeholder="班级名称"></el-input>
      </el-form-item>
      <el-form-item label="教室">
        <classroom-select
          ref="classroom"
          @classroom-select-change="classroomSelectChange"
        ></classroom-select>
      </el-form-item>
      <el-form-item label="开班时间">
        <div class="block">
          <el-date-picker
            v-model="dataForm.startTime"
            type="date"
            placeholder="选择日期"
          >
          </el-date-picker>
        </div>
      </el-form-item>
      <el-form-item label="结班时间">
        <div class="block">
          <el-date-picker
            v-model="dataForm.endTime"
            type="date"
            placeholder="选择日期"
          >
          </el-date-picker>
        </div>
      </el-form-item>
      <el-form-item label="备注">
        <el-input type="textarea" row ="2" v-model="dataForm.note" placeholder="备注"></el-input>
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
	import ScheduleSelect from "../common/schedule-select";
	import ClassTypeSelect from "../common/classtype-select";
	import ClassLevelSelect from "../common/classlevel-select";
	import ClassroomSelect from "../common/classroom-select";
export default {
  components: {
    CampusSelect,
		ScheduleSelect,
		ClassTypeSelect,
		ClassLevelSelect,
		ClassroomSelect,		
  },
  data() {
    return {
      visible: false,
      dataForm: {
        id: 0,
        institutionId: "",
        campusId: "",
		campusScheduleId: "",
		campusName:"",
        classTypeId: "",
		classLevelId:"",
		classTypeName:"",
        classroomId: "",
		classroomName:"",
        name: "",
        startTime: "",
        endTime: "",
        isFinished: 0,
        note: "",
      },
      dataRule: {
        // institutionId: [
        //   { required: true, message: "教育机构不能为空", trigger: "blur" },
        // ],
        campusId: [
          { required: true, message: "校区不能为空", trigger: "blur" },
        ],
		campusScheduleId: [
          { required: true, message: "时段不能为空", trigger: "blur" },
        ],
        classTypeId: [
          { required: true, message: "班级类型id不能为空", trigger: "blur" },
        ],
        classLevelId: [
          { required: true, message: "年级类型不能为空", trigger: "blur" },
        ],
		classroomId: [
          { required: true, message: "教室不能为空", trigger: "blur" },
        ],
        name: [
          { required: true, message: "班级名称不能为空", trigger: "blur" },
        ],
        startTime: [
          { required: true, message: "开班时间不能为空", trigger: "blur" },
        ],
        endTime: [
          { required: true, message: "结班时间不能为空", trigger: "blur" },
        ],
        isFinished: [
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
			//   this.dataForm.campusName = data.class.campusName;
              this.dataForm.classTypeId = data.class.classTypeId;
			//    this.dataForm.classTypeName = data.class.classTypeName;
              this.dataForm.classroomId = data.class.classroomId;			  
              this.dataForm.name = data.class.name;
              this.dataForm.startTime = data.class.startTime;
              this.dataForm.endTime = data.class.endTime;
              this.dataForm.isFinished = data.class.isFinished;
              this.dataForm.note = data.class.note;

			  this.$refs.campus.value = this.dataForm.campusId;
			  this.$refs.classroom.value = data.class.classroomId;
			   this.$refs.classtype.value = data.class.classTypeId;
            }
          });
        }
      });
    },
	campusSelectChange(data){
		this.dataForm.campusId = data;
		// update classroom data
		
		//更新schedule数据
        this.$refs.schedule.getSchedule(data);
		//更新classroom数据
		this.$refs.classroom.getClassroom(data);
		
	},	
	scheduleSelectChange(data) {
      console.log("scheduleId" + data);
      this.dataForm.campusScheduleId = data;
    },
	classTypeSelectChange(data){
		this.dataForm.classTypeId = data;
	},
	classLevelSelectChange(data){
		this.dataForm.classLevelId = data;
	},
	classroomSelectChange(data){
		this.dataForm.classroomId = data;
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
			  classLevelId: this.dataForm.classLevelId,
              classroomId: this.dataForm.classroomId,
              name: this.dataForm.name,
              startTime: this.dataForm.startTime,
              endTime: this.dataForm.endTime,
              isFinished: this.dataForm.isFinished,
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
