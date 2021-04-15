package com.yxz.base.common.utils.echarts;

import java.util.List;

import lombok.Data;

@Data
public class EchartsOptionSeries<T>  {

	/**
	 * name
	 */
	private String name;
	
	/**
	 * data
	 */
	private List<T> data;
}
