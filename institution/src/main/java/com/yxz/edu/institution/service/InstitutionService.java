package com.yxz.edu.institution.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.institution.entity.InstitutionEntity;

import java.util.Map;

/**
 * 教育机构
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 09:31:08
 */
public interface InstitutionService extends IService<InstitutionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

