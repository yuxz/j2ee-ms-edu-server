package com.yxz.edu.student.service;

import java.util.List;
import java.util.Map;

import com.yxz.base.common.vo.echarts.EchartsStatisticsVo;
import com.yxz.edu.student.vo.StudentStatisticsTableVo;

public interface StudentStatisticsService {
	EchartsStatisticsVo statisticsLine(Map<String, Object> params);
	
	EchartsStatisticsVo statisticsBar(Map<String, Object> params);
	
	EchartsStatisticsVo statisticsPie(Map<String, Object> params);
	
	EchartsStatisticsVo statisticsScatter(Map<String, Object> params);
	
	List<StudentStatisticsTableVo>  statisticsTable(Map<String, Object> params);

	Integer statisticsStudentCount(Map<String, Object> params);

	EchartsStatisticsVo statisticsLineStudent(Map<String, Object> params);
}
