package com.yxz.edu.exam.vo;

import java.util.List;

import com.yxz.edu.exam.entity.PaperItemEntity;

import lombok.Data;

@Data
public class PaperDetailVo extends PaperVo {

	private List<PaperItemVo> paperItems;

}
