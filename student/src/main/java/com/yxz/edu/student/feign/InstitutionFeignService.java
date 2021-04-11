package com.yxz.edu.student.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yxz.base.common.utils.R;
import com.yxz.edu.student.feign.fallback.InstitutionFallbackService;

@FeignClient(value = "edu-institution",fallback = InstitutionFallbackService.class)
public interface InstitutionFeignService {
	 
	@RequestMapping("/ims/class/listAll")
	 R listAllClass();
}
