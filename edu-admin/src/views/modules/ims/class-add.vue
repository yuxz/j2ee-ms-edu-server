<template>
  <div>
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="180px"
    >
      <!-- <el-form-item label="教育机构" prop="institutionId">
        <el-input
          v-model="dataForm.institutionId"
          placeholder="教育机构"
        ></el-input>
      </el-form-item> -->
      <el-form-item label="Campus" prop="campusId">
		<campus-select @campus-select-change="campusSelectChange"></campus-select>		
      </el-form-item>
	  <el-form-item label="Schedule" prop="campusScheduleId">
        <schedule-select
          ref="schedule"
          @schedule-select-change="scheduleSelectChange"
        ></schedule-select>
      </el-form-item>
      <el-form-item label="Type" prop="classTypeId">
		  <class-type-select @class-type-select-change="classTypeSelectChange"></class-type-select>
      </el-form-item>
      <el-form-item label="Level" prop="classLevelId">
		  <class-level-select @class-level-select-change="classLevelSelectChange"></class-level-select>
      </el-form-item>
      <el-form-item label="Name" prop="name">
        <el-input v-model="dataForm.name" placeholder="Class Name"></el-input>
      </el-form-item>
	  <el-form-item label="Maximum Number" prop="maximum">
        <el-input v-model="dataForm.maximum" placeholder="Class maximum Number"></el-input>
      </el-form-item>
	  <el-form-item label="Classroom">
		  <classroom-select ref="classroom" @classroom-select-change="classroomSelectChange" ></classroom-select>
      </el-form-item>
      <el-form-item label="Start Time">
        <div class="block">
          <el-date-picker
            v-model="dataForm.startTime"
            type="date"
            placeholder="Please Select"
          >
          </el-date-picker>
        </div>
      </el-form-item>
      <el-form-item label="End Time">
		<div class="block">
          <el-date-picker
            v-model="dataForm.endTime"
            type="date"
            placeholder="Please Select"
          >
          </el-date-picker>
        </div>
      </el-form-item>     
      <el-form-item label="Notes">
        <el-input type="textarea" row="2" v-model="dataForm.note" placeholder="notes"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <!-- <el-button @click="visible = false">取消</el-button> -->
      <el-button type="primary" @click="dataFormSubmit()">Create Class</el-button>
    </span>
  </div>
</template>

<script>
	import CampusSelect from "../common/campus-select";
	import ScheduleSelect from "../common/schedule-select";
	import ClassTypeSelect from "../common/classtype-select";
	import ClassLevelSelect from "../common/classlevel-select";
	import ClassroomSelect from "../common/classroom-select";
export default {
	components:{
		CampusSelect,
		ScheduleSelect,
		ClassTypeSelect,
		ClassLevelSelect,
		ClassroomSelect,		
	},
  data() {
    return {
      dataForm: {
        id: 0,
        institutionId: "",
        campusId: "",
		campusScheduleId: "",
        classTypeId: "",
		classLevelId:"",
        classroomId: "",
        name: "",
		maximum: 20,
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
          { required: true, message: "班级类型不能为空", trigger: "blur" },
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
		maximum: [
          { required: true, message: "班级最大人数不能为空", trigger: "blur" },
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
			  this.dataForm.campusScheduleId= data.class.campusScheduleId;
              this.dataForm.classTypeId = data.class.classTypeId;
			  this.dataForm.classLevelId = data.class.classLevelId;
              this.dataForm.classroomId = data.class.classroomId;
              this.dataForm.name = data.class.name;
			  this.dataForm.maximum = data.class.maximum;			  
              this.dataForm.startTime = data.class.startTime;
              this.dataForm.endTime = data.class.endTime;
              this.dataForm.isFinished = data.class.isFinished;
              this.dataForm.note = data.class.note;
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
			  maximum: this.dataForm.maximum,
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
