<template>
  <div>
    <el-switch
      v-model="draggable"
      active-text="开启拖拽"
      inactive-text="关闭拖拽"
    ></el-switch>
	<el-tooltip content="新增一级频道" placement="top">
    <el-button
      type="primary"
      @click="() => append()"
      icon="el-icon-plus"
      circle
    ></el-button>
	</el-tooltip>
    <el-button
      v-if="draggable"
      @click="batchSave"
      icon="el-icon-upload"
      circle
    ></el-button>

	<el-tooltip content="批量删除" placement="top">
    <el-button
      type="danger"
      @click="batchDelete"
      icon="el-icon-delete"
      circle
    ></el-button>
	</el-tooltip>
    <el-tree
      :data="menus"
      :props="defaultProps"
      :expand-on-click-node="false"
      show-checkbox
      node-key="id"
      :default-expanded-keys="expandedKey"
      :draggable="draggable"
      :allow-drop="allowDrop"
      @node-drop="handleDrop"
      ref="menuTree"
	  highlight-current="true"
    >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <i
            class="el-icon-plus"
            v-if="node.level <= 2"
            @click="() => append(data)"
          ></i>
          <i class="el-icon-edit-outline" @click="() => edit(data)"></i>
          <i
            class="el-icon-minus"
            v-if="node.childNodes.length == 0"
            @click="() => remove(node, data)"
          ></i>
        </span>
      </span>
    </el-tree>

    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      width="40%"
      :close-on-click-modal="false"
    >
      <el-form :model="category" :rules="rules" ref="categoryForm">
        <el-form-item label="Parent">
          <el-input
            v-model="parentCategoryName"
            autocomplete="off"
            :disabled="true"
          ></el-input>
        </el-form-item>
		 <el-form-item label="Code">
          <el-input v-model="category.code" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Channel">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <!-- <el-form-item label="Icon">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item> -->
        <!-- <el-form-item label="计量单位">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item> -->
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submitData">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},
  data() {
    return {
      pCid: [],
      draggable: false,
      updateNodes: [],
      maxLevel: 0,
      title: "",
      dialogType: "", //edit,add
      parentCategoryName: "",
      category: {
		code: "",
        name: "",
        parentId: 0,
        level: 0,
        //showStatus: 1,
        logicDeleted: 0,
        sort: 0,
        //productUnit: "",
        icon: "",
        id: null,
      },
      rules: {
        name: [
          {
            validator: (rule, value, callback) => {
              if (value.trim() == "") {
                callback(new Error("please input category type name"));
              } else {
                callback();
              }
            },
            trigger: "blur",
          },
        ],
      },
      dialogVisible: false,
      menus: [],
      expandedKey: [],
      defaultProps: {
        children: "children",
        label: "name",
      },
    };
  },

  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    getMenus() {
      this.$http({
        url: this.$http.adornUrl("/cms/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
        console.log("Success get data...", data.data);
        this.menus = data.data;
      });
    },
    batchDelete() {
      let ids = [];
      let checkedNodes = this.$refs.menuTree.getCheckedNodes();   
      for (let i = 0; i < checkedNodes.length; i++) {
        ids.push(checkedNodes[i].id);
      }
      this.$confirm(`是否批量删除【${ids}】菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl("/cms/category/delete"),
            method: "post",
            data: this.$http.adornData(ids, false),
          }).then(({ data }) => {
            this.$message({
              message: "批量删除成功",
              type: "success",
            });
            this.getMenus();
          });
        })
        .catch(() => {});
    },
    batchSave() {
      this.$http({
        url: this.$http.adornUrl("/cms/category/update/sort"),
        method: "post",
        data: this.$http.adornData(this.updateNodes, false),
      }).then(({ data }) => {
        this.$message({
          message: "顺序修改成功",
          type: "success",
        });
        //刷新出新的菜单
        this.getMenus();
        //设置需要默认展开的菜单
        this.expandedKey = this.pCid;
        this.updateNodes = [];
        this.maxLevel = 0;
        // this.pCid = 0;
      });
    },
    handleDrop(draggingNode, dropNode, dropType, ev) {
      console.log("handleDrop: ", draggingNode, dropNode, dropType);
      //1、当前节点最新的父节点id
      let pCid = 0;
      let siblings = null;
      if (dropType == "before" || dropType == "after") {
        pCid =
          dropNode.parent.data.id == undefined ? 0 : dropNode.parent.data.id;
        siblings = dropNode.parent.childNodes;
      } else {
        pCid = dropNode.data.id;
        siblings = dropNode.childNodes;
      }
      this.pCid.push(pCid);

      //2、当前拖拽节点的最新顺序，
      for (let i = 0; i < siblings.length; i++) {
        if (siblings[i].data.id == draggingNode.data.id) {
          //如果遍历的是当前正在拖拽的节点
          let level = draggingNode.level;
          if (siblings[i].level != draggingNode.level) {
            //当前节点的层级发生变化
            level = siblings[i].level;
            //修改他子节点的层级
            this.updateChildNodeLevel(siblings[i]);
          }
          this.updateNodes.push({
            id: siblings[i].data.id,
            sort: i,
            parentId: pCid,
            level: level,
          });
        } else {
          this.updateNodes.push({ id: siblings[i].data.id, sort: i });
        }
      }

      //3、当前拖拽节点的最新层级
      console.log("updateNodes", this.updateNodes);
    },
    updateChildNodeLevel(node) {
      if (node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          var cNode = node.childNodes[i].data;
          this.updateNodes.push({
            id: cNode.id,
            level: node.childNodes[i].level,
          });
          this.updateChildNodeLevel(node.childNodes[i]);
        }
      }
    },
    allowDrop(draggingNode, dropNode, type) {
      //1、被拖动的当前节点以及所在的父节点总层数不能大于3

      //1）、被拖动的当前节点总层数
      console.log("allowDrop:", draggingNode, dropNode, type);
      //
      this.countNodeLevel(draggingNode);
      //当前正在拖动的节点+父节点所在的深度不大于3即可
      let deep = Math.abs(this.maxLevel - draggingNode.level) + 1;
      console.log("深度：", deep);

      //   this.maxLevel
      if (type == "inner") {
        // console.log(
        //   `this.maxLevel：${this.maxLevel}；draggingNode.data.level：${draggingNode.data.level}；dropNode.level：${dropNode.level}`
        // );
        return deep + dropNode.level <= 3;
      } else {
        return deep + dropNode.parent.level <= 3;
      }
    },
    countNodeLevel(node) {
      //找到所有子节点，求出最大深度
      if (node.childNodes != null && node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          if (node.childNodes[i].level > this.maxLevel) {
            this.maxLevel = node.childNodes[i].level;
          }
          this.countNodeLevel(node.childNodes[i]);
        }
      }
    },
    edit(data) {
     
      this.dialogType = "edit";
      this.title = "Edit Channel";
      //父级分类名称
      this.parentCategoryName = "";
      this.dialogVisible = true;

      //发送请求获取当前节点最新的数据
      this.$http({
        url: this.$http.adornUrl(`/cms/category/info/${data.id}`),
        method: "get",
      }).then(({ data }) => {
        //请求成功
         this.category.code = data.data.code;
        this.category.name = data.data.name;
        this.category.id = data.data.id;
        this.category.icon = data.data.icon;
        //this.category.productUnit = data.data.productUnit;
        this.category.parentId = data.data.parentId;
        this.category.level = data.data.level;
        this.category.sort = data.data.sort;
        // this.category.showStatus = data.data.showStatus;
        this.category.logicDeleted = data.data.logicDeleted;

        //父级分类名称
        this.parentCategoryName = data.data.parentCategoryName;
        /**
         *         parentId: 0,
        level: 0,
        showStatus: 1,
        sort: 0,
         */
      });
    },
    append(data) {
      //新增一级分类
      this.dialogType = "add";
      this.title = "Add Channel";
      //父级分类名称
      this.parentCategoryName = "";
      this.dialogVisible = true;

      if (data == null) {
        console.log("append 未定义");
      } else {
        console.log("append", data);
        this.category.parentId = data.id;
        this.category.level = data.level * 1 + 1;
        //父级分类名称
        this.parentCategoryName = data.name;
      }

      this.category.id = null;
	  this.category.code = "";
      this.category.name = "";
      this.category.icon = "";
      //this.category.productUnit = "";
      this.category.sort = 0;
      //this.category.showStatus = 1;
      this.category.logicDeleted = 0;
    },

    submitData() {
      if (this.dialogType == "add") {
        this.addCategory();
      }
      if (this.dialogType == "edit") {
        this.editCategory();
      }
    },
    //修改三级分类数据
    editCategory() {
      var { id, name, icon } = this.category;
      this.$refs["categoryForm"].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl("/cms/category/update"),
            method: "post",
            data: this.$http.adornData({ id, name, icon }, false),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "修改成功",
                type: "success",
              });
              //关闭对话框
              this.dialogVisible = false;
              //刷新出新的菜单
              this.getMenus();
              //设置需要默认展开的菜单
              this.expandedKey = [this.category.parentId];

              //清空父级分类名称
              this.parentCategoryName = "";
            } else {
              this.$message.error(data.msg);
            }
          });
        } else {
          return false;
        }
      });
    },
    //添加三级分类
    addCategory() {
    //   console.log("提交的三级分类数据", this.category);
      this.$refs["categoryForm"].validate((valid) => {
        if (valid) {
          //alert('submit!');
          this.$http({
            url: this.$http.adornUrl("/cms/category/save"),
            method: "post",
            data: this.$http.adornData(this.category, false),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "菜单保存成功",
                type: "success",
              });
              //关闭对话框
              this.dialogVisible = false;
              //刷新出新的菜单
              this.getMenus();
              //设置需要默认展开的菜单
              this.expandedKey = [this.category.parentId];
              //清空父级分类名称
              this.parentCategoryName = "";
            } else {
				this.$message.error(data.msg);
            }
          });
        } else {
          //console.log('error submit!!');
          return false;
        }
      });
    },

    remove(node, data) {
      var ids = [data.id];
      this.$confirm(`是否删除【${data.name}】数据?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl("/cms/category/delete"),
            method: "post",
            data: this.$http.adornData(ids, false),
          }).then(({ data }) => {
            this.$message({
              message: "删除成功",
              type: "success",
            });
            //刷新出新的菜单
            this.getMenus();
            //设置需要默认展开的菜单
            this.expandedKey = [node.parent.data.id];
          });
        })
        .catch(() => {});

      console.log("remove", node, data);
    },
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {
    this.getMenus();
  },
  //生命周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
.custom-tree-node {
    font-size: 14px;
}
</style>