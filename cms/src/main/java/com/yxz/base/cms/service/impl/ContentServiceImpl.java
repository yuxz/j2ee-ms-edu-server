package com.yxz.base.cms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;

import com.yxz.base.cms.dao.ContentDao;
import com.yxz.base.cms.entity.CategoryEntity;
import com.yxz.base.cms.entity.ContentEntity;
import com.yxz.base.cms.service.ContentService;


@Service("contentService")
public class ContentServiceImpl extends ServiceImpl<ContentDao, ContentEntity> implements ContentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ContentEntity> page = this.page(
                new Query<ContentEntity>().getPage(params),
                new QueryWrapper<ContentEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public PageUtils queryPage(Map<String, Object> params, Long categoryId) {
		
		 QueryWrapper<ContentEntity> wrapper = new QueryWrapper<ContentEntity>();
		if (categoryId != 0) {
			wrapper.eq("category_id", categoryId);
		}

		String key = (String) params.get("key");
		if (!StringUtils.isEmpty(key)) {
			wrapper.and((obj) -> {
				obj.eq("id", key).or().like("name", key);
			});
		}
		 IPage<ContentEntity> page = this.page(
	                new Query<ContentEntity>().getPage(params),
	                wrapper
	        );

	        return new PageUtils(page);
	    }
	
	@Override
	public List<ContentEntity>  totalCount() {
		
		QueryWrapper<ContentEntity> wrapper = new QueryWrapper<>();
        wrapper.groupBy("category_id");
        wrapper.select("category_id, count(*) as total_count");
 
        List<ContentEntity> contentList = baseMapper.selectList(wrapper);
        for(ContentEntity contentEntity : contentList) {
            System.out.println("categoryid=" + contentEntity.getCategoryId() 
                    + ", totalCount=" + contentEntity.getTotalCount());
        }
        return contentList;
	}


}