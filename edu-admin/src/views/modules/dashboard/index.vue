<template>
  <div class="dashboard-editor-container">
    <!-- <github-corner class="github-corner" /> -->

    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <el-row style="background: #fff; padding: 16px 16px 0; margin-bottom: 32px">
      <line-chart :chart-data="lineChartData" />
    </el-row>

    <el-row :gutter="32">
      <!-- <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <raddar-chart />
        </div>
      </el-col> -->
      <el-col :xs="24" :sm="24" :lg="12">
        <div class="chart-wrapper">
          <pie-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="12">
        <div class="chart-wrapper">
          <bar-chart />
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="8">
      <el-col
        :xs="{ span: 24 }"
        :sm="{ span: 24 }"
        :md="{ span: 24 }"
        :lg="{ span: 12 }"
        :xl="{ span: 12 }"
        style="padding-right: 8px; margin-bottom: 30px"
      >
        <transaction-table />
      </el-col>
      <!-- <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px;">
        <todo-list />
      </el-col> -->
      <el-col
        :xs="{ span: 24 }"
        :sm="{ span: 12 }"
        :md="{ span: 12 }"
        :lg="{ span: 6 }"
        :xl="{ span: 6 }"
        style="margin-bottom: 30px"
      >
        <box-card />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import PanelGroup from './components/PanelGroup'
import LineChart from './components/LineChart'
import RaddarChart from './components/RaddarChart'
import PieChart from './components/PieChart'
import BarChart from './components/BarChart'
import TransactionTable from './components/TransactionTable'
import TodoList from './components/TodoList'
import BoxCard from './components/BoxCard'

let lineChartData = {
  
  campuses: {
	xAxisData:["1","2","3","4"],
	legendData: ['私校奖学金计划1', 'VCE备考冲刺','AEAS私校入学考试'],
    seriesData1: [130, 140, 150, 160],
    seriesData2: [120, 82, 91, 130],
	seriesData3: [100, 120, 161, 165]
  },
  teachers: {
	xAxisData:["1","2","3","4"],
	legendData: ['私校奖学金计划2', 'VCE备考冲刺','AEAS私校入学考试'],
    seriesData1: [80, 100, 121, 100],
    seriesData2: [120, 90, 100, 138],
	seriesData3: [100, 120, 161, 1345]
  },
  students:
   {
	xAxisData:["1","2","3","4"],
	legendData: ['私校奖学金计划', 'VCE备考冲刺','AEAS私校入学考试'],
    seriesData1: [100, 120, 161, 134],
    seriesData2: [120, 82, 91, 145],
	seriesData3: [80, 100, 121,  100]
  },
  classes: {
	xAxisData:["1","2","3","8"],
	legendData: ['私校奖学金计划', 'VCE备考冲刺','AEAS私校入学考试'],
    seriesData1: [200, 192,  130, 140],
    seriesData2: [180, 160, 150, 130],
	seriesData3: [100, 120, 160, 165]
  }
}

export default {
  name: 'DashboardAdmin',
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart,
    TransactionTable,
    TodoList,
    BoxCard
  },
  data() {
    return {
      lineChartData: lineChartData.students,	 
    }
  },
  mounted() {
    this.$nextTick(() => {
	  this.fetchCampusChartLineData();
      this.fetchStudentChartLineData();	  
	  this.fetchClassChartLineData();
    });
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    },

	
	//get data from server
	fetchCampusChartLineData(){	
	this.$http({
		url: this.$http.adornUrl("/sms/statistics/student/line/campus"),
		method: "get",
		}).then(({ data }) => {
			console.log("成功获取ChartlineStudent数据...", data.data.series[0].data);
			// this.lineChartData = data.data;	
			lineChartData.campuses.xAxisData = data.data.xAxis;
			lineChartData.campuses.legendData = data.data.legend;
			lineChartData.campuses.seriesData1 = data.data.series[0].data;	
			lineChartData.campuses.seriesData2 =data.data.series[1].data;	
			lineChartData.campuses.seriesData3 =data.data.series[2].data;		
	});
	},
	fetchStudentChartLineData(){	
	this.$http({
		url: this.$http.adornUrl("/sms/statistics/student/line/classtype"),
		method: "get",
		 params: this.$http.adornParams({
            'type': 'quarter',
            'started': 2021,
            'ended': 2020
          })
		}).then(({ data }) => {
			console.log("成功获取ChartlineStudent数据...", data.data.series[0].data);
			// this.lineChartData = data.data;	
			lineChartData.students.xAxisData = data.data.xAxis;
			lineChartData.students.legendData = data.data.legend;
			lineChartData.students.seriesData1 = data.data.series[0].data;	
			lineChartData.students.seriesData2 =data.data.series[1].data;	
			lineChartData.students.seriesData3 =data.data.series[2].data;		
	});
	},
	//get data from server
	fetchClassChartLineData(){	
	this.$http({
		url: this.$http.adornUrl("/ims/statistics/class/line"),
		method: "get",
		}).then(({ data }) => {
			console.log("成功获取ChartlineClasses数据...", data.data);
			// this.lineChartData = data.data;	
			lineChartData.classes.xAxisData = data.data.xAxis;
			lineChartData.classes.legendData = data.data.legend;
			lineChartData.classes.seriesData1 = data.data.series[0].data;	
			lineChartData.classes.seriesData2 =data.data.series[1].data;	
			lineChartData.classes.seriesData3 =data.data.series[2].data;			
	});
	},
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
