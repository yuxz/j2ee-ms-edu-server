<template>
<div>
  <!-- <el-select v-model="value"  placeholder="please select" @change="selectChange">
    <el-option
      v-for="item in schedule"
      :key="item.id"
      :label="item.name"
      :value="item.id"
    >
    </el-option>
  </el-select> -->
  <el-radio-group v-model="value" @change="selectChange">
    <el-radio 
	   v-for="item in schedule"
      :label="item.id"
	  :key="item.id"
	  >
	  {{item.name}}  {{item.started}}-{{item.ended}}
	  </el-radio>   
  </el-radio-group>
</div>
</template>
<script>
export default {
  data() {
    return {
      schedule: [],
	  value:"",
    };
  },
  methods: {
    getSchedule(campusId) {
	  this.schedule = [];
	  this.value ="";	
      this.$http({
        url: this.$http.adornUrl(`/ims/campustrainingschedule/list/${campusId}`),
        method: "get",
      }).then(({ data }) => {
        this.schedule = data.page.list;
      });
    },
	selectChange(data){
		this.$emit("schedule-select-change",data);
	}
  },
  created() {
    // this.getClassroom(0);
  },
};
</script>
<style scoped>
</style>