<template>
  <el-tree :data="categories" :props="defaultProps" node-key="id" ref="categoriesTree"
   @node-click="nodeClick" highlight-current>
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
        url: this.$http.adornUrl("/cms/category/list/tree"),
        method: "get",
      }).then(({ data }) => {
       //   
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