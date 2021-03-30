<template>
  <div>
    <el-dialog
      :title="!paperForm.id ? '新增' : '修改'"
      :close-on-click-modal="false"
      :visible.sync="visible"
      fullscreen="fullscreen"
    >
      <el-form :model="paperForm" ref="paperForm" label-width="140px">
        <el-form-item label="题库" prop="categoryId"> </el-form-item>
        <el-form-item label="试卷类型" prop="paperTypeId">
          <el-input
            v-model="paperForm.paperTypeId"
            placeholder="试卷类型"
          ></el-input>
        </el-form-item>
        <el-form-item label="试卷编号" prop="name">
          <el-input
            v-model="paperForm.paperNo"
            placeholder="试卷编号"
          ></el-input>
        </el-form-item>
        <el-form-item label="试卷名称" prop="name">
          <el-input v-model="paperForm.name" placeholder="试卷名称"></el-input>
        </el-form-item>
        <el-form-item label="总分" prop="score">
          <el-input v-model="paperForm.score" placeholder="总分"></el-input>
        </el-form-item>
        <el-form-item label="试题总数" prop="items">
          <el-input v-model="paperForm.items" placeholder="试题总数"></el-input>
        </el-form-item>
        <el-form-item label="答题时限" prop="limitedTime">
          <el-input
            v-model="paperForm.limitedTime"
            placeholder="答题时限"
          ></el-input>
        </el-form-item>

        <el-form-item label="是否显示" prop="logicDeleted">
          <el-switch
            v-model="paperForm.logicDeleted"
            active-color="#13ce66"
            inactive-color="#ff4949"
            :active-value="0"
            :inactive-value="1"
          >
          </el-switch>
        </el-form-item>
        <el-form-item
          v-for="(paperItem, index) in paperForm.paperItems"
          :label="paperItem.id + '.'"
          :key="paperItem.id"
          :prop="'paperItems.' + index + '.answer'"
        >
          {{ paperItem.name }} [{{ paperItem.score}} points]
          <br />
          <el-radio-group
            v-if="paperItem.itemTypeId === 1"
            v-model="paperItem.answer"
          >
            <el-radio
              :label="paperOption.name"
              :key="paperOption.name"
              v-for="paperOption in paperItem.paperOptions"
            >
              {{ paperOption.name }}
            </el-radio>
          </el-radio-group>
          <el-checkbox-group  v-if="paperItem.itemTypeId === 2"  v-model="paperItem.answer">
            <el-checkbox :label="paperOption.name" :key="paperOption.name"
              v-for="paperOption in paperItem.paperOptions"></el-checkbox>                  
          </el-checkbox-group>
		  <el-input v-if="paperItem.itemTypeId === 3"  v-model="paperItem.answer" placeholder="please input your answer..."></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="visible = false">取消</el-button>
        <el-button type="primary">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      radio1: 3,
      radio2: 3,
      radio3: 3,
      radio4: 3,
      visible: false,
      fullscreen: true,
      paperForm: {
        id: 0,
        categoryId: 0,
        paperTypeId: "",
        paperNo: "",
        name: "",
        score: 100,
        items: 60,
        limitedTime: 60,
        browseNum: 0,
        testNum: 0,
        logicDeleted: 0,
        createTime: "",
        updateTime: "",
        userId: "",
        paperItems: [],
      },
    };
  },
  methods: {
    init(id) {
      this.paperForm.id = id || 0;
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["paperForm"].resetFields();
        if (this.paperForm.id) {
          this.$http({
            url: this.$http.adornUrl(
              `/exam/paper/paperDetail/${this.paperForm.id}`
            ),
            method: "get",
            params: this.$http.adornParams(),
          }).then(({ data }) => {
            console.log("成功获取修改数据", data);
            if (data && data.code === 0) {
              this.paperForm.categoryId = data.paper.categoryId;
              this.paperForm.paperTypeId = data.paper.paperTypeId;
              this.paperForm.paperNo = data.paper.paperNo;
              this.paperForm.name = data.paper.name;
              this.paperForm.score = data.paper.score;
              this.paperForm.items = data.paper.items;
              this.paperForm.limitedTime = data.paper.limitedTime;
              this.paperForm.browseNum = data.paper.browseNum;
              this.paperForm.testNum = data.paper.testNum;
              this.paperForm.logicDeleted = data.paper.logicDeleted;
              this.paperForm.createTime = data.paper.createTime;
              this.paperForm.updateTime = data.paper.updateTime;
              this.paperForm.userId = data.paper.userId;
              this.paperForm.paperItems = data.paper.paperItems;
            }
          });
        }
      });
    },
  },
  created() {},
};
</script>