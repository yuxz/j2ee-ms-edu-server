package com.yxz.base.common.vo.echarts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EchartsStatisticsVo {
	
	/*
	 * "折线图堆叠",
	 */
	private String title;
	
	/*
	 * [ "邮件营销", "联盟广告", "视频广告", "直接访问", "搜索引擎" ],
	 */
	private List<String> legend;
	
	/*
	 * [ "周一", "周二", "周三", "周四", "周五", "周六", "周日" ],
	 */
	@JsonProperty("xAxis")
	private List<String> xAxis;
	
	/*
	 * [ "周一", "周二", "周三", "周四", "周五", "周六", "周日" ],
	 */
	@JsonProperty("yAxis")
	private List<String> yAxis;
	
	/*
	 * 
	 */
	private List<EchartsSeriesVo> series;
	

}
