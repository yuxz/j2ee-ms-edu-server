package com.yxz.edu.student.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.student.entity.StudentEntity;
import com.yxz.edu.student.vo.SchoolsVo;

/**
 * 学生
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 19:17:49
 */
public interface StudentService extends IService<StudentEntity> {

    PageUtils queryPage(Map<String, Object> params);

	List<SchoolsVo> querySchools();

	StudentEntity getByEmail(String email);
}

