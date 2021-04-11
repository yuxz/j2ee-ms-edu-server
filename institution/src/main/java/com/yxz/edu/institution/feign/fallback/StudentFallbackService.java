package com.yxz.edu.institution.feign.fallback;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.yxz.base.common.exception.BizCodeEnume;
import com.yxz.base.common.utils.R;
import com.yxz.edu.institution.feign.StudentFeignService;

@Service
public class StudentFallbackService implements StudentFeignService {

	@Override
	public R listIncludingByClass(Map<String, Object> params, Long classId) {
		 return R.error(BizCodeEnume.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnume.READ_TIME_OUT_EXCEPTION.getMsg());

	}

	@Override
	public R listNotIncludingByClass(Map<String, Object> params, Long classId) {
		 return R.error(BizCodeEnume.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnume.READ_TIME_OUT_EXCEPTION.getMsg());

	}

}
