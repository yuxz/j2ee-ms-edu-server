package com.yxz.base.cms.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.cms.entity.ContentEntity;
import com.yxz.base.common.utils.PageUtils;

/**
 * 文章
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-18 09:12:43
 */
public interface ContentService extends IService<ContentEntity> {

    PageUtils queryPage(Map<String, Object> params);

	PageUtils queryPage(Map<String, Object> params, Long categoryId);
	
	List<ContentEntity> totalCount();
	
}

