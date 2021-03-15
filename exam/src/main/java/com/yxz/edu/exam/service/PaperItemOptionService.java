package com.yxz.edu.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.exam.entity.PaperItemOptionEntity;

import java.util.Map;

/**
 * 试题选项
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-12 09:38:44
 */
public interface PaperItemOptionService extends IService<PaperItemOptionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

