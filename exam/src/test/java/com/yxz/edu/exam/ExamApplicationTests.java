package com.yxz.edu.exam;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yxz.edu.exam.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ExamApplicationTests {
	
	@Autowired
	CategoryService categoryService;

	@Test
	void contextLoads() {
	}
	
	
	
}
