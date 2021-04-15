package com.yxz.base.common.utils.echarts;

import lombok.Data;

@Data
public abstract class BaseEchartsStatisticsVo<T>{//<T extends EchartsOptionSeriesData> {

	/**
	 * title
	 * 
	 */
	private String title;
	
	/**
	 * xAxis
	 */
	private String xAxis;
	/**
	 * yAxis
	 */
	private String yAxis;
	
	/**
	 * legend
	 */
	private String legend;
	
	/**
	 * total count
	 */
	private T value;
}
