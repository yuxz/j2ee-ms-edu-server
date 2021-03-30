<template>
	<el-cascader
		v-model="dataForm.catagoryId"
		:options="categories"
		@change="handleChange"
		placeholder="试试搜索：VCE"
		filterable></el-cascader>	
</template>
<script>
export default {
	
    data () {
      return {
        visible: false,
		categories:[],
	  }
	},
	methods: {
		
	},
	created(){

	},
	
}
</script>
<style scoped>

</style>