package com.yxz.base.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("cms/uploadfile")
public class UploadFileController {

	private static final String FILE_PATH = "d:/ems/uploads";
	@PostMapping("/newfile")
	public Map<String, Object> newfile(@RequestPart("file") MultipartFile uploadFile, HttpServletRequest request) {
		if(!uploadFile.isEmpty()) {
			System.out.println("上传啦");
			Map<String, Object> result = new HashMap<>();
			System.out.println(uploadFile.getOriginalFilename());
			// 获取上传的原始文件名
			String fileName = uploadFile.getOriginalFilename();
			// 设置文件上传路径
//			String filePath = request.getSession().getServletContext().getRealPath(FILE_PATH);
//			System.out.println(filePath);
			// 获取文件后缀
			String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());

			// 判断并创建上传用的文件夹
			File file = new File(FILE_PATH);
			if (!file.exists()) {
				file.mkdir();
			}
			// 重新设置文件名为 UUID，以确保唯一
			file = new File(FILE_PATH, UUID.randomUUID() + fileSuffix);

			try {
				// 写入文件
				uploadFile.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String path = request.getServletContext().getContextPath() + FILE_PATH + "/";
			// 返回 JSON 数据，这里只带入了文件名
			result.put("err", 0);
			result.put("path", path);
			result.put("filename", file.getName());
			result.put("msg", "sucessed to upload file");
			// summernote 图片返回结构
			// res.status(200).send({ err: 0, filename: '/uploads/' + req.file.filename,
			// msg: "sucessed to upload file" }) ;
			return result;
		}
		return null;		
	}

	@RequestMapping("/removefile")
	public Map<String, Object> removefile(@RequestParam String filename, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();

		// 获取要删除的文件名
		// String fileName = request.getAttribute("filename");
		// 获取件上传路径
		String filePath = request.getSession().getServletContext().getRealPath(FILE_PATH);
		System.out.println(filePath + "\\" + filename);

		// 判断并文件是否存在
		File file = new File(filePath + "\\" + filename);
		if (!file.exists()) {
			result.put("status", "the file is not exist.");
			return result;
		}
		;
		if (file.isFile()) {
			file.delete();
			result.put("status", "successfully removed file.");
		}

		return result;
	}
}
