package com.yxz.edu.institution.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yxz.base.common.utils.R;
import com.yxz.edu.institution.feign.fallback.TeacherFallbackService;

@FeignClient(value = "edu-teacher",fallback = TeacherFallbackService.class)
public interface TeacherFeignService {

	 @RequestMapping("/tms/teacherclass/list/{classId}/includingByClass")	   
	 public R listIncludingByClass(@RequestParam Map<String, Object> params, @PathVariable("classId") Long classId);
		    
	    	
}
