package com.yxz.edu.student.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yxz.base.common.utils.R;
import com.yxz.edu.student.feign.fallback.InstitutionFallbackService;

@FeignClient(value = "edu-institution",fallback = InstitutionFallbackService.class)
public interface InstitutionFeignService {
	 
	@RequestMapping("/ims/class/listAll")
	R listAllClass();
	
	@RequestMapping("/ims/campus/info/{id}")
    R campusInfo(@PathVariable("id") Long id);
	
	@RequestMapping("/ims/campustrainingschedule/info/{id}")
    R campusscheduleInfo(@PathVariable("id") Long id);
	
	@RequestMapping("/ims/class/info/{id}")
    R classInfo(@PathVariable("id") Long id);
	@RequestMapping("/ims/class/classids/{status}")
    R classList(@PathVariable("status") String status);
	
	@RequestMapping("/ims/classtype/info/{id}")
    R classtypeInfo(@PathVariable("id") Long id);
	
	@RequestMapping("/ims/classlevel/info/{id}")
    R classlevelInfo(@PathVariable("id") Long id);
	
	
}
