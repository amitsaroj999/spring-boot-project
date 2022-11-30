package com.jsw.one.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsw.one.dao.UserDao;
import com.jsw.one.dao.UserImageDao;
import com.jsw.one.model.DAOUser;
import com.jsw.one.model.UserImage;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private UserImageDao userImageDao;

	@Autowired
	private UserDao userDao;

	@Override
	public void uploadImage(Long userId, String path, MultipartFile file) {
		String name = Math.random() + file.getOriginalFilename();
		String filePath = path + File.separator + name;
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
		try {
			Files.copy(file.getInputStream(), Paths.get(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		UserImage obj = new UserImage();
		obj.setFilePath(path);
		obj.setFileName(name);
		Optional<DAOUser> optional = userDao.findById(userId);
		obj.setUser(optional.get());
		userImageDao.save(obj);
	}

}
