<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <!-- <el-form-item label="教育机构" prop="institutionId">
      <el-input v-model="dataForm.institutionId" placeholder="教育机构id"></el-input>
    </el-form-item> -->
    <!-- <el-form-item label="校区id" prop="campusId">
      <el-input v-model="dataForm.campusId" placeholder="校区id"></el-input>
    </el-form-item> -->
    <el-form-item label="班级类型" prop="name">
      <el-input v-model="dataForm.name" placeholder="班级/课程类型名称"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="note">
      <el-input type="textarea" row="2" v-model="dataForm.note" placeholder="备注"></el-input>
    </el-form-item>
    <!-- <el-form-item label="是否被删除[0-已删，1未删]" prop="logicDeleted">
      <el-input v-model="dataForm.logicDeleted" placeholder="是否被删除[0-已删，1未删]"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.created" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更改时间" prop="updateTime">
      <el-input v-model="dataForm.updated" placeholder="更改时间"></el-input>
    </el-form-item>
    <el-form-item label="创建人" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="创建人"></el-input> 
    </el-form-item>-->
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
          name: '',
          note: '',
          logicDeleted: '',
          created: '',
          updated: '',
          userId: ''
        },
        dataRule: {
        //   institutionId: [
        //     { required: true, message: '教育机构id不能为空', trigger: 'blur' }
        //   ],
        //   campusId: [
        //     { required: true, message: '校区id不能为空', trigger: 'blur' }
        //   ],
          name: [
            { required: true, message: '班级/课程类型名称不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/ims/classtype/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.institutionId = data.classType.institutionId
                this.dataForm.campusId = data.classType.campusId
                this.dataForm.name = data.classType.name
                this.dataForm.note = data.classType.note
                this.dataForm.logicDeleted = data.classType.logicDeleted
                this.dataForm.created = data.classType.created
                this.dataForm.updated = data.classType.updated
                this.dataForm.userId = data.classType.userId
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
              url: this.$http.adornUrl(`/ims/classtype/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'institutionId': this.dataForm.institutionId,
                // 'campusId': this.dataForm.campusId,
                'name': this.dataForm.name,
                'note': this.dataForm.note                
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
