package com.yxz.edu.teacher.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxz.base.common.vo.echarts.EchartsStatisticsVo;
import com.yxz.edu.teacher.dao.TeacherDao;
import com.yxz.edu.teacher.entity.TeacherEntity;
import com.yxz.edu.teacher.service.TeacherStatisticsService;
import com.yxz.edu.teacher.vo.TeacherStatisticsTableVo;
@Service("teacherStatisticsService")
public class TeacherStatisticsServiceImpl implements TeacherStatisticsService {
	@Autowired
	private TeacherDao dao;
	
	@Autowired
	private TeacherStatisticsService statisticsService;
	
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
		
		return null;		
	}
	
	@Override
	public List<TeacherStatisticsTableVo> statisticsTable(Map<String, Object> params) {
		QueryWrapper<TeacherEntity> wrapper = new QueryWrapper<>();
	    wrapper.groupBy("campus_id");
	    wrapper.orderByAsc("campus_id ");		        
	    wrapper.select("campus_id, count(*) as total_count");
	    List<TeacherEntity> list = dao.selectList(wrapper);
	    
	    List<TeacherStatisticsTableVo> collect = list.stream().map(mapper->{
	    	TeacherStatisticsTableVo statisticsTableVo = new TeacherStatisticsTableVo();
	    	BeanUtils.copyProperties(mapper, statisticsTableVo);	    	
	    
//        	//query class Type
//        	Map classType = (Map)institutionFeignService.classtypeInfo(mapper.getClassTypeId()).get("classType");
//        	System.out.println("classType---->>>"+classType);
//        	if(classType != null)
//        		statisticsTableVo.setClassTypeName((String)classType.get("name"));			
			
	    	
	    	statisticsTableVo.setTotalCount(mapper.getTotalCount());
	    	return statisticsTableVo;
	    }).collect(Collectors.toList());
		return collect;		
		
	}

	@Override
	public Integer statisticsTeacherCount(Map<String, Object> params) {
		
		Integer selectCount = dao.selectCount(new QueryWrapper<TeacherEntity>());
		return selectCount;
	}

}
