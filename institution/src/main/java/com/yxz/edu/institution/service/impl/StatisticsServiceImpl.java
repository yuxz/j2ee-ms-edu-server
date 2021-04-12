package com.yxz.edu.institution.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.vo.echarts.EchartsSeriesDataPieVo;
import com.yxz.base.common.vo.echarts.EchartsSeriesVo;
import com.yxz.base.common.vo.echarts.EchartsStatisticsVo;
import com.yxz.edu.institution.dao.ClassDao;
import com.yxz.edu.institution.entity.ClassEntity;
import com.yxz.edu.institution.entity.ClassTypeEntity;
import com.yxz.edu.institution.service.ClassTypeService;
import com.yxz.edu.institution.service.StatisticsService;
import com.yxz.edu.institution.vo.ClassStatisticsVo;
import com.yxz.edu.institution.vo.StatisticsTableVo;

@Service("statisticsService")
public class StatisticsServiceImpl  extends ServiceImpl<ClassDao, ClassEntity> implements StatisticsService{

	@Autowired
	private ClassTypeService classTypeService;
	
	/**
	 * 
	 */
	@Override
	public EchartsStatisticsVo statisticsPie(Map<String, Object> params) {
		
		//1. get count per class Type
		QueryWrapper<ClassEntity> wrapper = new QueryWrapper<>();
        wrapper.groupBy("class_type_id");
        wrapper.select("class_type_id, count(*) as total_count");
        List<ClassEntity> classList = baseMapper.selectList(wrapper);
        
        //2. get class type name
        List<ClassTypeEntity> classTypeList = classTypeService.list();
        
        //3. create List<EchartsSeriesDataVo>
        List<EchartsSeriesDataPieVo> echartsSeriesDataList = classList.stream().map(classEntity ->{
        	EchartsSeriesDataPieVo echartsPieVo = new EchartsSeriesDataPieVo();
        	for (ClassTypeEntity classTypeEntity : classTypeList) {
				if(classTypeEntity.getId() == classEntity.getClassTypeId()) {					
					echartsPieVo.setName(classTypeEntity.getName());
					echartsPieVo.setValue(classEntity.getTotalCount());
					break;
				}				
			}
        	return echartsPieVo;
        }).collect(Collectors.toList());
        
       
        //4. create EchartsSeriesVo
        EchartsSeriesVo echartsSeriesVo = new EchartsSeriesVo<EchartsSeriesDataPieVo>();
        echartsSeriesVo.setName("");
        echartsSeriesVo.setType("");
        echartsSeriesVo.setStack("");
        echartsSeriesVo.setData(echartsSeriesDataList);
        
        //5. create List<EchartsSeriesVo>
        List<EchartsSeriesVo> echartsSerieslist = new ArrayList<>();
        echartsSerieslist.add(echartsSeriesVo);    
        
        //6. create EchartsStatisticsVo
        EchartsStatisticsVo echartsStatisticsVo = new EchartsStatisticsVo();
        echartsStatisticsVo.setTitle("培训类型"); 
        echartsStatisticsVo.setLegend(null);
        echartsStatisticsVo.setXAxis(null);
        echartsStatisticsVo.setYAxis(null);
        echartsStatisticsVo.setSeries(echartsSerieslist);
        
        return echartsStatisticsVo;
		
	}
/**
 *         series:[{
              'name': '邮件营销',
              'type': 'line',
              'stack': '总量',
              'data': [ 120, 132, 101, 134, 90, 230, 210 ]
            },
            ]
 */

	@Override
	public EchartsStatisticsVo statisticsLine(Map<String, Object> params) {
		//1. get count per class Type
				QueryWrapper<ClassEntity> wrapper = new QueryWrapper<>();
		        wrapper.groupBy("class_type_id, YEAR(started)");
		        wrapper.orderByAsc("class_type_id, YEAR(started) ");		        
		        wrapper.select("class_type_id, YEAR(started) as start_year, count(*) as total_count");
		        List<ClassEntity> classList = baseMapper.selectList(wrapper);
		        
		        //2. get class type name
		        List<ClassTypeEntity> classTypeList = classTypeService.list();
		        List<ClassStatisticsVo> classStatisticsVoList = classList.stream().map(classEntity ->{		        	
		        	ClassStatisticsVo classStatisticsVo = new ClassStatisticsVo();
		        	BeanUtils.copyProperties(classEntity, classStatisticsVo);		        	
		        	for (ClassTypeEntity classTypeEntity : classTypeList) {
						if(classTypeEntity.getId() == classEntity.getClassTypeId()) {					
							classStatisticsVo.setClassTypeName(classTypeEntity.getName());
							classStatisticsVo.setStartYear(classEntity.getStartYear());
							classStatisticsVo.setTotalCount(classEntity.getTotalCount());
							break;
						}				
					}
		        	return classStatisticsVo;
		        }).collect(Collectors.toList());
		        
		        
		        //3. create EchartsStatisticsVo		       
		        EchartsStatisticsVo  echartsStatisticsVo = new EchartsStatisticsVo();		       
		       
		        //3.1 set title
		        echartsStatisticsVo.setTitle("各个培训类型年度招生趋势图"); 
		       
		        //3.2-1 set legend [ '私校奖学金计划', 'VCE备考冲刺', 'AEAS私校入学考试'],
		        Set<String> legendSet = new HashSet<>();	   
		        
		        //3.3-1 set xAxis [ '2018', '2019', '2020', '2021', '2022' ]
		        Set<String> xAxisSet = new HashSet<>();	       
		        
		       //set legend and xAxis		   
		        for (ClassStatisticsVo classStatisticsVo : classStatisticsVoList) {
		        	//1. set legend data
		        	legendSet.add(classStatisticsVo.getClassTypeName());
		        	//2. set aAxis data
		        	xAxisSet.add(classStatisticsVo.getStartYear());
				};
				
				//3.4 set series 
		        String tempClassTypeName = null;
		        Boolean newSeries = true;
		        
		        List<EchartsSeriesVo> echartsSeriesList = new ArrayList<>();
		        for (ClassStatisticsVo classStatisticsVo : classStatisticsVoList) { 		        	
		        	// the same class type 
		        	if (tempClassTypeName == null || tempClassTypeName ==  classStatisticsVo.getClassTypeName()) {
		        	}
		        	// not same class type
		        	else {
		        		newSeries = true;
		        	}
		        	
		        	if(newSeries) {		        			
	        			EchartsSeriesVo echartsSeriesVo = new EchartsSeriesVo();	
	        			List<Integer> dataList = new ArrayList<>();	
	        			echartsSeriesVo.setData(dataList);	        			
 		        		echartsSeriesVo.setType("line");
 			        	echartsSeriesVo.setStack("total");	
 			        	echartsSeriesVo.setName(classStatisticsVo.getClassTypeName());
 			        	echartsSeriesList.add(echartsSeriesVo);
 			        	newSeries = false;
	        		}
		        	echartsSeriesList.get(echartsSeriesList.size()-1).getData().add(classStatisticsVo.getTotalCount());
		        	tempClassTypeName = classStatisticsVo.getClassTypeName();
				};
				
			
		        
		        //3.2-2 set legend [ '私校奖学金计划', 'VCE备考冲刺', 'AEAS私校入学考试'],
		        echartsStatisticsVo.setLegend(new ArrayList<String>(legendSet));	
		        //3.3-2 set xAxis [ '2018', '2019', '2020', '2021', '2022' ]
		        List<String> xAxislist = new ArrayList<String>(xAxisSet);
		        Collections.sort(xAxislist);
		        echartsStatisticsVo.setXAxis(xAxislist);		        
      
		        
		        echartsStatisticsVo.setSeries(echartsSeriesList);
		        
		        return echartsStatisticsVo;
				
	}
@Override
public EchartsStatisticsVo statisticsBar(Map<String, Object> params) {
	//1. get count per class Type
	QueryWrapper<ClassEntity> wrapper = new QueryWrapper<>();
    wrapper.groupBy("class_type_id, YEAR(started)");
    wrapper.orderByAsc("class_type_id, YEAR(started) ");		        
    wrapper.select("class_type_id, YEAR(started) as start_year, count(*) as total_count");
    List<ClassEntity> classList = baseMapper.selectList(wrapper);
    
    //2. get class type name
    List<ClassTypeEntity> classTypeList = classTypeService.list();
    List<ClassStatisticsVo> classStatisticsVoList = classList.stream().map(classEntity ->{		        	
    	ClassStatisticsVo classStatisticsVo = new ClassStatisticsVo();
    	BeanUtils.copyProperties(classEntity, classStatisticsVo);		        	
    	for (ClassTypeEntity classTypeEntity : classTypeList) {
			if(classTypeEntity.getId() == classEntity.getClassTypeId()) {					
				classStatisticsVo.setClassTypeName(classTypeEntity.getName());
				classStatisticsVo.setStartYear(classEntity.getStartYear());
				classStatisticsVo.setTotalCount(classEntity.getTotalCount());
				break;
			}				
		}
    	return classStatisticsVo;
    }).collect(Collectors.toList());
    
    
    //3. create EchartsStatisticsVo		       
    EchartsStatisticsVo  echartsStatisticsVo = new EchartsStatisticsVo();		       
   
    //3.1 set title
    echartsStatisticsVo.setTitle("各个培训类型年度招生柱状图"); 
   
    //3.2-1 set legend [ '私校奖学金计划', 'VCE备考冲刺', 'AEAS私校入学考试'],
    Set<String> legendSet = new HashSet<>();	   
    
    //3.3-1 set xAxis [ '2018', '2019', '2020', '2021', '2022' ]
    Set<String> xAxisSet = new HashSet<>();	       
    
   //set legend and xAxis		   
    for (ClassStatisticsVo classStatisticsVo : classStatisticsVoList) {
    	//1. set legend data
    	legendSet.add(classStatisticsVo.getClassTypeName());
    	//2. set aAxis data
    	xAxisSet.add(classStatisticsVo.getStartYear());
	};
	
	//3.4 set series 
    String tempClassTypeName = null;
    Boolean newSeries = true;
    
    List<EchartsSeriesVo> echartsSeriesList = new ArrayList<>();
    for (ClassStatisticsVo classStatisticsVo : classStatisticsVoList) { 		        	
    	// the same class type 
    	if (tempClassTypeName == null || tempClassTypeName ==  classStatisticsVo.getClassTypeName()) {
    	}
    	// not same class type
    	else {
    		newSeries = true;
    	}
    	
    	if(newSeries) {		        			
			EchartsSeriesVo echartsSeriesVo = new EchartsSeriesVo();	
			List<Integer> dataList = new ArrayList<>();	
			echartsSeriesVo.setData(dataList);	        			
     		echartsSeriesVo.setType("bar");
     		
     		//TODO change stack of the echarts bar 
//     		if(classStatisticsVo.getClassTypeName().contains("AEAS")) {
//     			echartsSeriesVo.setStack("AEAS");
//     		}else {     			
//     			echartsSeriesVo.setStack("total"); 
//     		}
     		echartsSeriesVo.setStack("total"); 
			echartsSeriesVo.setName(classStatisticsVo.getClassTypeName());
			echartsSeriesList.add(echartsSeriesVo);
			newSeries = false;
		}
    	echartsSeriesList.get(echartsSeriesList.size()-1).getData().add(classStatisticsVo.getTotalCount());
    	tempClassTypeName = classStatisticsVo.getClassTypeName();
	};
	

    
    //3.2-2 set legend [ '私校奖学金计划', 'VCE备考冲刺', 'AEAS私校入学考试'],
    echartsStatisticsVo.setLegend(new ArrayList<String>(legendSet));	
    //3.3-2 set xAxis [ '2018', '2019', '2020', '2021', '2022' ]
    List<String> xAxislist = new ArrayList<String>(xAxisSet);
    Collections.sort(xAxislist);
    echartsStatisticsVo.setXAxis(xAxislist);		        

    
    echartsStatisticsVo.setSeries(echartsSeriesList);
    
    return echartsStatisticsVo;
	
  }

@Override
public EchartsStatisticsVo statisticsScatter(Map<String, Object> params) {


	
	return null;
}
@Override
public StatisticsTableVo statisticsTable(Map<String, Object> params) {
    
	return null;
}
}
