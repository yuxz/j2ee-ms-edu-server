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
      label-width="200px"
    >
      <!-- <el-form-item label="Institution" prop="institutionId">
      <el-input v-model="dataForm.institutionId" placeholder="Institution"></el-input>
    </el-form-item> -->
      <el-form-item label="校区" prop="campusId">
        <campus-select
          @campus-select-change="campusSelectChange"
        ></campus-select>
      </el-form-item>
      <el-form-item label="Which day of the week" prop="name">
        <el-input
          v-model="dataForm.name"
          placeholder="Which day of the week"
        ></el-input>
      </el-form-item>
      <el-form-item label="Period of time" prop="period">
        <el-time-select
          placeholder="起始时间"
          v-model="dataForm.started"
          :picker-options="{
            start: '08:30',
            step: '00:15',
            end: '18:30',
          }"
        >
        </el-time-select>
        <el-time-select
          placeholder="结束时间"
          v-model="dataForm.ended"
          :picker-options="{
            start: '08:30',
            step: '00:15',
            end: '18:30',
            minTime: dataForm.started,
          }"
        >
        </el-time-select>
       
      </el-form-item>
      <!-- <el-form-item label="notes" prop="note">
      <el-input v-model="dataForm.note" placeholder="notes"></el-input>
    </el-form-item>
    <el-form-item label="是否被删除[0-已删，1未删]" prop="logicDeleted">
      <el-input v-model="dataForm.logicDeleted" placeholder="是否被删除[0-已删，1未删]"></el-input>
    </el-form-item>
    <el-form-item label="Create date" prop="created">
      <el-input v-model="dataForm.created" placeholder="Create date"></el-input>
    </el-form-item>
    <el-form-item label="Update date" prop="updated">
      <el-input v-model="dataForm.updated" placeholder="Update date"></el-input>
    </el-form-item>
    <el-form-item label="creator" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="creator"></el-input>
    </el-form-item> -->
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
      visible: false,      
      dataForm: {
        id: 0,
        institutionId: "",
        campusId: "",
        name: "",
        started: "",
		ended: "",
        note: "",
        logicDeleted: "",
        created: "",
        updated: "",
        userId: "",
      },
      dataRule: {
        //   institutionId: [
        //     { required: true, message: 'Institution不能为空', trigger: 'blur' }
        //   ],
        campusId: [
          { required: true, message: "Campus不能为空", trigger: "blur" },
        ],
        name: [
          {
            required: true,
            message: "Which day of the week不能为空",
            trigger: "blur",
          },
        ],
        started: [
          {
            required: true,
            message: "Start  time不能为空",
            trigger: "blur",
          },
        ],
		 ended: [
          {
            required: true,
            message: "End time不能为空",
            trigger: "blur",
          },
        ],
        //   note: [
        //     { required: true, message: 'notes不能为空', trigger: 'blur' }
        //   ],
        //   logicDeleted: [
        //     { required: true, message: '是否被删除[0-已删，1未删]不能为空', trigger: 'blur' }
        //   ],
        //   created: [
        //     { required: true, message: 'Create date不能为空', trigger: 'blur' }
        //   ],
        //   updated: [
        //     { required: true, message: 'Update date不能为空', trigger: 'blur' }
        //   ],
        //   userId: [
        //     { required: true, message: 'creator不能为空', trigger: 'blur' }
        //   ]
      },
    };
  },
  methods: {
    campusSelectChange(data) {
      this.dataForm.campusId = data;
    },
    init(id) {
      this.dataForm.id = id || 0;
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(
              `/ims/campustrainingschedule/info/${this.dataForm.id}`
            ),
            method: "get",
            params: this.$http.adornParams(),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              // this.dataForm.institutionId = data.campusTrainingSchedule.institutionId
              this.dataForm.campusId = data.campusTrainingSchedule.campusId;
              this.dataForm.name = data.campusTrainingSchedule.name;
              this.dataForm.started = data.campusTrainingSchedule.started;
			  this.dataForm.ended = data.campusTrainingSchedule.ended;
              this.dataForm.note = data.campusTrainingSchedule.note;
              // this.dataForm.logicDeleted = data.campusTrainingSchedule.logicDeleted
              // this.dataForm.created = data.campusTrainingSchedule.created
              // this.dataForm.updated = data.campusTrainingSchedule.updated
              // this.dataForm.userId = data.campusTrainingSchedule.userId
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
              `/ims/campustrainingschedule/${
                !this.dataForm.id ? "save" : "update"
              }`
            ),
            method: "post",
            data: this.$http.adornData({
              id: this.dataForm.id || undefined,
              // 'institutionId': this.dataForm.institutionId,
              campusId: this.dataForm.campusId,
              name: this.dataForm.name,
              started: this.dataForm.started,
			  ended: this.dataForm.ended,
              note: this.dataForm.note,
              // 'logicDeleted': this.dataForm.logicDeleted,
              // 'created': this.dataForm.created,
              // 'updated': this.dataForm.updated,
              // 'userId': this.dataForm.userId
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
