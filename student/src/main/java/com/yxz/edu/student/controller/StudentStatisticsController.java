package com.yxz.edu.student.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxz.base.common.utils.R;
import com.yxz.base.common.vo.echarts.EchartsStatisticsVo;
import com.yxz.edu.student.service.StudentStatisticsService;
import com.yxz.edu.student.vo.StudentStatisticsTableVo;

@RestController
@RequestMapping("sms/statistics")
public class StudentStatisticsController {
	
	@Autowired
	private StudentStatisticsService statisticsService;	
	
	
	
	@RequestMapping("/line")
    //@RequiresPermissions("ims:campus:list")
    public R line(@RequestParam Map<String, Object> params){
		
        EchartsStatisticsVo vo = statisticsService.statisticsLine(params);

        return R.ok().put("data", vo);
    }
	@RequestMapping("/line/student")
    //@RequiresPermissions("ims:campus:list")
    public R lineStudent(@RequestParam Map<String, Object> params){
		
		EchartsStatisticsVo vo = statisticsService.statisticsLineStudent(params);

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
		
        List<StudentStatisticsTableVo> vo = statisticsService.statisticsTable(params);

        return R.ok().put("data", vo);
    }
	@RequestMapping("/student")
    //@RequiresPermissions("ims:campus:list")
    public R students(@RequestParam Map<String, Object> params){
		
        Integer studentCount = statisticsService.statisticsStudentCount(params);

        return R.ok().put("data", studentCount);
    }
}
