<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="教育机构id" prop="institutionId">
      <el-input v-model="dataForm.institutionId" placeholder="教育机构id"></el-input>
    </el-form-item>
    <el-form-item label="校区id" prop="campusId">
      <el-input v-model="dataForm.campusId" placeholder="校区id"></el-input>
    </el-form-item>
    <el-form-item label="班级类型id" prop="classTypeId">
      <el-input v-model="dataForm.classTypeId" placeholder="班级类型id"></el-input>
    </el-form-item>
    <el-form-item label="教室id" prop="classroomId">
      <el-input v-model="dataForm.classroomId" placeholder="教室id"></el-input>
    </el-form-item>
    <el-form-item label="班级名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="班级名称"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="note">
      <el-input v-model="dataForm.note" placeholder="备注"></el-input>
    </el-form-item>
    <el-form-item label="是否被删除[0-已删，1未删]" prop="logicDeleted">
      <el-input v-model="dataForm.logicDeleted" placeholder="是否被删除[0-已删，1未删]"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更改时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="更改时间"></el-input>
    </el-form-item>
    <el-form-item label="创建人" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="创建人"></el-input>
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
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          institutionId: '',
          campusId: '',
          classTypeId: '',
          classroomId: '',
          name: '',
          note: '',
          logicDeleted: '',
          createTime: '',
          updateTime: '',
          userId: ''
        },
        dataRule: {
          institutionId: [
            { required: true, message: '教育机构id不能为空', trigger: 'blur' }
          ],
          campusId: [
            { required: true, message: '校区id不能为空', trigger: 'blur' }
          ],
          classTypeId: [
            { required: true, message: '班级类型id不能为空', trigger: 'blur' }
          ],
          classroomId: [
            { required: true, message: '教室id不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '班级名称不能为空', trigger: 'blur' }
          ],
          note: [
            { required: true, message: '备注不能为空', trigger: 'blur' }
          ],
          logicDeleted: [
            { required: true, message: '是否被删除[0-已删，1未删]不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '更改时间不能为空', trigger: 'blur' }
          ],
          userId: [
            { required: true, message: '创建人不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/ims/class/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.institutionId = data.class.institutionId
                this.dataForm.campusId = data.class.campusId
                this.dataForm.classTypeId = data.class.classTypeId
                this.dataForm.classroomId = data.class.classroomId
                this.dataForm.name = data.class.name
                this.dataForm.note = data.class.note
                this.dataForm.logicDeleted = data.class.logicDeleted
                this.dataForm.createTime = data.class.createTime
                this.dataForm.updateTime = data.class.updateTime
                this.dataForm.userId = data.class.userId
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/ims/class/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'institutionId': this.dataForm.institutionId,
                'campusId': this.dataForm.campusId,
                'classTypeId': this.dataForm.classTypeId,
                'classroomId': this.dataForm.classroomId,
                'name': this.dataForm.name,
                'note': this.dataForm.note,
                'logicDeleted': this.dataForm.logicDeleted,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime,
                'userId': this.dataForm.userId
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
