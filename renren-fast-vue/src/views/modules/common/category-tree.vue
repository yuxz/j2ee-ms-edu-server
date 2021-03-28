<template>
  <el-tree :data="categories" :props="defaultProps" node-key="id" ref="categoriesTree"
   @node-click="nodeClick">
  </el-tree>
</template>


<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      categories: [],
      expandedKey: [],
      defaultProps: {
        children: "children",
        label: "name",
      },
    };
  },

  methods: {
    getCategories() {
      this.$http({
        url: this.$http.adornUrl("/exam/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
       //
	    console.log("成功获取到菜单数据...", data.data);
        this.categories = data.data;
      });
    },
	nodeClick(data, node, component){
		// 向父组件发送事件
		this.$emit("category-tree-click",data, node, component);
	},

  },
  created() {
    this.getCategories();
  },

};
</script>

<style scoped>
</style>