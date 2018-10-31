package com.SpringProject.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
    List<UserInfo> findByUsername(String username);
    List<UserInfo> findAll();
}
