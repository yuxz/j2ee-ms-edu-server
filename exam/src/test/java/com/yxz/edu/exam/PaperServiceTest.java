package com.yxz.edu.exam;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yxz.edu.exam.entity.PaperItemEntity;
import com.yxz.edu.exam.service.CategoryService;
import com.yxz.edu.exam.service.PaperItemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PaperServiceTest {

	@Autowired
	PaperItemService paperItemService;
	
	@Test
	public void testgetFullPath() {
		
	}

}
