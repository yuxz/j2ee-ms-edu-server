package com.yxz.base.thirdparty.service;

import java.io.InputStream;
import java.util.List;

import com.yxz.base.thirdparty.entity.Student;

public interface StudentService {

	List<Student> importInfo(InputStream fis);

	List<Student> list();

}
