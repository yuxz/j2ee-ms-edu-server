<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="140px">
    <!-- <el-form-item label="教育机构" prop="institutionId">
      <el-input v-model="dataForm.institutionId" placeholder="教育机构"></el-input>
    </el-form-item> -->
    <el-form-item label="校区名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="校区名称"></el-input>
    </el-form-item>
    <el-form-item label="校区地址" >
      <el-input v-model="dataForm.address" placeholder="地址"></el-input>
    </el-form-item>
    <el-form-item label="负责人" >
      <el-input v-model="dataForm.leader" placeholder="负责人"></el-input>
    </el-form-item>
    <el-form-item label="手机号码" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="手机号码"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="備註" prop="email">
      <el-input type="textarea" :rows="2" v-model="dataForm.note" placeholder="備註"></el-input>
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
          name: '',
          address: '',
          leader: '',
          mobile: '',
          email: '',
          url: '',
          note: ''
        },
        dataRule: {
        //   institutionId: [
        //     { required: true, message: '教育机构不能为空', trigger: 'blur' }
        //   ],
          name: [
            { required: true, message: '校区名称不能为空', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '校区地址不能为空', trigger: 'blur' }
          ],
          leader: [
            { required: true, message: '校区负责人不能为空', trigger: 'blur' }
          ],
          mobile: [
            { message: '请输入正确手机号码',pattern: /^\d{10}$/, trigger: 'blur' }
          ],
          email: [
             {
           type: 'email',//要检验的类型（number，email，date等）
           message: '请输入正确的邮箱地址',
           trigger: ['blur', 'change']
           }
          ],
          url: [
            { required: true, message: '网站不能为空', trigger: 'blur' }
          ],
          note: [
            { required: true, message: '备注不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/ims/campus/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.institutionId = data.campus.institutionId
                this.dataForm.name = data.campus.name
                this.dataForm.address = data.campus.address
                this.dataForm.leader = data.campus.leader
                this.dataForm.mobile = data.campus.mobile
                this.dataForm.email = data.campus.email
                this.dataForm.url = data.campus.url
                this.dataForm.note = data.campus.note                
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
              url: this.$http.adornUrl(`/ims/campus/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'institutionId': this.dataForm.institutionId,
                'name': this.dataForm.name,
                'address': this.dataForm.address,
                'leader': this.dataForm.leader,
                'mobile': this.dataForm.mobile,
                'email': this.dataForm.email,
                'url': this.dataForm.url,
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
