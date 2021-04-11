<template>
      <div class="mod-config"> 
        <el-table
          :data="dataList"
          border          
          style="width: 100%"
        >    
          <el-table-column
            prop="name"
            header-align="center"
            align="center"
            label="Name"
          >
          </el-table-column>
		  <el-table-column
              prop="email"
              header-align="center"
              align="center"
              label="Email"
            ></el-table-column> 
			<el-table-column
              prop="mobile"
              header-align="center"
              align="center"
              label="Mobile"
            ></el-table-column>            
        </el-table>      
      </div>    
</template>

<script>
export default {
	components: {
  },
  data() {
    return {
	  //主窗口	
     
      dataList: [],      
      dataListSelections: [],
	  dataListLoading: false,

	  //內部彈出窗口   
 	
	  
	  DataListIncludingByClass:[],
	  pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
	  innerDataListSelections: [], 

    };
  },
  
  activated() {
    // this.getDataList();
  },
  methods: {
	//班級已有教師-初始化  
    init(id) {
      this.classId = id || 0;
      this.visible = true;
	  this.$http({
        url: this.$http.adornUrl(
          "/tms/teacherclass/list/" + this.classId
        ),
        method: "get",
        // params: this.$http.adornParams({})
      }).then(({ data }) => {
      // this.dataList = data.data;
	  this.dataList = data.data;
      });
    },    
    //班級已有教師-添加新教室後，刷新窗口數據？？？？
    getDataList() {
      this.dataListLoading = true;
      this.$http({
        url: this.$http.adornUrl("/tms/teacherclass/list/" + this.classId),
        method: "get",
      }).then(({ data }) => {
		console.log("existed teachers"+ data);
        if (data && data.code === 0) {
          this.dataList = data.data;          
        } else {
          this.dataList = [];          
        }
        this.dataListLoading = false;
      });
    },	
  },
};
</script>
