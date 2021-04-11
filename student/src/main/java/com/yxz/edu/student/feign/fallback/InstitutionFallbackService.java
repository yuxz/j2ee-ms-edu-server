package com.yxz.edu.student.feign.fallback;

import com.yxz.base.common.exception.BizCodeEnume;
import com.yxz.base.common.utils.R;
import com.yxz.edu.student.feign.InstitutionFeignService;

public class InstitutionFallbackService implements InstitutionFeignService {

	@Override
	public R listAllClass() {
		 return R.error(BizCodeEnume.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnume.READ_TIME_OUT_EXCEPTION.getMsg());
    }

	
}
