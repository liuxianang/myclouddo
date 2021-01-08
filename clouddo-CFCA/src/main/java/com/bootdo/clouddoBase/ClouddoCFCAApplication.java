package com.bootdo.clouddoBase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.bootdo.*.dao"})
@SpringBootApplication
public class ClouddoCFCAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClouddoCFCAApplication.class, args);
	}
}
