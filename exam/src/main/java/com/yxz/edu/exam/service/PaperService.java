package com.yxz.edu.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.exam.entity.PaperEntity;

import java.util.Map;

/**
 * 试卷
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-08 11:08:07
 */
public interface PaperService extends IService<PaperEntity> {

    PageUtils queryPage(Map<String, Object> params);

	PageUtils queryPage(Map<String, Object> params, Long categoryId);
}

