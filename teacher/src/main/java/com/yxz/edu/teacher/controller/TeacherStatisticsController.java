package com.yxz.edu.teacher.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxz.base.common.utils.R;
import com.yxz.base.common.utils.echarts.EchartsOption;
import com.yxz.edu.teacher.service.TeacherStatisticsService;
import com.yxz.edu.teacher.vo.TeacherStatisticsTableVo;

/**
 * 教师
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-04-15 16:32:08
 */
@RestController
@RequestMapping("tms/statistics")
public class TeacherStatisticsController {
	@Autowired
	private TeacherStatisticsService statisticsService;	
	
		
	@RequestMapping("/teacher/line")
    //@RequiresPermissions("ims:campus:list")
    public R line(@RequestParam Map<String, Object> params){
		
        EchartsOption vo = statisticsService.statisticsLine(params);

        return R.ok().put("data", vo);
    }
	
	@RequestMapping("/teacher/bar")
    //@RequiresPermissions("ims:campus:list")
    public R barline(@RequestParam Map<String, Object> params){
		
        EchartsOption vo = statisticsService.statisticsBar(params);

        return R.ok().put("data", vo);
    }
	
	@RequestMapping("/teacher/pie")
    //@RequiresPermissions("ims:campus:list")
    public R pie(@RequestParam Map<String, Object> params){
		
        EchartsOption vo = statisticsService.statisticsPie(params);

        return R.ok().put("data", vo);
    }
	
	@RequestMapping("/teacher/scatter")
    //@RequiresPermissions("ims:campus:list")
    public R scatter(@RequestParam Map<String, Object> params){
		
        EchartsOption vo = statisticsService.statisticsScatter(params);

        return R.ok().put("data", vo);
    }
	
	@RequestMapping("/teacher/table")
    //@RequiresPermissions("ims:campus:list")
    public R table(@RequestParam Map<String, Object> params){
		
        List<TeacherStatisticsTableVo> vo = statisticsService.statisticsTable(params);

        return R.ok().put("data", vo);
    }
	@RequestMapping("/teacher/count")
    //@RequiresPermissions("ims:campus:list")
    public R teachers(@RequestParam Map<String, Object> params){
		
        Integer teacherCount = statisticsService.statisticsTeacherCount(params);

        return R.ok().put("data", teacherCount);
    }
}
