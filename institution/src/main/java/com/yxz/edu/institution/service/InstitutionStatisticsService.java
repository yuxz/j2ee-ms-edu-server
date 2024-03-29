package com.yxz.edu.institution.service;

import java.util.Map;

import com.yxz.base.common.utils.echarts.EchartsOption;
import com.yxz.edu.institution.vo.StatisticsTableVo;

public interface InstitutionStatisticsService {

	EchartsOption statisticsLine(Map<String, Object> params);
	
	EchartsOption statisticsBar(Map<String, Object> params);
	
	EchartsOption statisticsPie(Map<String, Object> params);
	
	EchartsOption statisticsScatter(Map<String, Object> params);

	StatisticsTableVo statisticsTable(Map<String, Object> params);

	Integer statisticsCampus(Map<String, Object> params);	

}
