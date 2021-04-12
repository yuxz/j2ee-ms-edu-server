package com.yxz.edu.student.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.vo.echarts.EchartsStatisticsVo;
import com.yxz.edu.student.dao.StudentDao;
import com.yxz.edu.student.entity.StudentEntity;
import com.yxz.edu.student.service.StatisticsService;
import com.yxz.edu.student.vo.StatisticsTableVo;

@Service("statisticsService")
public class StatisticsServiceImpl extends ServiceImpl<StudentDao, StudentEntity> implements StatisticsService{

	@Override
	public EchartsStatisticsVo statisticsLine(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EchartsStatisticsVo statisticsBar(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EchartsStatisticsVo statisticsPie(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EchartsStatisticsVo statisticsScatter(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<StatisticsTableVo> statisticsTable(Map<String, Object> params) {
		QueryWrapper<StudentEntity> wrapper = new QueryWrapper<>();
	    wrapper.groupBy("campus_id, class_type_id,class_level_id");
	    wrapper.orderByAsc("campus_id, class_type_id,class_level_id ");		        
	    wrapper.select("campus_id, class_type_id,class_level_id, count(*) as total_count");
	    List<StudentEntity> studentList = baseMapper.selectList(wrapper);
	    
	    List<StatisticsTableVo> collect = studentList.stream().map(mapper->{
	    	StatisticsTableVo statisticsTableVo = new StatisticsTableVo();
	    	BeanUtils.copyProperties(mapper, statisticsTableVo);
	    	//TODO set statisticsTable name
	    	statisticsTableVo.setCampusName("aa");
	    	statisticsTableVo.setClassTypeName("bb");
	    	statisticsTableVo.setClassLevelName("cc");
	    	statisticsTableVo.setTotalCount(mapper.getTotalCount());
	    	return statisticsTableVo;
	    }).collect(Collectors.toList());
		return collect;
	}

}
