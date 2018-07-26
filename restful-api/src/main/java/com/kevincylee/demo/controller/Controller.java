package com.kevincylee.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kevincylee.demo.bean.UserVo;
import com.kevincylee.demo.entity.User;
import com.kevincylee.demo.service.UserService;

/**
 * ClassName: Controller
 * 
 * @author Chen Yuan Lee (Kevin)
 * @version 1.0
 * @createTime: 2018/07/15 20:42:52
 */

@RestController
public class Controller {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService service;

	@GetMapping("/restful")
	private User get(HttpServletRequest request, @RequestParam(required = false) Integer id) {
		logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		User user = service.findById(id);
		return user;
	}

	@PostMapping("/restful")
	private User post(HttpServletRequest request, @RequestParam String name, @RequestParam String email) {
		logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		User user = service.addUser(name, email);
		return user;
	}

	@PatchMapping("/restful")
	private User patch(HttpServletRequest request, @RequestBody UserVo userVo) {
		logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		User user = service.editUser(userVo.getId(), userVo.getName(), userVo.getEmail());
		return user;
	}

	@DeleteMapping("/restful")
	private String delete(HttpServletRequest request, @RequestParam Integer id) {
		logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		service.delUser(id);
		return "SUCCESS";
	}

	@PutMapping("/restful")
	private String put(HttpServletRequest request, @RequestParam Integer id) {
		logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		service.copyUser(id);
		return "SUCCESS";
	}

}
