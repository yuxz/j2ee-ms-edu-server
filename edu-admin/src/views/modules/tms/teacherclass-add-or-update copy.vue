<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="班级id" prop="classId">
      <el-input v-model="dataForm.classId" placeholder="班级id"></el-input>
    </el-form-item>
    <el-form-item label="老师id" prop="teacherId">
      <el-input v-model="dataForm.teacherId" placeholder="老师id"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="note">
      <el-input v-model="dataForm.note" placeholder="备注"></el-input>
    </el-form-item>
    <el-form-item label="是否被删除[0-未删，1已删]" prop="logicDeleted">
      <el-input v-model="dataForm.logicDeleted" placeholder="是否被删除[0-未删，1已删]"></el-input>
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
          classId: '',
          teacherId: '',
          note: '',
          logicDeleted: '',
          createTime: '',
          updateTime: '',
          userId: ''
        },
        dataRule: {
          classId: [
            { required: true, message: '班级id不能为空', trigger: 'blur' }
          ],
          teacherId: [
            { required: true, message: '老师id不能为空', trigger: 'blur' }
          ],
          note: [
            { required: true, message: '备注不能为空', trigger: 'blur' }
          ],
          logicDeleted: [
            { required: true, message: '是否被删除[0-未删，1已删]不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/tms/teacherclass/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.classId = data.teacherClass.classId
                this.dataForm.teacherId = data.teacherClass.teacherId
                this.dataForm.note = data.teacherClass.note
                this.dataForm.logicDeleted = data.teacherClass.logicDeleted
                this.dataForm.createTime = data.teacherClass.createTime
                this.dataForm.updateTime = data.teacherClass.updateTime
                this.dataForm.userId = data.teacherClass.userId
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
              url: this.$http.adornUrl(`/tms/teacherclass/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'classId': this.dataForm.classId,
                'teacherId': this.dataForm.teacherId,
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
