package com.yxz.edu.institution.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yxz.base.common.utils.R;
import com.yxz.edu.institution.feign.fallback.StudentFallbackService;

@FeignClient(value = "edu-student",fallback = StudentFallbackService.class)
public interface StudentFeignService {

	 @RequestMapping("/sms/studentclass/list/{classId}")	   
	 R listIncludingByClass(@RequestParam Map<String, Object> params,@PathVariable("classId") Long classId);	    
	    
	 @RequestMapping("/list/{classId}/includingByClass")
	    //@RequiresPermissions("tms:teacherclass:list")
	 R listNotIncludingByClass(@RequestParam Map<String, Object> params, @PathVariable("classId") Long classId);
	    
}
