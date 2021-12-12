package com.binh.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import com.binh.service.Service_Upload;
import com.sun.xml.bind.api.impl.NameConverter.Standard;

@Service
public class ServiceImpl_Upload implements Service_Upload{
//	@Autowired private ServletContext app;
//	@Autowired private HttpServletRequest request;

	@Override
	public File save(MultipartFile file, String folder) {
		Path root = Paths.get("src/main/resources/static/assets/"+folder);
		File dir = new File("src/main/resources/static/assets/"+folder);//file jar thì phải khai báo như vầy
		if(!dir.exists()) {
			dir.mkdirs();
		}
		System.out.println("dir: "+dir.getAbsolutePath());
//		String s = System.currentTimeMillis() + file.getOriginalFilename();
//		String filename = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
		String filename = file.getOriginalFilename();

		try {
			Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			File savedFile = new File(dir,filename);
//			System.out.println("file directory: "+savedFile.getAbsolutePath());
			return savedFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
