<template>
  <el-select v-model="value"  placeholder="请选择" @change="selectChange">
    <el-option
      v-for="item in classroom"
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
      classroom: [],
	  value:"",
    };
  },
  methods: {
    getClassroom(campusId) {
	  this.classroom = [];
	  this.value ="";	
      this.$http({
        url: this.$http.adornUrl(`/ims/classroom/list/${campusId}`),
        method: "get",
      }).then(({ data }) => {
        this.classroom = data.page.list;
      });
    },
	selectChange(data){
		this.$emit("classroom-select-change",data);
	}
  },
  created() {
    // this.getClassroom(0);
  },
};
</script>
<style scoped>
</style>