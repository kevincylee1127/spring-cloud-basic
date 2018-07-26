package com.kevincylee.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName: CustomPreFilter
 * 
 * @author Chen Yuan Lee (Kevin)
 * @version 1.0
 * @createTime: 2018/07/16 21:54:42
 */

@SpringBootApplication
@EnableDiscoveryClient
public class RestFulApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestFulApiApplication.class, args);
	}
}
