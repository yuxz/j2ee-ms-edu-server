package com.yxz.edu.institution.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxz.base.common.utils.R;
import com.yxz.base.common.vo.echarts.EchartsStatisticsVo;
import com.yxz.edu.institution.service.InstitutionStatisticsService;
import com.yxz.edu.institution.vo.StatisticsTableVo;

@RestController
@RequestMapping("ims/statistics")
public class InstitutionStatisticsController {
	
	@Autowired
	private InstitutionStatisticsService statisticsService;
	
	
	
	
	@RequestMapping("/line")
    //@RequiresPermissions("ims:campus:list")
    public R line(@RequestParam Map<String, Object> params){
		
        EchartsStatisticsVo vo = statisticsService.statisticsLine(params);

        return R.ok().put("data", vo);
    }
	
	@RequestMapping("/bar")
    //@RequiresPermissions("ims:campus:list")
    public R barline(@RequestParam Map<String, Object> params){
		
        EchartsStatisticsVo vo = statisticsService.statisticsBar(params);

        return R.ok().put("data", vo);
    }
	
	@RequestMapping("/pie")
    //@RequiresPermissions("ims:campus:list")
    public R pie(@RequestParam Map<String, Object> params){
		
        EchartsStatisticsVo vo = statisticsService.statisticsPie(params);

        return R.ok().put("data", vo);
    }
	
	@RequestMapping("/scatter")
    //@RequiresPermissions("ims:campus:list")
    public R scatter(@RequestParam Map<String, Object> params){
		
        EchartsStatisticsVo vo = statisticsService.statisticsScatter(params);

        return R.ok().put("data", vo);
    }
	
	
	@RequestMapping("/table")
    //@RequiresPermissions("ims:campus:list")
    public R table(@RequestParam Map<String, Object> params){
		
		StatisticsTableVo vo = statisticsService.statisticsTable(params);

        return R.ok().put("data", vo);
    }
	
	@RequestMapping("/line/class")
    //@RequiresPermissions("ims:campus:list")
    public R lineClass(@RequestParam Map<String, Object> params){
		
        EchartsStatisticsVo vo = statisticsService.statisticsLineClass(params);

        return R.ok().put("data", vo);
    }
	
	
	@RequestMapping("/campus")
    //@RequiresPermissions("ims:campus:list")
    public R campuses(@RequestParam Map<String, Object> params){
		
		Integer campuses = statisticsService.statisticsCampus(params);

        return R.ok().put("data", campuses);
    }
	

   
	@RequestMapping("/class")
    //@RequiresPermissions("ims:campus:list")
    public R classes(@RequestParam Map<String, Object> params){
		
		Integer classes = statisticsService.statisticsClasses(params);

        return R.ok().put("data", classes);
    }	
	
}
