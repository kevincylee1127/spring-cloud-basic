package com.kevincylee.demo.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevincylee.demo.dao.UserDao;
import com.kevincylee.demo.entity.User;
import com.kevincylee.demo.service.UserService;

/**
 * ClassName: UserServiceImpl
 * 
 * @author Chen Yuan Lee (Kevin)
 * @version 1.0
 * @createTime: 2018/07/15 20:42:52
 */

@Service
public class UserServiceImpl implements UserService {

	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao dao;

	public List<User> findAll() {
		return dao.findAll();
	}

	public User findByName(String name) {
		logger.info("findByName name={}",name);
		return dao.findByName(name);
	}

	public User findById(Integer id) {
		logger.info("findById id={}",id);
		return dao.findById(id).get();
	}

	public User addUser(String name, String email) {
		logger.info("addUser: name={}, email={}", name, email);
		User user = new User(name, email);
		dao.save(user);
		return user;
	}

	public User editUser(Integer id, String name, String email) {
		logger.info("editUser: id={}", id);
		User user = dao.findById(id).get();
		user.setName(name);
		user.setEmail(email);
		dao.save(user);
		return user;
	}

	@Override
	public void delUser(Integer id) {
		logger.info("delUser: id={}", id);
		User user = dao.findById(id).get();
		dao.delete(user);
	}

	@Override
	public void copyUser(Integer id) {
		logger.info("copyUser: id={}", id);
		User user = dao.findById(id).get();
		User newUser = new User(user.getName(), user.getEmail());
		dao.save(newUser);
	}
}
