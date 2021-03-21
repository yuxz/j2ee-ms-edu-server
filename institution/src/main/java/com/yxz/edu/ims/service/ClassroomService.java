package com.yxz.edu.ims.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.ims.entity.ClassroomEntity;

import java.util.Map;

/**
 * 教室
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 09:31:08
 */
public interface ClassroomService extends IService<ClassroomEntity> {

    PageUtils queryPage(Map<String, Object> params);

	PageUtils queryPageClassRoom(Map<String, Object> params);
}

