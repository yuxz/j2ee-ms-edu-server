<template>
  <div :class="className" :style="{ height: height, width: width }" />
</template>

<script>
import echarts from "echarts";
require("echarts/theme/macarons"); // echarts theme
import resize from "./mixins/resize";

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: "chart",
    },
    width: {
      type: String,
      default: "100%",
    },
    height: {
      type: String,
      default: "300px",
    },
  },
  data() {
    return {
      chart: null,
      //chartPie
      chartPie: null,
      //chartPieLegendData:[],
      chartPieData: {},
    };
  },
  mounted() {
    this.$nextTick(() => {
      this.initChartPie();
    });
  },
  beforeDestroy() {
    if (!this.chart) {
      return;
    }
    this.chart.dispose();
    this.chart = null;
  },
  methods: {
    initChartPie() {
      this.getChartPieData();
    },
    getChartPieData() {
      //get data from server
      this.$http({
        url: this.$http.adornUrl("/ims/statistics/class/pie"),
        method: "get",
      }).then(({ data }) => {
        console.log("成功获取Pie数据...", data.data);
        this.chartPieData = data.data;
        this.setChartPie();
      });
	  
    },
    setChartPie() {
      this.chart = echarts.init(this.$el, "macarons");

      this.chart.setOption({
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)",
        },
		title: {
            text: this.chartPieData.title,
            left: 10,
            top: 20,
            textStyle: {
              color: '#ccc'
            }
          },
        legend: {
          left: "center",
          bottom: "10",
          data: this.chartPieData.legend,//["Industries", "Technology", "Forex", "Gold", "Forecasts"],
        },
        series: [
          {
            name: this.chartPieData.series[0].name,//"WEEKLY WRITE ARTICLES",
            type: "pie",
            roseType: "radius",
            radius: [15, 95],
            center: ["50%", "38%"],
            data: this.chartPieData.series[0].data,
			// [
            //   { value: 320, name: "Industries" },
            //   { value: 240, name: "Technology" },
            //   { value: 149, name: "Forex" },
            //   { value: 100, name: "Gold" },
            //   { value: 59, name: "Forecasts" },
            // ],
            animationEasing: "cubicInOut",
            animationDuration: 2600,
          },
        ],
      });
    },
  },
};
</script>
