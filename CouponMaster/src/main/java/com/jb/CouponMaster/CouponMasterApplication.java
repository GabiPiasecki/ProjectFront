package com.jb.CouponMaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponMasterApplication.class, args);
	}

}
