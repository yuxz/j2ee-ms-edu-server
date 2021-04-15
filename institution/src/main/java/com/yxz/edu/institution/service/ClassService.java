package com.yxz.edu.institution.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.to.ClassTo;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.institution.entity.ClassEntity;
import com.yxz.edu.institution.vo.ClassDetailVo;

/**
 * 
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 10:13:23
 */
public interface ClassService extends IService<ClassEntity> {

    PageUtils queryPage(Map<String, Object> params, Integer status);

	ClassDetailVo getDetailById(Long id);

	List<ClassTo> queryAllClasses();

	String classListByStatus(String status);

	
}

