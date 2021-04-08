<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
	width="85%"
  >
    <el-form
      :label-position="labelPosition"
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      label-width="120px"
    >
	 <el-form-item label="Channel" prop="categoryId">
        <el-cascader
          v-model="categoryFullPath"
          :options="categories"
          :props="props"
          size="medium"
        ></el-cascader>
      </el-form-item>
      <el-form-item label="What's the title for this?">
        <el-input
          v-model="dataForm.title"
          placeholder="Type the title of this..."
        ></el-input>
      </el-form-item>
      <el-form-item label="What's the subtitle for this?">
        <el-input
          v-model="dataForm.subtitle"
          placeholder="Type the subtitle of this..."
        ></el-input>
      </el-form-item>
      <el-form-item label="Paste the Google Drive link here">
        <el-input
          v-model="dataForm.url"
          placeholder="eg. htttp://docs.google.com/..."
        ></el-input>
      </el-form-item>
      <el-form-item label="What kind of document is this?">
        <el-radio-group v-model="dataForm.contentTypeId" size="small">
          <el-radio label="1">Doc</el-radio>
          <el-radio label="2">Sheet</el-radio>
          <el-radio label="3">Slide</el-radio>
          <el-radio label="4">Other</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="Notes">        
		  <markdown-editor v-model="dataForm.content" height="300px" />
      </el-form-item>

      <el-form-item>
        <el-radio-group>
          <el-radio :label="1">When I post this, don’t notify anyone.</el-radio
          ><br />
          <el-radio :label="2"> Let me choose who to notify…</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="dataFormSubmit(1)" round
          >Post this doc</el-button
        >
        <el-button type="primary" @click="dataFormSubmit(0)" round
          >Save as a draft</el-button
        >
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import $ from "jquery";
var url = "";
function sendFile(file) {
  var data = new FormData();
  data.append("file", file);
  $.ajax({
    url: url,
    data: data,
    cache: false,
    contentType: false,
    processData: false,
    type: "POST",
    success: function (data) {
      //   alert("insert image: " + data);
      //   let url=data;
      let url =
        "https://th.bing.com/th/id/R77987b680372887acc65ed8d58a9ba75?rik=Hx0zx3IHGhniLw&riu=http%3a%2f%2ftupian.sioe.cn%2fb%2fbing-home-image%2fpic%2f20140818.jpg&ehk=801LJ3t%2fhFQaU5x3M4NDlRwNeau%2budLrH0LW3ni0ahU%3d&risl=&pid=ImgRaw";
      $("#summernote").summernote("insertImage", url, "filename");
    },
    error: function (jqXHR, textStatus, errorThrown) {
      console.log(textStatus + " " + errorThrown);
    },
  });
}

function deleteFile(src) {
  console.log("delete-----------");
  $.ajax({
    data: { src: src },
    type: "POST",
    url: "dropzone/delete_file", // replace with your url
    cache: false,
    success: function (resp) {
      console.log(resp);
    },
  });
}

import MarkdownEditor from '@/components/MarkdownEditor'
export default {
  components:{
	  MarkdownEditor
  }	,
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
      dataRule: {
        catagoryId: [
          { required: true, message: "分类id不能为空", trigger: "blur" },
        ],
        contentTypeId: [
          { required: true, message: "内容类型id不能为空", trigger: "blur" },
        ],
        title: [{ required: true, message: "标题不能为空", trigger: "blur" }],
        subtitle: [
          { required: true, message: "副标题不能为空", trigger: "blur" },
        ],
        titleDesc: [
          { required: true, message: "描述不能为空", trigger: "blur" },
        ],
        url: [{ required: true, message: "url不能为空", trigger: "blur" }],
        content: [{ required: true, message: "内容不能为空", trigger: "blur" }],
        logicDeleted: [
          {
            required: true,
            message: "是否被删除[0-已删，1未删]不能为空",
            trigger: "blur",
          },
        ],
        created: [
          { required: true, message: "创建时间不能为空", trigger: "blur" },
        ],
        updated: [
          { required: true, message: "更改时间不能为空", trigger: "blur" },
        ],
        userId: [
          { required: true, message: "创建人不能为空", trigger: "blur" },
        ],
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
        this.$refs["dataForm"].resetFields();
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
			  this.categoryFullPath = data.paper.categoryFullPath;
            }
          });
        }
      });
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate((valid) => {
        this.dataForm.content = $("#summernote").summernote("code");
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/cms/content/${!this.dataForm.id ? "save" : "update"}`
            ),
            method: "post",
            data: this.$http.adornData({
              id: this.dataForm.id || undefined,
              categoryId: this.categoryFullPath[
                this.categoryFullPath.length - 1
              ],
              contentTypeId: this.dataForm.contentTypeId,
              title: this.dataForm.title,
              subtitle: this.dataForm.subtitle,
              titleDesc: this.dataForm.titleDesc,
              url: this.dataForm.url,
              content: this.dataForm.content,
              logicDeleted: this.dataForm.logicDeleted,
              created: this.dataForm.created,
              updated: this.dataForm.updated,
              userId: this.dataForm.userId,
            }),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.visible = false;
                  this.$emit("refreshDataList");
                },
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    },
  },
  created() {
    url = this.$http.adornUrl("/cms/uploadfile/newfile");
	this.getCategories();
  },
  mounted() {
    $("#summernote").summernote({
      lang: "zh-CN",
      placeholder: "Type you document here... // Add any notes here...",
      tabsize: 2,
      height: 300, // set editor height
      minHeight: null, // set minimum height of editor
      maxHeight: null, // set maximum height of editor
      focus: true, // set focus to editable area after initializing summernote
      toolbar: [
        ["style", ["style"]],
        ["font", ["bold", "underline", "clear"]],
        ["color", ["color"]],
        ["para", ["ul", "ol", "paragraph"]],
        ["table", ["table"]],
        ["insert", ["link", "picture", "video"]],
        ["view", ["fullscreen", "codeview", "help"]],
      ],
      callbacks: {
        onImageUpload: function (files) {
          sendFile(files[0]);
        },
        onMediaDelete: function (target) {
          // alert(target[0].src)
          deleteFile(target[0].src);
        },
      },
    });
  },
};
</script>

<style scoped>
.editor-container{
  margin-bottom: 30px;
}
.tag-title{
  margin-bottom: 5px;
}
</style>

