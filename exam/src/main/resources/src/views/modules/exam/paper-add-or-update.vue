<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="题库id" prop="catagoryId">
      <el-input v-model="dataForm.catagoryId" placeholder="题库id"></el-input>
    </el-form-item>
    <el-form-item label="试卷类型id" prop="paperTypeId">
      <el-input v-model="dataForm.paperTypeId" placeholder="试卷类型id"></el-input>
    </el-form-item>
    <el-form-item label="试卷名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="试卷名称"></el-input>
    </el-form-item>
    <el-form-item label="总分" prop="score">
      <el-input v-model="dataForm.score" placeholder="总分"></el-input>
    </el-form-item>
    <el-form-item label="试题总数" prop="items">
      <el-input v-model="dataForm.items" placeholder="试题总数"></el-input>
    </el-form-item>
    <el-form-item label="答题时限" prop="limitedTime">
      <el-input v-model="dataForm.limitedTime" placeholder="答题时限"></el-input>
    </el-form-item>
    <el-form-item label="浏览量" prop="browseNum">
      <el-input v-model="dataForm.browseNum" placeholder="浏览量"></el-input>
    </el-form-item>
    <el-form-item label="测试量" prop="testNum">
      <el-input v-model="dataForm.testNum" placeholder="测试量"></el-input>
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
          catagoryId: '',
          paperTypeId: '',
          name: '',
          score: '',
          items: '',
          limitedTime: '',
          browseNum: '',
          testNum: '',
          logicDeleted: '',
          createTime: '',
          updateTime: '',
          userId: ''
        },
        dataRule: {
          catagoryId: [
            { required: true, message: '题库id不能为空', trigger: 'blur' }
          ],
          paperTypeId: [
            { required: true, message: '试卷类型id不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '试卷名称不能为空', trigger: 'blur' }
          ],
          score: [
            { required: true, message: '总分不能为空', trigger: 'blur' }
          ],
          items: [
            { required: true, message: '试题总数不能为空', trigger: 'blur' }
          ],
          limitedTime: [
            { required: true, message: '答题时限不能为空', trigger: 'blur' }
          ],
          browseNum: [
            { required: true, message: '浏览量不能为空', trigger: 'blur' }
          ],
          testNum: [
            { required: true, message: '测试量不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/exam/paper/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.catagoryId = data.paper.catagoryId
                this.dataForm.paperTypeId = data.paper.paperTypeId
                this.dataForm.name = data.paper.name
                this.dataForm.score = data.paper.score
                this.dataForm.items = data.paper.items
                this.dataForm.limitedTime = data.paper.limitedTime
                this.dataForm.browseNum = data.paper.browseNum
                this.dataForm.testNum = data.paper.testNum
                this.dataForm.logicDeleted = data.paper.logicDeleted
                this.dataForm.createTime = data.paper.createTime
                this.dataForm.updateTime = data.paper.updateTime
                this.dataForm.userId = data.paper.userId
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
              url: this.$http.adornUrl(`/exam/paper/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'catagoryId': this.dataForm.catagoryId,
                'paperTypeId': this.dataForm.paperTypeId,
                'name': this.dataForm.name,
                'score': this.dataForm.score,
                'items': this.dataForm.items,
                'limitedTime': this.dataForm.limitedTime,
                'browseNum': this.dataForm.browseNum,
                'testNum': this.dataForm.testNum,
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
