package com.jsw.one.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsw.one.dao.UserImageDao;
import com.jsw.one.model.UserImage;
import com.jsw.one.service.FileService;

@RestController
@RequestMapping("/api/social")
public class SocialMediaController {

	@Autowired
	private UserImageDao userImageDao;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping({ "/v1/users/{userId}/files" })
	public ResponseEntity<?> uploadUsersFile(@RequestParam("image") MultipartFile image, @PathVariable Long userId) {
		try {
			fileService.uploadImage(userId, path, image);
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(path = { "v1/users/{userId}/files" })
	public ResponseEntity<List<UserImage>> getUsersUploadedFiles(@PathVariable Long userId) {
		Optional<List<UserImage>> images = Optional.ofNullable(userImageDao.findUserImageById(userId));
		if (images.isPresent() && images.get().size() > 0) {
			return new ResponseEntity<List<UserImage>>(images.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}