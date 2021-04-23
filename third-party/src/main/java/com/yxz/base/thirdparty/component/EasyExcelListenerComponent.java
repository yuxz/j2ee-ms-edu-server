package com.yxz.base.thirdparty.component;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;

/**
 * 学生读取类
 * 
 * @author CL
 *
 */
public class EasyExcelListenerComponent<T> extends AnalysisEventListener<T> {

	@Getter
	private List<T> list = new ArrayList<T>();

	public EasyExcelListenerComponent() {
		super();
		list.clear();
	}

	/**
	 * 每一条数据解析都会调用
	 */
	@Override
	public void invoke(T entity, AnalysisContext context) {
		list.add(entity);
	}

	/**
	 * 所有数据解析完成都会调用
	 */
	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		list.forEach(System.out::println);
	}

}