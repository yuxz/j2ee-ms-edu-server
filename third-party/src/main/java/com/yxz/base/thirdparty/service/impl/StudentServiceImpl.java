package com.yxz.base.thirdparty.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.excel.EasyExcel;
import com.yxz.base.thirdparty.component.EasyExcelListenerComponent;
import com.yxz.base.thirdparty.component.EasyExcelUtilComponent;
import com.yxz.base.thirdparty.entity.Student;
import com.yxz.base.thirdparty.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Override
	public List<Student> importInfo(InputStream fis) {

		EasyExcelListenerComponent<Student> studentListener = new EasyExcelListenerComponent<>();
		EasyExcel.read(fis, Student.class, studentListener).sheet().doRead();

		return studentListener.getList();
	}



	@Override
	public void exportInfo(HttpServletResponse response, String string, String string2) {

		// 1. get export data
		List<Student> list = new ArrayList<Student>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			list.add(new Student("1001", "张三", 23, "男", "陕西西安", dateFormat.parse("2020-09-01")));
			list.add(new Student("1002", "李四", 22, "女", "陕西渭南", dateFormat.parse("2020-09-01")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //2.export data
		EasyExcelUtilComponent<Student> easyExcelComponent = new EasyExcelUtilComponent<>(Student.class);
		try {
			easyExcelComponent.exportFile(response, list, "学生花名册filename", "学生花名册sheet");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
