package com.yxz.edu.exam.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.exam.entity.PaperItemEntity;
import com.yxz.edu.exam.vo.PaperItemVo;

/**
 * 试题
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-11 22:21:52
 */
public interface PaperItemService extends IService<PaperItemEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PaperItemVo> querytByPaperId(Long paperId);
}

