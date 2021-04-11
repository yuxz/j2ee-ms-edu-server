package com.yxz.edu.institution.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxz.base.common.utils.R;
import com.yxz.base.common.vo.echarts.EchartsStatisticsVo;
import com.yxz.edu.institution.service.StatisticsService;

@RestController
@RequestMapping("ims/statistics")
public class StatisticsController {
	
	@Autowired
	private StatisticsService statisticsService;
	
	
	
	
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
}
