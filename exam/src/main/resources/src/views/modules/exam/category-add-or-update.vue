<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="教育机构id" prop="institutionId">
      <el-input v-model="dataForm.institutionId" placeholder="教育机构id"></el-input>
    </el-form-item>
    <el-form-item label="题库名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="题库名称"></el-input>
    </el-form-item>
    <el-form-item label="父分类id" prop="parentCid">
      <el-input v-model="dataForm.parentCid" placeholder="父分类id"></el-input>
    </el-form-item>
    <el-form-item label="层级" prop="catLevel">
      <el-input v-model="dataForm.catLevel" placeholder="层级"></el-input>
    </el-form-item>
    <el-form-item label="排序" prop="sort">
      <el-input v-model="dataForm.sort" placeholder="排序"></el-input>
    </el-form-item>
    <el-form-item label="试卷数量" prop="goodsCount">
      <el-input v-model="dataForm.goodsCount" placeholder="试卷数量"></el-input>
    </el-form-item>
    <el-form-item label="图标地址" prop="icon">
      <el-input v-model="dataForm.icon" placeholder="图标地址"></el-input>
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
          name: '',
          parentCid: '',
          catLevel: '',
          sort: '',
          goodsCount: '',
          icon: '',
          logicDeleted: '',
          createTime: '',
          updateTime: '',
          userId: ''
        },
        dataRule: {
          institutionId: [
            { required: true, message: '教育机构id不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '题库名称不能为空', trigger: 'blur' }
          ],
          parentCid: [
            { required: true, message: '父分类id不能为空', trigger: 'blur' }
          ],
          catLevel: [
            { required: true, message: '层级不能为空', trigger: 'blur' }
          ],
          sort: [
            { required: true, message: '排序不能为空', trigger: 'blur' }
          ],
          goodsCount: [
            { required: true, message: '试卷数量不能为空', trigger: 'blur' }
          ],
          icon: [
            { required: true, message: '图标地址不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/exam/category/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.institutionId = data.category.institutionId
                this.dataForm.name = data.category.name
                this.dataForm.parentCid = data.category.parentCid
                this.dataForm.catLevel = data.category.catLevel
                this.dataForm.sort = data.category.sort
                this.dataForm.goodsCount = data.category.goodsCount
                this.dataForm.icon = data.category.icon
                this.dataForm.logicDeleted = data.category.logicDeleted
                this.dataForm.createTime = data.category.createTime
                this.dataForm.updateTime = data.category.updateTime
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
              url: this.$http.adornUrl(`/exam/category/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'institutionId': this.dataForm.institutionId,
                'name': this.dataForm.name,
                'parentCid': this.dataForm.parentCid,
                'catLevel': this.dataForm.catLevel,
                'sort': this.dataForm.sort,
                'goodsCount': this.dataForm.goodsCount,
                'icon': this.dataForm.icon,
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
