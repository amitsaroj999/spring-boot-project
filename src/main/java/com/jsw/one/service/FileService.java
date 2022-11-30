package com.jsw.one.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	void uploadImage(Long userId,String path, MultipartFile file);

}
