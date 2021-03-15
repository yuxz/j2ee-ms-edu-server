package com.yxz.edu.exam.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.exam.entity.CategoryEntity;
import com.yxz.edu.exam.vo.CategoryChildrenVo;

/**
 * 试卷类型
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-08 11:08:06
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

	List<CategoryChildrenVo> listByTree();

	void removeMenuByIds(List<Long> asList);

	Long[] getFullPath(Long catagoryId);
}

