package com.yxz.edu.institution.service;

import java.util.Map;

import com.yxz.base.common.utils.echarts.EchartsOption;
import com.yxz.edu.institution.vo.StatisticsTableVo;

public interface ClassStatisticsService {

	EchartsOption statisticsLine(Map<String, Object> params);
	
	EchartsOption statisticsBar(Map<String, Object> params);
	
	EchartsOption statisticsPie(Map<String, Object> params);
	
	EchartsOption statisticsScatter(Map<String, Object> params);

	StatisticsTableVo statisticsTable(Map<String, Object> params);	

	Integer statisticsClassCount(Map<String, Object> params);

	EchartsOption statisticsLineClassByClasstype(Map<String, Object> params);
}
