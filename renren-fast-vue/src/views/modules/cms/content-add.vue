<template>
  <div>
    <el-form
      :label-position="labelPosition"
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="80px"
    >
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
          v-model="dataForm.title"
          placeholder="eg. htttp://docs.google.com/..."
        ></el-input>
      </el-form-item>
      <el-form-item label="What kind of document is this?">
        <el-radio-group v-model="dataForm.contentTypeId" size="small">
          <el-radio label="left">Doc</el-radio>
          <el-radio label="right">Sheet</el-radio>
          <el-radio label="top">Slide</el-radio>
          <el-radio label="top">Other</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="Notes">
        <el-input
          type="textarea"
          id="summernote"
          v-model="dataForm.content"
        ></el-input>
      </el-form-item>

      <el-form-item>        
        <el-radio-group >
          <el-radio :label="3">When I post this, don’t notify anyone.</el-radio><br>
          <el-radio :label="6"> Let me choose who to notify…</el-radio>
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
  </div>
</template>
<script>
import $ from "jquery";
var url = "";
function sendFile(file, editor, welEditable) {
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
      alert(data);
      $("#summernote").summernote("insertImage", data, "filename");
    },
    error: function (jqXHR, textStatus, errorThrown) {
      console.log(textStatus + " " + errorThrown);
    },
  });
}

export default {
  data() {
    return {
      labelPosition: "top",
      dataForm: {
        id: 0,
        catagoryId: "",
        contentTypeId: "",
        title: "",
        subtitle: "",
        titleDesc: "",
        url: "",
        content: "",
        ispublished: 0,
		published: "",
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
      },
    };
  },
  methods: {
    init(id) {
      this.dataForm.id = id || 0;
      //   this.visible = true;
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
			   this.dataForm.ispublished = data.content.ispublished;
              this.dataForm.published = data.content.published;
              this.dataForm.logicDeleted = data.content.logicDeleted;
              this.dataForm.created = data.content.created;
              this.dataForm.updated = data.content.updated;
              this.dataForm.userId = data.content.userId;
            }
          });
        }
      });
    },
    // 表单提交
    dataFormSubmit(isPublished) {
      //	console.log(isPublished);
      // this.dataForm.published = isPublished;
      //   this.dataForm.content = $("#summernote").summernote("code");
      this.$refs["dataForm"].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/cms/content/${!this.dataForm.id ? "save" : "update"}`
            ),
            method: "post",
            data: this.$http.adornData({
              id: this.dataForm.id || undefined,
              catagoryId: this.dataForm.catagoryId,
              contentTypeId: this.dataForm.contentTypeId,
              title: this.dataForm.title,
              subtitle: this.dataForm.subtitle,
              titleDesc: this.dataForm.titleDesc,
              url: this.dataForm.url,
              content: this.dataForm.content,
              published: isPublished,
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

  //

  created() {
    url = this.$http.adornUrl("/cms/uploadfile/newfile");
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
        onImageUpload: function (files, editor, $editable) {
          sendFile(files[0], editor, $editable);
        },
      },
    });
  },
};
</script>
<style scoped>
</style>