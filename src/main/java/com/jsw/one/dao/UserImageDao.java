package com.jsw.one.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jsw.one.model.UserImage;

public interface UserImageDao extends CrudRepository<UserImage, Long> {

	@Query("from UserImage v where v.user.id = :userId")
	List<UserImage> findUserImageById(@Param("userId") Long userId);


}
