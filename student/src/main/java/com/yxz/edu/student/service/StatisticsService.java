package com.yxz.edu.student.service;

import java.util.List;
import java.util.Map;

import com.yxz.base.common.vo.echarts.EchartsStatisticsVo;
import com.yxz.edu.student.vo.StatisticsTableVo;

public interface StatisticsService {
	EchartsStatisticsVo statisticsLine(Map<String, Object> params);
	
	EchartsStatisticsVo statisticsBar(Map<String, Object> params);
	
	EchartsStatisticsVo statisticsPie(Map<String, Object> params);
	
	EchartsStatisticsVo statisticsScatter(Map<String, Object> params);
	
	List<StatisticsTableVo>  statisticsTable(Map<String, Object> params);
}
