<template>
  <el-select v-model="value"  placeholder="请选择" @change="selectChange">
    <el-option
      v-for="item in campuse"
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
      campuse: [],
	   value:"",
    };
  },
  methods: {
    getCampuse() {
      this.$http({
        url: this.$http.adornUrl("/ims/campus/list"),
        method: "get",
      }).then(({ data }) => {
        this.campuse = data.page.list;
      });
    },
	selectChange(data){
		this.$emit("campus-select-change",data);
	}
  },
  created() {
    this.getCampuse();
  },
};
</script>
<style scoped>
</style>