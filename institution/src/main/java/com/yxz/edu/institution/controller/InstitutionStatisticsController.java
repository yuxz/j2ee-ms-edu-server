package com.yxz.edu.institution.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxz.base.common.utils.R;
import com.yxz.base.common.utils.echarts.EchartsOption;
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
		
        EchartsOption vo = statisticsService.statisticsLine(params);

        return R.ok().put("data", vo);
    }
	
	@RequestMapping("/bar")
    //@RequiresPermissions("ims:campus:list")
    public R barline(@RequestParam Map<String, Object> params){
		
        EchartsOption vo = statisticsService.statisticsBar(params);

        return R.ok().put("data", vo);
    }
	
	@RequestMapping("/pie")
    //@RequiresPermissions("ims:campus:list")
    public R pie(@RequestParam Map<String, Object> params){
		
        EchartsOption vo = statisticsService.statisticsPie(params);

        return R.ok().put("data", vo);
    }
	
	@RequestMapping("/scatter")
    //@RequiresPermissions("ims:campus:list")
    public R scatter(@RequestParam Map<String, Object> params){
		
        EchartsOption vo = statisticsService.statisticsScatter(params);

        return R.ok().put("data", vo);
    }
	
	
	@RequestMapping("/table")
    //@RequiresPermissions("ims:campus:list")
    public R table(@RequestParam Map<String, Object> params){
		
		StatisticsTableVo vo = statisticsService.statisticsTable(params);

        return R.ok().put("data", vo);
    }
	
	
	
	
	@RequestMapping("/campus/count")
    //@RequiresPermissions("ims:campus:list")
    public R campuses(@RequestParam Map<String, Object> params){
		
		Integer campuses = statisticsService.statisticsCampus(params);

        return R.ok().put("data", campuses);
    }   
	
	
	
}
