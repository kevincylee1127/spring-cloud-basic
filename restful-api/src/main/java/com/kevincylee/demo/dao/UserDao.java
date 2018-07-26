package com.kevincylee.demo.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevincylee.demo.entity.User;

/**
 * ClassName: UserDao
 * 
 * @author Chen Yuan Lee (Kevin)
 * @version 1.0
 * @createTime: 2018/07/15 20:42:52
 */

public interface UserDao extends JpaRepository<User, Serializable> {

	User findByName(String name);

}
