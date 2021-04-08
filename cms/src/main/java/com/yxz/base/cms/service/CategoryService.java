package com.yxz.base.cms.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.cms.entity.CategoryEntity;
import com.yxz.base.cms.vo.CategoryChildrenVo;
import com.yxz.base.common.utils.PageUtils;

/**
 * 内容分类
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-18 09:12:43
 */
public interface CategoryService extends IService<CategoryEntity> {

	 PageUtils queryPage(Map<String, Object> params);

		List<CategoryChildrenVo> listByTree();

		void removeMenuByIds(List<Long> asList);

		Long[] getFullPath(Long catagoryId);
		
		
	
}

