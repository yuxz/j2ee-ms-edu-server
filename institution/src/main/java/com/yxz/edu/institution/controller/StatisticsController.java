package com.yxz.edu.institution.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxz.base.common.utils.R;
import com.yxz.base.common.vo.echarts.EchartsStatisticsVo;
import com.yxz.edu.institution.service.InstitutionStatisticsService;

@RestController
@RequestMapping("ims/statistics")
public class StatisticsController {
	
	@Autowired
	private InstitutionStatisticsService institutionStatisticsService;
	
	
	
	
	@RequestMapping("/line")
    //@RequiresPermissions("ims:campus:list")
    public R line(@RequestParam Map<String, Object> params){
		
        EchartsStatisticsVo vo = institutionStatisticsService.statisticsLine(params);

        return R.ok().put("data", vo);
    }
	
	@RequestMapping("/bar")
    //@RequiresPermissions("ims:campus:list")
    public R barline(@RequestParam Map<String, Object> params){
		
        EchartsStatisticsVo vo = institutionStatisticsService.statisticsBar(params);

        return R.ok().put("data", vo);
    }
	
	@RequestMapping("/pie")
    //@RequiresPermissions("ims:campus:list")
    public R pie(@RequestParam Map<String, Object> params){
		
        EchartsStatisticsVo vo = institutionStatisticsService.statisticsPie(params);

        return R.ok().put("data", vo);
    }
	
}
