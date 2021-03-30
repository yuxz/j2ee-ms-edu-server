<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="Teacher" prop="teacherId">
      <el-input v-model="dataForm.teacherId" placeholder="Teacher"></el-input>
    </el-form-item>
    <el-form-item label="Start Time" prop="started">
      <el-input v-model="dataForm.started" placeholder="Start Time"></el-input>
    </el-form-item>
    <el-form-item label="End Time" prop="ended">
      <el-input v-model="dataForm.ended" placeholder="End Time"></el-input>
    </el-form-item>
    <el-form-item label="Company Name" prop="name">
      <el-input v-model="dataForm.name" placeholder="Company Name"></el-input>
    </el-form-item>
    <el-form-item label="Position" prop="position">
      <el-input v-model="dataForm.position" placeholder="Position"></el-input>
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
          position: '',
          note: '',
          logicDeleted: '',
          created: '',
          updated: '',
          userId: ''
        },
        dataRule: {
          teacherId: [
            { required: true, message: 'Teacher不能为空', trigger: 'blur' }
          ],
          started: [
            { required: true, message: 'Start Time不能为空', trigger: 'blur' }
          ],
          ended: [
            { required: true, message: 'End Time不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: 'Company Name不能为空', trigger: 'blur' }
          ],
          position: [
            { required: true, message: 'Position不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/teacher/teacherexperience/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.teacherId = data.teacherExperience.teacherId
                this.dataForm.started = data.teacherExperience.started
                this.dataForm.ended = data.teacherExperience.ended
                this.dataForm.name = data.teacherExperience.name
                this.dataForm.position = data.teacherExperience.position
                this.dataForm.note = data.teacherExperience.note
                this.dataForm.logicDeleted = data.teacherExperience.logicDeleted
                this.dataForm.created = data.teacherExperience.created
                this.dataForm.updated = data.teacherExperience.updated
                this.dataForm.userId = data.teacherExperience.userId
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
              url: this.$http.adornUrl(`/teacher/teacherexperience/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'teacherId': this.dataForm.teacherId,
                'started': this.dataForm.started,
                'ended': this.dataForm.ended,
                'name': this.dataForm.name,
                'position': this.dataForm.position,
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
