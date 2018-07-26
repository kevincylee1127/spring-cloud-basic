package com.kevincylee.demo.service;

import java.util.List;

import com.kevincylee.demo.entity.User;

/**
 * ClassName: UserService
 * 
 * @author Chen Yuan Lee (Kevin)
 * @version 1.0
 * @createTime: 2018/07/15 20:42:52
 */

public interface UserService {
	public List<User> findAll();

	public User findByName(String name);

	public User findById(Integer id);

	public User addUser(String name, String email);

	public User editUser(Integer id, String name, String email);

	public void delUser(Integer id);

	public void copyUser(Integer id);
}
