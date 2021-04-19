package com.yxz.base.thirdparty.service;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.yxz.base.thirdparty.entity.Student;

public interface StudentService {

	List<Student> importInfo(InputStream fis);
	void exportInfo(HttpServletResponse response, String string, String string2);

}
