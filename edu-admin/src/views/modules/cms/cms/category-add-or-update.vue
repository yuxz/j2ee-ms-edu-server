<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="教育机构id" prop="institutionId">
      <el-input v-model="dataForm.institutionId" placeholder="教育机构id"></el-input>
    </el-form-item>
    <el-form-item label="父分类id" prop="parentId">
      <el-input v-model="dataForm.parentId" placeholder="父分类id"></el-input>
    </el-form-item>
    <el-form-item label="分类名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="分类名称"></el-input>
    </el-form-item>
    <el-form-item label="层级" prop="catLevel">
      <el-input v-model="dataForm.catLevel" placeholder="层级"></el-input>
    </el-form-item>
    <el-form-item label="排序" prop="sort">
      <el-input v-model="dataForm.sort" placeholder="排序"></el-input>
    </el-form-item>
    <el-form-item label="内容数量" prop="goodsCount">
      <el-input v-model="dataForm.goodsCount" placeholder="内容数量"></el-input>
    </el-form-item>
    <el-form-item label="图标地址" prop="icon">
      <el-input v-model="dataForm.icon" placeholder="图标地址"></el-input>
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
          institutionId: '',
          parentId: '',
          name: '',
          catLevel: '',
          sort: '',
          goodsCount: '',
          icon: '',
          logicDeleted: '',
          created: '',
          updated: '',
          userId: ''
        },
        dataRule: {
          institutionId: [
            { required: true, message: '教育机构id不能为空', trigger: 'blur' }
          ],
          parentId: [
            { required: true, message: '父分类id不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '分类名称不能为空', trigger: 'blur' }
          ],
          catLevel: [
            { required: true, message: '层级不能为空', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '排序不能为空', trigger: 'blur' }
          ],
          goodsCount: [
            { required: true, message: '内容数量不能为空', trigger: 'blur' }
          ],
          icon: [
            { required: true, message: '图标地址不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/cms/category/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.institutionId = data.category.institutionId
                this.dataForm.parentId = data.category.parentId
                this.dataForm.name = data.category.name
                this.dataForm.catLevel = data.category.catLevel
                this.dataForm.sort = data.category.sort
                this.dataForm.goodsCount = data.category.goodsCount
                this.dataForm.icon = data.category.icon
                this.dataForm.logicDeleted = data.category.logicDeleted
                this.dataForm.created = data.category.created
                this.dataForm.updated = data.category.updated
                this.dataForm.userId = data.category.userId
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
              url: this.$http.adornUrl(`/cms/category/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'institutionId': this.dataForm.institutionId,
                'parentId': this.dataForm.parentId,
                'name': this.dataForm.name,
                'catLevel': this.dataForm.catLevel,
                'sort': this.dataForm.sort,
                'goodsCount': this.dataForm.goodsCount,
                'icon': this.dataForm.icon,
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
