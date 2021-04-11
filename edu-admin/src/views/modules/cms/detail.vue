<template>
  <el-dialog
    :title="'Detail'"
    :close-on-click-modal="false"
    :visible.sync="visible"
	@open="dialogOpen"
    width="90%"	
	fullscreen
  >
    <div class="content-header">
      <div class="content-title">
        {{ dataForm.title }}
      </div>
      <div class="content-author">
        {{ dataForm.subtitle }}
      </div>
      <div class="content-date">
        {{ dataForm.subtitle }}
      </div>
    </div>
    <div class="content-body">
      <p v-html="dataForm.content "></p>
    </div>
    <div class="content-footer">
      <span class="content-favorite"> </span>
      <span class="content-like">
        <el-badge :value="12" class="item">
          <el-button size="small">like</el-button>
        </el-badge>
      </span>
      <span class="content-comment">
        <el-badge :value="12" class="item">
          <el-button size="small">comment</el-button>
        </el-badge>
      </span>
    </div>
  </el-dialog>
</template>

<script>

export default {
  data() {
    return {
      //category-start
      categories: [],
      props: {
        value: "id",
        label: "name",
        children: "children",
      },
      categoryFullPath: [],
      //category-end

      labelPosition: "top",
      visible: false,
      dataForm: {
        id: 0,
        catagoryId: "",
        contentTypeId: "",
        title: "",
        subtitle: "",
        titleDesc: "",
        url: "",
        content: "",
        logicDeleted: "",
        created: "",
        updated: "",
        userId: "",
      },
    };
  },
  methods: {
	
    getCategories() {
      this.$http({
        url: this.$http.adornUrl("/cms/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
        console.log("成功获取分类级联数据...", data.data);
        this.categories = data.data;
      });
    },
    init(id) {
      this.dataForm.id = id || 0;
      this.visible = true;
      this.$nextTick(() => {
        //this.$refs["dataForm"].resetFields();
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/cms/content/info/${this.dataForm.id}`),
            method: "get",
            params: this.$http.adornParams(),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.catagoryId = data.content.catagoryId;
              this.dataForm.contentTypeId = data.content.contentTypeId;
              this.dataForm.title = data.content.title;
              this.dataForm.subtitle = data.content.subtitle;
              this.dataForm.titleDesc = data.content.titleDesc;
              this.dataForm.url = data.content.url;
              this.dataForm.content = data.content.content;
              this.dataForm.logicDeleted = data.content.logicDeleted;
              this.dataForm.created = data.content.created;
              this.dataForm.updated = data.content.updated;
              this.dataForm.userId = data.content.userId;
              this.categoryFullPath = data.content.categoryFullPath;
            }
          });
        }
      });
    },
  },
  created() {
    this.getCategories();
  },
  mounted() {
   // initSummernote();
  },
};
</script>
