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
      label-width="140px"
    >
      <el-form-item label="机构名称" prop="name">
        <el-input v-model="dataForm.name" placeholder="机构名称"></el-input>
      </el-form-item>
      <!-- <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item> -->
      <el-form-item label="负责人" prop="leader">
        <el-input v-model="dataForm.leader" placeholder="负责人"></el-input>
      </el-form-item>
	  <el-form-item label="邮箱" prop="email">
        <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
      </el-form-item>
      <el-form-item label="手机" prop="mobile">
        <el-input v-model="dataForm.mobile" placeholder="手机"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="dataForm.address" placeholder="地址"></el-input>
      </el-form-item>
      
      <el-form-item label="网站">
        <el-input v-model="dataForm.url" placeholder="网站"></el-input>
      </el-form-item>
      <!-- <el-form-item label="头像" prop="photo">
      <el-input v-model="dataForm.photo" placeholder="头像"></el-input>
    </el-form-item> -->
      <el-form-item label="备注">
        <el-input
          type="textarea"
          :rows="2"
          v-model="dataForm.note"
          placeholder=""
        ></el-input>
      </el-form-item>
      <!-- <el-form-item label="token" prop="token">
      <el-input v-model="dataForm.token" placeholder="token"></el-input>
    </el-form-item> -->
      <el-form-item label="是否激活" prop="isActived">
        <el-switch
          v-model="dataForm.isActived"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="1"
          :inactive-value="0"
        >
        </el-switch>
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
          name: '',        
          leader: '',
          mobile: '',
          address: '',
          email: '',
          url: '',
          photo: '',
          note: '',        
          isActived: '',         
          createTime: ''   
        },
        dataRule: {
          name: [
            { required: true, message: '教育机构名称不能为空', trigger: 'blur' }
          ],         
          leader: [
            { required: true, message: '机构负责人不能为空', trigger: 'blur' }
          ],
          mobile: [{
           required: true,
           pattern: /^\d{10}$/,//可以写正则表达式
           message: '请输入正确手机号码',
           trigger: 'blur'
           }],
          address: [
            { required: true, message: '地址不能为空', trigger: 'blur' }
          ],
          email: [{
           required: true,//是否必填
           message: '请输入邮箱地址',//错误提示信息
           trigger: 'blur'//检验方式（blur为鼠标点击其他地方，）
           },
           {
           type: 'email',//要检验的类型（number，email，date等）
           message: '请输入正确的邮箱地址',
           trigger: ['blur', 'change']
           }
           ],
          url: [
            { required: true, message: '网站不能为空', trigger: 'blur' }
          ],
          photo: [
            { required: true, message: '头像不能为空', trigger: 'blur' }
          ],
          note: [
            { required: true, message: '备注不能为空', trigger: 'blur' }
          ],
         
          isActived: [
            { required: true, message: '是否激活不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/ims/institution/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.institution.name               
                this.dataForm.leader = data.institution.leader
                this.dataForm.mobile = data.institution.mobile
                this.dataForm.address = data.institution.address
                this.dataForm.email = data.institution.email
                this.dataForm.url = data.institution.url
                this.dataForm.photo = data.institution.photo
                this.dataForm.note = data.institution.note                
                this.dataForm.isActived = data.institution.isActived
                this.dataForm.logicDeleted = data.institution.logicDeleted
                this.dataForm.createTime = data.institution.createTime
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
              url: this.$http.adornUrl(`/ims/institution/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,                
                'leader': this.dataForm.leader,
                'mobile': this.dataForm.mobile,
                'address': this.dataForm.address,
                'email': this.dataForm.email,
                'url': this.dataForm.url,
                'photo': this.dataForm.photo,
                'note': this.dataForm.note,               
                'isActived': this.dataForm.isActived,
                'logicDeleted': this.dataForm.logicDeleted
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
