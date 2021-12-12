package com.binh.service;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface Service_Upload {
	File save(MultipartFile file, String folder);
}
