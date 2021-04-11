<template>
  
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="200px">
    <!-- <el-form-item label="Institution" prop="institutionId">
      <el-input v-model="dataForm.institutionId" placeholder="Institution"></el-input>
    </el-form-item> -->
    <!-- <el-form-item label="Campus" prop="campusId">
      <el-input v-model="dataForm.campusId" placeholder="Campus"></el-input>
    </el-form-item> -->
    <el-form-item label="Family Name" prop="firstName">
      <el-input v-model="dataForm.firstName" placeholder="Family Name"></el-input>
    </el-form-item>
    <el-form-item label="Given Name" prop="lastName">
      <el-input v-model="dataForm.lastName" placeholder="Given Name"></el-input>
    </el-form-item>
    <!-- <el-form-item label="English Name" prop="name">
      <el-input v-model="dataForm.name" placeholder="English Name"></el-input>
    </el-form-item> -->
    <!-- <el-form-item label="Chinese Name" prop="cname">
      <el-input v-model="dataForm.cname" placeholder="Chinese Name"></el-input>
    </el-form-item> -->
    <el-form-item label="Gender" prop="gender">
      <el-radio-group v-model="dataForm.gender">
          <el-radio :label="1">Male</el-radio>
          <el-radio :label="2">Female</el-radio>
        </el-radio-group>
    </el-form-item>
    <el-form-item label="Date of Birth" >
     <el-date-picker
          v-model="dataForm.birth"
          type="date"
          placeholder="Please Select Date"
        >
        </el-date-picker>
    </el-form-item>
    <el-form-item label="Mobile" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="Mobile"></el-input>
    </el-form-item>
    <el-form-item label="Email" prop="email">
      <el-input v-model="dataForm.email" placeholder="Email"></el-input>
    </el-form-item>
    <!-- <el-form-item label="QQ" prop="qq">
      <el-input v-model="dataForm.qq" placeholder="QQ"></el-input>
    </el-form-item> -->
    <el-form-item label="Position" >
      <el-input v-model="dataForm.postId" placeholder="Position"></el-input>
    </el-form-item>
    <el-form-item label="Hire Date" prop="hired">
		<el-date-picker
          v-model="dataForm.hired"
          type="date"
          placeholder="Please Select Date"
        >
        </el-date-picker>
    </el-form-item>
    <el-form-item label="Leaved Date" >
      <el-date-picker
          v-model="dataForm.leaved"
          type="date"
            placeholder="Please Select Date"
        >
        </el-date-picker>
    </el-form-item>
    <el-form-item label="whether Leaved" prop="isLeaved">
      <el-switch
          v-model="dataForm.isLeaved"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="0"
          :inactive-value="1"
		  active-text="on"
      	  inactive-text="off"
        >
        </el-switch>
    </el-form-item>
    <el-form-item label="Whether to Active Account" prop="isActived">
      <el-switch
          v-model="dataForm.isActived"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="1"
          :inactive-value="0"
		  active-text="actived"
      	  inactive-text="unactived"
        >
        </el-switch>
		</el-form-item>
    <el-form-item label="Memo" >
      <el-input  type="textarea"
          :rows="2" v-model="dataForm.note" placeholder="Memo"></el-input>
    </el-form-item>
    <!-- 
		<el-form-item label="field1" prop="field1">
      <el-input v-model="dataForm.field1" placeholder="field1"></el-input>
    </el-form-item>
    <el-form-item label="field2" prop="field2">
      <el-input v-model="dataForm.field2" placeholder="field2"></el-input>
    </el-form-item>
    <el-form-item label="field3" prop="field3">
      <el-input v-model="dataForm.field3" placeholder="field3"></el-input>
    </el-form-item>
    <el-form-item label="field4" prop="field4">
      <el-input v-model="dataForm.field4" placeholder="field4"></el-input>
    </el-form-item>
    <el-form-item label="field5" prop="field5">
      <el-input v-model="dataForm.field5" placeholder="field5"></el-input>
    </el-form-item>
    <el-form-item label="field6" prop="field6">
      <el-input v-model="dataForm.field6" placeholder="field6"></el-input>
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
	-->
	 <el-form-item> 
	<!-- <el-button @click="visible = false">Cancle</el-button> -->
      <el-button type="primary" @click="dataFormSubmit()">Confirm</el-button>
	  </el-form-item> 
    </el-form>
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
          firstName: '',
          lastName: '',
          name: '',
          cname: '',
          gender: 1,
          birth: '',
          mobile: '',
          email: '',
          qq: '',
          postId: '',
          hired: new Date(),
          leaved: '',
          isLeaved: 0 ,
          isActived: 1,
          note: '',
        //   field1: '',
        //   field2: '',
        //   field3: '',
        //   field4: '',
        //   field5: '',
        //   field6: '',
        //   logicDeleted: '',
        //   created: '',
        //   updated: '',
        //   userId: ''
        },
        dataRule: {
          institutionId: [
            { required: true, message: 'Institution不能为空', trigger: 'blur' }
          ],
          campusId: [
            { required: true, message: 'Campus不能为空', trigger: 'blur' }
          ],
          firstName: [
            { required: true, message: 'Family Name不能为空', trigger: 'blur' }
          ],
          lastName: [
            { required: true, message: 'Given Name不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: 'English Name不能为空', trigger: 'blur' }
          ],
          cname: [
            { required: true, message: 'Chinese Name不能为空', trigger: 'blur' }
          ],
          gender: [
            { required: true, message: 'Gender不能为空', trigger: 'blur' }
          ],
          birth: [
            { required: true, message: 'Date of Birth不能为空', trigger: 'blur' }
          ],
          mobile: [
          {
			 required: true, 
            pattern: /^\d{10}$/,
            message: "手机号不能为空",
            trigger: "blur",
          },
        ],
        email: [
          {
            required: true, //是否必填
            message: "请输入邮箱地址", //错误提示信息
            trigger: "blur", //检验方式（blur为鼠标点击其他地方，）
          },
          {
            type: "email", //要检验的类型（number，email，date等）
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
          qq: [
            { required: true, message: 'QQ不能为空', trigger: 'blur' }
          ],
          postId: [
            { required: true, message: 'Position不能为空', trigger: 'blur' }
          ],
          hired: [
            { required: true, message: 'Hire Date不能为空', trigger: 'blur' }
          ],
          leaved: [
            { required: true, message: 'Leaved Date不能为空', trigger: 'blur' }
          ],
          isLeaved: [
            { required: true, message: 'whether Leaved不能为空', trigger: 'blur' }
          ],
          isActived: [
            { required: true, message: 'Whether to Active Account不能为空', trigger: 'blur' }
          ],
          note: [
            { required: true, message: 'Memo不能为空', trigger: 'blur' }
          ],
          field1: [
            { required: true, message: 'field1不能为空', trigger: 'blur' }
          ],
          field2: [
            { required: true, message: 'field2不能为空', trigger: 'blur' }
          ],
          field3: [
            { required: true, message: 'field3不能为空', trigger: 'blur' }
          ],
          field4: [
            { required: true, message: 'field4不能为空', trigger: 'blur' }
          ],
          field5: [
            { required: true, message: 'field5不能为空', trigger: 'blur' }
          ],
          field6: [
            { required: true, message: 'field6不能为空', trigger: 'blur' }
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
    //   init (id) {
    //     this.dataForm.id = id || 0
    //     this.visible = true
    //     this.$nextTick(() => {
    //       this.$refs['dataForm'].resetFields()
    //       if (this.dataForm.id) {
    //         this.$http({
    //           url: this.$http.adornUrl(`/tms/teacher/info/${this.dataForm.id}`),
    //           method: 'get',
    //           params: this.$http.adornParams()
    //         }).then(({data}) => {
    //           if (data && data.code === 0) {
    //             this.dataForm.institutionId = data.teacher.institutionId
    //             this.dataForm.campusId = data.teacher.campusId
    //             this.dataForm.firstName = data.teacher.firstName
    //             this.dataForm.lastName = data.teacher.lastName
    //             this.dataForm.name = data.teacher.name
    //             this.dataForm.cname = data.teacher.cname
    //             this.dataForm.gender = data.teacher.gender
    //             this.dataForm.birth = data.teacher.birth
    //             this.dataForm.mobile = data.teacher.mobile
    //             this.dataForm.email = data.teacher.email
    //             this.dataForm.qq = data.teacher.qq
    //             this.dataForm.postId = data.teacher.postId
    //             this.dataForm.hired = data.teacher.hired
    //             this.dataForm.leaved = data.teacher.leaved
    //             this.dataForm.isLeaved = data.teacher.isLeaved
    //             this.dataForm.isActived = data.teacher.isActived
    //             this.dataForm.note = data.teacher.note
    //             this.dataForm.field1 = data.teacher.field1
    //             this.dataForm.field2 = data.teacher.field2
    //             this.dataForm.field3 = data.teacher.field3
    //             this.dataForm.field4 = data.teacher.field4
    //             this.dataForm.field5 = data.teacher.field5
    //             this.dataForm.field6 = data.teacher.field6
    //             this.dataForm.logicDeleted = data.teacher.logicDeleted
    //             this.dataForm.created = data.teacher.created
    //             this.dataForm.updated = data.teacher.updated
    //             this.dataForm.userId = data.teacher.userId
    //           }
    //         })
    //       }
    //     })
    //   },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/tms/teacher/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'institutionId': this.dataForm.institutionId,
                'campusId': this.dataForm.campusId,
                'firstName': this.dataForm.firstName,
                'lastName': this.dataForm.lastName,
                'name': this.dataForm.name,
                'cname': this.dataForm.cname,
                'gender': this.dataForm.gender,
                'birth': this.dataForm.birth,
                'mobile': this.dataForm.mobile,
                'email': this.dataForm.email,
                'qq': this.dataForm.qq,
                'postId': this.dataForm.postId,
                'hired': this.dataForm.hired,
                'leaved': this.dataForm.leaved,
                'isLeaved': this.dataForm.isLeaved,
                'isActived': this.dataForm.isActived,
                'note': this.dataForm.note,
                'field1': this.dataForm.field1,
                'field2': this.dataForm.field2,
                'field3': this.dataForm.field3,
                'field4': this.dataForm.field4,
                'field5': this.dataForm.field5,
                'field6': this.dataForm.field6,
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
