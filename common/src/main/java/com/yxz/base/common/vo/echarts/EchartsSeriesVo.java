package com.yxz.base.common.vo.echarts;

import java.util.List;

import lombok.Data;

@Data
public class EchartsSeriesVo<T> {

	private String name;
	
	private String type;
	
	private String stack;
	
	private List<T> data;
}
