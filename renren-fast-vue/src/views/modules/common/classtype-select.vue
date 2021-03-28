<template>
  <el-select v-model="value"  placeholder="请选择" @change="selectChange">
    <el-option
      v-for="item in classtypes"
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
      classtypes: [],
	  value:"",
    };
  },
  methods: {
    getClasstypes() {
      this.$http({
        url: this.$http.adornUrl("/ims/classtype/list"),
        method: "get",
      }).then(({ data }) => {
        this.classtypes = data.page.list;
      });
    },
	selectChange(data){
		this.$emit("class-type-select-change",data);
	}
  },
  created() {
    this.getClasstypes();
  },
};
</script>
<style scoped>
</style>