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
		<campus-select @campus-select-change="campusSelectChange"></campus-select>
      </el-form-item>
      <el-form-item label="教室名称" prop="name">
        <el-input v-model="dataForm.name" placeholder="教室名称"></el-input>
      </el-form-item>
      <el-form-item label="所在位置">
        <el-input
          v-model="dataForm.classroom"
          placeholder="所在位置"
        ></el-input>
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
import CampusSelect from "../common/campus-select";
export default {
	components:{
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
        classroom: "",
        note: "",
      },
      dataRule: {        
        campusId: [
          { required: true, message: "校区不能为空", trigger: "blur" },
        ],
        name: [
          { required: true, message: "教室名称不能为空", trigger: "blur" },
        ],
        classroom: [
          { required: true, message: "容纳人数不能为空", trigger: "blur" },
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
            url: this.$http.adornUrl(`/ims/classroom/info/${this.dataForm.id}`),
            method: "get",
            params: this.$http.adornParams(),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.institutionId = data.classroom.institutionId;
              this.dataForm.campusId = data.classroom.campusId;
              this.dataForm.name = data.classroom.name;
              this.dataForm.classroom = data.classroom.classroom;
              this.dataForm.note = data.classroom.note;
            }
          });
        }
      });
    },
	campusSelectChange(data){
		this.dataForm.campusId = data;
	},
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/ims/classroom/${!this.dataForm.id ? "save" : "update"}`
            ),
            method: "post",
            data: this.$http.adornData({
              id: this.dataForm.id || undefined,
              institutionId: this.dataForm.institutionId,
              campusId: this.dataForm.campusId,
              name: this.dataForm.name,
              classroom: this.dataForm.classroom,
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
