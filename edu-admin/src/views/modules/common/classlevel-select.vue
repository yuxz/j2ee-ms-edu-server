<template>
  <el-select v-model="value"  placeholder="请选择" @change="selectChange">
    <el-option
      v-for="item in classlevels"
      :key="item.id"
      :label="item.name"
      :value="item.id"
    >
    </el-option>
  </el-select>
</template>
<script>
export default {
  data() {
    return {
      classlevels: [],
	  value:"",
    };
  },
  methods: {
    getClassLevels() {
      this.$http({
        url: this.$http.adornUrl("/ims/classlevel/list"),
        method: "get",
      }).then(({ data }) => {
        this.classlevels = data.page.list;
      });
    },
	selectChange(data){
		this.$emit("class-level-select-change",data);
	}
  },
  created() {
    this.getClassLevels();
  },
};
</script>
<style scoped>
</style>