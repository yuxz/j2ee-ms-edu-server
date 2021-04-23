package com.yxz.edu.institution.service.impl;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxz.base.common.utils.echarts.EchartsOption;
import com.yxz.edu.institution.dao.CampusDao;
import com.yxz.edu.institution.dao.ClassDao;
import com.yxz.edu.institution.service.ClassTypeService;
import com.yxz.edu.institution.service.InstitutionStatisticsService;
import com.yxz.edu.institution.vo.StatisticsTableVo;

@Service("statisticsService")
public class InstitutionStatisticsServiceImpl implements InstitutionStatisticsService {

	@Autowired
	private CampusDao campusDao;

	@Autowired
	private ClassDao classDao;

	@Autowired
	private ClassTypeService classTypeService;

	/**
	 * 
	 */
	@Override
	public EchartsOption statisticsPie(Map<String, Object> params) {

		return null;
	}

	/**
	 * series:[{ 'name': '邮件营销', 'type': 'line', 'stack': '总量', 'data': [ 120, 132,
	 * 101, 134, 90, 230, 210 ] }, ]
	 */

	@Override
	public EchartsOption statisticsLine(Map<String, Object> params) {

		return null;
	}

	@Override
	public EchartsOption statisticsBar(Map<String, Object> params) {

		return null;
	}

	@Override
	public EchartsOption statisticsScatter(Map<String, Object> params) {

		return null;
	}

	@Override
	public StatisticsTableVo statisticsTable(Map<String, Object> params) {

		return null;
	}

	@Override
	public Integer statisticsCampus(Map<String, Object> params) {

		Integer selectCount = campusDao.selectCount(new QueryWrapper<>());
		return selectCount;
	}

	

}
