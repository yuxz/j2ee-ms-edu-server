<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="分类id" prop="catagoryId">
      <el-input v-model="dataForm.catagoryId" placeholder="分类id"></el-input>
    </el-form-item>
    <el-form-item label="内容类型id" prop="contentTypeId">
      <el-input v-model="dataForm.contentTypeId" placeholder="内容类型id"></el-input>
    </el-form-item>
    <el-form-item label="标题" prop="title">
      <el-input v-model="dataForm.title" placeholder="标题"></el-input>
    </el-form-item>
    <el-form-item label="副标题" prop="subtitle">
      <el-input v-model="dataForm.subtitle" placeholder="副标题"></el-input>
    </el-form-item>
    <el-form-item label="描述" prop="titleDesc">
      <el-input v-model="dataForm.titleDesc" placeholder="描述"></el-input>
    </el-form-item>
    <el-form-item label="url" prop="url">
      <el-input v-model="dataForm.url" placeholder="url"></el-input>
    </el-form-item>
    <el-form-item label="内容" prop="content">
      <el-input v-model="dataForm.content" placeholder="内容"></el-input>
    </el-form-item>
    <el-form-item label="是否被删除[0-已删，1未删]" prop="logicDeleted">
      <el-input v-model="dataForm.logicDeleted" placeholder="是否被删除[0-已删，1未删]"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="created">
      <el-input v-model="dataForm.created" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更改时间" prop="updated">
      <el-input v-model="dataForm.updated" placeholder="更改时间"></el-input>
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
          catagoryId: '',
          contentTypeId: '',
          title: '',
          subtitle: '',
          titleDesc: '',
          url: '',
          content: '',
          logicDeleted: '',
          created: '',
          updated: '',
          userId: ''
        },
        dataRule: {
          catagoryId: [
            { required: true, message: '分类id不能为空', trigger: 'blur' }
          ],
          contentTypeId: [
            { required: true, message: '内容类型id不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '标题不能为空', trigger: 'blur' }
          ],
          subtitle: [
            { required: true, message: '副标题不能为空', trigger: 'blur' }
          ],
          titleDesc: [
            { required: true, message: '描述不能为空', trigger: 'blur' }
          ],
          url: [
            { required: true, message: 'url不能为空', trigger: 'blur' }
          ],
          content: [
            { required: true, message: '内容不能为空', trigger: 'blur' }
          ],
          logicDeleted: [
            { required: true, message: '是否被删除[0-已删，1未删]不能为空', trigger: 'blur' }
          ],
          created: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updated: [
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
              url: this.$http.adornUrl(`/cms/content/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.catagoryId = data.content.catagoryId
                this.dataForm.contentTypeId = data.content.contentTypeId
                this.dataForm.title = data.content.title
                this.dataForm.subtitle = data.content.subtitle
                this.dataForm.titleDesc = data.content.titleDesc
                this.dataForm.url = data.content.url
                this.dataForm.content = data.content.content
                this.dataForm.logicDeleted = data.content.logicDeleted
                this.dataForm.created = data.content.created
                this.dataForm.updated = data.content.updated
                this.dataForm.userId = data.content.userId
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
              url: this.$http.adornUrl(`/cms/content/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'catagoryId': this.dataForm.catagoryId,
                'contentTypeId': this.dataForm.contentTypeId,
                'title': this.dataForm.title,
                'subtitle': this.dataForm.subtitle,
                'titleDesc': this.dataForm.titleDesc,
                'url': this.dataForm.url,
                'content': this.dataForm.content,
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
