package com.yxz.base.common.utils.echarts;

import java.util.List;

import lombok.Data;

@Data
public class EchartsOptionSeriesDataScatter extends EchartsOptionSeriesData{
	
	private String name;
	
	private List<List<Object>> data;	
	
	
}
