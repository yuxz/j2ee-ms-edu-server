package com.yxz.edu.exam.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;
import com.yxz.edu.exam.dao.PaperItemDao;
import com.yxz.edu.exam.dao.PaperItemOptionDao;
import com.yxz.edu.exam.entity.PaperItemEntity;
import com.yxz.edu.exam.entity.PaperItemOptionEntity;
import com.yxz.edu.exam.service.PaperItemOptionService;
import com.yxz.edu.exam.service.PaperItemService;
import com.yxz.edu.exam.vo.PaperItemVo;


@Service("paperItemService")
public class PaperItemServiceImpl extends ServiceImpl<PaperItemDao, PaperItemEntity> implements PaperItemService {
	
	@Autowired
	PaperItemOptionDao paperItemOptionDao;
	
	@Autowired
	PaperItemOptionService paperItemOptionService;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	IPage<PaperItemEntity> page = this.page(
                new Query<PaperItemEntity>().getPage(params),
                new QueryWrapper<PaperItemEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public List<PaperItemVo> querytByPaperId(Long paperId) {		
		
		QueryWrapper<PaperItemEntity> wrapperItem = new QueryWrapper<>();
		
		if(paperId != null) {	
			wrapperItem.and((obj)->{
                obj.eq("paper_id",paperId);
            });
		}
		List<PaperItemEntity> paperItems = this.list(wrapperItem);
		
		List<PaperItemVo> list = paperItems.stream().map((item) ->{
			PaperItemVo paperItemVo = new PaperItemVo();
			BeanUtils.copyProperties(item, paperItemVo);
			
			//查找试题选项			
			QueryWrapper<PaperItemOptionEntity> wrapperOption = new QueryWrapper<>();
			wrapperOption.eq("paper_item_id", item.getId());
			List<PaperItemOptionEntity> paperOptions = paperItemOptionService.list(wrapperOption);
			paperItemVo.setPaperOptions(paperOptions);
			
			return paperItemVo;
			
		}).collect(Collectors.toList());
		
		return list;
	}

}