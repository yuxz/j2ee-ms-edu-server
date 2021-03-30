<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="老师" prop="teacherId">
      <el-input v-model="dataForm.teacherId" placeholder="老师"></el-input>
    </el-form-item>
    <el-form-item label="start time" prop="started">
      <el-input v-model="dataForm.started" placeholder="start time"></el-input>
    </el-form-item>
    <el-form-item label="end time" prop="ended">
      <el-input v-model="dataForm.ended" placeholder="end time"></el-input>
    </el-form-item>
    <el-form-item label="College" prop="name">
      <el-input v-model="dataForm.name" placeholder="College"></el-input>
    </el-form-item>
    <el-form-item label="Qualification&major" prop="qualiMajor">
      <el-input v-model="dataForm.qualiMajor" placeholder="Qualification&major"></el-input>
    </el-form-item>
    <el-form-item label="Memo" prop="note">
      <el-input v-model="dataForm.note" placeholder="Memo"></el-input>
    </el-form-item>
    <el-form-item label="whether be deleted[0-deleted，1.not deleted]" prop="logicDeleted">
      <el-input v-model="dataForm.logicDeleted" placeholder="whether be deleted[0-deleted，1.not deleted]"></el-input>
    </el-form-item>
    <el-form-item label="Created Time" prop="created">
      <el-input v-model="dataForm.created" placeholder="Created Time"></el-input>
    </el-form-item>
    <el-form-item label="Updated Time" prop="updated">
      <el-input v-model="dataForm.updated" placeholder="Updated Time"></el-input>
    </el-form-item>
    <el-form-item label="Creator" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="Creator"></el-input>
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
          teacherId: '',
          started: '',
          ended: '',
          name: '',
          qualiMajor: '',
          note: '',
          logicDeleted: '',
          created: '',
          updated: '',
          userId: ''
        },
        dataRule: {
          teacherId: [
            { required: true, message: '老师不能为空', trigger: 'blur' }
          ],
          started: [
            { required: true, message: 'start time不能为空', trigger: 'blur' }
          ],
          ended: [
            { required: true, message: 'end time不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: 'College不能为空', trigger: 'blur' }
          ],
          qualiMajor: [
            { required: true, message: 'Qualification&major不能为空', trigger: 'blur' }
          ],
          note: [
            { required: true, message: 'Memo不能为空', trigger: 'blur' }
          ],
          logicDeleted: [
            { required: true, message: 'whether be deleted[0-deleted，1.not deleted]不能为空', trigger: 'blur' }
          ],
          created: [
            { required: true, message: 'Created Time不能为空', trigger: 'blur' }
          ],
          updated: [
            { required: true, message: 'Updated Time不能为空', trigger: 'blur' }
          ],
          userId: [
            { required: true, message: 'Creator不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/teacher/teacherbackground/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.teacherId = data.teacherBackground.teacherId
                this.dataForm.started = data.teacherBackground.started
                this.dataForm.ended = data.teacherBackground.ended
                this.dataForm.name = data.teacherBackground.name
                this.dataForm.qualiMajor = data.teacherBackground.qualiMajor
                this.dataForm.note = data.teacherBackground.note
                this.dataForm.logicDeleted = data.teacherBackground.logicDeleted
                this.dataForm.created = data.teacherBackground.created
                this.dataForm.updated = data.teacherBackground.updated
                this.dataForm.userId = data.teacherBackground.userId
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
              url: this.$http.adornUrl(`/teacher/teacherbackground/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'teacherId': this.dataForm.teacherId,
                'started': this.dataForm.started,
                'ended': this.dataForm.ended,
                'name': this.dataForm.name,
                'qualiMajor': this.dataForm.qualiMajor,
                'note': this.dataForm.note,
                'logicDeleted': this.dataForm.logicDeleted,
                'created': this.dataForm.created,
                'updated': this.dataForm.updated,
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
