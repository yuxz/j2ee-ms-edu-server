<template>
  <el-table :data="list" style="width: 100%;padding-top: 15px;">
    <!-- <el-table-column label="Order_No" min-width="200">
      <template slot-scope="scope">
        {{ scope.row.order_no | orderNoFilter }}
      </template>
    </el-table-column>
    <el-table-column label="Price" width="195" align="center">
      <template slot-scope="scope">
        ¥{{ scope.row.price | toThousandFilter }}
      </template>
    </el-table-column>
    <el-table-column label="Status" width="100" align="center">
      <template slot-scope="{row}">
        <el-tag :type="row.status | statusFilter">
          {{ row.status }}
        </el-tag>
      </template>
    </el-table-column> -->
	 <el-table-column
        prop="campusName"
        header-align="center"
        align="center"
        label="Campus"
      >
      </el-table-column>
	   <el-table-column
        prop="classTypeName"
        header-align="center"
        align="center"
        label="Class Type"
      >
      </el-table-column>
	   <el-table-column
        prop="classLevelName"
        header-align="center"
        align="center"
        label="Class Level"
      >
      </el-table-column>
	   <el-table-column
        prop="totalCount"
        header-align="center"
        align="center"
        label="total"
      >
      </el-table-column>
   
  </el-table>
</template>

<script>
// import { transactionList } from '@/api/remote-search'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        success: 'success',
        pending: 'danger'
      }
      return statusMap[status]
    },
    orderNoFilter(str) {
      return str.substring(0, 30)
    }
  },
  data() {
    return {
      list: null
    }
  },
  created() {
    this.fetchTableData()
  },
  methods: {
	fetchTableData(){
		//get data from server
		this.$http({
			url: this.$http.adornUrl("/sms/statistics/table"),
			method: "get",
			}).then(({ data }) => {
				console.log("成功获取Bar数据...", data.data);
				this.list = data.data;				
		});
	  },    
  }
}
</script>
